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