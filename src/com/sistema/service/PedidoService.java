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



}
