package com.sistema.dao;

import com.sistema.model.Categoria;
import com.sistema.model.ItemPedido;
import com.sistema.model.Produto;
import com.sistema.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemPedidoDAO {

    public void salvar(Connection conn, long pedidoId, ItemPedido item) throws SQLException {
        String sql = "INSERT INTO itens_pedido (pedido_id, produto_id, quantidade, preco_unit) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, pedidoId);
            ps.setLong(2, item.getProduto().getId());
            ps.setInt(3, item.getQuantidade());
            ps.setBigDecimal(4, item.getPrecoUnit());
            ps.executeUpdate();
        }
    }

    public List<ItemPedido> listarPorPedido(long pedidoId) throws SQLException {
        String sql = "SELECT ip.id, ip.pedido_id, ip.produto_id, ip.quantidade, ip.preco_unit, " +
                     "p.nome, p.preco, p.quantidade_estoque, p.categoria, p.criado_em " +
                     "FROM itens_pedido ip JOIN produtos p ON p.id = ip.produto_id " +
                     "WHERE ip.pedido_id = ?";

        List<ItemPedido> lista = new ArrayList<>();

        try (Connection conn = ConnectionUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, pedidoId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

     private ItemPedido mapear(ResultSet rs) throws SQLException {
        Produto produto = new Produto(
            rs.getLong("produto_id"),
            rs.getString("nome"),
            rs.getBigDecimal("preco"),
            rs.getInt("quantidade_estoque"),
            Categoria.fromString(rs.getString("categoria")),
            rs.getTimestamp("criado_em").toLocalDateTime()
        );
         return new ItemPedido(
            rs.getLong("id"),
            rs.getLong("pedido_id"),
            produto,
            rs.getInt("quantidade"),
            rs.getBigDecimal("preco_unit")
        );
    }
}

