package com.biavan.brend.service;

import java.util.List;
import java.util.Set;

import com.biavan.brend.model.Peca;
import com.biavan.brend.model.PecaPedido;
import com.biavan.brend.model.Pedido;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

public interface PedidoService {

	public void inserePedido(Pedido pedido, Set<PecaPedido> pecasPedido);

	public void atualizaPedido(Pedido pedido, Set<PecaPedido> pecasPedido);

	public List<Pedido> listaPedidos();
	
	public <E> BootgridData<E> listaPedidosBootgrid(String palavra, BootgridParam query);
	
	public <E> BootgridData<E> listaPedidosBootgrid(BootgridParam query);

	public Pedido getPedidoById(long id);

	public void removePedido(long id);
	
	public List<Peca> getPecas(String param);
	
	public void atualizaEstoque(Pedido pedido, Set<PecaPedido> pecasPedido);
	
}
