package com.biavan.brend.service;

import java.util.List;

import com.biavan.brend.model.Orcamento;
import com.biavan.brend.model.OrdemServico;
import com.biavan.brend.model.PecaOrcamento;
import com.biavan.brend.model.ServicoOrcamento;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

public interface OrcamentoService {
	
	public void insereOrcamento(Orcamento orcamento, List<PecaOrcamento> pecasOrcamento, List<ServicoOrcamento> servicosOrcamento);

	public void atualizaOrcamento(Orcamento orcamento, List<PecaOrcamento> pecasOrcamento, List<ServicoOrcamento> servicosOrcamento);

	public List<Orcamento> listaOrcamentos();
	
	public <E> BootgridData<E> listaOrcamentosBootgrid(BootgridParam query);
	
	public <E> BootgridData<E> listaOrcamentosBootgrid(String palavra, BootgridParam query);

	public Orcamento getOrcamentoById(long id);

	public void removeOrcamento(long id);
	
	public Orcamento getOrcamentoByOrdemServico(OrdemServico ordemServico);

}
