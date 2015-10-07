package com.biavan.brend.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biavan.brend.dao.FornecedorDAO;
import com.biavan.brend.model.Fornecedor;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

@Service
public class FornecedorServiceImpl implements FornecedorService {
	
private FornecedorDAO fornecedorDAO;
	
	public void setFornecedorDAO(FornecedorDAO fornecedorDAO) {
		this.fornecedorDAO = fornecedorDAO;
	}
	
	@Override
	@Transactional
	public void insereFornecedor(Fornecedor fornecedor) {
		this.fornecedorDAO.insere(fornecedor);

	}

	@Override
	@Transactional
	public void atualizaFornecedor(Fornecedor fornecedor) {
		this.fornecedorDAO.atualiza(fornecedor);

	}

	@Override
	@Transactional
	public List<Fornecedor> listaFornecedores() {
		return this.fornecedorDAO.lista();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public BootgridData<Fornecedor> listaFornecedoresBootgrid(BootgridParam param) {
		return this.fornecedorDAO.listaToBootgrid(param);
	}
	
	
	@Override
	@Transactional
	public Fornecedor getFornecedorById(long id) {
		return this.fornecedorDAO.getById(id);
	}

	@Override
	@Transactional
	public void removeFornecedor(long id) {
		this.fornecedorDAO.remove(id);

	}
	
	@Override
	@Transactional
	public Map<Long, String> listaFornecedorForDropDown() {
		Map<Long, String> listaFornecedores = new LinkedHashMap<Long, String>();
		
		for (Fornecedor f : this.fornecedorDAO.lista()) {
			listaFornecedores.put(f.getId(), f.getRazao());
		}
		return listaFornecedores;
	}
}
