package com.biavan.brend.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biavan.brend.dao.TarifarioServicoDAO;
import com.biavan.brend.model.Servico;
import com.biavan.brend.model.TarifarioServico;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

@Service
public class TarifarioServicoServiceImpl implements TarifarioServicoService {
	
	private TarifarioServicoDAO tarifarioServicoDAO;
	
	public void setTarifarioServicoDAO(TarifarioServicoDAO tarifarioServicoDAO) {
		this.tarifarioServicoDAO = tarifarioServicoDAO;
	}

	@Override
	@Transactional
	public void insereTarifarioServico(TarifarioServico tarifarioServico) {
		this.tarifarioServicoDAO.insere(tarifarioServico);
		
	}

	@Override
	@Transactional
	public void atualizaTarifarioServico(TarifarioServico tarifarioServico) {
		this.tarifarioServicoDAO.atualiza(tarifarioServico);
		
	}

	@Override
	@Transactional
	public List<TarifarioServico> listaTarifarioServicos() {
		return this.tarifarioServicoDAO.lista();
	}

	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public BootgridData<TarifarioServico> listaTarifarioServicosBootgrid(Servico servico, BootgridParam param) {
		BootgridData<TarifarioServico> bootgridData = tarifarioServicoDAO.listaToBootgrid(servico, param);
		
		for(TarifarioServico ts : bootgridData.getRows()) {
			ts.getServico().setPreco();
			ts.getServico().setPrecos(null);
		}
		
		return bootgridData;
	}

	@Override
	@Transactional
	public TarifarioServico getTarifarioServicoById(long id) {
		return this.tarifarioServicoDAO.getById(id);
	}

	@Override
	@Transactional
	public void removeTarifarioServico(long id) {
		this.tarifarioServicoDAO.remove(id);
		
	}

}
