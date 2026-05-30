package com.sistema.service;

import com.sistema.dao.ProdutoDAO;
import com.sistema.exception.EntidadeNaoEncontradaException;
import com.sistema.exception.ValidacaoException;
import com.sistema.model.Categoria;
import com.sistema.model.Produto;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class ProdutoService {
    
    private final ProdutoDAO produtoDAO = new ProdutoDAO();
        public Produto cadastrar(String nome, BigDecimal preco, int qtdEstoque, Categoria categoria) throws SQLException {
        validar(nome, preco, qtdEstoque);
        return produtoDAO.salvar(new Produto(nome.trim(), preco, qtdEstoque, categoria));
    }

        public Produto buscarPorId(long id) throws SQLException {
        return produtoDAO.buscarPorId(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Produto", id));
    }
}