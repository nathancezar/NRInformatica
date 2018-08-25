/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;
import bancoDeDados.BancoDeDados;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author rafael
 */
public class Menu {
    
    Scanner scanner = new Scanner(System.in);
    Cadastros cadastro = Cadastros.getCadastros();
    BancoDeDados bd = BancoDeDados.getBancoDados();
    ControleDeBusca busca = ControleDeBusca.getControleBusca();
    
    public void menuInicial(){
       
        System.out.println("Seja bem-vindo a NR Informática");
        System.out.println("");
        System.out.println("Veja nossos destaques:");
        // funcao que retorna produtos aleatorios
        System.out.println("--");
        System.out.println("1 - Ver todos os produtos");
        System.out.println("2 - Procurar produtos");
        System.out.println("3 - Visualizar carrinho");
        System.out.println("4 - Entrar como administrador");
        System.out.println("5 - Sair");
        
        int escolha = scanner.nextInt();
        
        switch (escolha) {
            case 1: 
                cadastro.verProdutosCadastrados();
                menuInicial();
            case 2:
                System.out.println("procurar");
                menuInicial();// fazer funcao procurar produtos
            case 3:
                System.out.println("carrinho");
                menuInicial(); // fazer funcao carrinho
            case 4:
                menuAdmLogin(); 
                menuInicial();
            case 5: System.exit(0);
            
        }
    }

    private void menuAdmLogin() {
            System.out.println("Digite seu login");
            String login = scanner.next();
            System.out.println("Digite sua senha");
            String senha = scanner.next();
            if (login.equals(bd.getAdm().getLogin()) && senha.equals(bd.getAdm().getSenha()))
                menuAdm();
            else{
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
            case 1: cadastro.novoProduto(); break;
            case 2: System.out.println("ola"); break; //menuEditarProduto(); // fazer funcoes para editar
            case 3: menuExcluirProduto(); break;
            case 0: menuInicial(); break;
        }
    }
    
    private void menuExcluirProduto() {
        System.out.println("Digite o código do produto que deseja excluir:");
        cadastro.verProdutosCadastrados();
        int codProduto = scanner.nextInt();
        for(int i = 0; i<bd.produtos.length;i++){
            if (bd.produtos[i].getCodigo() == codProduto){
                System.out.println("Você tem certeza que deseja exluir o produto "+bd.produtos[i].getNome()+"? (S/N");
                String escolha = scanner.next();
                if(escolha.equalsIgnoreCase("s")) {
                    cadastro.removerProduto(bd.produtos[i]);
                    System.out.println("Produto removido com sucesso.");
                    menuAdm();
                }
                else {
                    System.out.println("Produto não removido");
                    menuAdm();
                }
            } else {
                System.out.println("Produto não encontrado!");
                menuAdm();            
            }
        }
    }
    
    private void editarProduto(){
        System.out.println("Procurar produto: (nome)");
        String nomeProduto = scanner.next();
        ArrayList<Produto> buscados = busca.buscaProdutoPorNome(nomeProduto);
        for (int i = 0; i<buscados.size();i++){
            
        }
        
    }
    
}