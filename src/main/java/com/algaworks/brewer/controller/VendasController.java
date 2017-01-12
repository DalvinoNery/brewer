package com.algaworks.brewer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/vendas")
public class VendasController {

	
	@RequestMapping("nova")
	public ModelAndView novo() {
		
		ModelAndView mv = new ModelAndView("venda/cadastro/index");

		return mv;
	}
}
