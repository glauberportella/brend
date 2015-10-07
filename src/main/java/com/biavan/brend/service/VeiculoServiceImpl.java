package com.biavan.brend.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biavan.brend.dao.VeiculoDAO;
import com.biavan.brend.model.Veiculo;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

@Service
public class VeiculoServiceImpl implements VeiculoService {
	
private VeiculoDAO veiculoDAO;
	
	public void setVeiculoDAO(VeiculoDAO veiculoDAO) {
		this.veiculoDAO = veiculoDAO;
	}
	
	@Override
	@Transactional
	public void insereVeiculo(Veiculo veiculo) {
		this.veiculoDAO.insere(veiculo);

	}

	@Override
	@Transactional
	public void atualizaVeiculo(Veiculo veiculo) {
		this.veiculoDAO.atualiza(veiculo);

	}

	@Override
	@Transactional
	public List<Veiculo> listaVeiculos() {
		return this.veiculoDAO.lista();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public BootgridData<Veiculo> listaVeiculosBootgrid(BootgridParam param) {
		return this.veiculoDAO.listaToBootgrid(param);
	}
	
	
	@Override
	@Transactional
	public Veiculo getVeiculoById(long id) {
		return this.veiculoDAO.getById(id);
	}

	@Override
	@Transactional
	public void removeVeiculo(long id) {
		this.veiculoDAO.remove(id);

	}
	
	@Override
	@Transactional
	public Map<Long, String> listaVeiculoForDropDown() {
		Map<Long, String> listaVeiculos = new LinkedHashMap<Long, String>();
		
		for (Veiculo m : this.veiculoDAO.lista()) {
			listaVeiculos.put(m.getId(), m.getModelo());
		}
		return listaVeiculos;
	}
}
