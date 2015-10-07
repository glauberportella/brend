package com.biavan.brend.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biavan.brend.dao.PecaDAO;
import com.biavan.brend.model.Peca;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

@Service
public class PecaServiceImpl implements PecaService {
	
	private PecaDAO pecaDAO;
	
	public void setPecaDAO(PecaDAO pecaDAO) {
		this.pecaDAO = pecaDAO;
	}

	@Override
	@Transactional
	public void inserePeca(Peca peca) {
		this.pecaDAO.insere(peca);
		
	}

	@Override
	@Transactional
	public void atualizaPeca(Peca peca) {
		this.pecaDAO.atualiza(peca);
		
	}

	@Override
	@Transactional
	public List<Peca> listaPecas() {
		List<Peca> pecas = new ArrayList<Peca>();
		for(Peca p : pecaDAO.lista()) {
			p.setPreco();
			p.setPrecos(null);
			pecas.add(p);
		}
		return pecas;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public BootgridData<Peca> listaPecasBootgrid(BootgridParam param) {
		BootgridData<Peca> bootgridData = pecaDAO.listaToBootgrid(param);

		for(Peca p : bootgridData.getRows()) {
			p.setPreco();
			p.setPrecos(null);
		}
		
		return bootgridData;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public BootgridData<Peca> listaPecasBootgrid(String palavra, BootgridParam param) {
		BootgridData<Peca> bootgridData = pecaDAO.listaToBootgrid(palavra, param);

		for(Peca p : bootgridData.getRows()) {
			p.setPreco();
			p.setPrecos(null);
		}
		
		return bootgridData;
	}
	
	@Override
	@Transactional
	public Peca getPecaById(long id) {
		Peca peca = pecaDAO.getById(id);
		peca.setPreco();
		return peca;
		
	}

	@Override
	@Transactional
	public void removePeca(long id) {
		this.pecaDAO.remove(id);
		
	}
	
	@Override
	@Transactional
	public Map<Long, String> listaPecaForDropDown() {
		Map<Long, String> listaPecas = new LinkedHashMap<Long, String>();
		
		for (Peca p : this.pecaDAO.lista()) {
			listaPecas.put(p.getId(), p.getNome());
		}
		return listaPecas;
	}


}
