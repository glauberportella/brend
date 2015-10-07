package com.biavan.brend.dao;

import java.util.List;

import com.biavan.brend.model.Cliente;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

public interface ClienteDAO {
	
	public void insere(Cliente cliente);

	public void atualiza(Cliente cliente);

	public BootgridData<Cliente> listaToBootgrid(BootgridParam param);
	
	public BootgridData<Cliente> listaToBootgrid(String palavra, BootgridParam param);
	
	public List<Cliente> lista();

	public Cliente getById(long id);

	public void remove(long id);
}
