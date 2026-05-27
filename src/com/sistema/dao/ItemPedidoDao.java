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

    