package org.example.repository;

import org.example.model.Tarefa;
import java.util.*;

public interface Repositorio {
    Tarefa save(Tarefa t);
    Optional<Tarefa> findById(UUID id);
    List<Tarefa> findAll();
    boolean delete(UUID id);
}
