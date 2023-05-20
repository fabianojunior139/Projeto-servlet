package com.br.loja.Dao;

import com.br.loja.Model.Produto;

import java.sql.*;
import java.util.ArrayList;

public class ProdutoDAO {
    private Connection conn;

    private final String url = "jdbc:mysql://127.0.0.1:3306/projeto1?useTimezone=true&serverTimezone=UTC";
    private final String user = "root";
    private final String password = "";

    //Método responsável por conectar com a base de dados
    public Connection conectar() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }

    //Método responsável por cadastrar novos produtos na base de dados
    public void inserirProduto(Produto produto) throws SQLException {
        String sql = "INSERT INTO produto (codigo, nome, categoria, valor, quantidade) VALUES (?,?,?,?,?)";
        try {
            Connection conn = conectar();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setLong(1, produto.getCodigo());
            pst.setString(2, produto.getNome());
            pst.setString(3, produto.getCategoria());
            pst.setDouble(4, produto.getValor());
            pst.setInt(5, produto.getQuantidade());

            pst.executeUpdate();
            conn.close();
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }

    //Método responsável por alterar um produto na base de dados
    public void alterarProduto(Produto produto) throws SQLException {
        String sql = "UPDATE produto SET codigo = ?, nome = ?, categoria = ? , valor = ?, quantidade = ? WHERE id = ?";
        try {
            Connection conn = conectar();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setLong(1, produto.getCodigo());
            pst.setString(2, produto.getNome());
            pst.setString(3, produto.getCategoria());
            pst.setDouble(4, produto.getValor());
            pst.setInt(5, produto.getQuantidade());
            pst.setLong(6, produto.getId());
            pst.executeUpdate();
            conn.close();
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }

    //Método responsável por remover um produto da base de dados
    public void removerProduto(Long id) {
        String sql = "DELETE FROM produto WHERE id = ?";
        try {
            Connection conn = conectar();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setDouble(1, id);
            pst.executeUpdate();
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //Método responsável por selecionar produto por ID
    public Produto selecionarProdutoPorId(Long id) throws SQLException {
        String sql = "SELECT * FROM produto WHERE id = ?";
        try {
            Connection conn = conectar();
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();

            Produto produto = new Produto();

            while (rs.next()) {
                produto.setId(rs.getLong(1));
                produto.setCodigo(rs.getLong(2));
                produto.setNome(rs.getString(3));
                produto.setCategoria(rs.getString(4));
                produto.setValor(rs.getDouble(5));
                produto.setQuantidade(rs.getInt(6));
            }
            conn.close();
            return produto;
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }

    //Criando lista para armazenar todos os produtos cadastrados na base de dados
    public ArrayList<Produto> ListarTodosOsProdutos() throws SQLException {
        ArrayList<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produto";

        try {
            Connection conn = conectar();
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Long id = rs.getLong(1);
                Long codigo = rs.getLong(2);
                String nome = rs.getString(3);
                String categoria = rs.getString(4);
                Double valor = rs.getDouble(5);
                int quantidade = rs.getInt(6);
                produtos.add(new Produto(id, codigo, nome, categoria, valor, quantidade));
            }
            conn.close();
            return produtos;
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }

}
