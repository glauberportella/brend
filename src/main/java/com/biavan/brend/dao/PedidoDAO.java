package com.biavan.brend.dao;

import java.util.List;

import com.biavan.brend.model.Peca;
import com.biavan.brend.model.PecaPedido;
import com.biavan.brend.model.Pedido;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

public interface PedidoDAO {
	
	public long insere(Pedido pedido);
	
	public void inserePecaPedido(PecaPedido pecaPedido);

	public long atualiza(Pedido pedido);

	public BootgridData<Pedido> listaToBootgrid(BootgridParam param);
	
	public BootgridData<Pedido> listaToBootgrid(String palavra, BootgridParam param);
	
	public List<Pedido> lista();

	public Pedido getById(long id);

	public void remove(long id);
	
	public List<Peca> getPecas(List<Long> ids);

}
