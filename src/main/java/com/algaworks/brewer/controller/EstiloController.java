package com.algaworks.brewer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EstiloController {
	
	/*
	 * Métodos novo e cadastrar possuem mesmo @RequestMapping
	 * 
	 * Se a requisição for get, será chamdo o método novo();
	 * Se for post, será chamado o método cadastrar();
	 */
	@RequestMapping("/estilo/novo")
	public String novo(){
		return "estilo/cadastro/index";
	}

}