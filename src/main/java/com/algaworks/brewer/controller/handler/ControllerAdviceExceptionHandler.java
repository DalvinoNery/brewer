package com.algaworks.brewer.controller.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.algaworks.brewer.service.exception.NomeEstiloJaCadastradoException;

/*
 * Classe utilizada para gerenciamento de exceções
 */
@ControllerAdvice
public class ControllerAdviceExceptionHandler {
	
	/*
	 * Quando algum método que acionar a exceção NomeEstiloJaCadastradoException,
	 *  essa classe é ativada para aplicar a exceção
	 */
	@ExceptionHandler(NomeEstiloJaCadastradoException.class)
	public ResponseEntity<String> handleNomeEstiloJaCadastradoException(NomeEstiloJaCadastradoException e){
		return ResponseEntity.badRequest().body(e.getMessage());
	}

}
