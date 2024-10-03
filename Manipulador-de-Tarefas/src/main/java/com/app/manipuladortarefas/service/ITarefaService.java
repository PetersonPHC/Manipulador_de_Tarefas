package com.app.manipuladortarefas.service;

import com.app.manipuladortarefas.model.Tarefa;

public interface ITarefaService {
	public Tarefa findTarefaByNumber(int number);
	public Tarefa createTarefa(Tarefa tarefa);
	public Tarefa updateTarefaByNumber(int number, Tarefa tarefa);
	public Tarefa deleteTarefaByNumber(int number);	
	public Iterable<Tarefa> findAllTarefasForType(String type);
	public Iterable<Tarefa> findAllTarefas();
}
