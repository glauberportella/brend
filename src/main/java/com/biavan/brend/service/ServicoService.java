package com.biavan.brend.service;

import java.util.List;
import java.util.Map;

import com.biavan.brend.model.Servico;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

public interface ServicoService {
	
	public void insereServico(Servico servico);

	public void atualizaServico(Servico servico);

	public List<Servico> listaServicos();
	
	public <E> BootgridData<E> listaServicosBootgrid(BootgridParam query);
	
	public <E> BootgridData<E> listaServicosBootgrid(String palavra, BootgridParam query);

	public Servico getServicoById(long id);

	public void removeServico(long id);
	
	public Map<Long, String> listaServicosForDropDown();

}
