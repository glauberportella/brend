package com.biavan.brend.dao;

import java.util.List;

import com.biavan.brend.model.OrdemServico;
import com.biavan.brend.model.ServicoOrdemServico;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

public interface ServicoOrdemServicoDAO {
	
	public void insere(ServicoOrdemServico servicoOrdemServico);

	public void atualiza(ServicoOrdemServico servicoOrdemServico);

	public BootgridData<ServicoOrdemServico> listaToBootgrid(BootgridParam param);
	
	public BootgridData<ServicoOrdemServico> listaToBootgrid(String palavra, BootgridParam param);
	
	public List<ServicoOrdemServico> listaByOrdemServico(OrdemServico ordemServico);

	public ServicoOrdemServico getById(long id);

	public void removeByOrdemServico(OrdemServico ordemServico);
}
