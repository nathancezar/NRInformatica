package Mapeadores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import modelo.Produto;

public class MapeadorProduto {

    private Connection con;

    public MapeadorProduto(Connection con) {
        this.con = con;
    }

    public void put(Produto produto) throws SQLException {
        if (this.get(produto.getCodigo())!=null) {
            atualizaProdutoExistente(produto);
        }
        else {
            insereNovoProduto(produto);
        }
    }

    private void insereNovoProduto(Produto produto) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("INSERT INTO PRODUTO (CODIGO, QUANTIDADE, PRECO, NOME, ENDIMG, DESCRICAO)" +
                "VALUES (?,?,?,?,?,?)");
            try {
                stmt.setInt(1, produto.getCodigo());
                stmt.setInt(2, produto.getQuantidade());
                stmt.setFloat(3, produto.getPreco());
                stmt.setString(4, produto.getNome());
                stmt.setString(5, produto.getEndereco_imagem());
                stmt.setString(6, produto.getDescricao());
                stmt.execute();
            } finally {
                stmt.close();
            }
    }

    private void atualizaProdutoExistente(Produto produto) throws SQLException {
            PreparedStatement stmt = con.prepareStatement("UPDATE PRODUTO SET CODIGO = ?, QUANTIDADE =? " +
                "PRECO = ?, NOME = ?, ENDIMG = ?, DESCRICAO = ? WHERE CODIGO = ?");
            try {
                stmt.setInt(1, produto.getCodigo());
                stmt.setInt(2, produto.getQuantidade());
                stmt.setFloat(3, produto.getPreco());
                stmt.setString(4, produto.getNome());
                stmt.setString(5, produto.getEndereco_imagem());
                stmt.setString(6, produto.getDescricao());
                stmt.setInt(7, produto.getCodigo());
                stmt.execute();
            } finally {
                stmt.close();
            }
    }

    public Produto get(int codigo) throws SQLException {
        Produto produto;
        PreparedStatement stmt = con.prepareStatement("SELECT CODIGO, QUANTIDADE, PRECO, NOME, ENDIMG, DESCRICAO "+
                "FROM PRODUTO WHERE CODIGO  = ?");
        stmt.setInt(1, codigo);
        ResultSet rs = stmt.executeQuery();

        try {
            if (rs.next()) {
                int quantidade = rs.getInt("QUANTIDADE");
                float preco = rs.getFloat("PRECO");
                String nome = rs.getString("NOME");
                String endimg = rs.getString("ENDIMG");
                String descricao = rs.getString("DESCRICAO");
                produto = new Produto(codigo, quantidade, preco, nome, endimg, descricao);
                return produto;
            } else {
                return null;
            }
        } finally {
            rs.close();
            stmt.close();
        }
    }

    public void remove(int codigo) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("DELETE FROM PRODUTO WHERE CODIGO=?");
        stmt.setInt(1, codigo);
        stmt.execute();
        stmt.close();
    }


}
