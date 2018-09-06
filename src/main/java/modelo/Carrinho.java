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

    ArrayList<Produto> listaDeProdutos;
    Cliente clienteDoCarrinho;
    GerenciadorDeBusca busca = GerenciadorDeBusca.getControleBusca();
    BancoDeDados bd = BancoDeDados.getBancoDados();
    Promocoes promocao = new Promocoes();
    DecimalFormat df = new DecimalFormat("#.00");
    private float valorTotal;

    public Carrinho() {
        this.listaDeProdutos = new ArrayList<>();
        this.clienteDoCarrinho = new Cliente();
        this.valorTotal = 0;
    }

    public ArrayList<Produto> getListaDeProdutos() {
        return listaDeProdutos;
    }

    public Cliente getClienteDoCarrinho() {
        return clienteDoCarrinho;
    }

    public void setClienteDoCarrinho(Cliente clienteDoCarrinho) {
        this.clienteDoCarrinho = clienteDoCarrinho;
    }

    public synchronized boolean adicionarProduto(Produto produto, int quantidade){
        if (verificaDisponibilidade(produto, quantidade)) {
            int index = bd.getProdutos().indexOf(produto); 
            Produto produtoNovo = null;
            try {
                produtoNovo = bd.getProdutos().get(index).clone();
                System.out.println(produtoNovo.toString());
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(Carrinho.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
            produtoNovo.setQuantidade(quantidade);
            this.getListaDeProdutos().add(produtoNovo);
            this.valorTotal += produtoNovo.getPreco()*quantidade;           
            bd.getProdutos().get(index).setQuantidade(
                    bd.getProdutos().get(index).getQuantidade() - quantidade);
            promocao.leve3pague2(this, produtoNovo);
            return true;
        }
        return false;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public synchronized void removerProduto(Produto produto) {
        if (this.listaDeProdutos.contains(produto)) {
            this.listaDeProdutos.remove(produto);
            this.valorTotal -= produto.getPreco();
        }
    }

    public boolean verificaDisponibilidade(Produto produto, int quantidade) {
        return (produto.getQuantidade() >= quantidade);
    }
      
    public String mostrarProdutos() {
        String mensagem = "\nProdutos atualmente no Carrinho: \n --- \n";
        for (Produto produto : listaDeProdutos) {
            mensagem += produto.toString() + "\n";
        }
        mensagem += "Valor Total: " + df.format(valorTotal) + "\n";
        return mensagem;
    }
}
