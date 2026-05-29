package com.sistema.dao;

import com.sistema.util.ConectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RelatorioDAO {
    
     public List<String> relatorioTopClientes(int limite) throws SQLException {
        String sql = "SELECT c.nome, COUNT(p.id) AS total_pedidos, " +
            "SUM(ip.quantidade * ip.preco_unit) AS valor_total " +
            "FROM clientes c " +

}