package com.sistema.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Pedido {

    private final long id;
    private final Cliente cliente;
    private final StatusPedido status;
    private final LocalDateTime criadoEm;
    private final List<ItemPedido> itens;

    public Pedido(Cliente cliente, List<ItemPedido> itens) {
        this(0L, cliente, StatusPedido.ABERTO, LocalDateTime.now(), itens);
    }

    public Pedido(long id, Cliente cliente, StatusPedido status,
                  LocalDateTime criadoEm, List<ItemPedido> itens) {
        this.id = id;
        this.cliente = cliente;
        this.status = status;
        this.criadoEm = criadoEm;
        this.itens = new ArrayList<>(itens);
    }

    public long getId() { return id; }
    public Cliente getCliente() { return cliente; }
    public StatusPedido getStatus() { return status; }
    public LocalDateTime getCriadoEm() { return criadoEm; }
    public List<ItemPedido> getItens() { return Collections.unmodifiableList(itens); }

    public BigDecimal getTotal() {
        return itens.stream()
                .map(ItemPedido::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Pedido #%d | %s | %s | Total: R$ %.2f%n",
                id, cliente.getNome(), status, getTotal()));
        itens.forEach(i -> sb.append(i).append("\n"));
        return sb.toString();
    }
}