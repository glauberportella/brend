package com.biavan.brend.service;

import java.util.List;

import com.biavan.brend.model.Servico;
import com.biavan.brend.model.TarifarioServico;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

public interface TarifarioServicoService {
	
	public void insereTarifarioServico(TarifarioServico tarifarioServico);

	public void atualizaTarifarioServico(TarifarioServico tarifarioServico);

	public List<TarifarioServico> listaTarifarioServicos();
	
	public <E> BootgridData<E> listaTarifarioServicosBootgrid(Servico servico, BootgridParam query);

	public TarifarioServico getTarifarioServicoById(long id);

	public void removeTarifarioServico(long id);

}
