/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Random;

/**
 *
 * @author nathancezar
 */
public class Venda {

    String boleto;
    String cupomFiscal;
    int codigo;
    Random rd = new Random();
    Cliente cliente;

    public Venda(Cliente cliente_, String cupom_, String boleto_) {
        this.boleto = boleto_;
        this.cliente = cliente_;
        this.cupomFiscal = cupom_;
        this.codigo = Integer.parseInt(String.format("%05d", rd.nextInt(10000)));
    }

    public String getBoleto() {
        return boleto;
    }

    public void setBoleto(String boleto) {
        this.boleto = boleto;
    }

    public String getCupomFiscal() {
        return cupomFiscal;
    }

    public void setCupomFiscal(String cupomFiscal) {
        this.cupomFiscal = cupomFiscal;
    }

    public Cliente getCliente() {
        return cliente;
    }

}
