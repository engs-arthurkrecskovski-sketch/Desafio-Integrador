package com.sistema.model;

public final class ItemPedido {

    private final long id;
    private final Produto produto;
    private final int quantidade;
    private final double precoUnitario;

    public ItemPedido(Produto produto, int quantidade) {
        this(0L, produto, quantidade, produto.getPreco());
    }