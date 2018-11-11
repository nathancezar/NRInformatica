/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import bancoDeDados.BancoDeDados;

/**
 *
 * @author nathancezar
 */
public class Venda {

    private BancoDeDados bd = BancoDeDados.getBancoDados();
    private String boleto;
    private String cupomFiscalProdutos;
    private String cupomFiscalServicos;
    private final int codigo;
    private boolean aplicadaPromocaoNaVenda = false;
    private final Cliente cliente;

    public Venda(Cliente cliente_, String boleto_) {
        this.cliente = cliente_;
        this.boleto = boleto_;
        this.codigo = (bd.getVendas().size()+1);
    }

    public String getBoleto() {
        return boleto;
    }

    public void setBoleto(String boleto) {
        this.boleto = boleto;
    }

    public String getCupomFiscalProdutos() {
        return cupomFiscalProdutos;
    }

    public void setCupomFiscalProdutos(String cupomFiscalProdutos) {
        this.cupomFiscalProdutos = cupomFiscalProdutos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public String getCupomFiscalServicos() {
        return cupomFiscalServicos;
    }

    public void setCupomFiscalServicos(String cupomFiscalServicos) {
        this.cupomFiscalServicos = cupomFiscalServicos;
    }

    public int getCodigo() {
        return codigo;
    }

    public boolean isAplicadaPromocaoNaVenda() {
        return aplicadaPromocaoNaVenda;
    }

    public void setAplicadaPromocaoNaVenda(boolean aplicadaPromocaoNaVenda) {
        this.aplicadaPromocaoNaVenda = aplicadaPromocaoNaVenda;
    }

}
