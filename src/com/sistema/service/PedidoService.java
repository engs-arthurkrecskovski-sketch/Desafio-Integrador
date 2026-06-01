package com.sistema.service;


import com.sistema.dao.PedidoDAO;
import com.sistema.dao.ProdutoDAO;
import com.sistema.exception.EntidadeNaoEncontradaException;
import com.sistema.exception.EstoqueInsuficienteException;
import com.sistema.exception.ValidacaoException;
import com.sistema.model.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class PedidoService {
    
 private final PedidoDAO pedidoDAO = new PedidoDAO();
private final ProdutoDAO produtoDAO = new ProdutoDAO();
private final ClienteService clienteService = new ClienteService();

  public Pedido criar(long clienteId, Map<Long, Integer> itensMap) throws SQLException {
        if (itensMap == null || itensMap.isEmpty())
            throw new ValidacaoException("Um pedido deve ter pelo menos um item.");

        Cliente cliente = clienteService.buscarPorId(clienteId);
        List<ItemPedido> itens = new ArrayList<>();

         for (Map.Entry<Long, Integer> entry : itensMap.entrySet()) {
            long produtoId = entry.getKey();
            int qtd = entry.getValue();

            if (qtd <= 0)
                throw new ValidacaoException("Quantidade deve ser maior que zero.");

            Produto produto = produtoDAO.buscarPorId(produtoId)
                    .orElseThrow(() -> new EntidadeNaoEncontradaException("Produto", produtoId));

            if (produto.getQuantidadeEstoque() < qtd)
                throw new EstoqueInsuficienteException(produto.getNome(), qtd, produto.getQuantidadeEstoque());

            itens.add(new ItemPedido(produto, qtd));
        }

        return pedidoDAO.salvar(new Pedido(cliente, itens));
}

 public Pedido buscarPorId(long id) throws SQLException {
        return pedidoDAO.buscarPorId(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Pedido", id));
    }





}
