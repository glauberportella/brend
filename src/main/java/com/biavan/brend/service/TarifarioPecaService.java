package com.biavan.brend.service;

import java.util.List;

import com.biavan.brend.model.Peca;
import com.biavan.brend.model.TarifarioPeca;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

public interface TarifarioPecaService {
	
	public void insereTarifarioPeca(TarifarioPeca tarifarioPeca);

	public void atualizaTarifarioPeca(TarifarioPeca tarifarioPeca);

	public List<TarifarioPeca> listaTarifarioPecas();
	
	public <E> BootgridData<E> listaTarifarioPecasBootgrid(Peca peca, BootgridParam query);

	public TarifarioPeca getTarifarioPecaById(long id);

	public void removeTarifarioPeca(long id);

}
