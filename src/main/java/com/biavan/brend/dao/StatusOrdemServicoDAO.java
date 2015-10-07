package com.biavan.brend.dao;

import java.util.List;

import com.biavan.brend.enums.StatusOS;
import com.biavan.brend.model.InformacaoStatusOrdemServico;
import com.biavan.brend.model.OrdemServico;
import com.biavan.brend.model.StatusOrdemServico;

public interface StatusOrdemServicoDAO {
	
	public void insere(StatusOrdemServico statusOrdemServico);

	public void atualiza(StatusOrdemServico statusOrdemServico);

	public StatusOrdemServico getStatusAtualByOrdemServico(OrdemServico ordemServico);
	
	public List<StatusOrdemServico> listaByOrdemServico(OrdemServico ordemServico);

	public StatusOrdemServico getById(long id);

	public void remove(long id);
	
	public InformacaoStatusOrdemServico percentualByStatus(StatusOS statusOS);

}
