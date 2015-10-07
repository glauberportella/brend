package com.biavan.brend.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biavan.brend.dao.OrdemServicoDAO;
import com.biavan.brend.dao.PecaOrdemServicoDAO;
import com.biavan.brend.dao.StatusOrdemServicoDAO;
import com.biavan.brend.model.OrdemServico;
import com.biavan.brend.model.PecaOrdemServico;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

@Service
public class PecaOrdemServicoServiceImpl implements PecaOrdemServicoService {

	private PecaOrdemServicoDAO pecaOrdemServicoDAO;
	private OrdemServicoDAO ordemServicoDAO;
	private StatusOrdemServicoDAO statusOrdemServicoDAO;
	
	public void setPecaOrdemServicoDAO(PecaOrdemServicoDAO pecaOrdemServicoDAO) {
		this.pecaOrdemServicoDAO = pecaOrdemServicoDAO;
	}

	public void setOrdemServicoDAO(OrdemServicoDAO ordemServicoDAO) {
		this.ordemServicoDAO = ordemServicoDAO;
	}	

	public void setStatusOrdemServicoDAO(StatusOrdemServicoDAO statusOrdemServicoDAO) {
		this.statusOrdemServicoDAO = statusOrdemServicoDAO;
	}	
	
	
	@Override
	@Transactional
	public void inserePecaOrdemServico(PecaOrdemServico pecaOrdemServico) {
		this.pecaOrdemServicoDAO.insere(pecaOrdemServico);

	}

	@Override
	@Transactional
	public void atualizaPecaOrdemServico(PecaOrdemServico pecaOrdemServico) {
		this.pecaOrdemServicoDAO.atualiza(pecaOrdemServico);

	}

	@Override
	@Transactional
	public List<PecaOrdemServico> listaPecaOrdemServicoByOrdemServico(OrdemServico ordemServico) {
		List<PecaOrdemServico> pecas = pecaOrdemServicoDAO.listaByOrdemServico(ordemServico);
		for(PecaOrdemServico p : pecas) {
			p.getOrdemServico().setHistorico(statusOrdemServicoDAO.
					listaByOrdemServico(ordemServico));
		}
		return pecas;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public BootgridData<PecaOrdemServico> listaPecaOrdemServicoBootgrid(BootgridParam param) {
		return this.pecaOrdemServicoDAO.listaToBootgrid(param);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public BootgridData<PecaOrdemServico> listaPecaOrdemServicoBootgrid(String palavra, BootgridParam param) {
		return this.pecaOrdemServicoDAO.listaToBootgrid(palavra, param);
	}
	
	
	@Override
	@Transactional
	public PecaOrdemServico getPecaOrdemServicoById(long id) {
		return this.pecaOrdemServicoDAO.getById(id);
	}

	@Override
	@Transactional
	public void removePecaOrdemServicoByOrdemServico(OrdemServico ordemServico) {
		this.pecaOrdemServicoDAO.removeByOrdemServico(ordemServico);

	}
}
