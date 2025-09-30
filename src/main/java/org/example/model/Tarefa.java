package org.example.model;

import java.util.UUID;

public class Tarefa {
    private final UUID id;
    private String titulo;
    private boolean concluida;

    public Tarefa(String titulo) {
        if (titulo == null || titulo.isBlank()) throw new IllegalArgumentException("Título obrigatório");
        this.id = UUID.randomUUID();
        this.titulo = titulo;
        this.concluida = false;
    }

    public UUID getId() { return id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public boolean isConcluida() { return concluida; }
    public void setConcluida(boolean concluida) { this.concluida = concluida; }
}
