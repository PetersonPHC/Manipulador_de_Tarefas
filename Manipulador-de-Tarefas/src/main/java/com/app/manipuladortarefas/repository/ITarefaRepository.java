package com.app.manipuladortarefas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.manipuladortarefas.model.Tarefa;

public interface ITarefaRepository extends JpaRepository<Tarefa, Long>{
	Optional<Tarefa> findByNumber(int number);
	List<Tarefa> findByType(String type);
}
