package com.biavan.brend.dao;

import java.util.List;

import com.biavan.brend.model.PecaPedido;
import com.biavan.brend.model.Pedido;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

public interface PecaPedidoDAO {
	
	public void insere(PecaPedido pecaPedido);

	public void atualiza(PecaPedido pecaPedido);

	public BootgridData<PecaPedido> listaToBootgrid(BootgridParam param);
	
	public BootgridData<PecaPedido> listaToBootgrid(String palavra, BootgridParam param);
	
	public List<PecaPedido> listaByPedido(Pedido pedido);

	public PecaPedido getById(long id);

	public void removeByPedido(Pedido pedido);
}
