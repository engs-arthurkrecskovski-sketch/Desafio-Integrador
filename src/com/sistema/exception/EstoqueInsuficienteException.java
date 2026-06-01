package com.sistema.exception;


public class EstoqueInsuficienteException extends RuntimeException {
    public EstoqueInsuficienteException(String nomeProduto, int solicitado, int disponivel) {
        super("Estoque insuficiente para '" + nomeProduto + "': solicitado=" + solicitado + ", disponivel=" + disponivel);
    }
}
