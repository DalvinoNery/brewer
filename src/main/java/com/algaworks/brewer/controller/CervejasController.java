package com.algaworks.brewer.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.algaworks.brewer.model.Cerveja;
import com.algaworks.brewer.model.Origem;
import com.algaworks.brewer.model.Sabor;
import com.algaworks.brewer.repository.Estilos;
import com.algaworks.brewer.service.CadastroCervejaService;

@Controller
public class CervejasController {

	@Autowired
	private Estilos estilos;
	
	@Autowired
	private CadastroCervejaService cadastroCervejaService;

	/*
	 * Métodos novo e cadastrar possuem mesmo @RequestMapping
	 * 
	 * Se a requisição for get, será chamdo o método novo(); Se for post, será
	 * chamado o método cadastrar();
	 */
	@RequestMapping("/cerveja/novo")
	public ModelAndView novo(Cerveja cerveja) {
		ModelAndView mv = new ModelAndView("cerveja/cadastro/index");
		mv.addObject("sabores", Sabor.values());
		mv.addObject("estilos", estilos.findAll());
		mv.addObject("origens", Origem.values());
		return mv;
	}

	@RequestMapping(value = "/cerveja/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Cerveja cerveja, BindingResult result, Model model, RedirectAttributes attributes) {
		System.out.println("sku "+cerveja.getSku());
		System.out.println("nome "+cerveja.getNome());
		System.out.println("descricao "+cerveja.getDescricao());
		System.out.println("estilo "+cerveja.getEstilo().getCodigo());
		System.out.println("origem "+cerveja.getOrigem());
		System.out.println("sabor "+cerveja.getSabor());
		System.out.println("estoque "+cerveja.getQuantidadeEstoque());
		System.out.println("comissao "+cerveja.getComissao());
		System.out.println("teor "+cerveja.getTeorAlcoolico());
		System.out.println("valor "+cerveja.getValor());
		if (result.hasErrors()) {
			return novo(cerveja);
		}

		cadastroCervejaService.salvar(cerveja);
		attributes.addFlashAttribute("mensagem", "Cerveja salva com sucessso!");
		return new ModelAndView("redirect:/cerveja/novo");
	}

}