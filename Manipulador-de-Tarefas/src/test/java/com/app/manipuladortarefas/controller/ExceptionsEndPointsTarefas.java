package com.app.manipuladortarefas.controller;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import jakarta.transaction.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
public class ExceptionsEndPointsTarefas {

	@Autowired
	private MockMvc mock;
	
	
	@Test // Classe De Exceção: TarefaIllegalArgumentException -> Number INVALIDO
	@Transactional
	public void testeRetornoTarefaIllegalArgumentExceptionInvalidNumber() throws Throwable {
		URI url = new URI("/tarefas");
		String tarefaJSON = "{ \"number\" : -1 , \"type\" : \"urgente\" , \"description\" : \"realizar controle de testes\"}";
		
		mock.perform(
			MockMvcRequestBuilders
				.post(url)
				.contentType(MediaType.APPLICATION_JSON)
				.content(tarefaJSON)
		
		).andExpect(
			MockMvcResultMatchers.status().isNotAcceptable()
		).andExpect(
			MockMvcResultMatchers.content().string("Valor Inválido, por favor insira um valor válido.")
		);
	}
	
	@Test // Classe De Exceção: TarefaIllegalArgumentException
	@Transactional
	public void testeRetornoTarefaIllegalArgumentExceptionInvalidType() throws Throwable {
		URI url = new URI("/tarefas");
		String tarefaJSON = "{ \"number\" : 1 , \"type\" : \"\" , \"description\" : \"realizar controle de testes\"}";
		
		mock.perform(
			MockMvcRequestBuilders
				.post(url)
				.contentType(MediaType.APPLICATION_JSON)
				.content(tarefaJSON)
				
		).andExpect(
			MockMvcResultMatchers.status().isNotAcceptable()
		).andExpect(
			MockMvcResultMatchers.content().string("O Campo " + "Type" + " Não pode ser Nulo.")
		);
	}
	
	@Test // Classe De Exceção: TarefaIllegalArgumentException
	@Transactional
	public void testeRetornoTarefaIllegalArgumentExceptionInvalidDescription() throws Throwable {
		URI url = new URI("/tarefas");
		String tarefaJSON = "{ \"number\" : 1 , \"type\" : \"urgente\" , \"description\" : \"\"}";
		
		mock.perform(
			MockMvcRequestBuilders
				.post(url)
				.contentType(MediaType.APPLICATION_JSON)
				.content(tarefaJSON)
				
		).andExpect(
			MockMvcResultMatchers.status().isNotAcceptable()
		).andExpect(
			MockMvcResultMatchers.content().string("O Campo " + "Description" + " Não pode ser Nulo.")
		);
	}
	
	@Test // Classe De Exceção: TareafaNotFoundException
	@Transactional
	public void testeRetornoTareafaNotFoundException() throws Throwable {
		URI url = new URI("/tarefas");
		
		mock.perform(
			MockMvcRequestBuilders.get(url)

		).andExpect(
			MockMvcResultMatchers.status().isNotFound()
		
		).andExpect(
			MockMvcResultMatchers.content().string("Não existem Tarefas no Banco de Dados, Tente Inserir uma tarefa Primeiro.")
		);
	}
	
	@Test // Classe De Exceção: TareafaAlreadyExists -> Number INEXISTENTE
	@Transactional
	public void testeRetornoTareafaNotFoundExceptionNumberNonExistentNumber() throws Throwable {
		URI url = new URI("/tarefas/1");
		
		mock.perform(
			MockMvcRequestBuilders.get(url)

		).andExpect(
			MockMvcResultMatchers.status().isNotFound()
		
		).andExpect(
			MockMvcResultMatchers.content().string("Tarefa com Número: " + "1" + " Não Encontrada na Base de Dados.")
		);
	}
	@Test // Classe De Exceção: TareafaAlreadyExists -> Type INEXISTENTE
	public void testeRetornoTareafaNotFoundExceptionNonExistentType() throws Throwable {
		URI url = new URI("/tarefas/type/Urgente");
		
		mock.perform(
			MockMvcRequestBuilders.get(url)

		).andExpect(
			MockMvcResultMatchers.status().isNotFound()
		
		).andExpect(
			MockMvcResultMatchers.content().string("Não existem Tarefas do Tipo: '" + "Urgente" + "' no Banco de Dados, Tente Inserir uma Primeiro.")
		);
	}
	
	
	@Test // Classe De Exceção: TareafaAlreadyExists
	@Transactional
	public void testeRetornoTarefaAlreadyExists() throws Throwable {
		URI url = new URI("/tarefas");
		String tarefaJSON = "{ \"number\" : 1 , \"type\" : \"urgente\" , \"description\" : \"realizar controle de testes\"}";
		mock.perform(
			MockMvcRequestBuilders
				.post(url)
				.contentType(MediaType.APPLICATION_JSON)
				.content(tarefaJSON)
		
		).andExpect(
				MockMvcResultMatchers.status().isCreated()
		);

		//EXCEPTION
		mock.perform(
			MockMvcRequestBuilders
				.post(url)
				.contentType(MediaType.APPLICATION_JSON)
				.content(tarefaJSON)
				
		).andExpect(
			MockMvcResultMatchers.status().isConflict()
		).andExpect(
			MockMvcResultMatchers.content().string("Tarefa com Número " + "1" + " Já Existente.")
		);
	}
	
	@Test // Classe De Exceção: Throwable
	@Transactional
	public void testeRetornoHandlerGenericException() throws Exception {
	    URI url = new URI("/tarefas/force-error");
	    	    mock.perform(
	            MockMvcRequestBuilders
	                .get(url)
	    ).andExpect(
	            MockMvcResultMatchers.status().isInternalServerError()
	    ).andExpect(
	            MockMvcResultMatchers.content().string("Erro Inesperado, Veja os Logs da Aplicação para Compreender o Erro!")
	    );
	}

}
