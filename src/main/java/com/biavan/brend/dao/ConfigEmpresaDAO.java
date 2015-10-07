package com.biavan.brend.dao;

import com.biavan.brend.model.ConfigEmpresa;

public interface ConfigEmpresaDAO {
	
	public void insere(ConfigEmpresa configEmpresa);
	
	public void atualiza(ConfigEmpresa configEmpresa);

	public ConfigEmpresa getById(long id);

}
