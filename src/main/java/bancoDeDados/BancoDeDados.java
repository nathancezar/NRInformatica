/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancoDeDados;

import java.util.LinkedList;
import modelo.Administrador;
import modelo.Cliente;
import modelo.Produto;
import modelo.Servico;
import modelo.Venda;

public final class BancoDeDados {

    private static BancoDeDados bd;

    private LinkedList<Produto> produtos;
    private LinkedList<Servico> servicos;
    private LinkedList<Cliente> clientes;
    private LinkedList<Venda> vendas;
    private Administrador adm;

    private BancoDeDados() {
        produtos = new LinkedList();
        clientes = new LinkedList();
        servicos = new LinkedList();
        vendas = new LinkedList();
        adm = new Administrador("adm", "adm");
    }

    //Padrão Singleton usado para permitir apenas uma instância
    //da classe Banco de Dados
    // usado synchronized para evitar concorrencia na criação da instância
    public static synchronized BancoDeDados getBancoDados() {
        if (bd == null) {
            bd = new BancoDeDados();
        }
        return bd;
    }

    public LinkedList<Produto> getProdutos() {
        return produtos;
    }

    public LinkedList<Servico> getServicos() {
        return servicos;
    }

    public LinkedList<Cliente> getClientes() {
        return clientes;
    }

    public LinkedList<Venda> getVendas() {
        return vendas;
    }

    public Administrador getAdm() {
        return adm;
    }
}
