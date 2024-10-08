package com.app.manipuladortarefas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.app.manipuladortarefas.exception.TarefaAlreadyExists;
import com.app.manipuladortarefas.exception.TarefaIllegalArgumentException;
import com.app.manipuladortarefas.exception.TarefaNotFoundException;
import com.app.manipuladortarefas.model.Tarefa;
import com.app.manipuladortarefas.service.ITarefaService;

import jakarta.transaction.Transactional;

@SpringBootTest
public class TarefaServiceTest {
	
	@Autowired
	private ITarefaService service;

	
	// Método findTarefaByNumber();
	@Test
	@Transactional
	public void verificaRetornoFindTarefaForNumber() {
		Tarefa tarefa = new Tarefa(1, "Tipo", "Descrição");
		service.createTarefa(tarefa);

		assertEquals(tarefa, service.findTarefaByNumber(1));
	}

	@Test
	@Transactional
	public void verificaRetornoInvalidoFindTarefaForNumber() {
		assertThrows(TarefaNotFoundException.class, () -> service.findTarefaByNumber(1));
	}

	
	// Método createTarefa();
	@Test
	@Transactional
	public void verificaRetornoTarefaCriada() {
		Tarefa novaTarefa = new Tarefa(1, "Urgente", "Verificar os Teste para a Qualidade do Sistema");

		Tarefa tarefaCriada = service.createTarefa(novaTarefa);

		assertEquals(novaTarefa.getNumber(), tarefaCriada.getNumber());
		assertEquals(novaTarefa.getType(), tarefaCriada.getType());
		assertEquals(novaTarefa.getDescription(), tarefaCriada.getDescription());

		assertEquals(novaTarefa, tarefaCriada);
	}

	@Test
	@Transactional
	public void verificaCampoNumberVazioCriacaoTarefa() {
		Tarefa tarefa = new Tarefa();
		tarefa.setType("Tipo da Tarefa");
		tarefa.setDescription("Descrição da tarefa");

		assertThrows(TarefaIllegalArgumentException.class, () -> service.createTarefa(tarefa));
	}

	@Test
	@Transactional
	public void verificaCampoTypeVazioCriacaoTarefa() {
		Tarefa tarefa = new Tarefa();
		tarefa.setNumber(1);
		tarefa.setType("");
		tarefa.setDescription("Descrição da tarefa");

		assertThrows(TarefaIllegalArgumentException.class, () -> service.createTarefa(tarefa));
	}

	@Test
	@Transactional
	public void verificaCampoDescriptionVazioCriacaoTarefa() {
		Tarefa tarefa = new Tarefa();
		tarefa.setNumber(1);
		tarefa.setType("Tipo da Tarefa");
		tarefa.setDescription("");

		assertThrows(TarefaIllegalArgumentException.class, () -> service.createTarefa(tarefa));
	}
	
	@Test
	@Transactional
	public void verificaRetornoInvalidoTarefaJaExistenteEmCriacaoTarefa() {
		Tarefa novaTarefa = new Tarefa(1, "Urgente", "Verificar os Teste para a Qualidade do Sistema");

		Tarefa tarefaJaCriada = service.createTarefa(novaTarefa);
		
		assertThrows(TarefaAlreadyExists.class, () -> service.createTarefa(tarefaJaCriada));
	}

	
	// Método updateTarefaByNumber();
	@Test
	@Transactional
	public void verificaRetornoUpdateParaTarefaInexistente() {
		service.createTarefa(new Tarefa(1, "urgente", "Realizar Teste"));
		Tarefa tarefaQualquer = new Tarefa(2, "Baixa Prioridade", "Tarefa Qualquer");
		assertThrows(TarefaNotFoundException.class, () -> service.updateTarefaByNumber(2, tarefaQualquer));
	}

	@Test
	@Transactional
	public void verificaCampoNumberZeroOuVazioUpdateTarefa() {
		service.createTarefa(new Tarefa(1, "urgente", "Realizar Teste"));
		Tarefa tarefaQualquer = new Tarefa(0, "Baixa Prioridade", "Tarefa Qualquer");
		assertThrows(TarefaIllegalArgumentException.class, () -> service.updateTarefaByNumber(1, tarefaQualquer));
	}

