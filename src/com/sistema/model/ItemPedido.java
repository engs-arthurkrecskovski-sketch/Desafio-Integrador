package com.sistema.model;

public final class ItemPedido {

    private final long id;
    private final Produto produto;
    private final int quantidade;
    private final double precoUnitario;

    public ItemPedido(Produto produto, int quantidade) {
        this(0L, produto, quantidade, produto.getPreco());
    }

    public ItemPedido(long id, Produto produto, int quantidade, double precoUnitario) {
        if (produto == null) {
            throw new IllegalArgumentException("O produto do item do pedido não pode ser nulo.");
        }
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade de itens deve ser maior que zero.");
        }
        if (precoUnitario < 0) {
            throw new IllegalArgumentException("O preço unitário não pode ser negativo.");
        }

        this.id = id;
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
    }

    public long getId() { return id; }
    public Produto getProduto() { return produto; }
    public int getQuantidade() { return quantidade; }
    public double getPrecoUnitario() { return precoUnitario; }