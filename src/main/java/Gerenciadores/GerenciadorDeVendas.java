/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gerenciadores;

import bancoDeDados.BancoDeDados;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import javax.swing.JOptionPane;
import modelo.Carrinho;
import modelo.Cliente;
import modelo.Produto;
import modelo.Servico;
import modelo.Venda;

/**
 *
 * @author nathancezar
 */
public class GerenciadorDeVendas {

    private BancoDeDados bd = BancoDeDados.getBancoDados();
    private Promocoes promocoes = Promocoes.getPromocoes();
    private LinkedList<Integer> cpfQueJaGanhouDesconto = new LinkedList();
    private static GerenciadorDeVendas venda;

    private GerenciadorDeVendas() {
    }

    public static synchronized GerenciadorDeVendas getVendas() {
        if (venda == null) {
            venda = new GerenciadorDeVendas();
        }
        return venda;
    }

    public synchronized void realizarVenda(Carrinho carrinho, Cliente cliente) {
        carrinho.setCliente(cliente);
        removerDescontosInvalidos(carrinho, cliente); // isso deve ser chamado no menu!! avisar ao cliente a retirada do desconto
        String boleto = gerarBoleto(carrinho);
        String cupomProdutos = gerarCupomFiscalProdutos(carrinho);
        String cupomServicos = gerarCupomFiscalServicos(carrinho);
        Venda novaVenda = new Venda(cliente, boleto);
        novaVenda.setCupomFiscalProdutos(cupomProdutos);
        novaVenda.setCupomFiscalServicos(cupomServicos);
        bd.getVendas().add(novaVenda);

        System.out.println("\n" + boleto + "\n");
        System.out.println(cupomProdutos);
        System.out.println(cupomServicos);

        esvaziarCarrinho(carrinho);
    }

    public boolean clienteJaTeveDesconto(int cpf) {
        return (cpfQueJaGanhouDesconto.stream().anyMatch((i) -> (i == cpf)));
    }

    private boolean removerDescontosInvalidos(Carrinho carrinho, Cliente cliente) {
        if (carrinho.getValorDoDescontoRecebido() != 0
                && clienteJaTeveDesconto(cliente.getCpf())) {
            promocoes.removePromocoesAplicadas(carrinho);
            return true;
        } else {
            cpfQueJaGanhouDesconto.add(cliente.getCpf());
            return false;
        }
    }

    private void esvaziarCarrinho(Carrinho carrinho) {
        carrinho.getListaDeProdutos().clear();
        carrinho.getListaDeServicos().clear();
        carrinho.setValorTotal(0);
        carrinho.setValorDoDescontoRecebido(0);
    }

    private String gerarBoleto(Carrinho carrinho) {
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
                + String.format("%05.0f", carrinho.getValorTotal()); // problema: aparece virgula
    }

    private float calculaValorTotalProdutos(ArrayList<Produto> produtos) {
        float total = 0;
        total = produtos.stream().map((produto)
                -> produto.getPreco() * produto.getQuantidade()).reduce(total, (accumulator, _item)
                -> accumulator + _item);
        return total;
    }

    private float calculaValorTotalServicos(ArrayList<Servico> servicos) {
        float total = 0;
        total = servicos.stream().map((servico)
                -> servico.getPreco()).reduce(total, (accumulator, _item)
                -> accumulator + _item);
        return total;
    }

    private String stringDeProdutosParaCF(ArrayList<Produto> produtos) {
        String produtosOrganizados = "\n";
        for (int i = 0; i < produtos.size(); i++) {
            double total = produtos.get(i).getPreco() * produtos.get(i).getQuantidade();
            produtosOrganizados += (i + 1) + "\t" + produtos.get(i).getCodigo() + "\t"
                    + produtos.get(i).getNome() + "\n"
                    + produtos.get(i).getQuantidade() + "\t X  \t"
                    + produtos.get(i).getPreco() + "\t\t\t"
                    + String.format("%05.2f", total) + "\n";
        }
        return produtosOrganizados;
    }

    private String gerarCupomFiscalProdutos(Carrinho carrinho) {
        ArrayList<Produto> produtosVendidos = carrinho.getListaDeProdutos();
        String cupomFiscal = " ";
        if (!produtosVendidos.isEmpty()) {
            cupomFiscal = "\tNR Informatica\n\t"
                    + "Rua Delfino Conti, 476\n\t"
                    + "Carvoeira, Florianópolis - SC, 88040-370\n"
                    + "CNPJ: 11.222.333/0001-44\nIE: 567.437.260.120\n"
                    + "IM: 4.345.876-0\n"
                    + "___________________________________________________\n"
                    + LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                    + "\n\n\t\tCUPOM FISCAL\n\n"
                    + "ITEM\t CÓDIGO\t  NOME\n"
                    + "QTD.\t   UN.\t  VL. UNIT(R$)ST  VL.ITEM(R$)\n"
                    + "___________________________________________________";
            cupomFiscal += stringDeProdutosParaCF(produtosVendidos)
                    + "___________________________________________________"
                    + "\nTOTAL\t\t\t\t\t"
                    + String.format("%05.2f", calculaValorTotalProdutos(produtosVendidos));
        }
        return cupomFiscal;
    }

    private String stringDeServicosParaCF(ArrayList<Servico> servicos) {
        String servicosOrganizados = "\n";
        for (int i = 0; i < servicos.size(); i++) {
            servicosOrganizados += (i + 1) + "\t" + servicos.get(i).getCodigo() + " "
                    + servicos.get(i).getNome()
                    + String.format("%04.2f", servicos.get(i).getPreco())
                    + "\n";
        }
        return servicosOrganizados;
    }

    private String gerarCupomFiscalServicos(Carrinho carrinho) {
        ArrayList<Servico> servicosVendidos = carrinho.getListaDeServicos();
        String cupomFiscal = " ";
        if (!servicosVendidos.isEmpty()) {
            cupomFiscal = "\tNR Informatica\n\t"
                    + "Rua Delfino Conti, 476\n\t"
                    + "Carvoeira, Florianópolis - SC, 88040-370\n"
                    + "CNPJ: 11.222.333/0001-44\nIE: 567.437.260.120\n"
                    + "IM: 4.345.876-0\n"
                    + "___________________________________________________\n"
                    + LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                    + "\n\n\t\tCUPOM FISCAL\n\n"
                    + "ITEM  \tCÓDIGO NOME   \t\tVL.SERVICO(R$)\n"
                    + "___________________________________________________";
            cupomFiscal += stringDeServicosParaCF(servicosVendidos)
                    + "___________________________________________________"
                    + "\n\t\tTOTAL\t\t\t"
                    + calculaValorTotalServicos(servicosVendidos);
        }
        return cupomFiscal;
    }

}
