package com.biavan.brend.dao;

import java.util.List;

import com.biavan.brend.model.Orcamento;
import com.biavan.brend.model.OrdemServico;
import com.biavan.brend.model.Peca;
import com.biavan.brend.model.PecaOrcamento;
import com.biavan.brend.model.Servico;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

public interface OrcamentoDAO {
	
	public void insere(Orcamento orcamento);
	
	public void inserePecaOrcamento(PecaOrcamento pecaOrcamento);

	public void atualiza(Orcamento orcamento);

	public BootgridData<Orcamento> listaToBootgrid(BootgridParam param);
	
	public BootgridData<Orcamento> listaToBootgrid(String palavra, BootgridParam param);
	
	public List<Orcamento> lista();

	public Orcamento getById(long id);

	public void remove(long id);
	
	public List<Servico> getServicos(List<Long> ids);
	
	public List<Peca> getPecas(List<Long> ids);
	
	public Orcamento getOrcamentoByOrdemServico(OrdemServico ordemServico);

}
