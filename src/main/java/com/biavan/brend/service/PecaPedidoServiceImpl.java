package com.biavan.brend.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biavan.brend.dao.PecaPedidoDAO;
import com.biavan.brend.model.PecaPedido;
import com.biavan.brend.model.Pedido;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

@Service
public class PecaPedidoServiceImpl implements PecaPedidoService {

	private PecaPedidoDAO pecaPedidoDAO;
	
	public void setPecaPedidoDAO(PecaPedidoDAO pecaPedidoDAO) {
		this.pecaPedidoDAO = pecaPedidoDAO;
	}
	
	@Override
	@Transactional
	public void inserePecaPedido(PecaPedido pecaPedido) {
		this.pecaPedidoDAO.insere(pecaPedido);

	}

	@Override
	@Transactional
	public void atualizaPecaPedido(PecaPedido pecaPedido) {
		this.pecaPedidoDAO.atualiza(pecaPedido);

	}

	@Override
	@Transactional
	public List<PecaPedido> listaPecaPedidosByPedido(Pedido pedido) {
		return this.pecaPedidoDAO.listaByPedido(pedido);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public BootgridData<PecaPedido> listaPecaPedidosBootgrid(BootgridParam param) {
		return this.pecaPedidoDAO.listaToBootgrid(param);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public BootgridData<PecaPedido> listaPecaPedidosBootgrid(String palavra, BootgridParam param) {
		return this.pecaPedidoDAO.listaToBootgrid(palavra, param);
	}
	
	
	@Override
	@Transactional
	public PecaPedido getPecaPedidoById(long id) {
		return this.pecaPedidoDAO.getById(id);
	}

	@Override
	@Transactional
	public void removePecaPedidoByPedido(Pedido pedido) {
		this.pecaPedidoDAO.removeByPedido(pedido);

	}
}
