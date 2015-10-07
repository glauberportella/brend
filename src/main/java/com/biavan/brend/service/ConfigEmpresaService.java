package com.biavan.brend.service;

import com.biavan.brend.model.ConfigEmpresa;

public interface ConfigEmpresaService {

	public void atualizaConfigEmpresa(ConfigEmpresa configEmpresa);

	public ConfigEmpresa getConfigEmpresaById(long id);
	
}
