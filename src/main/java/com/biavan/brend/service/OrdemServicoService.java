package com.biavan.brend.service;

import java.util.List;

import com.biavan.brend.model.Funcionario;
import com.biavan.brend.model.Orcamento;
import com.biavan.brend.model.OrdemServico;
import com.biavan.brend.model.PecaOrdemServico;
import com.biavan.brend.model.ServicoOrdemServico;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridParam;

public interface OrdemServicoService {

	public void insereOrdemServico(OrdemServico ordemServico,
			List<PecaOrdemServico> pecasOrdemServico,
			List<ServicoOrdemServico> servicosOrdemServico);

	public void atualizaOrdemServico(OrdemServico ordemServico,
			List<PecaOrdemServico> pecasOrdemServico,
			List<ServicoOrdemServico> servicosOrdemServico);

	public List<OrdemServico> listaOrdemServico();

	public <E> BootgridData<E> listaOrdemServicosBootgrid(BootgridParam query);

	public <E> BootgridData<E> listaOrdemServicosAdicionaisBootgrid(
			OrdemServico ordemServicoPai, BootgridParam query);

	public <E> BootgridData<E> listaOrdemServicosBootgrid(String palavra,
			BootgridParam query);

	public OrdemServico getOrdemServicoById(long id);

	public void removeOrdemServico(long id);

	public OrdemServico getOrdemServicoByOrcamento(Orcamento orcamento);

	public List<PecaOrdemServico> listaPecas(OrdemServico ordemServico);

	public int qtdeOrdemServicoByMecanico(Funcionario funcionario);
	
	public double getValorTotalOrdemServicoAdicional (OrdemServico ordemServico);
}
