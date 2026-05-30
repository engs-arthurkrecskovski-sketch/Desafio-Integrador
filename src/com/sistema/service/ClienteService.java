package com.sistema.service;

import com.sistema.dao.ClienteDAO;
import com.sistema.exception.EmailInvalidoException;
import com.sistema.exception.EntidadeNaoEncontradaException;
import com.sistema.exception.ValidacaoException;
import com.sistema.model.Cliente;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

public class ClienteService {
    
        private static final Pattern REGEX_EMAIL =
            Pattern.compile("^[\\w.+\\-]+@[a-zA-Z0-9\\-]+\\.[a-zA-Z]{2,}$");

    private final ClienteDAO clienteDAO = new ClienteDAO();
}