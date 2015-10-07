package com.biavan.brend.dao;

import java.util.List;

import com.biavan.brend.model.Servico;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

public interface ServicoDAO {
	
	public void insere(Servico servico);

	public void atualiza(Servico servico);

	public BootgridData<Servico> listaToBootgrid(BootgridParam param);
	
	public BootgridData<Servico> listaToBootgrid(String palavra, BootgridParam param);
	
	public List<Servico> lista();

	public Servico getById(long id);

	public void remove(long id);

}
