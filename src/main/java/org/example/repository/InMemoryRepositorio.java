package org.example.repository;

import org.example.model.Tarefa;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryRepositorio implements Repositorio {
    private final Map<UUID, Tarefa> store = new ConcurrentHashMap<>();

    @Override
    public Tarefa save(Tarefa t) {
        store.put(t.getId(), t);
        return t;
    }

    @Override
    public Optional<Tarefa> findById(UUID id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Tarefa> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public boolean delete(UUID id) {
        return store.remove(id) != null;
    }
}
