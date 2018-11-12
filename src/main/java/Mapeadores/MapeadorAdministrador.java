package Mapeadores;

import modelo.Administrador;
import modelo.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MapeadorAdministrador {

    private Connection con;

    public  MapeadorAdministrador(Connection con) {
        this.con = con;
    }

    public void put(Administrador administrador) throws SQLException {
        if (this.get(administrador.getLogin())!=null) {
            atualizaAdministradorExistente(administrador);
        }
        else {
            insereNovoAdministrador(administrador);
        }
    }

    private void insereNovoAdministrador(Administrador administrador) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("INSERT INTO ADMINISTRADOR (LOGIN, SENHA)" +
                "VALUES (?,?)");
        try {
            stmt.setString(1, administrador.getLogin());
            stmt.setString(2, administrador.getSenha());

            stmt.execute();
        } finally {
            stmt.close();
        }
    }

    private void atualizaAdministradorExistente(Administrador administrador) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("UPDATE ADMINISTRADOR SET LOGIN = ?, SENHA =? " +
                "WHERE LOGIN = ?");
        try {
            stmt.setString(1, administrador.getLogin());
            stmt.setString(2, administrador.getSenha());
            stmt.setString(3, administrador.getLogin());
            stmt.execute();
        } finally {
            stmt.close();
        }
    }

    public Administrador get(String login) throws SQLException {
        Administrador administrador;
        PreparedStatement stmt = con.prepareStatement("SELECT LOGIN, SENHA "+
                "FROM ADMINISTRADOR WHERE LOGIN  = ?");
        stmt.setString(1, login);
        ResultSet rs = stmt.executeQuery();

        try {
            if (rs.next()) {
                String senha = rs.getString("SENHA");
                administrador = new Administrador(login, senha);
                return administrador;
            } else {
                return null;
            }
        } finally {
            rs.close();
            stmt.close();
        }
    }

    public void remove(String login) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("DELETE FROM ADMINISTRADOR WHERE LOGIN=?");
        stmt.setString(1, login);
        stmt.execute();
        stmt.close();
    }
}
