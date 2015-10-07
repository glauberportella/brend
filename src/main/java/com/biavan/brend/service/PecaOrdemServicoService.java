package com.biavan.brend.service;

import java.util.List;

import com.biavan.brend.model.OrdemServico;
import com.biavan.brend.model.PecaOrdemServico;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

public interface PecaOrdemServicoService {

	public void inserePecaOrdemServico(PecaOrdemServico pecaOrdemServico);

	public void atualizaPecaOrdemServico(PecaOrdemServico pecaOrdemServico);

	public List<PecaOrdemServico> listaPecaOrdemServicoByOrdemServico(OrdemServico ordemServico);
	
	public <E> BootgridData<E> listaPecaOrdemServicoBootgrid(String palavra, BootgridParam query);
	
	public <E> BootgridData<E> listaPecaOrdemServicoBootgrid(BootgridParam query);

	public PecaOrdemServico getPecaOrdemServicoById(long id);

	public void removePecaOrdemServicoByOrdemServico(OrdemServico ordemServico);
	
}
