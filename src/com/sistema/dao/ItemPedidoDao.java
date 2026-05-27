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
