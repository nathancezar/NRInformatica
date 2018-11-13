
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
        if (this.get(endereco.getCodigo())!=null) {
            atualizaEnderecoExistente(endereco);
        }
        else {
            insereNovoEndereco(endereco);
        }
    }

    private void insereNovoEndereco(Endereco endereco) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("INSERT INTO ENDERECO (CODIGO, ESTADO, CIDADE, BAIRRO, RUA, COMPLEMENTO, NUMERO)" +
                "VALUES (?,?,?,?,?,?,?)");
        try {
            stmt.setInt(1, endereco.getCodigo());
            stmt.setString(2, endereco.getEstado());
            stmt.setString(3, endereco.getCidade());
            stmt.setString(4, endereco.getBairro());
            stmt.setString(5, endereco.getRua());
            stmt.setString(6, endereco.getComplemento());
            stmt.setInt(7, endereco.getNumero());
            stmt.execute();
        } finally {
            stmt.close();
        }
    }

    private void atualizaEnderecoExistente(Endereco endereco) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("UPDATE ENDERECO SET ESTADO = ?, CIDADE =? " +
                "BAIRRO = ?, RUA = ?, COMPLEMENTO = ?, NUMERO = ? WHERE CODIGO = ?");
        try {
            stmt.setString(1, endereco.getEstado());
            stmt.setString(2, endereco.getCidade());
            stmt.setString(3, endereco.getBairro());
            stmt.setString(4, endereco.getRua());
            stmt.setString(5, endereco.getComplemento());
            stmt.setInt(6, endereco.getNumero());
            stmt.setInt(7, endereco.getCodigo());
            stmt.execute();
        } finally {
            stmt.close();
        }
    }

    public Endereco get(int codigo) throws SQLException {
        Endereco endereco;
        PreparedStatement stmt = con.prepareStatement("SELECT CIDADE, ESTADO, BAIRRO, RUA, COMPLEMENTO, NUMERO "+
                "FROM ENDERECO WHERE CODIGO = ?");
        stmt.setInt(1, codigo);
        ResultSet rs = stmt.executeQuery();

        try {
            if (rs.next()) {
                String estado = rs.getString("ESTADO");
                String cidade = rs.getString("CIDADE");
                String bairro = rs.getString("BAIRRO");
                String rua = rs.getString("RUA");
                String complemento = rs.getString("COMPLEMENTO");
                int numero = rs.getInt("NUMERO");
                endereco = new Endereco(codigo, cidade, estado, bairro, rua, complemento, numero);
                return endereco;
            } else {
                return null;
            }
        } finally {
            rs.close();
            stmt.close();
        }
    }

    public void remove(int codigo) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("DELETE FROM ENDERECO WHERE CODIGO = ?");
        stmt.setInt(1, codigo);
        stmt.execute();
        stmt.close();
    }


}
