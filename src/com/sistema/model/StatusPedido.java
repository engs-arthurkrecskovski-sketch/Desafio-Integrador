package com.sistema.model;

public enum StatusPedido {
    
ABERTO, FILA, PROCESSANDO, FINALIZADO;

    public static StatusPedido fromString(String valor) {
        for (StatusPedido s : values()) {
            if (s.name().equalsIgnoreCase(valor.trim())) return s;
        }
        throw new IllegalArgumentException("Status invalido: " + valor);
        
    }

}
