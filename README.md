# Manipulador de Tarefas - API

Este projeto é uma API RESTful desenvolvida com **Spring Boot** que realiza todas as operações de CRUD(Create, Read, Update, Delete) em tarefas.
O sistema também implementa um controlador de exceções(ApplicationControllerAdvice) que gerencia erros específicos da aplicação.

O Foco principal deste projeto foi a implementação de *Testes Unitários* e *Testes de Integração* utilizando o JUnit(Framework) que foram implementados visando garantir todas as operações da aplicação.
Nos testes foi implementada a técnica de "Particionamento de Equivalência" e neste código as Classes de Controle, Exceção e Serviços alcançaram 100% de Coverage(cobertura).

- Abaixo Imagem Ilustrando Retorno do JUnit do Projeto:

![Testes Do AppManipuladorDeTarefas](https://github.com/user-attachments/assets/ed8d927f-77a1-4ba3-8828-9f2673a6d0b4)


## Funcionalidades

- **Criar Tarefas**: Permite criar novas tarefas com campos como número, tipo e descrição.
- **Consultar Tarefas**: Consulta tarefas por número ou tipo, e também lista todas as tarefas existentes.
- **Atualizar Tarefas**: Atualiza os detalhes de uma tarefa existente.
- **Excluir Tarefas**: Exclui tarefas com base no número.
- **Tratamento de Exceções**: Exceções customizadas para controle de erros como tarefa duplicada, número inválido ou tarefas não encontradas.

## Exceções Tratadas

- **TarefaAlreadyExists**: Lançada quando uma tarefa com o mesmo número já existe.
- **TarefaNotFoundException**: Lançada quando uma tarefa com o número ou tipo especificado não é encontrada.
- **TarefaIllegalArgumentException**: Lançada quando argumentos inválidos, como número nulo ou negativo, são fornecidos.
- **Throwable**: Captura qualquer exceção inesperada e retorna uma resposta com status 500.

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.3.2**
- **JPA (Hibernate)**
- **H2/MySQL** (para persistência de dados)
- **JUnit 5** (para testes)
- **MockMvc** (para testes de endpoints)
- **Swagger** (documentação da API)

_A Organização dos pacotes segue o Modelo MVC._

