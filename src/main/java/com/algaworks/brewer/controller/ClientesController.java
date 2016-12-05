package com.algaworks.brewer.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class ClientesController {

	
	/*
	 * Métodos novo e cadastrar possuem mesmo @RequestMapping
	 * 
	 * Se a requisição for get, será chamdo o método novo();
	 * Se for post, será chamado o método cadastrar();
	 */
	@RequestMapping("/cliente/novo")
	public String novo(){
		return "cliente/cadastro/index";
	}
	
/*	@RequestMapping(value = "/cliente/novo", method = RequestMethod.POST)
	public String cadastrar(@Valid Cerveja cerveja, BindingResult result, Model model, RedirectAttributes attributes){
		if(result.hasErrors()){
			return novo(cerveja);
			
		}
		
		attributes.addFlashAttribute("mensagem", "Cerveja salva com sucessso!");
		
		return "redirect:/cerveja/novo";
	}*/
}
