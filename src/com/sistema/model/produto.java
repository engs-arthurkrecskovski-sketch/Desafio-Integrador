package com.sistema.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public final class Produto {
    
   private final long id;
    private final String nome;
    private final BigDecimal preco;
    private final int quantidadeEstoque;
    private final Categoria categoria;
    private final LocalDateTime criadoEm;

 public Produto(String nome, BigDecimal preco, int quantidadeEstoque, Categoria categoria) {
        this(0L, nome, preco, quantidadeEstoque, categoria, LocalDateTime.now());
    }

  public Produto(long id, String nome, BigDecimal preco, int quantidadeEstoque,
                   Categoria categoria, LocalDateTime criadoEm) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
        this.categoria = categoria;
        this.criadoEm = criadoEm;
    }

     public long getId() { return id; }
    public String getNome() { return nome; }
    public BigDecimal getPreco() { return preco; }
    public int getQuantidadeEstoque() { return quantidadeEstoque; }
    public Categoria getCategoria() { return categoria; }
    public LocalDateTime getCriadoEm() { return criadoEm; }

    @Override
    public String toString() {
        return String.format("[%d] %s | R$ %.2f | Estoque: %d | %s",
                id, nome, preco, quantidadeEstoque, categoria);
    }


}
