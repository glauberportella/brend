package com.biavan.brend.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biavan.brend.dao.PecaDAO;
import com.biavan.brend.dao.PecaOrdemServicoDAO;
import com.biavan.brend.dao.SolicitacaoRetiradaDAO;
import com.biavan.brend.dao.StatusOrdemServicoDAO;
import com.biavan.brend.model.OrdemServico;
import com.biavan.brend.model.PecaOrdemServico;
import com.biavan.brend.model.SolicitacaoRetirada;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

@Service
public class SolicitacaoRetiradaServiceImpl implements SolicitacaoRetiradaService {
	
	private SolicitacaoRetiradaDAO solicitacaoRetiradaDAO;
	private PecaDAO pecaDAO;
	private PecaOrdemServicoDAO pecaOrdemServicoDAO;
	private StatusOrdemServicoDAO statusOrdemServicoDAO;
	
	public void setSolicitacaoRetiradaDAO(SolicitacaoRetiradaDAO solicitacaoRetiradaDAO) {
		this.solicitacaoRetiradaDAO = solicitacaoRetiradaDAO;
	}
	
	public void setPecaDAO(PecaDAO pecaDAO) {
		this.pecaDAO = pecaDAO;
	}
	
	public void setPecaOrdemServicoDAO(PecaOrdemServicoDAO pecaOrdemServicoDAO) {
		this.pecaOrdemServicoDAO = pecaOrdemServicoDAO;
	}

	public void setStatusOrdemServicoDAO(StatusOrdemServicoDAO statusOrdemServicoDAO) {
		this.statusOrdemServicoDAO = statusOrdemServicoDAO;
	}

	@Override
	@Transactional
	public void insereSolicitacaoRetirada(SolicitacaoRetirada solicitacaoRetirada) {
		solicitacaoRetiradaDAO.insere(solicitacaoRetirada);

	}

	@Override
	@Transactional
	public void atualizaSolicitacaoRetirada(SolicitacaoRetirada solicitacaoRetirada) {
		solicitacaoRetiradaDAO.atualiza(solicitacaoRetirada);
		
		OrdemServico ordemServico = solicitacaoRetirada.getOrdemServico();
		
		List<PecaOrdemServico> pecas = pecaOrdemServicoDAO.listaByOrdemServico(ordemServico);
		
		for(PecaOrdemServico po : pecas) {
			int qtde = po.getPeca().getQuantidade();
			po.getPeca().setQuantidade(qtde - po.getQtde());
			pecaDAO.atualiza(po.getPeca());
		}

	}

	@Override
	@Transactional
	public List<SolicitacaoRetirada> listaSolicitacaoRetiradas() {
		return this.solicitacaoRetiradaDAO.lista();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public BootgridData<SolicitacaoRetirada> listaSolicitacaoRetiradasBootgrid(BootgridParam param, String status) {
		if ("pendente".equals(status)) {
			BootgridData<SolicitacaoRetirada> bootgridData = this.solicitacaoRetiradaDAO.listaPendenteToBootgrid(param);
			for(SolicitacaoRetirada s : bootgridData.getRows()) {
				OrdemServico ordemServico = s.getOrdemServico();
				ordemServico.setOrdemServicoPai(null);
				ordemServico.setHistorico(statusOrdemServicoDAO.
						listaByOrdemServico(ordemServico));
			}
			return bootgridData;
		}
		BootgridData<SolicitacaoRetirada> bootgridData = this.solicitacaoRetiradaDAO.listaToBootgrid(param);
		for(SolicitacaoRetirada s : bootgridData.getRows()) {
			OrdemServico ordemServico = s.getOrdemServico();
			ordemServico.setOrdemServicoPai(null);
			ordemServico.setHistorico(statusOrdemServicoDAO.
					listaByOrdemServico(ordemServico));
		}
		return bootgridData;
	}
	
	
	@Override
	@Transactional
	public SolicitacaoRetirada getSolicitacaoRetiradaById(long id) {
		return this.solicitacaoRetiradaDAO.getById(id);
	}

	@Override
	@Transactional
	public void removeSolicitacaoRetirada(long id) {
		this.solicitacaoRetiradaDAO.remove(id);

	}
	
	@Override
	@Transactional
	public Map<Long, String> listaSolicitacaoRetiradaForDropDown() {
		Map<Long, String> listaSolicitacaoRetiradas = new LinkedHashMap<Long, String>();
		
		for (SolicitacaoRetirada sr : this.solicitacaoRetiradaDAO.lista()) {
			listaSolicitacaoRetiradas.put(sr.getId(), sr.getSolicitante() 
					+ " - " + sr.getDataSolicitacaoFormatada());
		}
		return listaSolicitacaoRetiradas;
	}
}
