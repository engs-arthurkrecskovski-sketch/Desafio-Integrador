package com.sistema.exception;

public class EmailInvalido extends RuntimeException {
    public EmailInvalido(String email) {
        super("E-mail invalido: " + email);
    }
}