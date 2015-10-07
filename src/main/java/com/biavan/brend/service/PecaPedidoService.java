package com.biavan.brend.service;

import java.util.List;

import com.biavan.brend.model.PecaPedido;
import com.biavan.brend.model.Pedido;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

public interface PecaPedidoService {

	public void inserePecaPedido(PecaPedido pecaPedido);

	public void atualizaPecaPedido(PecaPedido pecaPedido);

	public List<PecaPedido> listaPecaPedidosByPedido(Pedido pedido);
	
	public <E> BootgridData<E> listaPecaPedidosBootgrid(String palavra, BootgridParam query);
	
	public <E> BootgridData<E> listaPecaPedidosBootgrid(BootgridParam query);

	public PecaPedido getPecaPedidoById(long id);

	public void removePecaPedidoByPedido(Pedido pedido);
	
}
