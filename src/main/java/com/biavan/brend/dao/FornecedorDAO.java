package com.biavan.brend.dao;

import java.util.List;

import com.biavan.brend.model.Fornecedor;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

public interface FornecedorDAO {
	
	public void insere(Fornecedor fornecedor);

	public void atualiza(Fornecedor fornecedor);

	public BootgridData<Fornecedor> listaToBootgrid(BootgridParam param);
	
	public List<Fornecedor> lista();

	public Fornecedor getById(long id);

	public void remove(long id);
}
