package com.biavan.brend.service;

import java.util.List;

import com.biavan.brend.model.Funcionario;
import com.biavan.brend.model.Usuario;

public interface UsuarioService {

	public void insereUsuario(Usuario usuario);

	public void atualizaUsuario(Usuario usuario);
	
	public void atualizaUsuario(Usuario usuario, String senha);

	public List<Usuario> listaUsuarios();
	
	public Usuario getUsuarioByLogin(String login);

	public void removeUsuario(String login);
	
	public Usuario getUsuarioByFuncionario(Funcionario funcionario);
		
}
