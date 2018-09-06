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

    Carrinho carrinhoAtual;
    BancoDeDados bd = BancoDeDados.getBancoDados();

    public Promocoes() {
    }

    public void leve3pague2(Carrinho carrinho, Produto produto) {
        if (produto.getQuantidade() == 3) {            
            float novoValor = (carrinho.getValorTotal()
                    - produto.getPreco());
            carrinho.setValorTotal(novoValor);
        }
    }
    
    public void desconto(Produto produto, int desconto) {
        produto.setPreco(produto.getPreco()-(produto.getPreco()*desconto)/100);
    }
    
    

}
