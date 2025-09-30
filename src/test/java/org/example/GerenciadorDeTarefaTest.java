package org.example;

import org.example.repository.InMemoryRepositorio;
import org.example.repository.Repositorio;
import org.example.service.GerenciadorDeTarefas;
import org.example.model.Tarefa;
import org.example.service.TarefaNaoEncontradaException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GerenciadorDeTarefasTest {
    private Repositorio repoMock;
    private GerenciadorDeTarefas gerenciador;

    @BeforeEach
    void setup() {
        repoMock = Mockito.mock(Repositorio.class);
        gerenciador = new GerenciadorDeTarefas(repoMock);
    }

    @Test
    void criarTarefa_valida() {
        Tarefa t = new Tarefa("Testar");
        when(repoMock.save(any())).thenReturn(t);

        Tarefa resultado = gerenciador.criar("Testar");

        assertEquals("Testar", resultado.getTitulo());
        verify(repoMock, times(1)).save(any());
    }

    @Test
    void criarTarefa_tituloNull() {
        assertThrows(IllegalArgumentException.class, () -> new Tarefa(null));
    }

    @Test
    void criarTarefa_tituloVazio() {
        assertThrows(IllegalArgumentException.class, () -> new Tarefa(" "));
    }

    @Test
    void buscarTarefa_existente() {
        UUID id = UUID.randomUUID();
        Tarefa t = new Tarefa("Teste");
        when(repoMock.findById(id)).thenReturn(Optional.of(t));

        Tarefa resultado = gerenciador.buscar(id);

        assertEquals(t, resultado);
    }

    @Test
    void buscarTarefa_inexistente() {
        UUID id = UUID.randomUUID();
        when(repoMock.findById(id)).thenReturn(Optional.empty());

        assertThrows(TarefaNaoEncontradaException.class, () -> gerenciador.buscar(id));
    }

    @Test
    void listarTarefas_naoVazio() {
        List<Tarefa> tarefas = List.of(new Tarefa("T1"), new Tarefa("T2"));
        when(repoMock.findAll()).thenReturn(tarefas);

        List<Tarefa> resultado = gerenciador.listar();

        assertEquals(2, resultado.size());
    }

    @Test
    void listarTarefas_vazio() {
        when(repoMock.findAll()).thenReturn(Collections.emptyList());

        List<Tarefa> resultado = gerenciador.listar();

        assertTrue(resultado.isEmpty());
    }

    @Test
    void concluirTarefa_existente() {
        UUID id = UUID.randomUUID();
        Tarefa t = new Tarefa("Tarefa");
        when(repoMock.findById(id)).thenReturn(Optional.of(t));
        when(repoMock.save(t)).thenReturn(t);

        gerenciador.concluir(id);

        assertTrue(t.isConcluida());
        verify(repoMock).save(t);
    }

    @Test
    void concluirTarefa_inexistente() {
        UUID id = UUID.randomUUID();
        when(repoMock.findById(id)).thenReturn(Optional.empty());

        assertThrows(TarefaNaoEncontradaException.class, () -> gerenciador.concluir(id));
    }

    @Test
    void deletarTarefa_existente() {
        UUID id = UUID.randomUUID();
        when(repoMock.delete(id)).thenReturn(true);

        boolean resultado = gerenciador.deletar(id);

        assertTrue(resultado);
    }

    @Test
    void deletarTarefa_inexistente() {
        UUID id = UUID.randomUUID();
        when(repoMock.delete(id)).thenReturn(false);

        boolean resultado = gerenciador.deletar(id);

        assertFalse(resultado);
    }

    @Test
    void buscarPorTitulo_exato() {
        Tarefa t = new Tarefa("Aprender Java");
        when(repoMock.findAll()).thenReturn(List.of(t));

        List<Tarefa> resultado = gerenciador.buscarPorTitulo("Aprender Java");

        assertEquals(1, resultado.size());
    }

    @Test
    void buscarPorTitulo_parcial() {
        Tarefa t1 = new Tarefa("Java básico");
        Tarefa t2 = new Tarefa("Java avançado");
        when(repoMock.findAll()).thenReturn(List.of(t1, t2));

        List<Tarefa> resultado = gerenciador.buscarPorTitulo("básico");

        assertEquals(1, resultado.size());
    }

    @Test
    void buscarPorTitulo_caseInsensitive() {
        Tarefa t = new Tarefa("Java");
        when(repoMock.findAll()).thenReturn(List.of(t));

        List<Tarefa> resultado = gerenciador.buscarPorTitulo("java");

        assertEquals(1, resultado.size());
    }

    @Test
    void buscarPorTitulo_naoExistente() {
        Tarefa t = new Tarefa("Python");
        when(repoMock.findAll()).thenReturn(List.of(t));

        List<Tarefa> resultado = gerenciador.buscarPorTitulo("Java");

        assertTrue(resultado.isEmpty());
    }

    @Test
    void buscarPorTitulo_vazio() {
        Tarefa t = new Tarefa("T1");
        when(repoMock.findAll()).thenReturn(List.of(t));

        List<Tarefa> resultado = gerenciador.buscarPorTitulo("");

        assertEquals(1, resultado.size());
    }

    @Test
    void criarEBuscarTarefa() {
        Repositorio repoReal = new InMemoryRepositorio();
        GerenciadorDeTarefas g = new GerenciadorDeTarefas(repoReal);
        Tarefa t = g.criar("T1");

        Tarefa buscada = g.buscar(t.getId());

        assertEquals(t.getId(), buscada.getId());
    }

    @Test
    void criarConcluirVerificar() {
        Repositorio repoReal = new InMemoryRepositorio();
        GerenciadorDeTarefas g = new GerenciadorDeTarefas(repoReal);
        Tarefa t = g.criar("T1");

        g.concluir(t.getId());

        Tarefa atualizada = g.buscar(t.getId());
        assertTrue(atualizada.isConcluida());
    }


}
