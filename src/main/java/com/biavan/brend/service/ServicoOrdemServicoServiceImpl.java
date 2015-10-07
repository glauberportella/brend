package com.biavan.brend.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biavan.brend.dao.ServicoOrdemServicoDAO;
import com.biavan.brend.model.OrdemServico;
import com.biavan.brend.model.ServicoOrdemServico;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

@Service
public class ServicoOrdemServicoServiceImpl implements ServicoOrdemServicoService {

	private ServicoOrdemServicoDAO servicoOrdemServicoDAO;
	
	public void setServicoOrdemServicoDAO(ServicoOrdemServicoDAO servicoOrdemServicoDAO) {
		this.servicoOrdemServicoDAO = servicoOrdemServicoDAO;
	}
	
	@Override
	@Transactional
	public void insereServicoOrdemServico(ServicoOrdemServico servicoOrdemServicoDAO) {
		this.servicoOrdemServicoDAO.insere(servicoOrdemServicoDAO);

	}

	@Override
	@Transactional
	public void atualizaServicoOrdemServico(ServicoOrdemServico servicoOrdemServicoDAO) {
		this.servicoOrdemServicoDAO.atualiza(servicoOrdemServicoDAO);

	}

	@Override
	@Transactional
	public List<ServicoOrdemServico> listaServicoOrdemServicoByOrdemServico(OrdemServico ordemServico) {
		return this.servicoOrdemServicoDAO.listaByOrdemServico(ordemServico);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public BootgridData<ServicoOrdemServico> listaServicoOrdemServicoBootgrid(BootgridParam param) {
		return this.servicoOrdemServicoDAO.listaToBootgrid(param);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public BootgridData<ServicoOrdemServico> listaServicoOrdemServicoBootgrid(String palavra, BootgridParam param) {
		return this.servicoOrdemServicoDAO.listaToBootgrid(palavra, param);
	}
	
	
	@Override
	@Transactional
	public ServicoOrdemServico getServicoOrdemServicoById(long id) {
		return this.servicoOrdemServicoDAO.getById(id);
	}

	@Override
	@Transactional
	public void removeServicoOrdemServicoByOrdemServico(OrdemServico ordemServico) {
		this.servicoOrdemServicoDAO.removeByOrdemServico(ordemServico);

	}
}
