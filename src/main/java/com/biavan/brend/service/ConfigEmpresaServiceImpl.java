package com.biavan.brend.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biavan.brend.dao.ConfigEmpresaDAO;
import com.biavan.brend.model.ConfigEmpresa;

@Service
public class ConfigEmpresaServiceImpl implements ConfigEmpresaService {

	private ConfigEmpresaDAO configEmpresaDAO;
	
	public void setConfigEmpresaDAO(ConfigEmpresaDAO configEmpresaDAO) {
		this.configEmpresaDAO = configEmpresaDAO;
	}
	
	@Override
	@Transactional
	public void atualizaConfigEmpresa(ConfigEmpresa configEmpresa) {
		configEmpresa.setId(1);
		this.configEmpresaDAO.atualiza(configEmpresa);

	}

	@Override
	@Transactional
	public ConfigEmpresa getConfigEmpresaById(long id) {
		return this.configEmpresaDAO.getById(id);
	}
}
