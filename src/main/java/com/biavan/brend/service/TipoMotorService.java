package com.biavan.brend.service;

import java.util.List;
import java.util.Map;

import com.biavan.brend.model.TipoMotor;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

public interface TipoMotorService {
	
	public void insereTipoMotor(TipoMotor tipoMotor);

	public void atualizaTipoMotor(TipoMotor tipoMotor);

	public List<TipoMotor> listaTipoMotors();
	
	public <E> BootgridData<E> listaTipoMotorsBootgrid(BootgridParam query);

	public TipoMotor getTipoMotorById(long id);

	public void removeTipoMotor(long id);
	
	public Map<Long, String> listaTipoMotorForDropDown();

}
