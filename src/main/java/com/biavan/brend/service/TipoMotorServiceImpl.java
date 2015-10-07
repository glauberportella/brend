package com.biavan.brend.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biavan.brend.dao.TipoMotorDAO;
import com.biavan.brend.model.TipoMotor;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

@Service
public class TipoMotorServiceImpl implements TipoMotorService {
	
private TipoMotorDAO tipoMotorDAO;
	
	public void setTipoMotorDAO(TipoMotorDAO tipoMotorDAO) {
		this.tipoMotorDAO = tipoMotorDAO;
	}
	
	@Override
	@Transactional
	public void insereTipoMotor(TipoMotor tipoMotor) {
		this.tipoMotorDAO.insere(tipoMotor);

	}

	@Override
	@Transactional
	public void atualizaTipoMotor(TipoMotor tipoMotor) {
		this.tipoMotorDAO.atualiza(tipoMotor);

	}

	@Override
	@Transactional
	public List<TipoMotor> listaTipoMotors() {
		return this.tipoMotorDAO.lista();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public BootgridData<TipoMotor> listaTipoMotorsBootgrid(BootgridParam param) {
		return this.tipoMotorDAO.listaToBootgrid(param);
	}
	
	
	@Override
	@Transactional
	public TipoMotor getTipoMotorById(long id) {
		return this.tipoMotorDAO.getById(id);
	}

	@Override
	@Transactional
	public void removeTipoMotor(long id) {
		this.tipoMotorDAO.remove(id);

	}
	
	@Override
	@Transactional
	public Map<Long, String> listaTipoMotorForDropDown() {
		Map<Long, String> listaTipoMotors = new LinkedHashMap<Long, String>();
		
		for (TipoMotor m : this.tipoMotorDAO.lista()) {
			listaTipoMotors.put(m.getId(), m.getNome());
		}
		return listaTipoMotors;
	}
}
