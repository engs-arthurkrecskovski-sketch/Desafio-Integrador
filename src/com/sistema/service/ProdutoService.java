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

        public List<Produto> listarTodos() throws SQLException {
        return produtoDAO.listarTodos();
    }
        public List<Produto> listarPorCategoria(Categoria categoria) throws SQLException {
        return produtoDAO.listarPorCategoria(categoria);
    }

       public Produto atualizar(long id, String nome, BigDecimal preco, int qtdEstoque, Categoria categoria) throws SQLException {
        buscarPorId(id);
        validar(nome, preco, qtdEstoque);
        Produto atualizado = new Produto(id, nome.trim(), preco, qtdEstoque, categoria, java.time.LocalDateTime.now());
        produtoDAO.atualizar(atualizado);
        return atualizado;
    }
    
    
    public void deletar(long id) throws SQLException {
        buscarPorId(id);
        produtoDAO.deletar(id);
    }

        private void validar(String nome, BigDecimal preco, int qtdEstoque) {
        if (nome == null || nome.trim().isEmpty())
            throw new ValidacaoException("Nome do produto nao pode ser vazio.");
}