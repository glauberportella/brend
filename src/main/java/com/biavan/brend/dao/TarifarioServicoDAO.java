package com.biavan.brend.dao;

import java.util.List;

import com.biavan.brend.model.Servico;
import com.biavan.brend.model.TarifarioServico;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

public interface TarifarioServicoDAO {
	
	public void insere(TarifarioServico tarifarioServico);

	public void atualiza(TarifarioServico tarifarioServico);

	public BootgridData<TarifarioServico> listaToBootgrid(Servico servico, BootgridParam param);
	
	public List<TarifarioServico> lista();

	public TarifarioServico getById(long id);

	public void remove(long id);

}
