package com.biavan.brend.dao;

import java.util.List;

import com.biavan.brend.model.Funcionario;
import com.biavan.brend.model.Orcamento;
import com.biavan.brend.model.OrdemServico;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

public interface OrdemServicoDAO {
	
	public void insere(OrdemServico ordemServico);

	public void atualiza(OrdemServico ordemServico);

	public BootgridData<OrdemServico> listaToBootgrid(BootgridParam param);
	
	public BootgridData<OrdemServico> listaAdicionalToBootgrid(OrdemServico ordemServicoPai, BootgridParam param);	
	
	public BootgridData<OrdemServico> listaToBootgrid(String palavra, BootgridParam param);
	
	public List<OrdemServico> lista();

	public OrdemServico getById(long id);
	
	public OrdemServico getOrdemServicoByOrcamento(Orcamento orcamento);

	public void remove(long id);
	
	public List<OrdemServico> listaByMecanico(Funcionario funcionario);

	public Double valorTotalOrdemServicoAdicionais(OrdemServico ordemServico);
}
