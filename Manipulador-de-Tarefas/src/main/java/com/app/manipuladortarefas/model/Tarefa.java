package com.app.manipuladortarefas.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Tarefa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Long id;
	
	@Column
	private Integer number;
	
	@Column
	private String type;
	
	@Column
	private String description;

	public Tarefa() {
		super();
	}

	public Tarefa(int number, String type, String description) {
		this.number = number;
		this.type = type;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (o == null || getClass() != o.getClass()) return false;
	    Tarefa tarefa = (Tarefa) o;
	    return number == tarefa.number && 
	           Objects.equals(type, tarefa.type) && 
	           Objects.equals(description, tarefa.description);
	}

	@Override
	public int hashCode() {
	    return Objects.hash(number, type, description);
	}

}
