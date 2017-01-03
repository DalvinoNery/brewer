package com.algaworks.brewer.controller.page;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

public class PageWrapper<T>   {

	private Page<T> page;
	private UriComponentsBuilder uriBuilder;

	public PageWrapper(Page<T> page, HttpServletRequest httpServletRequest) {
		this.page = page;
	//	this.uriBuilder = ServletUriComponentsBuilder.fromRequest(httpServletRequest); foi subistituido porque ao pesquisar nomes compostos
		//(cerveja doce) o spring não conseque tratar o espaço (+), gerando assim um errro.
		String httpUrl = httpServletRequest.getRequestURL().append(
				httpServletRequest.getQueryString() != null ? "?" + httpServletRequest.getQueryString() : "")
				.toString().replaceAll("\\+", "%20");
         this.uriBuilder = UriComponentsBuilder.fromHttpUrl(httpUrl);
	}
	
	public List<T> getConteudo(){
		return page.getContent();
	}
	
	public int getAtual(){
		return page.getNumber();
	}
	
	public int getTotal(){
		return page.getTotalPages();
	}
	public boolean isVazia(){
		return page.getContent().isEmpty();
	}
	
	public boolean isPrimeira(){
		return  page.isFirst();
	}
	
	public boolean isUltima(){
		return  page.isLast();
	}
	
	public String urlParaPagina(int pagina){
		return uriBuilder.replaceQueryParam("page", pagina).build(true).encode().toUriString();
	}
	
	public String urlOrdenada(String propriedade){
		UriComponentsBuilder uriBIlderOrder = UriComponentsBuilder.fromUriString(uriBuilder.build(true).encode().toUriString());
		
		String valorSort = String.format("%s,%s", propriedade, inverterDirecao(propriedade));
		
		return uriBIlderOrder.replaceQueryParam("sort", valorSort ).build(true).encode().toUriString();
	}
	
	public String inverterDirecao(String propriedade){
		String direcao = "asc";
		
		Order order = page.getSort() != null ? page.getSort().getOrderFor(propriedade) : null;
		if(order != null){
			direcao =  Sort.Direction.ASC.equals(order.getDirection()) ? "desc" : "asc";
		}
		
		return direcao;
	}
		
	/*
	 * Altera o icone de ordenação da tabela de pesquisa. 
	 */
	public boolean descendente(String propriedade){
		return inverterDirecao(propriedade).equals("asc");
	}
	
	public boolean ordenada(String propriedade){
		Order order = page.getSort() != null ? page.getSort().getOrderFor(propriedade) : null;
		
		if(order==null){
			return false;
		}
		
		return page.getSort().getOrderFor(propriedade) != null ? true : false;
	}
}
