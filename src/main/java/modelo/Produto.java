/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.text.DecimalFormat;

public class Produto implements Cloneable{

    private int codigo, quantidade;
    private float preco;
    private String nome, endereco_imagem, descricao;
    DecimalFormat df = new DecimalFormat("#.00");

    public Produto() {
    }

    public Produto(int codigo, int quantidade, float preco, String nome, String endereco_imagem, String descricao) {
        this.codigo = codigo;
        this.quantidade = quantidade;
        this.preco = preco;
        this.nome = nome;
        this.endereco_imagem = endereco_imagem;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public float getPreco() {
        return preco;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco_imagem() {
        return endereco_imagem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEndereco_imagem(String endereco_imagem) {
        this.endereco_imagem = endereco_imagem;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    @Override
    public Produto clone() throws CloneNotSupportedException {
        return (Produto) super.clone();
    }

    @Override
    public String toString() {
        String msg = "Produto Indisponível";
        if (this.quantidade > 0) {
            msg = "Quantidade Disponível: " + this.getQuantidade();
        }
        return "Código: " + codigo + " | Nome: " + nome + " | preco: "
                + df.format(preco) + " | " + msg;
    }
}
