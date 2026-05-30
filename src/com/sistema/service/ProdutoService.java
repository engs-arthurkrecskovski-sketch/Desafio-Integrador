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
}