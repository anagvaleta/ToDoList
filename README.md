# 🌸 Gerenciador de Tarefas

Um projeto simples em **Java** para gerenciar tarefas, utilizando conceitos de **POO** (Programação Orientada a Objetos), testes unitários com **JUnit 5** e persistência em memória.  

Este projeto também possui um **pipeline de CI/CD** configurado via **GitHub Actions**, que roda testes, faz build e envia notificações por e-mail.

---

## 🗂 Estrutura do Projeto

src/
└─ main/
├─ java/org/example
│ ├─ Main.java
│ ├─ model/Tarefa.java
│ ├─ repository/Repositorio.java
│ ├─ repository/InMemoryRepositorio.java
│ └─ service/GerenciadorDeTarefas.java
│ └─ service/TarefaNaoEncontradaException.java
└─ test/
└─ java/org/example
├─ GerenciadorDeTarefasTest.java
└─ InMemoryRepositorioTest.java

---

## 🗂 Funcionalidades Principais

- 📝 Criar tarefas com título obrigatório.
- 📋 Listar todas as tarefas.
- 🔍 Buscar tarefas pelo **ID**.
- ✨ Buscar tarefas pelo **título** (case insensitive e pesquisa parcial).
- ✅ Concluir tarefas.
- ❌ Deletar tarefas.
- ⚠️ Lançar exceção se a tarefa não existir.

---

## Testes Unitários 🐰

Os testes usam **JUnit 5** e cobrem vários cenários:

- Criação de tarefas.
- Busca de tarefas por ID ou título.
- Conclusão e deleção de tarefas.
- Casos de exceção (tarefa não encontrada).

**Para rodar os testes:**

```bash
mvn test
```
---
🚀 Como Rodar o Projeto

Clone o repositório:

```bash
git clone https://github.com/SeuUsuario/seu-projeto.git
```

Entre na pasta:
```bash
cd seu-projeto
```
Execute o programa:
```bash
mvn compile exec:java -Dexec.mainClass="org.example.Main"
```

Você verá algo assim no console:
```bash
Tarefa criada: Estudar Java (c8b6b2a5-...)
```
