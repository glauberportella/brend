package com.biavan.brend.dao;

import java.util.List;

import com.biavan.brend.model.Perfil;
import com.biavan.brend.model.Recurso;

public interface PerfilDAO {
	
	public void insere(Perfil perfil);

	public void atualiza(Perfil perfil);

	public List<Perfil> lista();

	public Perfil getById(long id);

	public void remove(long id);
	
	public List<Recurso> getRecursosByPerfil(Perfil perfil);
}
