package com.biavan.brend.dao;

import java.util.List;

import com.biavan.brend.model.Peca;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

public interface PecaDAO {
	
	public void insere(Peca peca);

	public void atualiza(Peca peca);

	public BootgridData<Peca> listaToBootgrid(BootgridParam param);
	
	public BootgridData<Peca> listaToBootgrid(String palavra, BootgridParam param);
	
	public List<Peca> lista();

	public Peca getById(long id);

	public void remove(long id);

}
