package modelo;

import java.util.ArrayList;

public class Servico {

    private int codigo;
    private String nome;
    private ArrayList<String> datas;
    private float preco;

    public Servico(int codigo, String nome, ArrayList<String> datas, float preco) {
        this.codigo = codigo;
        this.nome = nome;
        this.datas = datas;
        this.preco = preco;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public ArrayList<String> getDatas() {
        return datas;
    }

    public float getPreco() {
        return preco;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDatas(ArrayList<String> datas) {
        this.datas = datas;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public String toString() {
        String msg = "";
        if (datas.isEmpty()) {
            msg = " | Serviço indisponível";
        }
        return "Código: "+this.codigo + " | Serviço: " + this.nome + " | Valor: " + this.preco + msg;
    }
}
