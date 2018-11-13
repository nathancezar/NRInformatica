/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import Gerenciadores.Cadastros;
import Mapeadores.MapeadorEndereco;
import Mapeadores.MapeadorProduto;
import modelo.Cliente;
import modelo.Endereco;
import modelo.Servico;
import visao.Menu;
import modelo.Produto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author nathancezar
 */
public class Main {

    public static void main(String[] args) throws SQLException {

        String url = "jdbc:postgresql://localhost:5432/NRinformatica";
        String username = "postgres";
        String password = "root";

        Connection db = DriverManager.getConnection(url, username, password);

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

        String data1 = "10/10/2010 10:20";
        String data2 = "10/10/2018 10:50";
        String data3 = "10/10/2018 12:00";
        ArrayList<String> datas = new ArrayList<>();
        datas.add(data1);
        datas.add(data2);
        datas.add(data3);

        Servico s1 = new Servico(2000, "Formatação para Windows 10", datas , 79.90f);
        Servico s2 = new Servico(2001, "Instalçao pacote office 2016", datas, 59.90f);

 
        cadastro.adicionarProduto(produtoP1);
        cadastro.adicionarProduto(produtoP2);
        cadastro.adicionarProduto(produtoP3);
        cadastro.adicionarProduto(produtoP4);
        cadastro.adicionarProduto(produtoP5);
        cadastro.adicionarServico(s1);
        cadastro.adicionarServico(s2);

        Endereco end = new Endereco(1,"RS", "Constantina", "São Roque", "São José", "casa", 361);

        Cliente cliente = new Cliente("Rafael", 12345678, end, "1234");

        cadastro.adicionarCliente(cliente);

        MapeadorProduto mp = new MapeadorProduto(db);
        MapeadorEndereco me = new MapeadorEndereco(db);

        // Produtos adicionados pelo BD.

        Produto p1 = mp.get(4000);
        cadastro.adicionarProduto(p1);

        Produto p2 = mp.get(4001);
        cadastro.adicionarProduto(p2);

        Produto p3 = mp.get(4002);
        cadastro.adicionarProduto(p3);

        Endereco endereco10 = me.get(10);
        Cliente cliente2 = new Cliente("Nathan", 23456789, endereco10, "nathan");
        cadastro.adicionarCliente(cliente2);
        
        Menu menu = new Menu();
        menu.menuInicial();
    }

}
