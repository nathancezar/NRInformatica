/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gerenciadores;

import modelo.Produto;
import bancoDeDados.BancoDeDados;
import java.util.Scanner;

/**
 *
 * @author rafael
 */
public class Cadastros {
    
    private static Cadastros cadastrador;
    BancoDeDados bd = BancoDeDados.getBancoDados();
    Scanner scanner ;

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
        
    public void novoProduto(){
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
        
        public void  adicionarProduto(Produto produto){
            for( int i = 0; i < bd.getProdutos().length; i++){
                if (bd.getProdutos()[i] == null ) {
                    bd.getProdutos()[i] = produto;                    
                    break;
                }
            }
        }
    
        public void removerProduto(Produto produto){
            for(int i = 0; i < bd.getProdutos().length; i++){
                if (produto.getCodigo() == bd.getProdutos()[i].getCodigo()){
                    bd.getProdutos()[i] = null;                    
                    break;
                }
            }
        }
        
        public void verProdutosCadastrados(){
            for(int i = 0; i < bd.getProdutos().length; i++){
                if (bd.getProdutos()[i] != null){
                    System.out.println("Produto "+ i
                            + " | Nome: "+bd.getProdutos()[i].getNome()
                            +""+ " | Código: "+bd.getProdutos()[i].getCodigo()
                            + " | Quantidade: "
                            +bd.getProdutos()[i].getQuantidade());
                }
            }
        }
    
}
