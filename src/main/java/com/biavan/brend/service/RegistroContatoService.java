package com.biavan.brend.service;

import java.util.List;

import com.biavan.brend.model.OrdemServico;
import com.biavan.brend.model.RegistroContato;
import com.biavan.brend.model.StatusOrdemServico;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

public interface RegistroContatoService {
	
	public String insereRegistroContato(RegistroContato registroContato);
	
	public String insereRegistroContato(RegistroContato registroContato, StatusOrdemServico statusOrdemServico);

	public List<RegistroContato> listaRegistroContatosByOrdemServico(OrdemServico ordemServico);
	
	public <E> BootgridData<E> listaRegistroContatosBootgridByOrdemServico(OrdemServico ordemServico, BootgridParam query);

	public RegistroContato getRegistroContatoById(long id);

}
