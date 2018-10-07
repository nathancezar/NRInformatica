/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gerenciadores;

import modelo.Produto;
import bancoDeDados.BancoDeDados;
import modelo.Servico;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author rafael
 */
public class Cadastros {

    private static Cadastros cadastrador;
    BancoDeDados bd = BancoDeDados.getBancoDados();
    Scanner scanner;

    Cadastros() {
        bd = BancoDeDados.getBancoDados();
        scanner = new Scanner(System.in);
    }

    //Padrão Singleton usado para permitir apenas uma instância
    //da classe de Cadastros
    // usado synchronized para evitar concorrencia na criação da instância
    public static synchronized Cadastros getCadastros() {
        if (cadastrador == null) {
            cadastrador = new Cadastros();
        }
        return cadastrador;
    }

    public void novoProduto() {
        System.out.println("Nome do produto: ");
        String nome = scanner.next();
        System.out.println("Código do Produto (>1000): ");
        int codigo = scanner.nextInt();
        System.out.println("Quantidade no estoque: ");
        int quantidade = scanner.nextInt();
        System.out.println("Preço a ser vendido: ");
        float preco = scanner.nextFloat();
        String endereco_imagem = "sem endereco";
        System.out.println("Descriçao do produto: ");
        String descricao = scanner.next();

        Produto produto = new Produto(codigo, quantidade, preco, nome, endereco_imagem, descricao);
        adicionarProduto(produto);

    }

    public void criarNovoServico(int codigo, String nome, ArrayList<Date> datas, float preco) {
        Servico servico = new Servico(codigo, nome, datas, preco);
        adicionarServico(servico);
    }

    public void adicionarProduto(Produto produto) {
        bd.getProdutos().add(produto);
    }

    public void adicionarServico(Servico servico) {
        bd.getServicos().add(servico);
    }

    public void removerServico(Servico servico) {
        bd.getServicos().remove(servico);
    }

    public void removerProduto(Produto produto) {
        bd.getProdutos().remove(produto);
    }

}