	@Test
	@Transactional
	public void verificaCampoTypeVazioUpdateTarefa() {
		service.createTarefa(new Tarefa(1, "urgente", "Realizar Teste"));
		Tarefa tarefaQualquer = new Tarefa(2, "", "Tarefa Qualquer");
		assertThrows(TarefaIllegalArgumentException.class, () -> service.updateTarefaByNumber(1, tarefaQualquer));
	}

	@Test
	@Transactional
	public void verificaCampoDescriptionVazioUpdateTarefa() {
		service.createTarefa(new Tarefa(1, "urgente", "Realizar Teste"));
		Tarefa tarefaQualquer = new Tarefa(2, "Baixa Prioridade", "");
		assertThrows(TarefaIllegalArgumentException.class, () -> service.updateTarefaByNumber(1, tarefaQualquer));
	}

	@Test
	@Transactional
	public void verificaRetornoUpdateParaTarefaExistente() {
		service.createTarefa(new Tarefa(5, "Urgente", "Realizar Teste"));
		Tarefa tarefaQualquer = new Tarefa(1, "Baixa Prioridade", "Tarefa Qualquer");
		assertEquals(tarefaQualquer, service.updateTarefaByNumber(5, tarefaQualquer));
	}
	
	
	//Método deleteTarefaByNumber()
	@Test
	@Transactional
	public void verificaRetornoDeleteNumberValido() {
		Tarefa tarefa = new Tarefa(5, "Urgente", "Realizar Teste");
		service.createTarefa(tarefa);
		assertEquals(tarefa, service.deleteTarefaByNumber(5));
	}
	
	@Test
	@Transactional
	public void verificaRetornoDeleteNumberInvalido() {
		Tarefa tarefa = new Tarefa(5, "Urgente", "Realizar Teste");
		service.createTarefa(tarefa);
		assertThrows(TarefaNotFoundException.class, () -> service.deleteTarefaByNumber(1));
	}
	
	// Método findAllTarefas
	@Test
	@Transactional
	public void verificaRetornoInvalidoVazioFindAll() {
		assertThrows(TarefaNotFoundException.class, () -> service.findAllTarefas());
	}
	
	@Test
	@Transactional
	public void verificaRetornoValidoFindAll() {
		Tarefa tarefa1 = new Tarefa(1, "Baixa Prioridade", "Tarefa Qualquer");
		Tarefa tarefa2 = new Tarefa(2, "Urgente", "Realizar Testes");
		List<Tarefa> tarefas = new ArrayList<>();
		
		tarefas.add(tarefa1);
		tarefas.add(tarefa2);
		
		service.createTarefa(tarefa1);
		service.createTarefa(tarefa2);

		assertEquals(tarefas, service.findAllTarefas());
	}
	
	// Método findAllTarefasForType
	@Test
	@Transactional
	public void verificaRetornoInvalidoVazioFindForType() {
		assertThrows(TarefaNotFoundException.class, () -> service.findAllTarefasForType("Urgente"));
	}
	
	@Test
	@Transactional
	public void verificaRetornoValidoFindForType() {
		List<Tarefa> tarefas = new ArrayList<>();

		Tarefa tarefa1 = new Tarefa(1,"Basica","Realizar Testes da Aplicação");
		Tarefa tarefa11 = new Tarefa(11,"Urgente","Realizar Testes da Aplicação");
		Tarefa tarefa12 = new Tarefa(12,"Urgente","Controlar Qualidade do Software");
		Tarefa tarefa13 = new Tarefa(13,"Urgente","Utilizar Boas Práticas de Programação");
		
		//adicionando somente tarefas do tipo Buscado
		tarefas.add(tarefa11);
		tarefas.add(tarefa12);
		tarefas.add(tarefa13);
		
		service.createTarefa(tarefa1);
		service.createTarefa(tarefa11);
		service.createTarefa(tarefa12);
		service.createTarefa(tarefa13);
		
		assertEquals(tarefas, service.findAllTarefasForType("Urgente"));
	}

}
