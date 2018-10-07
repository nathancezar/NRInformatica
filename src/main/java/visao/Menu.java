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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;
import modelo.Carrinho;
import modelo.Servico;

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
    DecimalFormat df = new DecimalFormat("#.00");

    public void menuInicial() {

        System.out.println("\nSeja bem-vindo a NR Informática");
        System.out.println("");
        System.out.println("Veja nossos destaques:");
        System.out.println(busca.mostraProdutosDaLista(busca.buscaRandomicaDeProdutos(3)));
        System.out.println("--");
        System.out.println("1 - Ver todos os produtos");
        System.out.println("2 - Procurar produtos");
        System.out.println("3 - Inserir um Produto no carrinho");
        System.out.println("4 - Inserir um Serviço no carrinho");
        System.out.println("5 - Visualizar carrinho");
        System.out.println("6 - Entrar como administrador");
        System.out.println("0 - Sair");

        int escolha = scanner.nextInt();

        switch (escolha) {
            case 1:
                visualizarProdutos();
                menuInicial();
            case 2:
                menuProcurarProduto();
                menuInicial();
            case 4:
                menuComprarServico();
                menuInicial();
            case 3:
                menuColocarNoCarrinhoPorCodigo();
                menuInicial();
            case 5:
                visualizarCarrinho();
                menuInicial();
            case 6:
                menuAdmLogin();
                menuInicial();
            case 0:
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
        System.out.println("1 - Produtos");
        System.out.println("2 - Serviços");
        System.out.println("0 - Sair");

        int escolha = scanner.nextInt();

        switch (escolha) {
            case 1:
                menuAdmProdutos();
                menuAdm();
            case 2:
                menuAdmServicos();
                menuAdm();
            case 0:
                menuInicial();
        }
    }

    private void menuAdmProdutos() {
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
                menuAdmProdutos();
                break;
            case 2:
                editarProduto();
                menuAdmProdutos();
                break;
            case 3:
                menuExcluirProduto();
                System.out.println("Produto removido com sucesso");
                menuAdmProdutos();
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
                    menuAdmProdutos();
                } else {
                    System.out.println("Produto não removido");
                    menuAdmProdutos();
                }
            } else {
                System.out.println("Produto não encontrado!");
                menuAdmProdutos();
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
                    + " | Preço: " + df.format(produto2.getPreco()));
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

    private void menuAdmServicos() {
        System.out.println("----AREA ADMINISTRATIVA----");
        System.out.println("");
        System.out.println("1 - Cadastrar serviço");
        System.out.println("2 - Editar serviço");
        System.out.println("3 - Excluir serviço");
        System.out.println("0 - Voltar ao menu principal");

        int escolha = scanner.nextInt();

        switch (escolha) {
            case 1:
                menuCadastrarServico();
                System.out.println("Serviço cadastrado com sucesso!");
        }
    }

    private void menuCadastrarServico() {
        System.out.println("Digite o código do servico.");
        int codigo = scanner.nextInt();

        System.out.println("Digite o nome do serviço.");
        System.out.print("");
        String nomeServico = scanner.next();

        System.out.println("Digite a quantidade de datas/horários que deseja cadastrar");
        int qtdHorarios = scanner.nextInt();

        ArrayList<String> horarios = new ArrayList<>();
        for (int i = 0; i < qtdHorarios; i++) {
            System.out.println("Digite a "+i+"ª data/horário (d/m/a h:m)");
            String horario = scanner.next();
            horarios.add(horario);
        }

        System.out.println("Digite o valor do serviço.");
        float preco = scanner.nextFloat();

        cadastro.criarNovoServico(codigo, nomeServico, horarios, preco);
        System.out.println("Serviço "+nomeServico + " criado com sucesso.");
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
                    + " | Preço: " + df.format(produto2.getPreco()));
        });
        menuColocarNoCarrinho(produtosEncontrados);
    }
    
    // Insere um produto no carrinho, verificando se o codigo passado é válido
    private void menuColocarNoCarrinhoPorCodigo() {
        System.out.println("Digite o codigo do produto desejado");
        String resposta = scanner.next();
        if (busca.verificaSeCodigoExiste(Integer.parseInt(resposta))) {            
            Produto produtoDesejado = busca.buscaProdutoPorCodigo(Integer.parseInt(resposta));
            System.out.println("Quantos " + produtoDesejado.getNome() + " gostaia de comprar? ");
            resposta = scanner.next();
            carrinho.adicionarProduto(produtoDesejado, Integer.parseInt(resposta));
            System.out.println(produtoDesejado.getNome() + " adicionado com sucesso!");
        } else {
            System.out.println("Código incorreto.");
        }
        System.out.println("  -----------------------------------  ");
    }
    
    
    private void menuColocarNoCarrinho(ArrayList<Produto> lista) {
        System.out.println("Deseja colocar algum produto encontrado no carrinho? (S/N)");
        String resposta = scanner.next();
        if (resposta.equalsIgnoreCase("S")) {
            System.out.println("Insira o codigo do produto desejado.");
            resposta = scanner.next();
            Produto produtoDesejado = busca.buscaProdutoPorCodigo(Integer.parseInt(resposta));
            System.out.println("Quantos " + produtoDesejado.getNome() + " gostaia de comprar? ");
            resposta = scanner.next();
            carrinho.adicionarProduto(produtoDesejado, Integer.parseInt(resposta));
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

    private void menuComprarServico() {
        System.out.println("1 - Ver serviços disponíveis.");
        System.out.println("2 - Agendar serviço.");
        System.out.println("0 - Voltar ao Menu Inicial");

        int escolha = scanner.nextInt();

        switch (escolha) {
            case 1:
                verServicosDisponiveis();
                menuComprarServico();
            case 2:
                menuAgendarServico();
                menuComprarServico();
            case 0:
                menuInicial();
        }
    }

    private void verServicosDisponiveis() {
       bd.getServicos().forEach(servico -> {
           System.out.println(servico.toString());
       });
    }

    private void menuAgendarServico() {
        System.out.println("Digite o código do serviço desejado:");
        int codigoServico = scanner.nextInt();
        Servico servicoDesejado = busca.buscaServicoPorCodigo(codigoServico);
        String dataDesejada = selecionarDataServico(servicoDesejado);
        carrinho.adicionarServicoNoCarrinho(servicoDesejado, dataDesejada);
    }

    private String selecionarDataServico(Servico servico) {
        System.out.println("Escolha a opção da data desejada");
        System.out.println("----------------------------------");

        for (int i = 1; i <= servico.getDatas().size(); i++) {
            System.out.println("Opção "+i+ " - "+servico.getDatas().get(i-1));
        }

        System.out.println("----------------------------------");
        int escolha = scanner.nextInt();
        String data = servico.getDatas().get(escolha-1);
        return data;

    }
}


