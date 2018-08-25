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
        
        Cadastros cadastro = Cadastros.getCadastros();
        
        Produto produtoP1 = new Produto(1000, 30, 49.90f, "Mouse Optico",
                "endereco", "Mouse Optico Razer 1800DPI Modelo 075 Nagius");
        
        Produto produtoP2 = new Produto(1001, 24, 49.90f, "Teclado sem fio",
                "endereco", "Mouse Optico Razer 1800DPI Modelo 075 Nagius");
        
        Produto produtoP3 = new Produto(1002, 40, 49.90f, "Roteador Wi-fi",
                "endereco", "Mouse Optico Razer 1800DPI Modelo 075 Nagius");
        
        Produto produtoP4 = new Produto(1003, 40, 49.90f, "Impresso jato de tinta",
                "endereco", "Mouse Optico Razer 1800DPI Modelo 075 Nagius");
        
        Produto produtoP5 = new Produto(1004, 3, 49.90f, "Pendrive Sandisk 8GB",
                "endereco", "Mouse Optico Razer 1800DPI Modelo 075 Nagius");
        
 
        cadastro.adicionarProduto(produtoP1);
        cadastro.adicionarProduto(produtoP2);
        cadastro.adicionarProduto(produtoP3);
        cadastro.adicionarProduto(produtoP4);
        cadastro.adicionarProduto(produtoP5);
        
        Menu menu = new Menu();
        menu.menuInicial();
    }

}
