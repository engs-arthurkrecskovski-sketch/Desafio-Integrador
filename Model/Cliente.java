package model;

import java.time.LocalDateTime;

public final class Cliente {

    private final long id;
    private final String nome;
    private final String email;
    private final LocalDateTime criadoEm;

    public Cliente(String nome, String email) {
        this(0L, nome, email,LocalDateTime.now());
    }

     public Cliente(long id, String nome, String email, LocalDateTime criadoEm) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.criadoEm = criadoEm;
    }

    public long getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public LocalDateTime getCriadoEm() { return criadoEm; }

    @Override 
    public String toString() {
        return "[" + id + "]" + nome + " - " + email; 
    } 
}

