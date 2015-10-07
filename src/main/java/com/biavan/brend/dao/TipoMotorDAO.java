package com.biavan.brend.dao;

import java.util.List;

import com.biavan.brend.model.TipoMotor;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

public interface TipoMotorDAO {
	
	public void insere(TipoMotor tipoMotor);

	public void atualiza(TipoMotor tipoMotor);

	public BootgridData<TipoMotor> listaToBootgrid(BootgridParam param);
	
	public List<TipoMotor> lista();

	public TipoMotor getById(long id);

	public void remove(long id);
}
