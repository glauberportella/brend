package com.biavan.brend.service;

import java.util.List;
import java.util.Map;

import com.biavan.brend.model.Peca;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

public interface PecaService {
	
	public void inserePeca(Peca peca);

	public void atualizaPeca(Peca peca);

	public List<Peca> listaPecas();
	
	public <E> BootgridData<E> listaPecasBootgrid(BootgridParam query);
	
	public <E> BootgridData<E> listaPecasBootgrid(String palavra, BootgridParam query);

	public Peca getPecaById(long id);

	public void removePeca(long id);

	public Map<Long, String> listaPecaForDropDown();

}
