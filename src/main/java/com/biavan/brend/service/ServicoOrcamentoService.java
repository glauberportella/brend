package com.biavan.brend.service;

import java.util.List;

import com.biavan.brend.model.Orcamento;
import com.biavan.brend.model.ServicoOrcamento;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

public interface ServicoOrcamentoService {

	public void insereServicoOrcamento(ServicoOrcamento servicoOrcamento);

	public void atualizaServicoOrcamento(ServicoOrcamento servicoOrcamento);

	public List<ServicoOrcamento> listaServicoOrcamentoByOrcamento(Orcamento orcamento);
	
	public <E> BootgridData<E> listaServicoOrcamentoBootgrid(String palavra, BootgridParam query);
	
	public <E> BootgridData<E> listaServicoOrcamentoBootgrid(BootgridParam query);

	public ServicoOrcamento getServicoOrcamentoById(long id);

	public void removeServicoOrcamentoByOrcamento(Orcamento orcamento);
	
}
