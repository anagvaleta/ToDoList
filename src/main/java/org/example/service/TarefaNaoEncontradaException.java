package org.example.service;

import java.util.UUID;

public class TarefaNaoEncontradaException extends RuntimeException{
    public TarefaNaoEncontradaException(UUID id) {
        super("Tarefa não encontrada: " + id);
    }
}
