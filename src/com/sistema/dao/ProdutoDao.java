package com.sistema.dao;

import com.sistema.model.Categoria;
import com.sistema.model.Produto;
import com.sistema.util.ConnectionUtil;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProdutoDAO {

    public Produto salvar(Produto produto) throws SQLException {
        String sql = "INSERT INTO produtos (nome, preco, quantidade_estoque, categoria) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, produto.getNome());
            ps.setBigDecimal(2, produto.getPreco());
            ps.setInt(3, produto.getQuantidadeEstoque());
            ps.setString(4, produto.getCategoria().name());
            ps.executeUpdate();

            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) {
                return new Produto(keys.getLong(1), produto.getNome(), produto.getPreco(),
                        produto.getQuantidadeEstoque(), produto.getCategoria(), LocalDateTime.now());
            }
        }
        throw new SQLException("Erro ao salvar produto.");
    }

       public Optional<Produto> buscarPorId(long id) throws SQLException {
        String sql = "SELECT id, nome, preco, quantidade_estoque, categoria, criado_em FROM produtos WHERE id = ?";

        try (Connection conn = ConnectionUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return Optional.of(mapear(rs));
        }
        return Optional.empty();
    }

    public List<Produto> listarTodos() throws SQLException {
        String sql = "SELECT id, nome, preco, quantidade_estoque, categoria, criado_em FROM produtos ORDER BY categoria, nome";
        List<Produto> lista = new ArrayList<>();

        try (Connection conn = ConnectionUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

     public List<Produto> listarPorCategoria(Categoria categoria) throws SQLException {
        String sql = "SELECT id, nome, preco, quantidade_estoque, categoria, criado_em FROM produtos WHERE categoria = ? ORDER BY nome";
        List<Produto> lista = new ArrayList<>();

         try (Connection conn = ConnectionUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, categoria.name());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }