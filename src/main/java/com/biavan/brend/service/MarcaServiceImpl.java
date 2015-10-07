package com.biavan.brend.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biavan.brend.dao.MarcaDAO;
import com.biavan.brend.model.Marca;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

@Service
public class MarcaServiceImpl implements MarcaService {
	
private MarcaDAO marcaDAO;
	
	public void setMarcaDAO(MarcaDAO marcaDAO) {
		this.marcaDAO = marcaDAO;
	}
	
	@Override
	@Transactional
	public void insereMarca(Marca marca) {
		this.marcaDAO.insere(marca);

	}

	@Override
	@Transactional
	public void atualizaMarca(Marca marca) {
		this.marcaDAO.atualiza(marca);

	}

	@Override
	@Transactional
	public List<Marca> listaMarcas() {
		return this.marcaDAO.lista();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public BootgridData<Marca> listaMarcasBootgrid(BootgridParam param) {
		return this.marcaDAO.listaToBootgrid(param);
	}
	
	
	@Override
	@Transactional
	public Marca getMarcaById(long id) {
		return this.marcaDAO.getById(id);
	}

	@Override
	@Transactional
	public void removeMarca(long id) {
		this.marcaDAO.remove(id);

	}
	
	@Override
	@Transactional
	public Map<Long, String> listaMarcaForDropDown() {
		Map<Long, String> listaMarcas = new LinkedHashMap<Long, String>();
		
		for (Marca m : this.marcaDAO.lista()) {
			listaMarcas.put(m.getId(), m.getNome());
		}
		return listaMarcas;
	}
}
