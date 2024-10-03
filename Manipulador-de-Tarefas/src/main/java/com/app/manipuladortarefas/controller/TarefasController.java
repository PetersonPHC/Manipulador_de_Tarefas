package com.app.manipuladortarefas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.manipuladortarefas.model.Tarefa;
import com.app.manipuladortarefas.service.ITarefaService;

@RestController
@RequestMapping("/tarefas")
public class TarefasController {
	
	@Autowired
	ITarefaService service;
	
	@GetMapping("{number}")
	public ResponseEntity<Tarefa> findTarefa(@PathVariable int number){		
		Tarefa tarefaBuscada = service.findTarefaByNumber(number);
		return ResponseEntity.ok(tarefaBuscada);
	}
	
	@PostMapping()
	public ResponseEntity<Tarefa> createTarefa(@RequestBody Tarefa tarefa){
		Tarefa tarefaCriada = service.createTarefa(tarefa);
		return ResponseEntity.status(HttpStatus.CREATED).body(tarefaCriada);
	}
	
	@PutMapping("{number}")
	public ResponseEntity<Tarefa> updateTarefa(@PathVariable int number, @RequestBody Tarefa tarefa){
		Tarefa tarefaAtualizada = service.updateTarefaByNumber(number, tarefa);
		return ResponseEntity.ok(tarefaAtualizada);
	}
	
	@DeleteMapping("{number}")
	public ResponseEntity<Tarefa> deleteTarefa(@PathVariable int number){
		Tarefa tarefaExcluida = service.deleteTarefaByNumber(number);
		return ResponseEntity.ok().body(tarefaExcluida);
	}
	
	@GetMapping
	public ResponseEntity<Iterable<Tarefa>> getAllTarefas(){
		Iterable<Tarefa> tarefas = service.findAllTarefas();
		return ResponseEntity.ok(tarefas);
	}
	
	@GetMapping("/type/{type}")
	public ResponseEntity<Iterable<Tarefa>> getAllTarefasForType(@PathVariable String type){
		Iterable<Tarefa> tarefasPorTipo = service.findAllTarefasForType(type);
		return ResponseEntity.ok(tarefasPorTipo);
	}

}
