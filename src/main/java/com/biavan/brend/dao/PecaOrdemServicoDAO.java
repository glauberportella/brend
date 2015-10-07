package com.biavan.brend.dao;

import java.util.List;

import com.biavan.brend.model.OrdemServico;
import com.biavan.brend.model.PecaOrdemServico;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

public interface PecaOrdemServicoDAO {
	
	public void insere(PecaOrdemServico pecaOrdemServico);

	public void atualiza(PecaOrdemServico pecaOrdemServico);

	public BootgridData<PecaOrdemServico> listaToBootgrid(BootgridParam param);
	
	public BootgridData<PecaOrdemServico> listaToBootgrid(String palavra, BootgridParam param);
	
	public List<PecaOrdemServico> listaByOrdemServico(OrdemServico ordemServico);

	public PecaOrdemServico getById(long id);

	public void removeByOrdemServico(OrdemServico ordemServico);
}
