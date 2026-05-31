package com.sistema.service;

import com.sistema.dao.RelatorioDAO;

import java.sql.SQLException;
import java.util.List;

public class RelatorioService {
    
    private final RelatorioDAO relatorioDAO = new RelatorioDAO();

    public List<String> topClientes(int limite) throws SQLException{
        return relatorioDAO.relatorioTopClientes(limite);
    }

    public List<String> produtosMaisVendidos(int limite) throws SQLException{
        return relatorioDAO.relatorioProdutosMaisVendidos(limite);
    }

    public List<String> pedidosPorStatus() throws SQLException {
        return relatorioDAO.relatorioPedidosporStatus();
    }

    public List<String> estoqueCritico(int limiteEstoque) throws SQLException {
        return relatorioDAO.relatorioEstoqueCritico(limiteEstoque);
    }
}
