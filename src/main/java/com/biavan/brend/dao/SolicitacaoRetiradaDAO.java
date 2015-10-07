package com.biavan.brend.dao;

import java.util.List;

import com.biavan.brend.model.SolicitacaoRetirada;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

public interface SolicitacaoRetiradaDAO {
	
	public void insere(SolicitacaoRetirada solicitacao);

	public void atualiza(SolicitacaoRetirada solicitacao);

	public BootgridData<SolicitacaoRetirada> listaToBootgrid(BootgridParam param);
	
	public BootgridData<SolicitacaoRetirada> listaPendenteToBootgrid(BootgridParam param);	
	
	public List<SolicitacaoRetirada> lista();

	public SolicitacaoRetirada getById(long id);

	public void remove(long id);
}
