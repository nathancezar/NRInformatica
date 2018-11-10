/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

public class Cliente {
    
    private String nome;
    private int cpf;
    private String senha;
    private Endereco endereco;
    private Carrinho carrinho;

    public Cliente(String nome, int cpf, Endereco endereco, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.carrinho = new Carrinho();
        this.senha = senha;
    }

    public Cliente() {
    }       

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Carrinho getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(Carrinho carrinho) {
        this.carrinho = carrinho;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
