package com.app.manipuladortarefas.exception;

import java.io.Serial;

public class TarefaIllegalArgumentException extends RuntimeException{
	@Serial
	private static final long serialVersionUID = 1L;
	
	public TarefaIllegalArgumentException() {
		super("Valor Inválido, por favor insira um valor válido.");
	}
	
	public TarefaIllegalArgumentException(String field) {
		super("O Campo " + field + " Não pode ser Nulo.");
	}

}
