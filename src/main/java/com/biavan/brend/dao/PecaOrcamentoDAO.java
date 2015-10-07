package com.biavan.brend.dao;

import java.util.List;

import com.biavan.brend.model.Orcamento;
import com.biavan.brend.model.PecaOrcamento;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

public interface PecaOrcamentoDAO {
	
	public void insere(PecaOrcamento pecaOrcamento);

	public void atualiza(PecaOrcamento pecaOrcamento);

	public BootgridData<PecaOrcamento> listaToBootgrid(BootgridParam param);
	
	public BootgridData<PecaOrcamento> listaToBootgrid(String palavra, BootgridParam param);
	
	public List<PecaOrcamento> listaByOrcamento(Orcamento orcamento);

	public PecaOrcamento getById(long id);

	public void removeByOrcamento(Orcamento orcamento);
}
