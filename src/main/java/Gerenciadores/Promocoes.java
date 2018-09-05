/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gerenciadores;

import bancoDeDados.BancoDeDados;
import modelo.Carrinho;

/**
 *
 * @author nathan.cezar
 */
public class Promocoes {

    Carrinho carrinhoAtual;
    BancoDeDados bd = BancoDeDados.getBancoDados();

    public Promocoes() {
    }

    public void leve3pague2(Carrinho carrinhoAtual) {
        int quantidadeContada = 0;
        int posicaoAtual = 0;
        for (int i = 0; i < bd.getProdutos().size() - 1; i++) {
            posicaoAtual = i + 1;
            
            for (int j = posicaoAtual; i < bd.getProdutos().size(); j++) {
                if (bd.getProdutos().get(i).getCodigo() == bd.getProdutos().get(j).getCodigo()) {
                    quantidadeContada++;
                    if (quantidadeContada == 3) {
                        float novoValor = this.carrinhoAtual.getValorTotal()
                                - bd.getProdutos().get(i).getPreco();
                        this.carrinhoAtual.setValorTotal(novoValor);
                        System.out.println("Desconto aplicado com sucesso.");
                        break;
                    }
                }
            }
        }
    }

}
