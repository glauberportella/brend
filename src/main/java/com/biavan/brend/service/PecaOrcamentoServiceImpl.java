package com.biavan.brend.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biavan.brend.dao.PecaOrcamentoDAO;
import com.biavan.brend.model.Orcamento;
import com.biavan.brend.model.PecaOrcamento;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

@Service
public class PecaOrcamentoServiceImpl implements PecaOrcamentoService {

	private PecaOrcamentoDAO pecaOrcamentoDAO;
	
	public void setPecaOrcamentoDAO(PecaOrcamentoDAO pecaOrcamentoDAO) {
		this.pecaOrcamentoDAO = pecaOrcamentoDAO;
	}
	
	@Override
	@Transactional
	public void inserePecaOrcamento(PecaOrcamento pecaOrcamento) {
		this.pecaOrcamentoDAO.insere(pecaOrcamento);

	}

	@Override
	@Transactional
	public void atualizaPecaOrcamento(PecaOrcamento pecaOrcamento) {
		this.pecaOrcamentoDAO.atualiza(pecaOrcamento);

	}

	@Override
	@Transactional
	public List<PecaOrcamento> listaPecaOrcamentoByOrcamento(Orcamento orcamento) {
		return this.pecaOrcamentoDAO.listaByOrcamento(orcamento);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public BootgridData<PecaOrcamento> listaPecaOrcamentoBootgrid(BootgridParam param) {
		return this.pecaOrcamentoDAO.listaToBootgrid(param);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public BootgridData<PecaOrcamento> listaPecaOrcamentoBootgrid(String palavra, BootgridParam param) {
		return this.pecaOrcamentoDAO.listaToBootgrid(palavra, param);
	}
	
	
	@Override
	@Transactional
	public PecaOrcamento getPecaOrcamentoById(long id) {
		return this.pecaOrcamentoDAO.getById(id);
	}

	@Override
	@Transactional
	public void removePecaOrcamentoByOrcamento(Orcamento orcamento) {
		this.pecaOrcamentoDAO.removeByOrcamento(orcamento);

	}
}
