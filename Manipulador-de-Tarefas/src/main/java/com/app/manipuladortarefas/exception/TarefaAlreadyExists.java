package com.app.manipuladortarefas.exception;

public class TarefaAlreadyExists extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public TarefaAlreadyExists(int number) {
		super("Tarefa com Número " + number + " Já Existente.");
	}
	
}
