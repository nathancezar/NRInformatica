/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gerenciadores;

import bancoDeDados.BancoDeDados;
import modelo.Carrinho;
import modelo.Produto;

/**
 *
 * @author nathan.cezar
 */
public class Promocoes {

    private static Promocoes promocoes;
    BancoDeDados bd = BancoDeDados.getBancoDados();
    private short quantidadeDeProdutosParaDesconto = 5;
    private short descontoParaCadaXProdutos = 5;
    private short descontoMaximo = 15;

    public Promocoes() {
    }

    public void setQuantidadeDeProdutosParaDesconto(short quantidadeDeProdutosParaDesconto) {
        this.quantidadeDeProdutosParaDesconto = quantidadeDeProdutosParaDesconto;
    }

    public void setDescontoParaCadaXProdutos(short descontoParaCadaXProdutos) {
        this.descontoParaCadaXProdutos = descontoParaCadaXProdutos;
    }

    public void setDescontoMaximo(short descontoMaximo) {
        this.descontoMaximo = descontoMaximo;
    }

    public static synchronized Promocoes getPromocoes() {
        if (promocoes == null) {
            promocoes = new Promocoes();
        }
        return promocoes;
    }

    // Aplica um desconto no valor de 1 produto na compra de 3 produtos iguais
    // o desconto só é usado 1 vez por carrinho e vale para o produto com
    // valor mais alto
    private synchronized float leve3pague2(Carrinho carrinho) {
        float desconto = carrinho.getValorDoDescontoRecebido();
        for (Produto produto : carrinho.getListaDeProdutos()) {
            if (produto.getQuantidade() >= 3) {
                desconto = Math.max(desconto, produto.getPreco());
            }
        }
        return desconto;
    }

    // altera o valor de um produto, diminuindo o valor de acordo
    // com a porcentagem passada como parametro
    public synchronized void aplicarDescontoEmUmProduto(Produto produto, int desconto) {
        produto.setPreco(produto.getPreco() - ((produto.getPreco() * desconto) / 100));
    }

    // retorna o total de itens dentro do carrinho
    // contando a quantidade de cada item
    private synchronized int quantidadeDeItensNoCarrinho(Carrinho carrinho) {
        int quantidadeDeItens = 0;
        for (Produto produto : carrinho.getListaDeProdutos()) {
            quantidadeDeItens += produto.getQuantidade();
        }
        return quantidadeDeItens;
    }

    // gera um desconto no valor total do carrinho, de acordo com a quantidade
    // de produtos adicionados, quanto mais produtos, maior o desconto
    // quantidade de produtos que gera o desconto: -> quantidadeDeProdutosParaDesconto
    // desconto a ser consedido -> descontoParaCadaXProdutos
    // maior desconto permitido -> descontoMaximo
    private synchronized float descontoACadaXProdutos(Carrinho carrinho) {
        if (quantidadeDeItensNoCarrinho(carrinho) > quantidadeDeProdutosParaDesconto) {
            int quantidadeDeProdutos = quantidadeDeItensNoCarrinho(carrinho);
            float desconto = carrinho.getValorTotal();
            float quantidadeDeDescontos = ((Math.floorDiv(
                    quantidadeDeProdutos, quantidadeDeProdutosParaDesconto)) * descontoParaCadaXProdutos);
            if (quantidadeDeDescontos >= descontoMaximo) {
                desconto = (desconto * descontoMaximo) / 100;
            } else {
                desconto = (desconto * quantidadeDeDescontos) / 100;
            }
            return desconto;
        }
        return 0;
    }

    // começa retirando do carrinho algum desconto recebido anteriormente
    // verifica o maior valor entre os descontos possíveis
    // compara se o desconto novo é superior ao antigo
    // subtrai do valor do carrinho o maior desconto
    public synchronized void verificaPromocoesAplicaveis(Carrinho carrinho) {
        carrinho.setValorTotal(carrinho.getValorTotal() + carrinho.getValorDoDescontoRecebido());
        float maiorDesconto = Math.max(leve3pague2(carrinho), descontoACadaXProdutos(carrinho));
        maiorDesconto = Math.max(maiorDesconto, carrinho.getValorDoDescontoRecebido());
        carrinho.setValorDoDescontoRecebido(maiorDesconto);
        carrinho.setValorTotal(carrinho.getValorTotal() - maiorDesconto);
    }
}
