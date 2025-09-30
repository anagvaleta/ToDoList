package org.example.service;

import org.example.model.Tarefa;
import org.example.repository.Repositorio;

import java.util.*;
import java.util.stream.Collectors;

public class GerenciadorDeTarefas {
    private final Repositorio repo;

    public GerenciadorDeTarefas(Repositorio repo) {
        this.repo = repo;
    }

    public Tarefa criar(String titulo) {
        Tarefa t = new Tarefa(titulo);
        return repo.save(t);
    }

    public Tarefa buscar(UUID id) {
        return repo.findById(id).orElseThrow(() -> new TarefaNaoEncontradaException(id));
    }

    public List<Tarefa> listar() {
        return repo.findAll();
    }

    public void concluir(UUID id) {
        Tarefa t = buscar(id);
        t.setConcluida(true);
        repo.save(t);
    }

    public boolean deletar(UUID id) {
        return repo.delete(id);
    }

    public List<Tarefa> buscarPorTitulo(String texto) {
        String q = (texto == null) ? "" : texto.toLowerCase();
        return listar().stream()
                .filter(t -> t.getTitulo().toLowerCase().contains(q))
                .collect(Collectors.toList());
    }
}
