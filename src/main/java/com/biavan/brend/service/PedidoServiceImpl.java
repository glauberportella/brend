package com.biavan.brend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biavan.brend.dao.ConfigEmpresaDAO;
import com.biavan.brend.dao.PecaDAO;
import com.biavan.brend.dao.PecaPedidoDAO;
import com.biavan.brend.dao.PedidoDAO;
import com.biavan.brend.dao.TarifarioPecaDAO;
import com.biavan.brend.model.ConfigEmpresa;
import com.biavan.brend.model.Peca;
import com.biavan.brend.model.PecaPedido;
import com.biavan.brend.model.Pedido;
import com.biavan.brend.model.TarifarioPeca;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

@Service
public class PedidoServiceImpl implements PedidoService {

	private PedidoDAO pedidoDAO;
	
	private PecaPedidoDAO pecaPedidoDAO;
	
	private TarifarioPecaDAO tarifarioPecaDAO;
	
	private PecaDAO pecaDAO;
	
	private ConfigEmpresaDAO configEmpresaDAO;

	public void setPedidoDAO(PedidoDAO pedidoDAO) {
		this.pedidoDAO = pedidoDAO;
	}
	
	public void setPecaPedidoDAO(PecaPedidoDAO pecaPedidoDAO) {
		this.pecaPedidoDAO = pecaPedidoDAO;
	}
	
	public void setTarifarioPecaDAO(TarifarioPecaDAO tarifarioPecaDAO) {
		this.tarifarioPecaDAO = tarifarioPecaDAO;
	}
	
	public void setPecaDAO(PecaDAO pecaDAO) {
		this.pecaDAO = pecaDAO;
	}
	
	public void setConfigEmpresaDAO(ConfigEmpresaDAO configEmpresaDAO) {
		this.configEmpresaDAO = configEmpresaDAO;
	}
	
	@Override
	@Transactional
	public void inserePedido(Pedido pedido, Set<PecaPedido> pecasPedido) {
		pedidoDAO.insere(pedido);
		
		inserirPedidos(pedido, pecasPedido);
	}

	@Override
	@Transactional
	public void atualizaPedido(Pedido pedido, Set<PecaPedido> pecasPedido) {
		pedidoDAO.atualiza(pedido);
		
		pecaPedidoDAO.removeByPedido(pedido);
		
		inserirPedidos(pedido, pecasPedido);

	}
	
	private void inserirPedidos(Pedido pedido, Set<PecaPedido> pecasPedido) {
		long pecaIdAnterior = 0;
		for(PecaPedido pp : pecasPedido) {
			pp.setPedido(pedido);
			if (pp.getPeca().getId() != pecaIdAnterior)
				pecaPedidoDAO.insere(pp);
			pecaIdAnterior = pp.getPeca().getId(); 
		}
	}

	@Override
	@Transactional
	public List<Pedido> listaPedidos() {
		return this.pedidoDAO.lista();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public BootgridData<Pedido> listaPedidosBootgrid(BootgridParam param) {
		BootgridData<Pedido> bootgridData = pedidoDAO.listaToBootgrid(param);
		
		for(Pedido p : bootgridData.getRows()) {
			p.getRazaoFornecedor();
		}
		
		return bootgridData;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public BootgridData<Pedido> listaPedidosBootgrid(String palavra, BootgridParam param) {
		return this.pedidoDAO.listaToBootgrid(palavra, param);
	}
	
	
	@Override
	@Transactional
	public Pedido getPedidoById(long id) {
		return this.pedidoDAO.getById(id);
	}

	@Override
	@Transactional
	public void removePedido(long id) {
		this.pedidoDAO.remove(id);

	}
	
	@Override
	@Transactional
	public List<Peca> getPecas(String param) {
		String[] paramSplited = param.split("&");
		
		List<Long> ids = new ArrayList<Long>();
		for(int i = 0; i < paramSplited.length; i++) {
			if (paramSplited[i].contains("select")) {
				ids.add(Long.valueOf(paramSplited[i].split("=")[1]));
			}
		}
		
		List<Peca> pecas = pedidoDAO.getPecas(ids);
		
		for(Peca p : pecas) {
			p.setPreco();
			for(TarifarioPeca tp : p.getPrecos()) {
				tp.setPeca(null);
			}
		}
		
		return pecas;
	}
	
	@Override
	@Transactional
	public void atualizaEstoque(Pedido pedido, Set<PecaPedido> pecasPedido) {
		ConfigEmpresa configEmpresa = configEmpresaDAO.getById(1);
		for(PecaPedido pp : pecasPedido) {
			TarifarioPeca tarifarioPeca = new TarifarioPeca();
			tarifarioPeca.setData(pedido.getDataCompra());
			tarifarioPeca.setPeca(pp.getPeca());
			tarifarioPeca.setValor(pp.getValor());
			tarifarioPeca.setMarkup(configEmpresa.getMarkup());
			tarifarioPeca.setValorVenda((pp.getValor() * (configEmpresa.getMarkup() / 100)) + pp.getValor());
			tarifarioPecaDAO.insere(tarifarioPeca);
			
			Peca peca = pecaDAO.getById(pp.getPeca().getId());
			peca.setQuantidade(pp.getQtde() + peca.getQuantidade());
			pecaDAO.atualiza(peca);
		}
	}

}
