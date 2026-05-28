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

                            } catch (Exception e) {
                conn.rollback();
                throw e;
            } finally {
                conn.setAutoCommit(true);
            }
        }
    }

    public Optional<Pedido> buscarPorId(long id) throws SQLException {
        String sql = "SELECT p.id, p.cliente_id, p.status, p.criado_em, " +
                     "c.nome, c.email, c.criado_em AS cli_criado " +
                     "FROM pedidos p JOIN clientes c ON c.id = p.cliente_id WHERE p.id = ?";

    try (Connection conn = ConnectionUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Pedido base = mapear(rs);
                List<ItemPedido> itens = itemPedidoDAO.listarPorPedido(base.getId());
                return Optional.of(new Pedido(base.getId(), base.getCliente(), base.getStatus(), base.getCriadoEm(), itens));
            }
        }
        return Optional.empty();
    }

    public List<Pedido> listarTodos() throws SQLException {
        String sql = "SELECT p.id, p.cliente_id, p.status, p.criado_em, " +
                     "c.nome, c.email, c.criado_em AS cli_criado " +
                     "FROM pedidos p JOIN clientes c ON c.id = p.cliente_id ORDER BY p.criado_em DESC";

        List<Pedido> lista = new ArrayList<>();

                try (Connection conn = ConnectionUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Pedido base = mapear(rs);
                List<ItemPedido> itens = itemPedidoDAO.listarPorPedido(base.getId());
                lista.add(new Pedido(base.getId(), base.getCliente(), base.getStatus(), base.getCriadoEm(), itens));
            }
        }
        return lista;
    }

    public List<Pedido> listarPorCliente(long clienteId) throws SQLException {
        String sql = "SELECT p.id, p.cliente_id, p.status, p.criado_em, " +
                     "c.nome, c.email, c.criado_em AS cli_criado " +
                     "FROM pedidos p JOIN clientes c ON c.id = p.cliente_id " +
                     "WHERE p.cliente_id = ? ORDER BY p.criado_em DESC";

        List<Pedido> lista = new ArrayList<>();

        try (Connection conn = ConnectionUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, clienteId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Pedido base = mapear(rs);
                List<ItemPedido> itens = itemPedidoDAO.listarPorPedido(base.getId());
                lista.add(new Pedido(base.getId(), base.getCliente(), base.getStatus(), base.getCriadoEm(), itens));
            }
        }
        return lista;
    }

        public boolean atualizarStatus(long pedidoId, StatusPedido status) throws SQLException {
        String sql = "UPDATE pedidos SET status = ? WHERE id = ?";

        try (Connection conn = ConnectionUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, status.name());
            ps.setLong(2, pedidoId);
            return ps.executeUpdate() > 0;
        }
    }

    public long reservarProximoPedidoDaFila(Connection conn) throws SQLException {
        String sqlUpdate = "UPDATE pedidos SET status = 'PROCESSANDO' " +
                           "WHERE id = (SELECT id FROM (SELECT id FROM pedidos WHERE status = 'FILA' ORDER BY criado_em LIMIT 1) AS sub)";

                                   try (PreparedStatement ps = conn.prepareStatement(sqlUpdate)) {
            if (ps.executeUpdate() == 0) return -1L;
        }

        String sqlSelect = "SELECT id FROM pedidos WHERE status = 'PROCESSANDO' ORDER BY criado_em LIMIT 1";
        try (PreparedStatement ps = conn.prepareStatement(sqlSelect);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getLong("id");
        }
        return -1L;
    }