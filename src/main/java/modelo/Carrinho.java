/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import Gerenciadores.GerenciadorDeBusca;
import Gerenciadores.Promocoes;
import java.util.ArrayList;
import Gerenciadores.Promocoes;
import bancoDeDados.BancoDeDados;

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

    public boolean adicionarProduto(Produto produto, int quantidade) {
        this.getListaDeProdutos().add(produto);
        this.valorTotal += produto.getPreco();
        if (verificaDisponibilidade(produto, quantidade)) {
            int i = bd.getProdutos().indexOf(produto);
            bd.getProdutos().get(i).setQuantidade(bd.getProdutos().get(i).getQuantidade() - quantidade);
            promocao.leve3pague2(this);
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

    public void removerProduto(Produto produto) {
        if (this.listaDeProdutos.contains(
                busca.buscaProdutoPorCodigo(produto.getCodigo()))) {
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
        mensagem += "Valor Total: " + valorTotal;
        return mensagem;
    }
}
