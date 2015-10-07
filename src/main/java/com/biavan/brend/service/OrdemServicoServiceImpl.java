package com.biavan.brend.service;

import java.util.Date;
import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biavan.brend.dao.OrdemServicoDAO;
import com.biavan.brend.dao.PecaOrdemServicoDAO;
import com.biavan.brend.dao.ServicoOrdemServicoDAO;
import com.biavan.brend.dao.StatusOrdemServicoDAO;
import com.biavan.brend.enums.StatusOS;
import com.biavan.brend.model.Funcionario;
import com.biavan.brend.model.Orcamento;
import com.biavan.brend.model.OrdemServico;
import com.biavan.brend.model.PecaOrdemServico;
import com.biavan.brend.model.ServicoOrdemServico;
import com.biavan.brend.model.StatusOrdemServico;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;
import com.biavan.brend.util.Formatos;

@Service
public class OrdemServicoServiceImpl implements OrdemServicoService {
	
	private OrdemServicoDAO ordemServicoDAO;
	private PecaOrdemServicoDAO pecaOrdemServicoDAO;
	private ServicoOrdemServicoDAO servicoOrdemServicoDAO;
	private StatusOrdemServicoDAO statusOrdemServicoDAO;
	
	public void setOrdemServicoDAO(OrdemServicoDAO ordemServicoDAO) {
		this.ordemServicoDAO = ordemServicoDAO;
	}
	
	public void setPecaOrdemServicoDAO(PecaOrdemServicoDAO pecaOrdemServicoDAO) {
		this.pecaOrdemServicoDAO = pecaOrdemServicoDAO;
	}
	
	public void setServicoOrdemServicoDAO(ServicoOrdemServicoDAO servicoOrdemServicoDAO) {
		this.servicoOrdemServicoDAO = servicoOrdemServicoDAO;
	}

	public void setStatusOrdemServicoDAO(StatusOrdemServicoDAO statusOrdemServicoDAO) {
		this.statusOrdemServicoDAO = statusOrdemServicoDAO;
	}
	@Override
	@Transactional
	public void insereOrdemServico(OrdemServico ordemServico, List<PecaOrdemServico> pecasOrdemServico, List<ServicoOrdemServico> servicosOrdemServico) {
		ordemServicoDAO.insere(ordemServico);
		
		for(PecaOrdemServico pp : pecasOrdemServico) {
			pp.setOrdemServico(ordemServico);
			pecaOrdemServicoDAO.insere(pp);
		}
		
		for (ServicoOrdemServico ss : servicosOrdemServico) {
			ss.setOrdemServico(ordemServico);
			servicoOrdemServicoDAO.insere(ss);
		}
		
		StatusOrdemServico statusOrdemServico = new StatusOrdemServico();
		statusOrdemServico.setOrdemServico(ordemServico);
		statusOrdemServico.setDataInicio(new Date());
		statusOrdemServico.setDataFim(Formatos.getUltimaData());
		statusOrdemServico.setDescricao("Status inicial");
		statusOrdemServico.setStatusOS(StatusOS.PENDENTE);
		statusOrdemServicoDAO.insere(statusOrdemServico);
		
		if (ordemServico.getOrdemServicoPai() != null) {
			OrdemServico ordemServicoPai = ordemServico.getOrdemServicoPai();
			ordemServicoPai.setValorTotalAdicionais(ordemServicoDAO.valorTotalOrdemServicoAdicionais(ordemServicoPai));
			ordemServicoPai.setValorTotal(ordemServicoPai.getValorTotalAdicionais() + ordemServicoPai.getValorSubTotal());
					
			ordemServicoDAO.atualiza(ordemServicoPai);
		}

	}

