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



}
