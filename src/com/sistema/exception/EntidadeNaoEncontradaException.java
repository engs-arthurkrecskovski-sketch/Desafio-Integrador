package com.sistema.exception;

public class EntidadeNaoEncontradaException extends RuntimeException {
    public EntidadeNaoEncontradaException(String entidade, long id) {
        super(entidade + " nao encontrado(a) com id=" + id);
    }
}