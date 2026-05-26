package com.sistema.exception;

public class EntidadeNaoEncontrada extends RuntimeException {
    public EntidadeNaoEncontrada(String entidade, long id) {
        super(entidade + " nao encontrado(a) com id=" + id);
    }
}