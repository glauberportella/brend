package com.biavan.brend.service;

import java.util.List;
import java.util.Map;

import com.biavan.brend.model.Veiculo;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

public interface VeiculoService {
	
	public void insereVeiculo(Veiculo veiculo);

	public void atualizaVeiculo(Veiculo veiculo);

	public List<Veiculo> listaVeiculos();
	
	public <E> BootgridData<E> listaVeiculosBootgrid(BootgridParam query);

	public Veiculo getVeiculoById(long id);

	public void removeVeiculo(long id);
	
	public Map<Long, String> listaVeiculoForDropDown();

}
