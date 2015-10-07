package com.biavan.brend.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biavan.brend.dao.ServicoDAO;
import com.biavan.brend.model.Servico;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

@Service
public class ServicoServiceImpl implements ServicoService {
	
	private ServicoDAO servicoDAO;
	
	public void setServicoDAO(ServicoDAO servicoDAO) {
		this.servicoDAO = servicoDAO;
	}

	@Override
	@Transactional
	public void insereServico(Servico servico) {
		this.servicoDAO.insere(servico);
		
	}

	@Override
	@Transactional
	public void atualizaServico(Servico servico) {
		this.servicoDAO.atualiza(servico);
		
	}

	@Override
	@Transactional
	public List<Servico> listaServicos() {
		List<Servico> servicos = new ArrayList<Servico>();
		for(Servico s : servicoDAO.lista()) {
			s.setPreco();
			s.setPrecos(null);
			servicos.add(s);
		}
		return servicos;
		//return this.servicoDAO.lista();
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public BootgridData<Servico> listaServicosBootgrid(BootgridParam param) {
		BootgridData<Servico> bootgridData = servicoDAO.listaToBootgrid(param);
		
		for(Servico s : bootgridData.getRows()) {
			s.setPreco();
			s.setPrecos(null);
		}
		
		return bootgridData;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public BootgridData<Servico> listaServicosBootgrid(String palavra, BootgridParam param) {
		BootgridData<Servico> bootgridData = servicoDAO.listaToBootgrid(palavra, param);

		for(Servico p : bootgridData.getRows()) {
			p.setPreco();
			p.setPrecos(null);
		}
		
		return bootgridData;
	}
	
	@Override
	@Transactional
	public Map<Long, String> listaServicosForDropDown() {
		Map<Long, String> listaServicos = new LinkedHashMap<Long, String>();
		
		for (Servico s : this.servicoDAO.lista()) {
			listaServicos.put(s.getId(), s.getNome());
		}
		return listaServicos;
	}

	@Override
	@Transactional
	public Servico getServicoById(long id) {
		Servico servico = servicoDAO.getById(id);
		servico.setPreco();
		return servico;
	}

	@Override
	@Transactional
	public void removeServico(long id) {
		this.servicoDAO.remove(id);
		
	}

}