	@Override
	@Transactional
	public void atualizaOrdemServico(OrdemServico ordemServico, 
			List<PecaOrdemServico> pecasOrdemServico, 
			List<ServicoOrdemServico> servicosOrdemServico) {
		
		ordemServicoDAO.atualiza(ordemServico);
		
		pecaOrdemServicoDAO.removeByOrdemServico(ordemServico);
		servicoOrdemServicoDAO.removeByOrdemServico(ordemServico);
		
		for(PecaOrdemServico pp : pecasOrdemServico) {
			pp.setOrdemServico(ordemServico);
			pecaOrdemServicoDAO.insere(pp);
		}
		
		for (ServicoOrdemServico ss : servicosOrdemServico)
		{
			ss.setOrdemServico(ordemServico);
			servicoOrdemServicoDAO.insere(ss);
		}
		
		if (ordemServico.getOrdemServicoPai() != null) {
			OrdemServico ordemServicoPai = ordemServico.getOrdemServicoPai();
			ordemServicoPai.setValorTotalAdicionais(ordemServicoDAO.valorTotalOrdemServicoAdicionais(ordemServicoPai));
			ordemServicoPai.setValorTotal(ordemServicoPai.getValorTotalAdicionais() + ordemServicoPai.getValorSubTotal());
					
			ordemServicoDAO.atualiza(ordemServicoPai);
		}
			
	}

	@Override
	@Transactional
	public List<OrdemServico> listaOrdemServico() {
		return ordemServicoDAO.lista();
	}

	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public BootgridData<OrdemServico> listaOrdemServicosBootgrid(BootgridParam param) {
		BootgridData<OrdemServico> bootgridData = ordemServicoDAO.listaToBootgrid(param);

		for(OrdemServico o : bootgridData.getRows()) {
			o.setModeloVeiculo();
			o.setHistorico(statusOrdemServicoDAO.listaByOrdemServico(o));
			o.getStatusAtual().setOrdemServico(null);
		}
		
		return bootgridData;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public BootgridData<OrdemServico> listaOrdemServicosAdicionaisBootgrid(OrdemServico ordemServicoPai, 
			BootgridParam param) {
		BootgridData<OrdemServico> bootgridData = ordemServicoDAO.listaAdicionalToBootgrid(ordemServicoPai, param);

		for(OrdemServico o : bootgridData.getRows()) {
			o.setModeloVeiculo();
			o.setHistorico(statusOrdemServicoDAO.listaByOrdemServico(o));
			o.getStatusAtual().setOrdemServico(null);
			o.setOrdemServicoPai(null);
		}
		
		return bootgridData;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public BootgridData<OrdemServico> listaOrdemServicosBootgrid(String palavra, BootgridParam param) {
		BootgridData<OrdemServico> bootgridData = ordemServicoDAO.listaToBootgrid(palavra, param);

		for(OrdemServico o : bootgridData.getRows()) {
			o.setModeloVeiculo();
			o.setHistorico(statusOrdemServicoDAO.listaByOrdemServico(o));
			o.getStatusAtual().setOrdemServico(null);
		}
		
		return bootgridData;
	}
	
	@Override
	@Transactional
	public OrdemServico getOrdemServicoById(long id) {
		try {
			OrdemServico ordemServico = this.ordemServicoDAO.getById(id);
			ordemServico.getOrcamento();
			return ordemServico;
		} catch (ObjectNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	@Transactional
	public void removeOrdemServico(long id) {
		this.ordemServicoDAO.remove(id);
		
	}

	@Override
	@Transactional
	public OrdemServico getOrdemServicoByOrcamento(Orcamento orcamento) {
		return this.ordemServicoDAO.getOrdemServicoByOrcamento(orcamento);
	}

	@Override
	@Transactional
	public List<PecaOrdemServico> listaPecas(OrdemServico ordemServico) {
		return pecaOrdemServicoDAO.listaByOrdemServico(ordemServico);
	}
	
	@Override
	@Transactional
	public int qtdeOrdemServicoByMecanico(Funcionario funcionario) {
		List<OrdemServico> listaOrdemServico = ordemServicoDAO.listaByMecanico(funcionario);
		return listaOrdemServico.size();
	}

	@Override
	@Transactional
	public double getValorTotalOrdemServicoAdicional (OrdemServico ordemServico) {
		return ordemServicoDAO.valorTotalOrdemServicoAdicionais(ordemServico);
	}
}
