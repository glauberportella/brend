package com.biavan.brend.service;

import java.util.List;
import java.util.Map;

import com.biavan.brend.model.TipoDocumento;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

public interface TipoDocumentoService {
	
	public void insereTipoDocumento(TipoDocumento tipoDocumento);

	public void atualizaTipoDocumento(TipoDocumento tipoDocumento);

	public List<TipoDocumento> listaTipoDocumentos();
	
	public <E> BootgridData<E> listaTipoDocumentosBootgrid(BootgridParam query);

	public TipoDocumento getTipoDocumentoById(long id);

	public void removeTipoDocumento(long id);
	
	public Map<Long, String> listaTipoDocumentoForDropDown();

}
