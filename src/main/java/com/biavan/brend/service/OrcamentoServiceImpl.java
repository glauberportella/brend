package com.biavan.brend.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biavan.brend.dao.OrcamentoDAO;
import com.biavan.brend.dao.PecaOrcamentoDAO;
import com.biavan.brend.dao.ServicoOrcamentoDAO;
import com.biavan.brend.model.Orcamento;
import com.biavan.brend.model.OrdemServico;
import com.biavan.brend.model.PecaOrcamento;
import com.biavan.brend.model.ServicoOrcamento;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

@Service
public class OrcamentoServiceImpl implements OrcamentoService {
	
	private OrcamentoDAO orcamentoDAO;
	private PecaOrcamentoDAO pecaOrcamentoDAO;
	private ServicoOrcamentoDAO servicoOrcamentoDAO;
	
	public void setOrcamentoDAO(OrcamentoDAO orcamentoDAO) {
		this.orcamentoDAO = orcamentoDAO;
	}
	
	public void setPecaOrcamentoDAO(PecaOrcamentoDAO pecaOrcamentoDAO) {
		this.pecaOrcamentoDAO = pecaOrcamentoDAO;
	}
	
	public void setServicoOrcamentoDAO(ServicoOrcamentoDAO servicoOrcamentoDAO) {
		this.servicoOrcamentoDAO = servicoOrcamentoDAO;
	}

	@Override
	@Transactional
	public void atualizaOrcamento(Orcamento orcamento, List<PecaOrcamento> pecasOrcamento, List<ServicoOrcamento> servicosOrcamento) {
		//this.orcamentoDAO.atualiza(orcamento);
		orcamentoDAO.atualiza(orcamento);
		
		pecaOrcamentoDAO.removeByOrcamento(orcamento);
		servicoOrcamentoDAO.removeByOrcamento(orcamento);
		
		for(PecaOrcamento pp : pecasOrcamento) {
			pp.setOrcamento(orcamento);
			pecaOrcamentoDAO.insere(pp);
		}
		
		for (ServicoOrcamento ss : servicosOrcamento)
		{
			ss.setOrcamento(orcamento);
			servicoOrcamentoDAO.insere(ss);
		}
		
	}

	@Override
	@Transactional
	public List<Orcamento> listaOrcamentos() {
		return orcamentoDAO.lista();
	}

	@Override
	@Transactional
	public void insereOrcamento(Orcamento orcamento, List<PecaOrcamento> pecasOrcamento, List<ServicoOrcamento> servicosOrcamento) {
		orcamentoDAO.insere(orcamento);
		for(PecaOrcamento pp : pecasOrcamento) {
			pp.setOrcamento(orcamento);
			pecaOrcamentoDAO.insere(pp);
		}
		
		for (ServicoOrcamento ss : servicosOrcamento) {
			ss.setOrcamento(orcamento);
			servicoOrcamentoDAO.insere(ss);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public BootgridData<Orcamento> listaOrcamentosBootgrid(BootgridParam param) {
		BootgridData<Orcamento> bootgridData = orcamentoDAO.listaToBootgrid(param);

		for(Orcamento o : bootgridData.getRows()) {
			o.setModeloVeiculo();
		}
		
		return bootgridData;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public BootgridData<Orcamento> listaOrcamentosBootgrid(String palavra, BootgridParam param) {
		BootgridData<Orcamento> bootgridData = orcamentoDAO.listaToBootgrid(palavra, param);

		for(Orcamento o : bootgridData.getRows()) {
			o.setModeloVeiculo();
		}
		
		return bootgridData;
	}
	
	@Override
	@Transactional
	public Orcamento getOrcamentoById(long id) {
		return this.orcamentoDAO.getById(id);
	}

	@Override
	@Transactional
	public void removeOrcamento(long id) {
		this.orcamentoDAO.remove(id);
		
	}
	
	@Override
	@Transactional
	public Orcamento getOrcamentoByOrdemServico(OrdemServico ordemServico) {
		return orcamentoDAO.getOrcamentoByOrdemServico(ordemServico);
	}
}
