package com.biavan.brend.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biavan.brend.dao.TipoServicoDAO;
import com.biavan.brend.model.TipoServico;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

@Service
public class TipoServicoServiceImpl implements TipoServicoService {
	
private TipoServicoDAO tipoServicoDAO;
	
	public void setTipoServicoDAO(TipoServicoDAO tipoServicoDAO) {
		this.tipoServicoDAO = tipoServicoDAO;
	}
	
	@Override
	@Transactional
	public void insereTipoServico(TipoServico tipoServico) {
		this.tipoServicoDAO.insere(tipoServico);

	}

	@Override
	@Transactional
	public void atualizaTipoServico(TipoServico tipoServico) {
		this.tipoServicoDAO.atualiza(tipoServico);

	}

	@Override
	@Transactional
	public List<TipoServico> listaTipoServicos() {
		return this.tipoServicoDAO.lista();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public BootgridData<TipoServico> listaTipoServicosBootgrid(BootgridParam param) {
		return this.tipoServicoDAO.listaToBootgrid(param);
	}
	
	
	@Override
	@Transactional
	public TipoServico getTipoServicoById(long id) {
		return this.tipoServicoDAO.getById(id);
	}

	@Override
	@Transactional
	public void removeTipoServico(long id) {
		this.tipoServicoDAO.remove(id);

	}
	
	@Override
	@Transactional
	public Map<Long, String> listaTipoServicoForDropDown() {
		Map<Long, String> listaTipoServicos = new LinkedHashMap<Long, String>();
		
		for (TipoServico m : this.tipoServicoDAO.lista()) {
			listaTipoServicos.put(m.getId(), m.getNome());
		}
		return listaTipoServicos;
	}
}
