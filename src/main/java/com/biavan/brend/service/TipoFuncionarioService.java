package com.biavan.brend.service;

import java.util.List;
import java.util.Map;

import com.biavan.brend.model.TipoFuncionario;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

public interface TipoFuncionarioService {

	public void insereTipoFuncionario(TipoFuncionario tipoFuncionario);

	public void atualizaTipoFuncionario(TipoFuncionario tipoFuncionario);

	public List<TipoFuncionario> listaTipoFuncionarios();
	
	public <E> BootgridData<E> listaTipoFuncionariosBootgrid(BootgridParam query);

	public TipoFuncionario getTipoFuncionarioById(long id);

	public void removeTipoFuncionario(long id);
	
	public Map<Long, String> listaTipoFuncionarioForDropDown();
}
