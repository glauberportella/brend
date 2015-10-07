package com.biavan.brend.dao;

import java.util.List;

import com.biavan.brend.model.Orcamento;
import com.biavan.brend.model.ServicoOrcamento;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

public interface ServicoOrcamentoDAO {
	
	public void insere(ServicoOrcamento servicoOrcamento);

	public void atualiza(ServicoOrcamento servicoOrcamento);

	public BootgridData<ServicoOrcamento> listaToBootgrid(BootgridParam param);
	
	public BootgridData<ServicoOrcamento> listaToBootgrid(String palavra, BootgridParam param);
	
	public List<ServicoOrcamento> listaByOrcamento(Orcamento orcamento);

	public ServicoOrcamento getById(long id);

	public void removeByOrcamento(Orcamento orcamento);
}
