package com.sistema.model;

public enum Categoria {
    ALIMENTOS, ELETRONICOS, LIVROS;

    public static Categoria fromString(String valor) {
        for (Categoria c : values()) {
            if (c.name().equalsIgnoreCase(valor.trim())) return c;
        }
        throw new IllegalArgumentException("Categoria nvalida: " + valor);
    }
}
