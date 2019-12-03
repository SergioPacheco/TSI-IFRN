package com.algaworks.brewer.controller.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.algaworks.brewer.service.exception.NomeEstiloJaCadastradoException;

//Qualquer lugar da aplicação que lançar um NomeEstiloJaCadastradoException cai aqui. Por isso @ControllerAdvice
@ControllerAdvice//11.4 
public class ControllerAdviceExceptionHandler {
	
	@ExceptionHandler(NomeEstiloJaCadastradoException.class)
	public ResponseEntity<String> handlerNomeEstiloJaCadastradoException(NomeEstiloJaCadastradoException e){
		
		/*Nesse caso sabemos qual vai ser o retorno do badRequest pois o body retorna um responseEntity do tipo string
		 * Não havendo nescessidade do ponto de "?" por isso "String" */
		
		return ResponseEntity.badRequest().body(e.getMessage());
	}

}
