package com.app.manipuladortarefas.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

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
public class EndPointsTarefaControllerTest {

	@Autowired
	private MockMvc mock;
	
	@Test //Método GET -> findTarefa()
	@Transactional
	public void testeRetornoFindTarefa() throws Exception {
		URI urlPOST = new URI("/tarefas");
		URI urlGET = new URI("/tarefas/1");
		
		String tarefaJSON = "{ \"number\" : 1 ,\"type\" : \"Urgente\", \"description\" : \"Realizar Testes de Integração\"}";
	
		mock.perform(
				MockMvcRequestBuilders
					.post(urlPOST)
					.content(tarefaJSON)
					.contentType(MediaType.APPLICATION_JSON)
			).andExpect(
				MockMvcResultMatchers
					.status().isCreated()
				);
		
		mock.perform(
				MockMvcRequestBuilders
					.get(urlGET)
			).andExpect(
				MockMvcResultMatchers
					.status().isOk()
		);
	}
	
	
	@Test //Método POST -> createTarefa()
	@Transactional
	public void testeRetornoCreateTarefa() throws Throwable {
		URI url = new URI("/tarefas");
		String tarefaJSON = "{ \"number\" : 1 , \"type\" : \"urgente\" , \"description\" : \"realizar controle de testes\"}"; 
		mock.perform(
			MockMvcRequestBuilders
				.post(url)
				.content(tarefaJSON)
				.contentType(MediaType.APPLICATION_JSON)
			
		).andExpect(
			MockMvcResultMatchers
				.status().isCreated()
		);
	}
	
	
	@Test //Método PUT -> updateTarefa()
	@Transactional
	public void testeRetornoUpdateTarefa() throws Exception {
		URI urlPOST = new URI("/tarefas");
		URI urlPUT = new URI("/tarefas/1");
		String tarefaPostJSON = "{ \"number\" : 1 , \"type\" : \"Baixa Prioridade\" , \"description\" : \"Tarefa Qualquer\"}";
		String tarefaPutJSON = "{ \"number\" : 1101 , \"type\" : \"Urgente\" , \"description\" : \"Realizar Controle de Testes da Aplicação\"}";
	
		mock.perform(
				MockMvcRequestBuilders
					.post(urlPOST)
					.content(tarefaPostJSON)
					.contentType(MediaType.APPLICATION_JSON)
					
			).andExpect(
				MockMvcResultMatchers
					.status().isCreated()
			);

		mock.perform(
				MockMvcRequestBuilders
				.put(urlPUT)
				.content(tarefaPutJSON)
				.contentType(MediaType.APPLICATION_JSON)
				
			).andExpect(
					MockMvcResultMatchers
						.status().isOk()
			);
		
	}
	
	@Test //Método DELETE -> deleteTarefa
	@Transactional
	public void testeRetornoDeleteTarefa() throws Exception {
		URI urlPOST = new URI("/tarefas");
		URI urlDelete = new URI("/tarefas/1");
		
		String tarefaPostJSON = "{ \"number\" : 1 , \"type\" : \"Baixa Prioridade\" , \"description\" : \"Tarefa Qualquer\"}";
		
		mock.perform(
				MockMvcRequestBuilders
					.post(urlPOST)
					.content(tarefaPostJSON)
					.contentType(MediaType.APPLICATION_JSON)
					
			).andExpect(
				MockMvcResultMatchers
					.status().isCreated()
			);
		
		mock.perform(
				MockMvcRequestBuilders
					.delete(urlDelete)
				).andExpect(
					MockMvcResultMatchers
						.status().isOk()
				);
		
	}
	
	
	@Test //Método GET -> getAllTarefas()
	@Transactional
	public void testeRetornoGetAllTarefas() throws Throwable {
		URI url = new URI("/tarefas");
		
		String tarefa1JSON = "{ \"number\" : 1 ,\"type\" : \"Urgente\", \"description\" : \"Realizar Testes de Integração\" }";
		String tarefa2JSON = "{ \"number\" : 2 ,\"type\" : \"Urgente\", \"description\" : \"Realizar Testes Unitários em cada Módulo\" }";

		mock.perform(
				MockMvcRequestBuilders
					.post(url)
					.content(tarefa1JSON)
					.contentType(MediaType.APPLICATION_JSON)
			).andExpect(
				MockMvcResultMatchers
					.status().isCreated()
			);
		
		mock.perform(
				MockMvcRequestBuilders
					.post(url)
					.content(tarefa2JSON)
					.contentType(MediaType.APPLICATION_JSON)
			).andExpect(
				MockMvcResultMatchers
					.status().isCreated()
			);
		mock.perform(
			MockMvcRequestBuilders
				.get(url)
			
		).andExpect(
			MockMvcResultMatchers
				.status().isOk()
		);
	}
	
	@Test //Método GET -> getAllTarefasForType()
	@Transactional
	public void testeRetornoGetAllTarefasForType() throws Throwable {
		URI urlPOST = new URI("/tarefas");
		URI urlGetForType = new URI("/tarefas/type/Urgente");
		
		String tarefa1JSON = "{ \"number\" : 1 ,\"type\" : \"Urgente\", \"description\" : \"Realizar Testes de Integração\" }";
		String tarefa2JSON = "{ \"number\" : 2 ,\"type\" : \"Urgente\", \"description\" : \"Realizar Testes Unitários em cada Módulo\" }";
		String tarefa3JSON = "{ \"number\" : 3 ,\"type\" : \"Baixa Prioridade\", \"description\" : \"Tarefa Qualquer\" }";

		List<String> tarefasDoTipoUrgente = new ArrayList<>();
		
		tarefasDoTipoUrgente.add(tarefa1JSON);
		tarefasDoTipoUrgente.add(tarefa2JSON);
		tarefasDoTipoUrgente.add(tarefa3JSON);
		
		mock.perform(
				MockMvcRequestBuilders
					.post(urlPOST)
					.content(tarefa1JSON)
					.contentType(MediaType.APPLICATION_JSON)
			).andExpect(
				MockMvcResultMatchers.status().isCreated()
			);
		
		mock.perform(
				MockMvcRequestBuilders
					.post(urlPOST)
					.content(tarefa2JSON)
					.contentType(MediaType.APPLICATION_JSON)
			).andExpect(
				MockMvcResultMatchers
					.status().isCreated()
			);
		
		mock.perform(
				MockMvcRequestBuilders
					.post(urlPOST)
					.content(tarefa3JSON)
					.contentType(MediaType.APPLICATION_JSON)
			).andExpect(
				MockMvcResultMatchers
					.status().isCreated()
			);
		
		mock.perform(
			MockMvcRequestBuilders
				.get(urlGetForType)
		).andExpect(
			MockMvcResultMatchers
				.status().isOk()
		);
	}
	
	
}
