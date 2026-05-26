package com.sistema.exception;


public class EstoqueInsuficiente extends RuntimeException {
    public EstoqueInsuficiente(String nomeProduto, int solicitado, int disponivel) {
        super("Estoque insuficiente para '" + nomeProduto + "': solicitado=" + solicitado + ", disponivel=" + disponivel);
    }
}
