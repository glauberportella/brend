package com.biavan.brend.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biavan.brend.dao.TarifarioPecaDAO;
import com.biavan.brend.model.Peca;
import com.biavan.brend.model.TarifarioPeca;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

@Service
public class TarifarioPecaServiceImpl implements TarifarioPecaService {
	
	private TarifarioPecaDAO tarifarioPecaDAO;
	
	public void setTarifarioPecaDAO(TarifarioPecaDAO tarifarioPecaDAO) {
		this.tarifarioPecaDAO = tarifarioPecaDAO;
	}

	@Override
	@Transactional
	public void insereTarifarioPeca(TarifarioPeca tarifarioPeca) {
		this.tarifarioPecaDAO.insere(tarifarioPeca);
	}

	@Override
	@Transactional
	public void atualizaTarifarioPeca(TarifarioPeca tarifarioPeca) {
		this.tarifarioPecaDAO.atualiza(tarifarioPeca);
	}

	@Override
	@Transactional
	public List<TarifarioPeca> listaTarifarioPecas() {
		return this.tarifarioPecaDAO.lista();
	}

	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public BootgridData<TarifarioPeca> listaTarifarioPecasBootgrid(Peca peca, BootgridParam param) {
		BootgridData<TarifarioPeca> bootgridData = tarifarioPecaDAO.listaToBootgrid(peca, param);
		
		for(TarifarioPeca tp : bootgridData.getRows()) {
			tp.getPeca().setPreco();
			tp.getPeca().setPrecos(null);
		}
		
		return bootgridData;
	}

	@Override
	@Transactional
	public TarifarioPeca getTarifarioPecaById(long id) {
		return this.tarifarioPecaDAO.getById(id);
	}

	@Override
	@Transactional
	public void removeTarifarioPeca(long id) {
		this.tarifarioPecaDAO.remove(id);
		
	}

}
