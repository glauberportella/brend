package com.biavan.brend.service;

import java.util.List;
import java.util.Map;

import com.biavan.brend.model.Marca;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

public interface MarcaService {
	
	public void insereMarca(Marca marca);

	public void atualizaMarca(Marca marca);

	public List<Marca> listaMarcas();
	
	public <E> BootgridData<E> listaMarcasBootgrid(BootgridParam query);

	public Marca getMarcaById(long id);

	public void removeMarca(long id);
	
	public Map<Long, String> listaMarcaForDropDown();

}
