package com.sistema.model;

import java.math.BigDecimal;

public final class ItemPedido {

    private final long id;
    private final long pedidoId;
    private final Produto produto;
    private final int quantidade;
    private final BigDecimal precoUnit;

   public ItemPedido(Produto produto, int quantidade) {
        this(0L, 0L, produto, quantidade, produto.getPreco());
    }

    public ItemPedido(long id, long pedidoId, Produto produto, int quantidade, BigDecimal precoUnit) {
        this.id = id;
        this.pedidoId = pedidoId;
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoUnit = precoUnit;
    }

    public long getId() { return id; }
    public long getPedidoId() { return pedidoId; }
    public Produto getProduto() { return produto; }
    public int getQuantidade() { return quantidade; }
    public BigDecimal getPrecoUnit() { return precoUnit; }

    public BigDecimal getSubtotal() {
        return precoUnit.multiply(BigDecimal.valueOf(quantidade));
    }

    @Override
    public String toString() {
        return String.format("  - %s x%d @ R$ %.2f = R$ %.2f",
                produto.getNome(), quantidade, precoUnit, getSubtotal());
    }
}
