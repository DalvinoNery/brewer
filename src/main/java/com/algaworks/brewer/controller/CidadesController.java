package com.algaworks.brewer.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.algaworks.brewer.controller.page.PageWrapper;
import com.algaworks.brewer.model.Cidade;
import com.algaworks.brewer.repository.Cidades;
import com.algaworks.brewer.repository.Estados;
import com.algaworks.brewer.repository.filter.CidadeFilter;
import com.algaworks.brewer.service.CadastroCidadeService;
import com.algaworks.brewer.service.exception.NomeCidadeJaCadastradaException;

@Controller
@RequestMapping("/cidades")
public class CidadesController {
	
	@Autowired
	private Cidades cidades;
	
	@Autowired
	private Estados estados;
	
	@Autowired
	private CadastroCidadeService cadastroCidadeService;
	
	/*
	 * Métodos novo e cadastrar possuem mesmo @RequestMapping
	 * 
	 * Se a requisição for get, será chamdo o método novo();
	 * Se for post, será chamado o método cadastrar();
	 */
	@RequestMapping("/nova")
	public ModelAndView nova(Cidade cidade){
		ModelAndView mv = new ModelAndView("cidade/cadastro/index");
		mv.addObject("estados", estados.findAll());
		
		return mv;
	}
	
	
	@RequestMapping(value = "/nova", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Cidade cidade, BindingResult result, RedirectAttributes attributes) {

		if (result.hasErrors()) {
			return nova(cidade);
		}
		try {
			cadastroCidadeService.salvar(cidade);
		} catch (NomeCidadeJaCadastradaException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return nova(cidade);
		}

		attributes.addFlashAttribute("mensagem", "Cidade salva com sucessso!");

		return new ModelAndView("redirect:/cidades/nova");
	}
	
	@RequestMapping (consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Cidade> pesquisarPorCodigoEstado(
			@RequestParam(name="estado", defaultValue = "-1")Long codigoEstado){
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {}
		return cidades.findByEstadoCodigo(codigoEstado);
	}
	
	public String pesquisar(){
		
		return "cidade/cadastro/index";
	}

	@GetMapping
	public ModelAndView pesquisar(CidadeFilter cidadeFilter, @PageableDefault(size= 2) Pageable pageable, HttpServletRequest httpServletRequest){
		ModelAndView mv = new ModelAndView("cidade/pesquisa/index");
		mv.addObject("estados", estados.findAll());
		
		PageWrapper<Cidade> paginaWrapper = new PageWrapper<>(cidades.filtrar(cidadeFilter, pageable),httpServletRequest);
		
		mv.addObject("pagina", paginaWrapper);
		
		return mv;
	}
}
