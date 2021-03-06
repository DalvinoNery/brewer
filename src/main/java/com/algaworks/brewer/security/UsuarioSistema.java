package com.algaworks.brewer.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.algaworks.brewer.model.Usuario;

public class UsuarioSistema extends User{

	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	
	//Disponibilizando informações do usuário logado para o thymeleaf apresentar na barra de navegação
	public UsuarioSistema(Usuario usuario, Collection<? extends GrantedAuthority> authorities) {
		super(usuario.getEmail(), usuario.getSenha(), authorities);
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}


}
