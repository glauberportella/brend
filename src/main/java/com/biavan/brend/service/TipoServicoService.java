package com.biavan.brend.service;

import java.util.List;
import java.util.Map;

import com.biavan.brend.model.TipoServico;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

public interface TipoServicoService {
	
	public void insereTipoServico(TipoServico tipoServico);

	public void atualizaTipoServico(TipoServico tipoServico);

	public List<TipoServico> listaTipoServicos();
	
	public <E> BootgridData<E> listaTipoServicosBootgrid(BootgridParam query);

	public TipoServico getTipoServicoById(long id);

	public void removeTipoServico(long id);
	
	public Map<Long, String> listaTipoServicoForDropDown();

}
