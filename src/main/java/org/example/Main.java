package org.example;

import org.example.repository.InMemoryRepositorio;
import org.example.service.GerenciadorDeTarefas;

public class Main {
    public static void main(String[] args) {
        var repositorio = new InMemoryRepositorio();
        var gerenciar = new GerenciadorDeTarefas(repositorio);

        var t = gerenciar.criar("Estudar Java");
        System.out.println("Tarefa criada: " + t.getTitulo() + " (" + t.getId() + ")");
    }
}
