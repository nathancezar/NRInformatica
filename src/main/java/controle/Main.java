/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

/**
 *
 * @author nathancezar
 */
public class Main {

    public static void main(String[] args) {
        
        Cadastros cadastro = new Cadastros();
        Produto produtoP1 = new Produto(1000, 30, 49.90f, "Mouse Optico",
                "endereco", "Mouse Optico Razer 1800DPI Modelo 075 Nagius");
        
        //cadastro.novoProduto();

        cadastro.adicionarProduto(produtoP1);
        
        cadastro.verProdutosCadastrados();
    }

}
