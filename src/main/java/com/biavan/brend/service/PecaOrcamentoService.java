package com.biavan.brend.service;

import java.util.List;

import com.biavan.brend.model.Orcamento;
import com.biavan.brend.model.PecaOrcamento;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

public interface PecaOrcamentoService {

	public void inserePecaOrcamento(PecaOrcamento pecaOrcamento);

	public void atualizaPecaOrcamento(PecaOrcamento pecaOrcamento);

	public List<PecaOrcamento> listaPecaOrcamentoByOrcamento(Orcamento orcamento);
	
	public <E> BootgridData<E> listaPecaOrcamentoBootgrid(String palavra, BootgridParam query);
	
	public <E> BootgridData<E> listaPecaOrcamentoBootgrid(BootgridParam query);

	public PecaOrcamento getPecaOrcamentoById(long id);

	public void removePecaOrcamentoByOrcamento(Orcamento orcamento);
	
}
