package com.biavan.brend.dao;

import java.util.List;

import com.biavan.brend.model.Marca;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

public interface MarcaDAO {
	
	public void insere(Marca marca);

	public void atualiza(Marca marca);

	public BootgridData<Marca> listaToBootgrid(BootgridParam param);
	
	public List<Marca> lista();

	public Marca getById(long id);

	public void remove(long id);
}
