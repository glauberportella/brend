package com.biavan.brend.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biavan.brend.dao.TipoDocumentoDAO;
import com.biavan.brend.model.TipoDocumento;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

@Service
public class TipoDocumentoServiceImpl implements TipoDocumentoService {
	
private TipoDocumentoDAO tipoDocumentoDAO;
	
	public void setTipoDocumentoDAO(TipoDocumentoDAO tipoDocumentoDAO) {
		this.tipoDocumentoDAO = tipoDocumentoDAO;
	}
	
	@Override
	@Transactional
	public void insereTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumentoDAO.insere(tipoDocumento);

	}

	@Override
	@Transactional
	public void atualizaTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumentoDAO.atualiza(tipoDocumento);

	}

	@Override
	@Transactional
	public List<TipoDocumento> listaTipoDocumentos() {
		return this.tipoDocumentoDAO.lista();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public BootgridData<TipoDocumento> listaTipoDocumentosBootgrid(BootgridParam param) {
		return this.tipoDocumentoDAO.listaToBootgrid(param);
	}
	
	
	@Override
	@Transactional
	public TipoDocumento getTipoDocumentoById(long id) {
		return this.tipoDocumentoDAO.getById(id);
	}

	@Override
	@Transactional
	public void removeTipoDocumento(long id) {
		this.tipoDocumentoDAO.remove(id);

	}
	
	@Override
	@Transactional
	public Map<Long, String> listaTipoDocumentoForDropDown() {
		Map<Long, String> listaTipoDocumentos = new LinkedHashMap<Long, String>();
		
		for (TipoDocumento m : this.tipoDocumentoDAO.lista()) {
			listaTipoDocumentos.put(m.getId(), m.getNome());
		}
		return listaTipoDocumentos;
	}
}
