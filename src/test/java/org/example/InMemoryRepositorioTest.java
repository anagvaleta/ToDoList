package org.example;

import org.example.model.Tarefa;
import org.example.repository.InMemoryRepositorio;
import org.junit.jupiter.api.*;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryRepositorioTest {

    private InMemoryRepositorio repo;

    @BeforeEach
    void setUp() {
        repo = new InMemoryRepositorio();
    }

    @Test
    void saveAndFind() {
        var t = new Tarefa("Teste");
        repo.save(t);
        assertTrue(repo.findById(t.getId()).isPresent());
    }

    @Test
    void findById_nonExistent() {
        UUID id = UUID.randomUUID();
        assertTrue(repo.findById(id).isEmpty());
    }

    @Test
    void delete() {
        Tarefa t = new Tarefa("Teste");
        repo.save(t);
        assertTrue(repo.delete(t.getId()));
        assertTrue(repo.findById(t.getId()).isEmpty());
    }

    @Test
    void findAll_empty() {
        assertTrue(repo.findAll().isEmpty());
    }
}
