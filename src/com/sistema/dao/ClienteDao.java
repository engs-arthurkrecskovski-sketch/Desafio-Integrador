package com.sistema.dao;

public class ClienteDAO {

}

import com.sistema.model.Cliente;
import com.sistema.util.ConnectionUtil;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public Cliente salvar(Cliente cliente) throws SQLException {
    }

    String sql = "INSERT INTO clientes (nome, email) VALUES (?, ?)";

    try (Connection conn = ConnectionUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getEmail());
            ps.executeUpdate();

            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) {
                return new Cliente(keys.getLong(1), cliente.getNome(), cliente.getEmail(), LocalDateTime.now());
            }

            }
        throw new SQLException("Erro ao salvar cliente.");

        public Optional<Cliente> buscarPorId(long id) throws SQLException {
    }

    String sql = "SELECT id, nome, email, criado_em FROM clientes WHERE id = ?";

        try (Connection conn = ConnectionUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return Optional.of(mapear(rs));
        }
        return Optional.empty();

        public Optional<Cliente> buscarPorEmail(String email) throws SQLException {
        String sql = "SELECT id, nome, email, criado_em FROM clientes WHERE email = ?";

        try (Connection conn = ConnectionUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return Optional.of(mapear(rs));
        }
        return Optional.empty();
    }

    public List<Cliente> listarTodos() throws SQLException {
        String sql = "SELECT id, nome, email, criado_em FROM clientes ORDER BY nome";
        List<Cliente> lista = new ArrayList<>();
    }

    try (Connection conn = ConnectionUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;

        public boolean atualizar(Cliente cliente) throws SQLException {
        String sql = "UPDATE clientes SET nome = ?, email = ? WHERE id = ?";
    }

    try (Connection conn = ConnectionUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getEmail());
            ps.setLong(3, cliente.getId());
            return ps.executeUpdate() > 0;
        }

        public boolean deletar(long id) throws SQLException {
        String sql = "DELETE FROM clientes WHERE id = ?";
    }

    try (Connection conn = ConnectionUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        }

        private Cliente mapear(ResultSet rs) throws SQLException {
    }