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

        public Cliente cadastrar(String nome, String email) throws SQLException {
        validarNome(nome);
        validarEmail(email);
        verificarEmailUnico(email, 0L);
        return clienteDAO.salvar(new Cliente(nome.trim(), email.trim().toLowerCase()));
    }

    public Cliente buscarPorId(long id) throws SQLException {
        return clienteDAO.buscarPorId(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Cliente", id));
    }

        public List<Cliente> listarTodos() throws SQLException {
        return clienteDAO.listarTodos();
    }

    public Cliente atualizar(long id,
        String novoNome,
        String novoEmail) throws SQLException {
        buscarPorId(id);
        validarNome(novoNome);
        validarEmail(novoEmail);
        verificarEmailUnico(novoEmail, id);
        Cliente atualizado = new Cliente(id, novoNome.trim(), novoEmail.trim().toLowerCase(),
                java.time.LocalDateTime.now());
        clienteDAO.atualizar(atualizado);
        return atualizado;
    }

        public void deletar(long id) throws SQLException {
        buscarPorId(id);
        clienteDAO.deletar(id);
    }

        private void validarNome(String nome) {
        if (nome == null || nome.trim().isEmpty())
            throw new ValidacaoException("Nome nao pode ser vazio.");
        if (nome.trim().length() < 2)
            throw new ValidacaoException("Nome deve ter ao menos 2 caracteres.");
    }

       private void validarEmail(String email) {
        if (email == null || email.trim().isEmpty())
            throw new ValidacaoException("E-mail nao pode ser vazio.");
        if (!REGEX_EMAIL.matcher(email.trim()).matches())
            throw new EmailInvalidoException(email);
    }
}