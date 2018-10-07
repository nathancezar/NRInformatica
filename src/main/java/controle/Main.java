/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import Gerenciadores.Cadastros;
import modelo.Servico;
import visao.Menu;
import modelo.Produto;

import java.util.ArrayList;
import java.util.Date;

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

        Date data1 = new Date(2018, 10, 10, 10, 00);
        Date data2 = new Date(2018, 10, 10, 10, 30);
        Date data3 = new Date(2018, 10, 10, 11, 00);
        ArrayList<Date> datas = new ArrayList<>();
        datas.add(data1);

        Servico s1 = new Servico(2000, "Formatação para Windows 10", datas , 79.90f);
        Servico s2 = new Servico(2001, "Instalçao pacote office 2016", datas, 59.90f);

 
        cadastro.adicionarProduto(produtoP1);
        cadastro.adicionarProduto(produtoP2);
        cadastro.adicionarProduto(produtoP3);
        cadastro.adicionarProduto(produtoP4);
        cadastro.adicionarProduto(produtoP5);
        cadastro.adicionarServico(s1);
        cadastro.adicionarServico(s2);
        
        Menu menu = new Menu();
        menu.menuInicial();
    }

}
