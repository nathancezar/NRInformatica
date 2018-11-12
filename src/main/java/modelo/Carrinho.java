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
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private float valorDoDescontoRecebido;
    private final Cronometro cronometro;
    private Cliente cliente;

    public Carrinho() {
        this.listaDeProdutos = new ArrayList<>();
        this.listaDeServicos = new ArrayList<>();
        this.clienteDoCarrinho = new Cliente();
        this.valorTotal = 0;
        this.valorDoDescontoRecebido = 0;
        this.cronometro = new Cronometro(this);
    }

    public ArrayList<Produto> getListaDeProdutos() {
        return listaDeProdutos;
    }

    public ArrayList<Servico> getListaDeServicos() {
        return listaDeServicos;
    }

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

    public float getValorDoDescontoRecebido() {
        return valorDoDescontoRecebido;
    }

    public void setValorDoDescontoRecebido(float valorDoDescontoRecebido) {
        this.valorDoDescontoRecebido = valorDoDescontoRecebido;
    }

    public Cronometro getCronometro() {
        return cronometro;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    // inicia uma contagem para cancelar o carrinho
    // ainda não achei uma forma de cancelar apos adicionar outro produto
    private void iniciarContagem() {
        if (!cronometro.isCronometroRodando()) {
            cronometro.run();
        }
    }

    // adiciona ao carrinho a quantidade desejada de um produto
    // retira essa quantidade do estoque
    // adiciona o valor do produto ao carrinho, dependendo da quantidade
    // verifica se há alguma promoção a ser aplicada
    // inicia um timer novo a cada inserção
    public synchronized boolean adicionarProduto(Produto produto, int quantidade) {
        if (verificaSeJaContemProduto(produto.getCodigo())) {
            adicionaProdutoExistente(produto, quantidade);
            promocao.verificaPromocoesAplicaveis(this);
            //iniciarContagem();
            return true;
        } else if (verificaDisponibilidade(produto, quantidade)) {
            adicionarProdutoNovo(produto, quantidade);
            promocao.verificaPromocoesAplicaveis(this);
            //iniciarContagem();
            return true;
        }
        return false;
    }

    private void adicionaProdutoExistente(Produto produto, int quantidade) {
        Produto produtoAux = retornaProdutoPorCodigo(produto.getCodigo());
        if (produtoAux != null) {
            produtoAux.setQuantidade(quantidade + produtoAux.getQuantidade());
            this.valorTotal += produto.getPreco() * quantidade;
            removeQuantidadeDoEstoque(produto, quantidade);
        }
    }

    // adiciona um novo produto ao carrinho, clonando o mesmo do banco
    // de dados, e altera o valor do carrinho
    // remove do estoque a quantidade selecionada
    private void adicionarProdutoNovo(Produto produto, int quantidade) {
        Produto produtoNovo = clonarProduto(produto);
        produtoNovo.setQuantidade(quantidade);
        this.getListaDeProdutos().add(produtoNovo);
        this.valorTotal += produtoNovo.getPreco() * quantidade;
        removeQuantidadeDoEstoque(produto, quantidade);
    }

    // retorna um produto do carrinho pelo codigo, ou retorna null
    public Produto retornaProdutoPorCodigo(int codigo) {
        for (Produto p1 : listaDeProdutos) {
            if (p1.getCodigo() == codigo) {
                return p1;
            }
        }
        return null;
    }

    public synchronized boolean adicionarServicoNoCarrinho(Servico servico, String data) {
        ArrayList<String> datasDoServico = new ArrayList<>();
        datasDoServico.add(data);

        Servico servicoNoCarrinho = new Servico(servico.getCodigo(),
                servico.getNome(), datasDoServico, servico.getPreco());

        removeDataDoServicoEmEstoque(servico, data);
        this.listaDeServicos.add(servicoNoCarrinho);
        this.setValorTotal(getValorTotal() + servico.getPreco());
        iniciarContagem();
        return true;
    }

    private void removeDataDoServicoEmEstoque(Servico servico, String data) {
        int indiceServico = bd.getServicos().indexOf(servico);
        bd.getServicos().get(indiceServico).getDatas().remove(data);
    }

    public void removeServicoDoCarrinho(int cod) {
        Servico servico = retornaServicoPorCodigo(cod);

        if (servico != null) {
            listaDeServicos.remove(servico);
            Servico servicoBD = busca.buscaServicoPorCodigo(cod);
            int indiceServico = bd.getServicos().indexOf(servicoBD);
            bd.getServicos().get(indiceServico).getDatas().add(servico.getDatas().get(0));
            this.valorTotal -= servico.getPreco();
        }
    }

    public Servico retornaServicoPorCodigo(int codigo) {
        for (Servico s1 : this.listaDeServicos) {
            if (s1 != null && s1.getCodigo() == codigo) {
                return s1;
            }
        }
        return null;
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

    // remove o produto do carrinho e retorna novamente a quantidade
    //que havia no carrinho para o estoque
    public synchronized void removerProduto(Produto produto, int quantARemover) throws NullPointerException {
        if (this.listaDeProdutos.contains(produto)) {
            this.retornaQuantidadeAoEstoque(produto, quantARemover);
            this.valorTotal -= (produto.getPreco() * quantARemover);
            this.valorTotal = Math.max(valorTotal, 0);
            produto.setQuantidade(produto.getQuantidade() - quantARemover);
            if (produto.getQuantidade() <= 0) {
                this.listaDeProdutos.remove(produto);
            }
            promocao.verificaPromocoesAplicaveis(this);
        }
    }

    // metodo para retornar ao estoque a quantidade de um produto
    private synchronized void retornaQuantidadeAoEstoque(Produto produto, int quantARemover) {
        int quantidadeCorreta = Math.min(quantARemover, produto.getQuantidade());
        Produto produtoOriginal = instanciaDoProdutoOriginal(produto);
        if (produtoOriginal != null) {
            produtoOriginal.setQuantidade(quantidadeCorreta + produtoOriginal.getQuantidade());
        }
    }

    // limpa o carrinho, retornando os produtos ao estoque
    // zera o valor a ser pago pelo carrinho
    public synchronized boolean removerTodosOsProdutos() {
        listaDeProdutos.forEach((produto) -> {
            retornaQuantidadeAoEstoque(produto, produto.getQuantidade());
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
    public boolean verificaSeJaContemProduto(int codigo) {
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

        for (Servico servico : listaDeServicos) {
            mensagem += servico.toString() + "\n";
        }
        mensagem += "Valor Total: " + df.format(valorTotal) + "\n";
        return mensagem;
    }
}
