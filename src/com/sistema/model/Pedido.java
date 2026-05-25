package com.sistema.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Pedido {

    private final long id;
    private final Cliente cliente;
    private final List<ItemPedido> itens;
    private StatusPedido status;
    private final LocalDateTime criadoEm;

    public Pedido(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("Um pedido precisa estar associado a um cliente válido.");
        }