package com.biavan.brend.dao;

import java.util.List;

import com.biavan.brend.model.OrdemServico;
import com.biavan.brend.model.RegistroContato;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

public interface RegistroContatoDAO {
	
	public void insere(RegistroContato registroContato);

	public BootgridData<RegistroContato> listaToBootgridByOrdemServico(OrdemServico ordemServico, BootgridParam param);
	
	public List<RegistroContato> listaByOrdemServico(OrdemServico ordemServico);

	public RegistroContato getById(long id);
}
