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
        this.id = 0L;
        this.cliente = cliente;
        this.itens = new ArrayList<>();
        this.status = StatusPedido.ABERTO;
        this.criadoEm = LocalDateTime.now();
    }

    public Pedido(long id, Cliente cliente, List<ItemPedido> itens, StatusPedido status, LocalDateTime criadoEm) {
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não pode ser nulo.");
        }
        if (status == null) {
            throw new IllegalArgumentException("Status do pedido não pode ser nulo.");
        }
        if (criadoEm == null) {
            throw new IllegalArgumentException("Data de criação não pode ser nula.");
        }

        this.id = id;
        this.cliente = cliente;
        this.itens = (itens != null) ? new ArrayList<>(itens) : new ArrayList<>();
        this.status = status;
        this.criadoEm = criadoEm;
    }

    public long getId() { return id; }
    public Cliente getCliente() { return cliente; }
    public StatusPedido getStatus() { return status; }
    public LocalDateTime getCriadoEm() { return criadoEm; }