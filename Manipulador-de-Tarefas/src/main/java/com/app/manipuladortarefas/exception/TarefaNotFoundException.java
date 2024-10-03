package com.app.manipuladortarefas.exception;

import java.io.Serial;

public class TarefaNotFoundException extends RuntimeException {
	
	@Serial
	private static final long serialVersionUID = 1L;
	
	public TarefaNotFoundException() {
		super("Não existem Tarefas no Banco de Dados, Tente Inserir uma tarefa Primeiro.");
	}

	public TarefaNotFoundException(int number) {
		super("Tarefa com Número: " + number + " Não Encontrada na Base de Dados.");
	}

	public TarefaNotFoundException(String type) {
		super("Não existem Tarefas do Tipo: '" + type + "' no Banco de Dados, Tente Inserir uma Primeiro.");
	}
	

}
