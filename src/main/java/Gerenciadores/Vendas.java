/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gerenciadores;

import java.util.ArrayList;
import java.util.Random;
import modelo.Carrinho;
import modelo.Cliente;
import modelo.Produto;
import modelo.Servico;
import modelo.Venda;

/**
 *
 * @author nathancezar
 */
public class Vendas {

    private static Vendas venda;

    private Vendas() {
    }

    public static synchronized Vendas getVendas() {
        if (venda == null) {
            venda = new Vendas();
        }
        return venda;
    }

    public synchronized void realizarVenda(Carrinho carrinho, Cliente cliente) {        
        String boleto = gerarBoleto(carrinho);
        String cupomFiscal = gerarCupomFiscalProdutos(carrinho, cliente);        
        Venda novaVenda = new Venda(cliente, cupomFiscal, boleto);

    }

    private void esvaziarCarrinho(Carrinho carrinho) {
        carrinho.getListaDeProdutos().clear();
        carrinho.setValorTotal(0);
        carrinho.setValorDoDescontoRecebido(0);
        carrinho.setClienteDoCarrinho(null);
    }

    public String gerarBoleto(Carrinho carrinho) {
        String boleto = "23790";
        Random rd = new Random();
        for (int i = 0; i < 5; i++) {
            if (i % 2 == 0) {
                boleto += "." + String.format("%05d", rd.nextInt(10000));
            } else {
                boleto += " " + String.format("%05d", rd.nextInt(10000));
            }
        }
        return boleto += " 8 " + "746500000"
                + String.format("%05f", carrinho.getValorTotal());
    }

    public String stringDeProdutosParaCF(ArrayList<Produto> produtos) {
        String produtosOrganizados = "\n";
        for (int i = 1; i <= produtos.size(); i++) {
            double total = produtos.get(i).getPreco() * produtos.get(i).getQuantidade();
            produtosOrganizados += i + " " + produtos.get(i).getCodigo() + " "
                    + produtos.get(i).getNome()
                    + produtos.get(i).getQuantidade() + " X  "
                    + produtos.get(i).getPreco() + "\t"
                    + total + "\n";
        }
        return produtosOrganizados;
    }
    
    private String stringDeServicosParaCF(ArrayList<Servico> servicos) {
        
        
        
        return " ";
    }

    private String gerarCupomFiscalProdutos(Carrinho carrinho, Cliente cliente) {
        ArrayList<Produto> produtosVendidos = carrinho.getListaDeProdutos();
        String cupomFiscal = " ";
        if (!produtosVendidos.isEmpty()) {
            cupomFiscal = "\t\tNR Informatica\n\t\t"
                    + "Rua Delfino Conti, 476\n\t\t"
                    + "Carvoeira, Florianópolis - SC, 88040-370\n"
                    + "CNPJ: 11.222.333/0001-44\nIE: 567.437.260.120\n"
                    + "IM: 4.345.876-0\n"
                    + "___________________________________________________\n"
                    + java.time.Instant.now()
                    + "\n\t\tCUPOM FISCAL\n"
                    + "ITEM CÓDIGO  NOME\n"
                    + "QTD.   UN.  VL. UNIT(R$)ST  VL.ITEM(R$)"
                    + "___________________________________________________";
            cupomFiscal += stringDeProdutosParaCF(produtosVendidos)
                    + "___________________________________________________"
                    + "\nTOTAL\t\t\t" + carrinho.getValorTotal();
        }
        return cupomFiscal;
    }
    
  /*  private String gerarCupomFiscalServicos(Carrinho carrinho, Cliente cliente) {
        ArrayList<Servico> servicosVendidos = carrinho.getListaDeServicos();
        String cupomFiscal = " ";
        if (!servicosVendidos.isEmpty()) {
            cupomFiscal = "\t\tNR Informatica\n\t\t"
                    + "Rua Delfino Conti, 476\n\t\t"
                    + "Carvoeira, Florianópolis - SC, 88040-370\n"
                    + "CNPJ: 11.222.333/0001-44\nIE: 567.437.260.120\n"
                    + "IM: 4.345.876-0\n"
                    + "___________________________________________________\n"
                    + java.time.Instant.now()
                    + "\n\t\tCUPOM FISCAL\n"
                    + "ITEM CÓDIGO  NOME\n"
                    + "QTD.   UN.  VL. UNIT(R$)ST  VL.ITEM(R$)"
                    + "___________________________________________________";
            cupomFiscal += stringDeProdutosParaCF(servicosVendidos)
                    + "___________________________________________________"
                    + "\nTOTAL\t\t\t" + carrinho.getValorTotal();
        }
        return cupomFiscal;
    } */
}
