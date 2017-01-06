package com.algaworks.brewer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.brewer.model.Cidade;
import com.algaworks.brewer.repository.Cidades;
import com.algaworks.brewer.service.exception.NomeCidadeJaCadastradaException;

@Service
public class CadastroCidadeService {
	
	
	@Autowired
	private Cidades cidades;
	
	@Transactional
	public Cidade salvar(Cidade cidade){
		Optional<Cidade> cidadeOptional = cidades.findByNomeAndEstado(cidade.getNome(), cidade.getEstado());
		if(cidadeOptional.isPresent()){
			throw new NomeCidadeJaCadastradaException("Cidade já cadastrada no estado selecionado.");
		}
			
		return cidades.save(cidade);
	}

}
