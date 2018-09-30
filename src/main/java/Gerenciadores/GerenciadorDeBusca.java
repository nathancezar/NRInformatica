/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gerenciadores;

import modelo.Cliente;
import modelo.Produto;
import bancoDeDados.BancoDeDados;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author nathancezar
 */
public class GerenciadorDeBusca {

    private static GerenciadorDeBusca busca;
    private final BancoDeDados bd;

    private GerenciadorDeBusca() {
        bd = BancoDeDados.getBancoDados();
    }

    //Padrão Singleton usado para permitir apenas uma instância
    //da classe de Controle de Busca
    // usado synchronized para evitar concorrencia na criação da instância
    public static synchronized GerenciadorDeBusca getControleBusca() {
        if (busca == null) {
            busca = new GerenciadorDeBusca();
        }
        return busca;
    }

    //Opção de buscar produtos pelo nome
    //retorna um ArrayList com os produtos encontrados
    public ArrayList<Produto> buscaProdutoPorNome(String nome) {
        ArrayList<Produto> produtosEncontrados = new ArrayList<>();
        bd.getProdutos().stream().filter((produto) -> (produto != null
                && produto.getNome().toLowerCase().contains(nome.toLowerCase()))).forEachOrdered((produto) -> {
            produtosEncontrados.add(produto);
        });
        return produtosEncontrados;
    }

    //Opção de buscar Clientes pelo nome
    //retorna um ArrayList com os Clientes encontrados
    public ArrayList<Cliente> buscaClientePorNome(String nome) {
        ArrayList<Cliente> clientesEncontrados = new ArrayList<>();
        bd.getClientes().stream().filter((cliente) -> (cliente != null
                && cliente.getNome().toLowerCase().contains(nome.toLowerCase()))).forEachOrdered((cliente) -> {
            clientesEncontrados.add(cliente);
        });
        return clientesEncontrados;
    }

    //Opção de buscar um único cliente pelo CPF
    //Retorna um Objeto Cliente
    public Cliente buscaClientePorCPF(int cpf) {
        for (Cliente cliente : bd.getClientes()) {
            if (cliente != null && cliente.getCpf() == cpf) {
                return cliente;
            }
        }
        return null;
    }

    //Opção de buscar um único produto pelo código
    // Retorna um Objeto Produto
    public Produto buscaProdutoPorCodigo(int codigo) {
        for (Produto p1 : bd.getProdutos()) {
            if (p1 != null && p1.getCodigo() == codigo) {
                return p1;
            }
        }
        return null;
    }

    //Opção de buscar produtos pela descrição
    //retorna um ArrayList com os produtos encontrados
    public ArrayList<Produto> buscaProdutoPorDescricao(String descricao) {
        ArrayList<Produto> produtosEncontrados = new ArrayList<>();
        for (Produto produto : bd.getProdutos()) {
            if (produto != null
                    && produto.getDescricao().toLowerCase().contains(descricao.toLowerCase())) {
                produtosEncontrados.add(produto);
            }
        }
        return produtosEncontrados;
    }

    //Opção de buscar produtos por faixa de preço
    //retorna um ArrayList com os produtos encontrados
    public ArrayList<Produto> buscaProdutoPorFaixaDePreco(int min, int max) {
        ArrayList<Produto> produtosEncontrados = new ArrayList<>();
        for (Produto produto : bd.getProdutos()) {
            if (produto != null
                    && produto.getPreco() > min && produto.getPreco() < max) {
                produtosEncontrados.add(produto);
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
        while (quantidadeDesejada > 0) {
            Produto produtoSorteado = bd.getProdutos().get(random.nextInt(bd.getProdutos().size()));
            if (!produtosSorteados.contains(produtoSorteado)) {
                produtosSorteados.add(produtoSorteado);
                quantidadeDesejada--;
            }
        }
        return produtosSorteados;
    }

    // Dado um array de produtos retorna uma string com os Dados
    public String mostraProdutosDaLista(ArrayList<Produto> produtos) {
        String mensagem = "";
        for (int i = 0; i < produtos.size(); i++) {
            mensagem += "Cód: " + produtos.get(i).getCodigo()
                    + " | Nome: " + produtos.get(i).getNome()
                    + " | Preço: " + produtos.get(i).getPreco() + "\n";
        }
        return mensagem;
    }

    public Produto[] procuraProdutoPorNome2(String nome) {
        Produto[] aux = new Produto[50];
        int j = 0;
        for (Produto produto : bd.getProdutos()) {
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
                    + " | Nome: " + produtos[i].getNome()
                    + " | Preço: " + produtos[i].getPreco());
            i++;
        }
    }
    
    // verifica se existe, atualmente no Estoque, um produto com
    // o código passado
    public boolean verificaSeCodigoExiste(int codigo) {
        for (Produto produto : bd.getProdutos()) {
            if (codigo == produto.getCodigo()) {
                return true;
            }
        }
        return false;
    }
}
