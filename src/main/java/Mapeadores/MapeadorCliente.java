package Mapeadores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import modelo.Cliente;
import modelo.Endereco;


/**
 *
 * @author nathancezar
 */
public class MapeadorCliente {

    private Connection con;
    private MapeadorEndereco mapeadorEndereco;
    
    public MapeadorCliente(Connection con) {
        this.con = con;
        this.mapeadorEndereco = new MapeadorEndereco(con);
    }

    public void put(Cliente cliente) throws SQLException {
        if (this.get(cliente.getCpf()) != null) {
            atualizaClienteExistente(cliente);
        } else {
            insereNovoCliente(cliente);
        }
    }

    private void insereNovoCliente(Cliente cliente) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("INSERT INTO CLIENTE (NOME, CPF, ENDERECO, SENHA)"
                + "VALUES (?,?,?,?)");
        try {
            stmt.setString(1, cliente.getNome());
            stmt.setInt(2, cliente.getCpf()); 
            if (cliente.getEndereco() == null) {
                stmt.setNull(3, Types.INTEGER);
            } else {
                stmt.setInt(3, cliente.getEndereco().getCodigo());
            }            
            
            stmt.setString(4, cliente.getSenha());
            stmt.execute();
        } finally {
            stmt.close();
        }
    }

    private void atualizaClienteExistente(Cliente cliente) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("UPDATE CLIENTE SET NOME = ?, SENHA = ? "
                + "WHERE CPF = ?");
        try {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getSenha());
            stmt.setInt(3, cliente.getCpf());           
            stmt.execute();
        } finally {
            stmt.close();
        }
    }

    public Cliente get(int cpf) throws SQLException {
        Cliente cliente;
        PreparedStatement stmt = con.prepareStatement("SELECT NOME, CPF, ENDERECO, SENHA "
                + "FROM CLIENTE WHERE CPF = ?");
        stmt.setInt(1, cpf);
        ResultSet rs = stmt.executeQuery();

        try {
            if (rs.next()) {
                String nome = rs.getString("NOME");
                int cpf_ = rs.getInt("CPF");
                String senha = rs.getString("SENHA");
                Endereco endereco_ = mapeadorEndereco.get(rs.getInt("ENDERECO"));
                cliente = new Cliente(nome, cpf_, endereco_, senha);
                return cliente;
            } else {
                return null;
            }
        } finally {
            rs.close();
            stmt.close();
        }
    }

    public void remove(int cpf) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("DELETE FROM Cliente WHERE CPF = ?");
        stmt.setInt(1, cpf);
        stmt.execute();
        stmt.close();
    }

}
