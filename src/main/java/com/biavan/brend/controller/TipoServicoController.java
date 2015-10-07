package com.biavan.brend.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biavan.brend.model.TipoServico;
import com.biavan.brend.plugin.bootgrid.BootgridAction;
import com.biavan.brend.plugin.bootgrid.BootgridColumn;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridJS;
import com.biavan.brend.plugin.bootgrid.BootgridUtil;
import com.biavan.brend.service.TipoServicoService;

@Controller
@RequestMapping("/tipoServico")
public class TipoServicoController {

	private TipoServicoService tipoServicoService;

	@Autowired(required = true)
	@Qualifier(value = "tipoServicoService")
	public void setTipoServicoService(TipoServicoService tipoServicoService) {
		this.tipoServicoService = tipoServicoService;
	}

	
	
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listaTipoServicos(Model model) {

		Map<Integer, BootgridAction> botoes = new HashMap<Integer, BootgridAction>();
		botoes.put(1, new BootgridAction("alterar", "Alterar", "telaAtualizar"));
		botoes.put(2, new BootgridAction("remover", "Excluir", "remover", true));
		
		Map<Integer, BootgridColumn> colunas = new HashMap<Integer, BootgridColumn>();
		colunas.put(1, new BootgridColumn("id", "ID"));
		colunas.put(2, new BootgridColumn("nome", "Nome"));
				
		BootgridJS bootgridJS = new BootgridJS();

		bootgridJS.setBotoes(botoes);
		bootgridJS.setColumns(colunas);
		bootgridJS.setCssId("grid-data");
		bootgridJS.setObjId("b0df282a-0d67-40e5-8558-c9e93b7befed");
		bootgridJS.setUrl("listarjson");
		bootgridJS.setCssClass("table table-condensed table-hover table-striped");
		
		model.addAttribute("renderBootgridJS", BootgridUtil.renderJS(bootgridJS));
		model.addAttribute("renderBootgridGrid", BootgridUtil.renderGrid(bootgridJS));
		model.addAttribute("tipoServico", new TipoServico());
		return "tipo_servico_listar";
	}
	
	@RequestMapping(value = "/listarjson", method = RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody BootgridData<TipoServico> listaTipoServicoJson(@RequestBody String parametros) {
		return this.tipoServicoService.listaTipoServicosBootgrid(BootgridUtil.makeParam(parametros));
	}

	@RequestMapping(value = "/inserir", method = RequestMethod.POST)
	public String insereTipoServico(
			@Valid TipoServico tipoServico
			,BindingResult result) {
		
		if (result.hasErrors())
			return "tipo_servico_atualizar";
		
		if (tipoServico.getId() == 0) {
			this.tipoServicoService.insereTipoServico(tipoServico);
		} else {
			this.tipoServicoService.atualizaTipoServico(tipoServico);
		}

		return "redirect:/tipoServico/listar";
	}

	@RequestMapping(value = "/remover/{id}")
	public String removeTipoServico(@PathVariable("id") long id) {
		this.tipoServicoService.removeTipoServico(id);
		return "redirect:/tipoServico/listar";
	}

	@RequestMapping(value = "/telaAtualizar/{id}")
	public String atualizarTipoServico(@PathVariable("id") long id, Model model) {
		//model.addAttribute("listaTipoServicos", tipoServicoService.listaTipoServicoForDropDown());
		model.addAttribute("tipoServico", this.tipoServicoService.getTipoServicoById(id));
		return "tipo_servico_atualizar";
	}
	
	@RequestMapping(value = "/telaInserir")
	public String telaInserirTipoServico(Model model) {
		//model.addAttribute("listaTipoServicos", tipoServicoService.listaTipoServicoForDropDown());
		model.addAttribute("tipoServico", new TipoServico());
		return "tipo_servico_atualizar";
	}
}
