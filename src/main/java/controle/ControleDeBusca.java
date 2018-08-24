/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;
import bancoDeDados.BancoDeDados;
import java.util.ArrayList;

/**
 *
 * @author nathancezar
 */
public class ControleDeBusca {
    
    
    private static ControleDeBusca busca; 
    private BancoDeDados bd;
    
    private ControleDeBusca() {        
    }
    
    public static synchronized ControleDeBusca getControleBusca() {
        if (busca == null) {
            busca = new ControleDeBusca();
        }
        return busca;
    }                            
    
    public ArrayList<Produto> buscaPorNome(String nome) {
        ArrayList<Produto> produtosEncontrados = new ArrayList<>();        
        for (short i = 0; i < bd.getProdutos().length; i++) {
            if (bd.getProdutos()[i].getNome().contains(nome)) {
                produtosEncontrados.add(bd.getProdutos()[i]);
            }                                                         
        }
        return produtosEncontrados;
    }
    
    public Produto buscaPorCodigo(int codigo) {
        for (Produto p1 : bd.getProdutos()) {
            if (p1.getCodigo() == codigo)
                return p1;
        }                
        return null;
    }
    
    public ArrayList<Produto> buscaPorDescricao(String descricao) {
        ArrayList<Produto> produtosEncontrados = new ArrayList<>(); 
        for (short i = 0; i < bd.getProdutos().length; i++) {
            if (bd.getProdutos()[i].getDescricao().contains(descricao)) {
                produtosEncontrados.add(bd.getProdutos()[i]);
            }                                                         
        }
        return produtosEncontrados;
    }
    
    public ArrayList<Produto> buscaPorFaixaDePreco(int min,int max) {
        ArrayList<Produto> produtosEncontrados = new ArrayList<>();
        for (short i = 0; i < bd.getProdutos().length; i++) {
            if (bd.getProdutos()[i].getPreco() > min && 
                    bd.getProdutos()[i].getPreco() < max) {
                produtosEncontrados.add(bd.getProdutos()[i]);
            }
        }        
        return produtosEncontrados;
    }
    
    public ArrayList<Produto> buscaPorNomePreco(String nome, int min, int max) {
        ArrayList<Produto> produtosEncontrados = buscaPorNome(nome);
        produtosEncontrados.removeIf((Produto p2) -> 
                p2.getPreco() > max || p2.getPreco() < min); 
        return produtosEncontrados;
    }
}
