package com.sistema.dao;

import com.sistema.exception.EstoqueInsuficienteException;
import com.sistema.model.*;
import com.sistema.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PedidoDAO {
    private final ClienteDAO clienteDAO = new ClienteDAO();
    private final ItemPedidoDAO itemPedidoDAO = new ItemPedidoDAO();
    private final ProdutoDAO produtoDAO = new ProdutoDAO();

        public Pedido salvar(Pedido pedido) throws SQLException {
        String sql = "INSERT INTO pedidos (cliente_id, status) VALUES (?, ?)";

        try (Connection conn = ConnectionUtil.getConnection()) {
            conn.setAutoCommit(false);
            try {
                long pedidoId;

                try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                    ps.setLong(1, pedido.getCliente().getId());
                    ps.setString(2, pedido.getStatus().name());
                    ps.executeUpdate();

                    ResultSet keys = ps.getGeneratedKeys();
                    if (!keys.next()) throw new SQLException("Erro ao gerar ID do pedido.");
                    pedidoId = keys.getLong(1);
                }

                                for (ItemPedido item : pedido.getItens()) {
                    boolean ok = produtoDAO.decrementarEstoque(conn, item.getProduto().getId(), item.getQuantidade());
                    if (!ok) {
                        throw new EstoqueInsuficienteException(
                                item.getProduto().getNome(),
                                item.getQuantidade(),
                                item.getProduto().getQuantidadeEstoque());
                    }
                    itemPedidoDAO.salvar(conn, pedidoId, item);
                }

                                conn.commit();

                List<ItemPedido> itensSalvos = itemPedidoDAO.listarPorPedido(pedidoId);
                return new Pedido(pedidoId, pedido.getCliente(), pedido.getStatus(), pedido.getCriadoEm(), itensSalvos);