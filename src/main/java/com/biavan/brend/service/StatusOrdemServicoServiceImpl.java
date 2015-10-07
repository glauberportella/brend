package com.biavan.brend.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biavan.brend.dao.StatusOrdemServicoDAO;
import com.biavan.brend.enums.StatusOS;
import com.biavan.brend.model.InformacaoStatusOrdemServico;
import com.biavan.brend.model.OrdemServico;
import com.biavan.brend.model.StatusOrdemServico;

@Service
public class StatusOrdemServicoServiceImpl implements StatusOrdemServicoService {

	private StatusOrdemServicoDAO statusOrdemServicoDAO;

	public void setStatusOrdemServicoDAO(
			StatusOrdemServicoDAO statusOrdemServicoDAO) {
		this.statusOrdemServicoDAO = statusOrdemServicoDAO;
	}

	@Override
	@Transactional
	public void insereStatusOrdemServico(StatusOrdemServico statusOrdemServico) {
		StatusOrdemServico statusOrdemServicoAtual = Collections
				.max(statusOrdemServicoDAO
						.listaByOrdemServico(statusOrdemServico
								.getOrdemServico()));
		
		statusOrdemServicoAtual.setDataFim(new Date());
		statusOrdemServicoDAO.atualiza(statusOrdemServicoAtual);
		
		statusOrdemServicoDAO.insere(statusOrdemServico);
	}

	@Override
	@Transactional
	public void atualizaStatusOrdemServico(StatusOrdemServico statusOrdemServico) {
		statusOrdemServicoDAO.atualiza(statusOrdemServico);
	}

	@Override
	@Transactional
	public List<StatusOrdemServico> listaStatusOrdensServicoByOrdemServico(
			OrdemServico ordemServico) {
		return statusOrdemServicoDAO.listaByOrdemServico(ordemServico);
	}

	@Override
	@Transactional
	public StatusOrdemServico getStatusOrdemServicoByOrdemServico(
			OrdemServico ordemServico) {
		return statusOrdemServicoDAO.getStatusAtualByOrdemServico(ordemServico);
	}

	@Override
	@Transactional
	public void removeStatusOrdemServico(long id) {
		statusOrdemServicoDAO.remove(id);

	}
	
	@Override
	@Transactional
	public InformacaoStatusOrdemServico percentualByStatus(StatusOS statusOS) {
		return statusOrdemServicoDAO.percentualByStatus(statusOS);
	}
	
	@Override
	@Transactional
	public List<InformacaoStatusOrdemServico> percentuais() {
		List<InformacaoStatusOrdemServico> lista = new ArrayList<InformacaoStatusOrdemServico>();
		
		InformacaoStatusOrdemServico infoPendente = statusOrdemServicoDAO.percentualByStatus(StatusOS.PENDENTE);
		infoPendente.setCor("#F7464A");
		infoPendente.setDestaque("#FF5A5E");
		
		InformacaoStatusOrdemServico infoAndamento = statusOrdemServicoDAO.percentualByStatus(StatusOS.EM_ANDAMENTO);
		infoAndamento.setCor("#FDB45C");
		infoAndamento.setDestaque("#FFC870");
		
		InformacaoStatusOrdemServico infoFinalizada = statusOrdemServicoDAO.percentualByStatus(StatusOS.FINALIZADA);
		infoFinalizada.setCor("#46BFBD");
		infoFinalizada.setDestaque("#5AD3D1");
		
		InformacaoStatusOrdemServico infoCancelada = statusOrdemServicoDAO.percentualByStatus(StatusOS.CANCELADA);
		infoCancelada.setCor("#949FB1");
		infoCancelada.setDestaque("#A8B3C5");
		
		InformacaoStatusOrdemServico infoReaberta = statusOrdemServicoDAO.percentualByStatus(StatusOS.REABERTA);
		infoReaberta.setCor("#6A5ACD");
		infoReaberta.setDestaque("#8470FF");
		
		
		lista.add(infoPendente);
		lista.add(infoAndamento);
		lista.add(infoFinalizada);
		lista.add(infoCancelada);
		lista.add(infoReaberta);
		
		return lista;
	}

}
