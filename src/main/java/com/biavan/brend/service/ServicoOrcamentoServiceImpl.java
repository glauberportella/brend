package com.biavan.brend.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biavan.brend.dao.ServicoOrcamentoDAO;
import com.biavan.brend.model.Orcamento;
import com.biavan.brend.model.ServicoOrcamento;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

@Service
public class ServicoOrcamentoServiceImpl implements ServicoOrcamentoService {

	private ServicoOrcamentoDAO servicoOrcamentoDAO;
	
	public void setServicoOrcamentoDAO(ServicoOrcamentoDAO servicoOrcamentoDAO) {
		this.servicoOrcamentoDAO = servicoOrcamentoDAO;
	}
	
	@Override
	@Transactional
	public void insereServicoOrcamento(ServicoOrcamento servicoOrcamentoDAO) {
		this.servicoOrcamentoDAO.insere(servicoOrcamentoDAO);

	}

	@Override
	@Transactional
	public void atualizaServicoOrcamento(ServicoOrcamento servicoOrcamentoDAO) {
		this.servicoOrcamentoDAO.atualiza(servicoOrcamentoDAO);

	}

	@Override
	@Transactional
	public List<ServicoOrcamento> listaServicoOrcamentoByOrcamento(Orcamento orcamento) {
		return this.servicoOrcamentoDAO.listaByOrcamento(orcamento);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public BootgridData<ServicoOrcamento> listaServicoOrcamentoBootgrid(BootgridParam param) {
		return this.servicoOrcamentoDAO.listaToBootgrid(param);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public BootgridData<ServicoOrcamento> listaServicoOrcamentoBootgrid(String palavra, BootgridParam param) {
		return this.servicoOrcamentoDAO.listaToBootgrid(palavra, param);
	}
	
	
	@Override
	@Transactional
	public ServicoOrcamento getServicoOrcamentoById(long id) {
		return this.servicoOrcamentoDAO.getById(id);
	}

	@Override
	@Transactional
	public void removeServicoOrcamentoByOrcamento(Orcamento orcamento) {
		this.servicoOrcamentoDAO.removeByOrcamento(orcamento);

	}
}
