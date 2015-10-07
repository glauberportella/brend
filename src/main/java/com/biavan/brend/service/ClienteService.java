package com.biavan.brend.service;

import java.util.List;
import java.util.Map;

import com.biavan.brend.model.Cliente;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

public interface ClienteService {

	public void insereCliente(Cliente cliente);

	public void atualizaCliente(Cliente cliente);

	public List<Cliente> listaClientes();
	
	public <E> BootgridData<E> listaClientesBootgrid(String palavra, BootgridParam query);
	
	public <E> BootgridData<E> listaClientesBootgrid(BootgridParam query);

	public Cliente getClienteById(long id);

	public void removeCliente(long id);
	
	public Map<Long, String> listaClientesForDropDown();
	
}
