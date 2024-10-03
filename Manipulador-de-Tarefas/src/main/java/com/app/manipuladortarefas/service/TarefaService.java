package com.app.manipuladortarefas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.manipuladortarefas.exception.TarefaAlreadyExists;
import com.app.manipuladortarefas.exception.TarefaIllegalArgumentException;
import com.app.manipuladortarefas.exception.TarefaNotFoundException;
import com.app.manipuladortarefas.model.Tarefa;
import com.app.manipuladortarefas.repository.ITarefaRepository;

import jakarta.transaction.Transactional;

@Service
public class TarefaService implements ITarefaService{
	
	@Autowired
	private ITarefaRepository repository;

	@Override
	public Tarefa findTarefaByNumber(int number) {
		Optional<Tarefa> tarefa = repository.findByNumber(number);
		return tarefa.orElseThrow(() -> new TarefaNotFoundException(number));
	}

	@Override
	public Tarefa createTarefa(Tarefa tarefa) {

		if((tarefa.getNumber() == null) || (tarefa.getNumber() <= 0)) {
			throw new TarefaIllegalArgumentException();
		}		
		if(tarefa.getType().isEmpty()) {
			throw new TarefaIllegalArgumentException("Type");
		}
		if(tarefa.getDescription().isEmpty()) {
			throw new TarefaIllegalArgumentException("Description");
		}
		
		Optional<Tarefa> tarefaExistente = repository.findByNumber(tarefa.getNumber());
		if(tarefaExistente.isPresent()) {
			throw new TarefaAlreadyExists(tarefaExistente.get().getNumber());
		}
		
		return repository.save(tarefa);
		
	}

	@Override
	@Transactional
	public Tarefa updateTarefaByNumber(int number, Tarefa tarefa) {
		Optional<Tarefa> tarefaExistente = repository.findByNumber(number);
		if(tarefaExistente.isEmpty()) {
			throw new TarefaNotFoundException(number);
		}
		
		Tarefa tarefaAtualizada = tarefaExistente.get();
		
		if((tarefa.getNumber() == null) || (tarefa.getNumber() <= 0)) {
			throw new TarefaIllegalArgumentException();
		} tarefaAtualizada.setNumber(tarefa.getNumber());
		
		if(tarefa.getType().isEmpty()) {
			throw new TarefaIllegalArgumentException("Type");
		} tarefaAtualizada.setType(tarefa.getType());
		
		if(tarefa.getDescription().isEmpty()) {
			throw new TarefaIllegalArgumentException("Description");
		} tarefaAtualizada.setDescription(tarefa.getDescription());
		
		return repository.save(tarefaAtualizada);
	}

	@Override
	@Transactional
	public Tarefa deleteTarefaByNumber(int number) {
		Optional<Tarefa> tarefaExistente = repository.findByNumber(number);
		if(tarefaExistente.isEmpty()) {
			throw new TarefaNotFoundException(number);
		}
		repository.deleteById(tarefaExistente.get().getId());;
		return tarefaExistente.get();
	}

	@Override
	public Iterable<Tarefa> findAllTarefasForType(String type) {
		List<Tarefa> tarefasPorTipo = repository.findByType(type);
		if(tarefasPorTipo.isEmpty()) {
			throw new TarefaNotFoundException(type);
		}
		return tarefasPorTipo;
	}

	@Override
	public Iterable<Tarefa> findAllTarefas() {
		List<Tarefa> listaDeTarefas = repository.findAll();
		if(listaDeTarefas.isEmpty()) {
			throw new TarefaNotFoundException();
		}
		return listaDeTarefas;
	}
	

}
