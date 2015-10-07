package com.biavan.brend.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biavan.brend.dao.ClienteDAO;
import com.biavan.brend.model.Cliente;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

@Service
public class ClienteServiceImpl implements ClienteService {

	private ClienteDAO clienteDAO;
	
	public void setClienteDAO(ClienteDAO clienteDAO) {
		this.clienteDAO = clienteDAO;
	}
	
	@Override
	@Transactional
	public void insereCliente(Cliente cliente) {
		this.clienteDAO.insere(cliente);

	}

	@Override
	@Transactional
	public void atualizaCliente(Cliente cliente) {
		this.clienteDAO.atualiza(cliente);

	}

	@Override
	@Transactional
	public List<Cliente> listaClientes() {
		return this.clienteDAO.lista();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public BootgridData<Cliente> listaClientesBootgrid(BootgridParam param) {
		return this.clienteDAO.listaToBootgrid(param);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public BootgridData<Cliente> listaClientesBootgrid(String palavra, BootgridParam param) {
		return this.clienteDAO.listaToBootgrid(palavra, param);
	}
	
	
	@Override
	@Transactional
	public Cliente getClienteById(long id) {
		return this.clienteDAO.getById(id);
	}

	@Override
	@Transactional
	public void removeCliente(long id) {
		this.clienteDAO.remove(id);

	}
	
	@Override
	@Transactional
	public Map<Long, String> listaClientesForDropDown() {
		Map<Long, String> listaClientes = new LinkedHashMap<Long, String>();
		
		for (Cliente m : this.clienteDAO.lista()) {
			listaClientes.put(m.getId(), m.getRazao());
		}
		return listaClientes;
	}

}
