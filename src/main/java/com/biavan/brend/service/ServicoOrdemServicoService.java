package com.biavan.brend.service;

import java.util.List;

import com.biavan.brend.model.OrdemServico;
import com.biavan.brend.model.ServicoOrdemServico;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

public interface ServicoOrdemServicoService {

	public void insereServicoOrdemServico(ServicoOrdemServico servicoOrdemServico);

	public void atualizaServicoOrdemServico(ServicoOrdemServico servicoOrdemServico);

	public List<ServicoOrdemServico> listaServicoOrdemServicoByOrdemServico(OrdemServico ordemServico);
	
	public <E> BootgridData<E> listaServicoOrdemServicoBootgrid(String palavra, BootgridParam query);
	
	public <E> BootgridData<E> listaServicoOrdemServicoBootgrid(BootgridParam query);

	public ServicoOrdemServico getServicoOrdemServicoById(long id);

	public void removeServicoOrdemServicoByOrdemServico(OrdemServico ordemServico);
	
}
