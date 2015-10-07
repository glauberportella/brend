package com.biavan.brend.dao;

import java.util.List;

import com.biavan.brend.model.Funcionario;
import com.biavan.brend.model.Usuario;

public interface UsuarioDAO {
	
	public void insere(Usuario usuario);

	public void atualiza(Usuario usuario);
	
	public List<Usuario> lista();

	public void remove(String login);
	
	public Usuario findByLogin(String login);
	
	public Usuario getByFuncionario(Funcionario funcionario);
	
}
