
package Mapeadores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import modelo.Endereco;
import modelo.Produto;

public class MapeadorEndereco {

    private Connection con;

    public MapeadorEndereco(Connection con) {
        this.con = con;
    }

    public void put(Endereco endereco) throws SQLException {
        if (this.get(endereco.getCidade(), endereco.getEstado())!=null) {
            atualizaEnderecoExistente(endereco);
        }
        else {
            insereNovoEndereco(endereco);
        }
    }

    private void insereNovoEndereco(Endereco endereco) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("INSERT INTO ENDERECO (ESTADO, CIDADE, BAIRRO, RUA, COMPLEMENTO, NUMERO)" +
                "VALUES (?,?,?,?,?,?)");
        try {
            stmt.setString(1, endereco.getEstado());
            stmt.setString(2, endereco.getCidade());
            stmt.setString(3, endereco.getBairro());
            stmt.setString(4, endereco.getRua());
            stmt.setString(5, endereco.getComplemento());
            stmt.setInt(6, endereco.getNumero());
            stmt.execute();
        } finally {
            stmt.close();
        }
    }

    private void atualizaEnderecoExistente(Endereco endereco) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("UPDATE ENDERECO SET ESTADO = ?, CIDADE =? " +
                "BAIRRO = ?, RUA = ?, COMPLEMENTO = ?, NUMERO = ? WHERE CIDADE = ? AND ESTADO = ?");
        try {
            stmt.setString(1, endereco.getEstado());
            stmt.setString(2, endereco.getCidade());
            stmt.setString(3, endereco.getBairro());
            stmt.setString(4, endereco.getRua());
            stmt.setString(5, endereco.getComplemento());
            stmt.setInt(6, endereco.getNumero());
            stmt.setString(7, endereco.getCidade());
            stmt.setString(8, endereco.getEstado());
            stmt.execute();
        } finally {
            stmt.close();
        }
    }

    public Endereco get(String cidade, String estado) throws SQLException {
        Endereco endereco;
        PreparedStatement stmt = con.prepareStatement("SELECT CIDADE, ESTADO, BAIRRO, RUA, COMPLEMENTO, NUMERO "+
                "FROM ENDERECO WHERE CIDADE  = ? AND ESTADO = ?");
        stmt.setString(1, cidade);
        stmt.setString(2, estado);
        ResultSet rs = stmt.executeQuery();

        try {
            if (rs.next()) {
                String bairro = rs.getString("BAIRRO");
                String rua = rs.getString("RUA");
                String complemento = rs.getString("COMPLEMENTO");
                int numero = rs.getInt("NUMERO");
                endereco = new Endereco(cidade, estado, bairro, rua, complemento, numero);
                return endereco;
            } else {
                return null;
            }
        } finally {
            rs.close();
            stmt.close();
        }
    }

    public void remove(String cidade, String estado) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("DELETE FROM ENDERECO WHERE CIDADE=? AND ESTADO = ?");
        stmt.setString(1, cidade);
        stmt.setString(2, estado);
        stmt.execute();
        stmt.close();
    }


}
