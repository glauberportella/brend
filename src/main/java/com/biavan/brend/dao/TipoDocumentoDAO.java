package com.biavan.brend.dao;

import java.util.List;

import com.biavan.brend.model.TipoDocumento;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

public interface TipoDocumentoDAO {
	
	public void insere(TipoDocumento tipoDocumento);

	public void atualiza(TipoDocumento tipoDocumento);

	public BootgridData<TipoDocumento> listaToBootgrid(BootgridParam param);
	
	public List<TipoDocumento> lista();

	public TipoDocumento getById(long id);

	public void remove(long id);
}
