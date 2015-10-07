package com.biavan.brend.dao;

import java.util.List;

import com.biavan.brend.model.TipoFuncionario;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

public interface TipoFuncionarioDAO {
	
	public void insere(TipoFuncionario tipoFuncionario);

	public void atualiza(TipoFuncionario tipoFuncionario);

	public BootgridData<TipoFuncionario> listaToBootgrid(BootgridParam param);
	
	public List<TipoFuncionario> lista();

	public TipoFuncionario getById(long id);

	public void remove(long id);
}
