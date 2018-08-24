/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancoDeDados;

import controle.Administrador;
import controle.Cliente;
import controle.Produto;



public class BancoDeDados {
    
    public Produto[] produtos = new Produto[100];
    public Cliente[] clientes = new Cliente[50];
    public Administrador adm = new Administrador("adm", "adm");  

    public BancoDeDados() {          
    }            

    public Produto[] getProdutos() {
        return produtos;
    }
    
    public Cliente[] getClientes() {
        return clientes;
    }
    
    Produto produtoP1 = new Produto(1000, 30, 49.90f, "Mouse Optico",
            "endereco", "Mouse Optico Razer 1800DPI Modelo 075 Nagius");
    
    
    public void  adicionarProduto(Produto produto){
        for( int i = 0; i < this.produtos.length; i++){
            if (produtos[i] == null ) {
                produtos[i] = produtoP1;
                System.out.println("Produto Adicionado com sucesso.");
                break;
            }
        }
    }
    
    public void removerProduto(Produto produto){
        for(int i = 0; i < this.produtos.length; i++){
            if (produto.getCodigo() == produtos[i].getCodigo()){
                produtos[i] = null;
                System.out.println("Produto removido com sucesso");
            }
        }
    }
    
            
    
    
    
}


