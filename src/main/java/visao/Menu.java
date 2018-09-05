/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import modelo.Produto;
import bancoDeDados.BancoDeDados;
import Gerenciadores.Cadastros;
import Gerenciadores.GerenciadorDeBusca;
import java.util.ArrayList;
import java.util.Scanner;
import modelo.Carrinho;

/**
 *
 * @author rafael
 */
public class Menu {

    Scanner scanner = new Scanner(System.in);
    Cadastros cadastro = Cadastros.getCadastros();
    BancoDeDados bd = BancoDeDados.getBancoDados();
    GerenciadorDeBusca busca = GerenciadorDeBusca.getControleBusca();
    Carrinho carrinho = new Carrinho();

    public void menuInicial() {

        System.out.println("\nSeja bem-vindo a NR Informática");
        System.out.println("");
        System.out.println("Veja nossos destaques:");
        System.out.println(busca.mostraProdutosDaLista(busca.buscaRandomicaDeProdutos(3)));
        System.out.println("--");
        System.out.println("1 - Ver todos os produtos");
        System.out.println("2 - Procurar produtos");
        System.out.println("3 - Visualizar carrinho");
        System.out.println("4 - Entrar como administrador");
        System.out.println("5 - Sair");

        int escolha = scanner.nextInt();

        switch (escolha) {
            case 1:
                visualizarProdutos();
                menuInicial();
            case 2:
                menuProcurarProduto();
                menuInicial();
            case 3:
                visualizarCarrinho();
                menuInicial();
            case 4:
                menuAdmLogin();
                menuInicial();
            case 5:
                System.exit(0);

        }
    }

    private void menuAdmLogin() {
        System.out.println("Digite seu login");
        String login = scanner.next();
        System.out.println("Digite sua senha");
        String senha = scanner.next();
        if (login.equals(bd.getAdm().getLogin()) && senha.equals(bd.getAdm().getSenha())) {
            menuAdm();
        } else {
            System.out.println("Login e/ou senha inválidos");
            menuInicial();
        }
    }

    private void menuAdm() {
        System.out.println("----AREA ADMINISTRATIVA----");
        System.out.println("");
        System.out.println("1 - Cadastrar produto");
        System.out.println("2 - Editar produto");
        System.out.println("3 - Excluir produto");
        System.out.println("0 - Voltar ao menu principal");

        int escolha = scanner.nextInt();

        switch (escolha) {
            case 1:
                cadastro.novoProduto();
                System.out.println("Produto Adicionado com sucesso.");
                menuAdm();
                break;
            case 2:
                editarProduto();
                menuAdm();
                break;
            case 3:
                menuExcluirProduto();
                System.out.println("Produto removido com sucesso");
                menuAdm();
                break;
            case 0:
                menuInicial();                
                break;
        }
    }

    private void menuExcluirProduto() {
        System.out.println("Digite o código do produto que deseja excluir:");
        visualizarProdutos();
        int codProduto = scanner.nextInt();
        for (int i = 0; i < bd.getProdutos().size(); i++) {
            if (bd.getProdutos().get(i).getCodigo() == codProduto) {
                System.out.println("Você tem certeza que deseja exluir o produto " + bd.getProdutos().get(i).getNome() + "? (S/N");
                String escolha = scanner.next();
                if (escolha.equalsIgnoreCase("s")) {
                    cadastro.removerProduto(bd.getProdutos().get(i));
                    System.out.println("Produto removido com sucesso.");
                    menuAdm();
                } else {
                    System.out.println("Produto não removido");
                    menuAdm();
                }
            } else {
                System.out.println("Produto não encontrado!");
                menuAdm();
            }
        }
    }

    private void editarProduto() {
        System.out.println("Procurar produto: (nome)");
        String nomeProduto = scanner.next();
        ArrayList<Produto> buscados = busca.buscaProdutoPorNome(nomeProduto);
        buscados.forEach((produto2) -> {
            System.out.println("Cód: " + produto2.getCodigo()
                    + " | Nome: " + produto2.getNome()
                    + " | Preço: " + produto2.getPreco());
        });
        if (buscados.get(0) == null) { //armazenar
            System.out.println("Nenhum produto encontrado");
        } else {
            busca.mostraProdutosDaLista(buscados);
            System.out.println("Agora, digite o código do produto a ser editado.");
            int codProduto = scanner.nextInt();
            for (int i = 0; i < bd.getProdutos().size(); i++) {
                if (bd.getProdutos().get(i).getCodigo() == codProduto) {                    
                    menuEscolheEdicao(bd.getProdutos().get(i), codProduto);
                    break;
                } else {
                    System.out.println("Produto não encontrado");
                }
            }
        }

    }

    private void menuEscolheEdicao(Produto produto, int cod) {
        System.out.println("1 - Editar nome");
        System.out.println("2 - Editar código");
        System.out.println("3 - Editar preço");
        System.out.println("4 - Editar descrição");
        System.out.println("5 - Editar quantidade em estoque");
        System.out.println("6 - Voltar ao menu anterior");

        int escolha = scanner.nextInt();

        switch (escolha) {
            case 1:
                System.out.println("Digite o novo nome: ");
                String nome = scanner.next();
                produto.setNome(nome);
                break;
            case 2:
                System.out.println("Digite o novo código: ");
                int codigo = scanner.nextInt();
                produto.setCodigo(codigo);
                break;
            case 3:
                System.out.println("Digite o novo preço");
                float preco = scanner.nextFloat();
                produto.setPreco(preco);
                break;
            case 4:
                System.out.println("Digite a nova descrição");
                String descricao = scanner.next();
                produto.setDescricao(descricao);
                break;
            case 5:
                System.out.println("Digite a nova quantidade");
                int qtd = scanner.nextInt();
                produto.setQuantidade(qtd);
                break;
            default:
                editarProduto();
                break;
        }
    }

    private void menuProcurarProduto() {
        System.out.println("Pesquisar por nome: ");
        String nome = scanner.next();
        ArrayList<Produto> produtosEncontrados = busca.buscaProdutoPorNome(nome);
        produtosEncontrados.forEach((produto2) -> {
            System.out.println("Cód: " + produto2.getCodigo()
                    + " | Nome: " + produto2.getNome()
                    + " | Preço: " + produto2.getPreco());
        });
        menuColocarNoCarrinho(produtosEncontrados);
    }
    
    private void menuColocarNoCarrinho(ArrayList<Produto> lista) {
        System.out.println("Deseja colocar algum produto encontrado no carrinho? (S/N)");
        String resposta = scanner.next();
        if (resposta.equalsIgnoreCase("S")) {
            System.out.println("Insira o codigo do produto desejado.");
            resposta = scanner.next();
            Produto produtoDesejado = busca.buscaProdutoPorCodigo(Integer.parseInt(resposta));
            carrinho.adicionarProduto(produtoDesejado);
            System.out.println(produtoDesejado.getNome() + " adicionado com sucesso!");
        } else {
            System.out.println("Voltando ao Menu.");
        }
        System.out.println("  -----------------------------------  ");
    }
    
    private void visualizarCarrinho(){        
        System.out.println(this.carrinho.mostrarProdutos());
    }
    
    private void visualizarProdutos() {
        bd.getProdutos().forEach((produto) -> {
            System.out.println(produto.toString());
        });
                
    }
}

