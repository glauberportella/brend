package com.biavan.brend.dao;

import java.util.List;

import com.biavan.brend.model.Peca;
import com.biavan.brend.model.TarifarioPeca;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

public interface TarifarioPecaDAO {
	
	public void insere(TarifarioPeca tarifarioPeca);

	public void atualiza(TarifarioPeca tarifarioPeca);

	public BootgridData<TarifarioPeca> listaToBootgrid(Peca peca, BootgridParam param);
	
	public List<TarifarioPeca> lista();

	public TarifarioPeca getById(long id);

	public void remove(long id);

}
