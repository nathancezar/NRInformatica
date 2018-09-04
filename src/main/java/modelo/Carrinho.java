/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import Gerenciadores.GerenciadorDeBusca;
import java.util.ArrayList;

/**
 *
 * @author nathancezar
 */
public class Carrinho {

    ArrayList<Produto> listaDeProdutos;
    Cliente clienteDoCarrinho;
    GerenciadorDeBusca busca = GerenciadorDeBusca.getControleBusca();

    public Carrinho() {
        this.listaDeProdutos = new ArrayList<>();
        this.clienteDoCarrinho = new Cliente();
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

    public void adicionarProduto(Produto produto) {
        this.getListaDeProdutos().add(produto);
    }

    public void removerProduto(Produto produto) {
        if (this.listaDeProdutos.contains(
                busca.buscaProdutoPorCodigo(produto.getCodigo()))) {
            this.listaDeProdutos.remove(produto);
        }
    }

    public String mostrarProdutos() {
        String mensagem = "\nProdutos atualmente no Carrinho: \n --- \n";
        for (Produto produto : listaDeProdutos) {
            mensagem += " Nome: " + produto.getNome()
                    + " Código: " + produto.getCodigo()
                    + " Preço: " + produto.getPreco() + "\n";
        }
        return mensagem;
    }
}
