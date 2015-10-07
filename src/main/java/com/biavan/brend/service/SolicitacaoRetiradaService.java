package com.biavan.brend.service;

import java.util.List;
import java.util.Map;

import com.biavan.brend.model.SolicitacaoRetirada;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

public interface SolicitacaoRetiradaService {
	
	public void insereSolicitacaoRetirada(SolicitacaoRetirada solicitacao);

	public void atualizaSolicitacaoRetirada(SolicitacaoRetirada solicitacao);

	public List<SolicitacaoRetirada> listaSolicitacaoRetiradas();
	
	public <E> BootgridData<E> listaSolicitacaoRetiradasBootgrid(BootgridParam query, String status);

	public SolicitacaoRetirada getSolicitacaoRetiradaById(long id);

	public void removeSolicitacaoRetirada(long id);
	
	public Map<Long, String> listaSolicitacaoRetiradaForDropDown();

}
