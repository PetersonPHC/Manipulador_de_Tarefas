package com.app.manipuladortarefas.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.app.manipuladortarefas.exception.TarefaAlreadyExists;
import com.app.manipuladortarefas.exception.TarefaIllegalArgumentException;
import com.app.manipuladortarefas.exception.TarefaNotFoundException;


@RestControllerAdvice
public class ApplicationControllerAdvice {
	
	private final Logger logger = LoggerFactory.getLogger(ApplicationControllerAdvice.class);
	
	@ExceptionHandler(TarefaIllegalArgumentException.class)
	public ResponseEntity<String> handlerIllegalArgumentException(TarefaIllegalArgumentException exception){
		return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(TarefaNotFoundException.class)
	public ResponseEntity<String> handlerNotFoundException(TarefaNotFoundException exception){
		return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(TarefaAlreadyExists.class)
	public ResponseEntity<String> handlerConflitExistsValue(TarefaAlreadyExists exception){
		return new ResponseEntity<String>(exception.getMessage(), HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(Throwable.class)
	public ResponseEntity<String> handlerGenericException(Throwable unexpectedException){
		String message = "Unexpected Exception, See the Logs";
		logger.error(message, unexpectedException);
		
		return new ResponseEntity<String>(message, HttpStatus.INTERNAL_SERVER_ERROR);	
	}
}
