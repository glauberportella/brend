package com.biavan.brend.dao;

import java.util.List;

import com.biavan.brend.model.Veiculo;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

public interface VeiculoDAO {
	
	public void insere(Veiculo veiculo);

	public void atualiza(Veiculo veiculo);

	public BootgridData<Veiculo> listaToBootgrid(BootgridParam param);
	
	public List<Veiculo> lista();

	public Veiculo getById(long id);

	public void remove(long id);
}
