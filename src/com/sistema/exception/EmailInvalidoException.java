package com.sistema.exception;

public class EmailInvalidoException extends RuntimeException {
    public EmailInvalidoException(String email) {
        super("E-mail invalido: " + email);
    }
}