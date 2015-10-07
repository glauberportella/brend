package com.biavan.brend.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biavan.brend.dao.TipoFuncionarioDAO;
import com.biavan.brend.model.TipoFuncionario;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

@Service
public class TipoFuncionarioServiceImpl implements TipoFuncionarioService {

	private TipoFuncionarioDAO tipoFuncionarioDAO;
	
	public void setTipoFuncionarioDAO(TipoFuncionarioDAO tipoFuncionarioDAO) {
		this.tipoFuncionarioDAO = tipoFuncionarioDAO;
	}
	
	@Override
	@Transactional
	public void insereTipoFuncionario(TipoFuncionario tipoFuncionario) {
		this.tipoFuncionarioDAO.insere(tipoFuncionario);

	}

	@Override
	@Transactional
	public void atualizaTipoFuncionario(TipoFuncionario tipoFuncionario) {
		this.tipoFuncionarioDAO.atualiza(tipoFuncionario);

	}

	@Override
	@Transactional
	public List<TipoFuncionario> listaTipoFuncionarios() {
		return this.tipoFuncionarioDAO.lista();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public BootgridData<TipoFuncionario> listaTipoFuncionariosBootgrid(BootgridParam param) {
		return this.tipoFuncionarioDAO.listaToBootgrid(param);
	}
	
	
	@Override
	@Transactional
	public TipoFuncionario getTipoFuncionarioById(long id) {
		return this.tipoFuncionarioDAO.getById(id);
	}

	@Override
	@Transactional
	public void removeTipoFuncionario(long id) {
		this.tipoFuncionarioDAO.remove(id);

	}
	
	@Override
	@Transactional
	public Map<Long, String> listaTipoFuncionarioForDropDown() {
		Map<Long, String> listaTipoFuncionarios = new LinkedHashMap<Long, String>();
		
		for (TipoFuncionario t : this.tipoFuncionarioDAO.lista()) {
			listaTipoFuncionarios.put(t.getId(), t.getNome());
		}
		
		return listaTipoFuncionarios;
	}

}
