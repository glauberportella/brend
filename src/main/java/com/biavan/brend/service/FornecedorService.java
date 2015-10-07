package com.biavan.brend.service;

import java.util.List;
import java.util.Map;

import com.biavan.brend.model.Fornecedor;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

public interface FornecedorService {
	
	public void insereFornecedor(Fornecedor fornecedor);

	public void atualizaFornecedor(Fornecedor fornecedor);

	public List<Fornecedor> listaFornecedores();
	
	public <E> BootgridData<E> listaFornecedoresBootgrid(BootgridParam query);

	public Fornecedor getFornecedorById(long id);

	public void removeFornecedor(long id);
	
	public Map<Long, String> listaFornecedorForDropDown();

}
