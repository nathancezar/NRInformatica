/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;
import bancoDeDados.BancoDeDados;

/**
 *
 * @author nathancezar
 */
public class Main {

    public static void main(String[] args) {
        
        BancoDeDados bd = new BancoDeDados();
        Produto produtoP1 = new Produto(1000, 30, 49.90f, "Mouse Optico",
                "endereco", "Mouse Optico Razer 1800DPI Modelo 075 Nagius");

        bd.adicionar(produtoP1);
        System.out.println(bd.produtos[0].getNome());
    }

}
