/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import Gerenciadores.GerenciadorDeBusca;
import java.util.ArrayList;
import Gerenciadores.Promocoes;
import bancoDeDados.BancoDeDados;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author nathancezar
 */
public class Carrinho {

    private ArrayList<Produto> listaDeProdutos;
    private ArrayList<Servico> listaDeServicos;
    private Cliente clienteDoCarrinho;
    private GerenciadorDeBusca busca = GerenciadorDeBusca.getControleBusca();
    private BancoDeDados bd = BancoDeDados.getBancoDados();
    private final Promocoes promocao = new Promocoes();
    private DecimalFormat df = new DecimalFormat("#.00");
    private float valorTotal;
    private Timer timer;
    private TimerTask tarefa;

    public Carrinho() {
        this.listaDeProdutos = new ArrayList<>();
        this.listaDeServicos = new ArrayList<>();
        this.clienteDoCarrinho = new Cliente();
        this.valorTotal = 0;
    }

    public ArrayList<Produto> getListaDeProdutos() {
        return listaDeProdutos;
    }

    public ArrayList<Servico> getListaDeServicos() { return listaDeServicos; }

    public Cliente getClienteDoCarrinho() {
        return clienteDoCarrinho;
    }

    public void setClienteDoCarrinho(Cliente clienteDoCarrinho) {
        this.clienteDoCarrinho = clienteDoCarrinho;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

    // inicia uma contagem para cancelar o carrinho
    // ainda não achei uma forma de cancelar apos adicionar outro produto
    private void iniciarContagem() {
        tarefa = new TimerTask() {
            @Override
            public void run() {
                if (removerTodosOsProdutos()) {
                    System.out.println("Tempo do Carrinho Expirado!");
                }
            }
        }; 
        //tarefa.cancel();
        this.timer.schedule(tarefa, 5000);    //120000 milis./120 segundos/2 minutos
    }

    // adiciona ao carrinho a quantidade desejada de um produto
    // retira essa quantidade do estoque
    // adiciona o valor do produto ao carrinho, dependendo da quantidade
    // verifica se há alguma promoção a ser aplicada
    // inicia um timer novo a cada inserção
    public synchronized boolean adicionarProduto(Produto produto, int quantidade) {
        if (verificaDisponibilidade(produto, quantidade)) {
            Produto produtoNovo = clonarProduto(produto);
            produtoNovo.setQuantidade(quantidade);
            this.getListaDeProdutos().add(produtoNovo);
            this.valorTotal += produtoNovo.getPreco() * quantidade;
            removeQuantidadeDoEstoque(produto, quantidade);
            promocao.leve3pague2(this, produtoNovo);
            iniciarContagem();
            return true;
        }
        return false;
    }

    public synchronized boolean adicionarServicoNoCarrinho(Servico servico, Date data) {
        Servico servicoNoCarrinho = servico;
        ArrayList<Date> datasDoServico = new ArrayList<>();
        datasDoServico.add(data);
        servicoNoCarrinho.setDatas(datasDoServico);
        removeDataDoServicoEmEstoque(servico, data);
        return true;
    }

    private void removeDataDoServicoEmEstoque(Servico servico, Date data) {
        int indice = bd.getServicos().indexOf(servico);
        bd.getServicos().get(indice).getDatas().remove(data);
    }

    // remove a quantidade de produtos que o cliente colocou no carrinho
    private void removeQuantidadeDoEstoque(Produto produto, int quantidade) {
        int index = bd.getProdutos().indexOf(produto);
        bd.getProdutos().get(index).setQuantidade(
                bd.getProdutos().get(index).getQuantidade() - quantidade);
    }

    //cria um Clone do produto para salvar no Carrinho
    private Produto clonarProduto(Produto produto) {
        int index = bd.getProdutos().indexOf(produto);
        Produto produtoNovo = null;
        try {
            produtoNovo = bd.getProdutos().get(index).clone();
            System.out.println(produtoNovo.toString());
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(Carrinho.class.getName()).log(Level.SEVERE, null, ex);
        }
        return produtoNovo;
    }

    private Servico clonarServico (Servico servico) {
        return null;
    }

    // remove o produto do carrinho e retorna novamente a quantidade
    //que havia no carrinho para o estoque
    private synchronized void removerProduto(Produto produto) throws NullPointerException {
        if (this.listaDeProdutos.contains(produto)) {
            this.retornaQuantidadeAoEstoque(produto);
            this.listaDeProdutos.remove(produto);
            this.valorTotal -= produto.getPreco();
        }
    }

    // retorna uma instancia do produto do estoque
    // já que ao clonar o produto e colocar no carrinho
    // é criado um produto diferente, ocasionando erros.
    private Produto instanciaDoProdutoOriginal(Produto produtoClone) {
        for (Produto produtoOriginal : bd.getProdutos()) {
            if (produtoClone.getCodigo() == produtoOriginal.getCodigo()) {
                return produtoOriginal;
            }
        }
        return null;
    }

    // metodo para retornar ao estoque a quantidade de um produto
    private synchronized void retornaQuantidadeAoEstoque(Produto produto) throws NullPointerException {
        Produto produtoAux = instanciaDoProdutoOriginal(produto);
        if (produtoAux != null) {
            int index = bd.getProdutos().indexOf(produtoAux);
            int quantidadeNoEstoque = bd.getProdutos().get(index).getQuantidade();
            bd.getProdutos().get(index).setQuantidade(quantidadeNoEstoque + produto.getQuantidade());        
        }
    }

    // limpa o carrinho, retornando os produtos ao estoque
    // zera o valor a ser pago pelo carrinho
    public synchronized boolean removerTodosOsProdutos() {
        listaDeProdutos.forEach((produto) -> {
            retornaQuantidadeAoEstoque(produto);
        });
        this.listaDeProdutos.clear();
        this.valorTotal = 0;
        return this.listaDeProdutos.isEmpty();
    }

    // verifica se existe a quantidade desejada do produto no estoque
    public boolean verificaDisponibilidade(Produto produto, int quantidade) {
        return (produto.getQuantidade() >= quantidade);
    }

    // verifica se existe, atualmente no carrinho, um produto com
    // o código passado
    public boolean verificaSeCodigoExiste(int codigo) {
        return this.listaDeProdutos.stream().anyMatch((produto)
                -> (codigo == produto.getCodigo()));
    }

    // retorna String com as caracteristicas dos produtos que
    // aualmente estão no carrinho
    public String mostrarProdutos() {
        String mensagem = "\nProdutos atualmente no Carrinho: \n --- \n";
        for (Produto produto : listaDeProdutos) {
            mensagem += produto.toString() + "\n";
        }
        mensagem += "Valor Total: " + df.format(valorTotal) + "\n";
        return mensagem;
    }
}
