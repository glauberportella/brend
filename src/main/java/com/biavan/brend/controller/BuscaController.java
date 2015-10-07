package com.biavan.brend.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biavan.brend.model.Cliente;
import com.biavan.brend.plugin.bootgrid.BootgridAction;
import com.biavan.brend.plugin.bootgrid.BootgridColumn;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridJS;
import com.biavan.brend.plugin.bootgrid.BootgridUtil;
import com.biavan.brend.service.ClienteService;
import com.biavan.brend.service.OrcamentoService;
import com.biavan.brend.service.OrdemServicoService;
import com.biavan.brend.service.PecaService;

@Controller
@RequestMapping("/busca")
public class BuscaController {

	private ClienteService clienteService;
	private PecaService pecaService;
	private OrcamentoService orcamentoService;
	private OrdemServicoService ordemServicoService;

	@Autowired(required = true)
	@Qualifier(value = "clienteService")
	public void setClienteService(ClienteService clienteService) {
		this.clienteService = clienteService;
	}
	
	@Autowired(required = true)
	@Qualifier(value = "pecaService")
	public void setPecaService(PecaService pecaService) {
		this.pecaService = pecaService;
	}	

	@Autowired(required = true)
	@Qualifier(value = "orcamentoService")
	public void setOrcamentoService(OrcamentoService orcamentoService) {
		this.orcamentoService = orcamentoService;
	}	

	@Autowired(required = true)
	@Qualifier(value = "ordemServicoService")
	public void setOrdemServicoService(OrdemServicoService ordemServicoService) {
		this.ordemServicoService = ordemServicoService;
	}	

	@RequestMapping(value = "/orcamento/listarjson/{palavra}", method = RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody BootgridData<Cliente> listaOrcamentoJson(@PathVariable("palavra") String palavra,
			@RequestBody String parametros) {
		
		return orcamentoService.listaOrcamentosBootgrid(palavra, BootgridUtil.makeParam(parametros));
	}	
	
	@RequestMapping(value = "/ordemservico/listarjson/{palavra}", method = RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody BootgridData<Cliente> listaOrdemServicoJson(@PathVariable("palavra") String palavra,
			@RequestBody String parametros) {
		
		return ordemServicoService.listaOrdemServicosBootgrid(palavra, BootgridUtil.makeParam(parametros));
	}
	
	@RequestMapping(value = "/pesquisar", method = RequestMethod.GET)
	public String pesquisar(
			@ModelAttribute("palavra") String palavra, Model model) {
		
		BootgridJS bootgridJS = new BootgridJS();
		bootgridJS.setCssOptions("hide-search");
		
		model.addAttribute("renderCSS", BootgridUtil.renderCSS(bootgridJS));
		model.addAttribute("renderOrcamento", renderizaOrcamentos(palavra));
		model.addAttribute("renderOrdemServico", renderizaOrdemServicos(palavra));

		return "resultado_pesquisa";
	}

	public Map<String, String> renderizaOrcamentos(String palavra) {

		Map<Integer, BootgridAction> botoes = new HashMap<Integer, BootgridAction>();
		botoes.put(1, new BootgridAction("alterar", "Alterar", "../orcamento/telaAtualizar"));
		botoes.put(2, new BootgridAction("excluir", "Excluir", "../orcamento/remover", true));
		
		Map<Integer, BootgridColumn> colunas = new HashMap<Integer, BootgridColumn>();
		colunas.put(1, new BootgridColumn("id", "ID"));
		colunas.put(2, new BootgridColumn("clienteNome", "Cliente"));
		colunas.put(3, new BootgridColumn("modeloVeiculo", "Veiculo"));
		colunas.put(4, new BootgridColumn("ano", "Ano"));

		BootgridJS bootgridJS = new BootgridJS();

		bootgridJS.setBotoes(botoes);
		bootgridJS.setColumns(colunas);
		bootgridJS.setCssId("grid-data-orcamento");
		bootgridJS.setObjId("renderOrcamentoId");
		bootgridJS.setUrl("orcamento/listarjson/" + palavra);
		bootgridJS.setCssClass("table table-condensed table-hover table-striped");

		Map<String, String> renderOrcamento = new HashMap<String, String>();
		renderOrcamento.put("renderBootgridJS", BootgridUtil.renderJS(bootgridJS));
		renderOrcamento.put("renderBootgridGrid", BootgridUtil.renderGrid(bootgridJS));
		
		return renderOrcamento;
	}

	public Map<String, String> renderizaOrdemServicos(String palavra) {
		
		Map<Integer, BootgridAction> botoes = new HashMap<Integer, BootgridAction>();
		botoes.put(1, new BootgridAction("visualizar", "Visualizar", "../ordemServico/telaAtualizar"));
		
		Map<Integer, BootgridColumn> colunas = new HashMap<Integer, BootgridColumn>();
		colunas.put(1, new BootgridColumn("id", "ID"));
		colunas.put(2, new BootgridColumn("clienteNome", "Cliente"));
		colunas.put(3, new BootgridColumn("nomeVeiculo", "Veiculo"));
		colunas.put(4, new BootgridColumn("statusAtualDescricao", "Status"));
		colunas.put(5, new BootgridColumn("valorTotal", "Valor Total" ));
		
		BootgridJS bootgridJS = new BootgridJS();

		bootgridJS.setBotoes(botoes);
		bootgridJS.setColumns(colunas);
		bootgridJS.setCssId("grid-data-ordem-servico");
		bootgridJS.setObjId("renderOrdemServicoId");
		bootgridJS.setUrl("ordemservico/listarjson/" + palavra);
		bootgridJS.setCssClass("table table-condensed table-hover table-striped");

		Map<String, String> renderOrdemServico = new HashMap<String, String>();
		renderOrdemServico.put("renderBootgridJS", BootgridUtil.renderJS(bootgridJS));
		renderOrdemServico.put("renderBootgridGrid", BootgridUtil.renderGrid(bootgridJS));
		
		return renderOrdemServico;

	}
}
