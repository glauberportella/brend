package com.biavan.brend.dao;

import java.util.List;

import com.biavan.brend.model.TipoServico;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

public interface TipoServicoDAO {
	
	public void insere(TipoServico tipoServico);

	public void atualiza(TipoServico tipoServico);

	public BootgridData<TipoServico> listaToBootgrid(BootgridParam param);
	
	public List<TipoServico> lista();

	public TipoServico getById(long id);

	public void remove(long id);
}
