package com.biavan.brend.service;

import java.util.List;

import com.biavan.brend.enums.StatusOS;
import com.biavan.brend.model.InformacaoStatusOrdemServico;
import com.biavan.brend.model.OrdemServico;
import com.biavan.brend.model.StatusOrdemServico;

public interface StatusOrdemServicoService {
	
	public void insereStatusOrdemServico(StatusOrdemServico statusOrdemServico);
	
	public void atualizaStatusOrdemServico(StatusOrdemServico statusOrdemServico);

	public List<StatusOrdemServico> listaStatusOrdensServicoByOrdemServico(OrdemServico ordemServico);

	public StatusOrdemServico getStatusOrdemServicoByOrdemServico(OrdemServico ordemServico);

	public void removeStatusOrdemServico(long id);
	
	public InformacaoStatusOrdemServico percentualByStatus(StatusOS statusOS);
	
	public List<InformacaoStatusOrdemServico> percentuais();

}
