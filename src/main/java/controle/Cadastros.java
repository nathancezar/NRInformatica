/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import bancoDeDados.BancoDeDados;
import java.util.Scanner;

/**
 *
 * @author rafael
 */
public class Cadastros {
    
    BancoDeDados bd = new BancoDeDados();
    Scanner scanner = new Scanner(System.in);
    
    public void novoProduto(int codigo,int quantidade, float preco, String nome, String endereco_imagem, String descricao){
        System.out.println("Nome do produto: ");
        nome = scanner.next();
        System.out.println("Código do Produto (>1000): ");
        codigo = scanner.nextInt();
        System.out.println("Quantidade no estoque: ");
        quantidade = scanner.nextInt();
        System.out.println("Preço a ser vendido: ");
        preco = scanner.nextFloat();
        endereco_imagem = "sem endereco";
        System.out.println("Descriçao do produto: ");
        descricao = scanner.next();
        
        Produto produto = new Produto(codigo, quantidade, preco, nome, endereco_imagem, descricao);
        bd.adicionarProduto(produto);
    }
    
}
