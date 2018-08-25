/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import bancoDeDados.BancoDeDados;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author nathancezar
 */
public class ControleDeBusca {

    private static ControleDeBusca busca;
    private BancoDeDados bd;

    private ControleDeBusca() {
    }

    //Padrão Singleton usado para permitir apenas uma instância
    //da classe de Controle de Busca
    // usado synchronized para evitar concorrencia na criação da instância
    public static synchronized ControleDeBusca getControleBusca() {
        if (busca == null) {
            busca = new ControleDeBusca();
        }
        return busca;
    }

    //Opção de buscar produtos pelo nome
    //retorna um ArrayList com os produtos encontrados
    public ArrayList<Produto> buscaProdutoPorNome(String nome) {
        ArrayList<Produto> produtosEncontrados = new ArrayList<>();
        for (short i = 0; i < bd.getProdutos().length; i++) {
            if (bd.getProdutos()[i].getNome().toLowerCase().contains(nome.toLowerCase())) {
                produtosEncontrados.add(bd.getProdutos()[i]);
            }
        }
        return produtosEncontrados;
    }

    //Opção de buscar Clientes pelo nome
    //retorna um ArrayList com os Clientes encontrados
    public ArrayList<Cliente> buscaClientePorNome(String nome) {
        ArrayList<Cliente> clientesEncontrados = new ArrayList<>();
        for (short i = 0; i < bd.getClientes().length; i++) {
            if (bd.getClientes()[i].getNome().toLowerCase().contains(nome.toLowerCase())) {
                clientesEncontrados.add(bd.getClientes()[i]);
            }
        }
        return clientesEncontrados;
    }

    //Opção de buscar um único cliente pelo CPF
    //Retorna um Objeto Cliente
    public Cliente buscaClientePorCPF(int cpf) {
        for (Cliente cliente : bd.getClientes()) {
            if (cliente.getCpf() == cpf) {
                return cliente;
            }
        }
        return null;
    }

    //Opção de buscar um único produto pelo código
    // Retorna um Objeto Produto
    public Produto buscaProdutoPorCodigo(int codigo) {
        for (Produto p1 : bd.getProdutos()) {
            if (p1.getCodigo() == codigo) {
                return p1;
            }
        }
        return null;
    }

    //Opção de buscar produtos pela descrição
    //retorna um ArrayList com os produtos encontrados
    public ArrayList<Produto> buscaProdutoPorDescricao(String descricao) {
        ArrayList<Produto> produtosEncontrados = new ArrayList<>();
        for (short i = 0; i < bd.getProdutos().length; i++) {
            if (bd.getProdutos()[i].getDescricao().toLowerCase().contains(descricao.toLowerCase())) {
                produtosEncontrados.add(bd.getProdutos()[i]);
            }
        }
        return produtosEncontrados;
    }

    //Opção de buscar produtos por faixa de preço
    //retorna um ArrayList com os produtos encontrados
    public ArrayList<Produto> buscaProdutoPorFaixaDePreco(int min, int max) {
        ArrayList<Produto> produtosEncontrados = new ArrayList<>();
        for (short i = 0; i < bd.getProdutos().length; i++) {
            if (bd.getProdutos()[i].getPreco() > min
                    && bd.getProdutos()[i].getPreco() < max) {
                produtosEncontrados.add(bd.getProdutos()[i]);
            }
        }
        return produtosEncontrados;
    }

    //Opção de buscar produtos por nome e faixa de preço
    //retorna um ArrayList com os produtos encontrados
    public ArrayList<Produto> buscaPorNomePreco(String nome, int min, int max) {
        ArrayList<Produto> produtosEncontrados = this.buscaProdutoPorNome(nome);
        produtosEncontrados.removeIf((Produto p2)
                -> p2.getPreco() > max || p2.getPreco() < min);
        return produtosEncontrados;
    }

    //Retorna um ArrayList com a quantidade escolhida de Produtos escolhidos
    //randomicamente do Banco de Dados
    public ArrayList<Produto> buscaRandomicaDeProdutos(int quantidadeDesejada) {
        Random random = new Random();
        ArrayList<Produto> produtosSorteados = new ArrayList<>();
        for (int i = 0; i <= quantidadeDesejada; i++) {
            produtosSorteados.add(bd.getProdutos()[random.nextInt(bd.getProdutos().length)]);
        }
        return produtosSorteados;
    }

    // Dado um array de produtos, printa seus dados
    public void mostraProdutosDaLista(ArrayList<Produto> produtos) {
        for (int i = 0; i < produtos.size(); i++) {
            System.out.println("Cód: " + produtos.get(i).getCodigo()
                    + " | Nome: " + produtos.get(i).getNome() +
                    " | Preço: " + produtos.get(i).getPreco());
        }
    }

    public Produto[] procuraProdutoPorNome2(String nome) {
        Produto[] aux = new Produto[50];
        int j = 0;
        for (Produto produto : bd.produtos) {
            if (produto == null) {
                break;
            } else {
                if (produto.getNome().toLowerCase().contains(nome)) {
                    aux[j] = produto;
                    j++;
                }
            }
        }
        return aux;
    }

    public void mostraProdutosDaLista2(Produto[] produtos) {
        int i = 0;
        while (produtos[i] != null) {
            System.out.println("Cód: " + produtos[i].getCodigo()
                    + " | Nome: " + produtos[i].getNome() +
                    " | Preço: " + produtos[i].getPreco());
            i++;
        }
    }
}
