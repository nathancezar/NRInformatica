/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancoDeDados;

import controle.Administrador;
import controle.Cliente;
import controle.Produto;

public final class BancoDeDados {
    
    public Produto[] produtos = new Produto[100];
    public Cliente[] clientes = new Cliente[50];
    public Administrador adm = new Administrador("adm", "adm");      
}


