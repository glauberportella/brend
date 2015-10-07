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

import com.biavan.brend.model.Veiculo;
import com.biavan.brend.plugin.bootgrid.BootgridAction;
import com.biavan.brend.plugin.bootgrid.BootgridColumn;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridJS;
import com.biavan.brend.plugin.bootgrid.BootgridUtil;
import com.biavan.brend.service.VeiculoService;

@Controller
@RequestMapping("/veiculo")
public class VeiculoController {

	private VeiculoService veiculoService;

	@Autowired(required = true)
	@Qualifier(value = "veiculoService")
	public void setVeiculoService(VeiculoService veiculoService) {
		this.veiculoService = veiculoService;
	}

	
	
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listaVeiculos(Model model) {

		Map<Integer, BootgridAction> botoes = new HashMap<Integer, BootgridAction>();
		botoes.put(1, new BootgridAction("alterar", "Alterar", "telaAtualizar"));
		botoes.put(2, new BootgridAction("remover", "Excluir", "remover", true));
		
		Map<Integer, BootgridColumn> colunas = new HashMap<Integer, BootgridColumn>();
		colunas.put(1, new BootgridColumn("id", "ID"));
		colunas.put(2, new BootgridColumn("marca", "Marca"));
		colunas.put(3, new BootgridColumn("modelo", "Modelo"));
				
		BootgridJS bootgridJS = new BootgridJS();

		bootgridJS.setBotoes(botoes);
		bootgridJS.setColumns(colunas);
		bootgridJS.setCssId("grid-data");
		bootgridJS.setObjId("b0df282a-0d67-40e5-8558-c9e93b7befed");
		bootgridJS.setUrl("listarjson");
		bootgridJS.setCssClass("table table-condensed table-hover table-striped");
		
		model.addAttribute("renderBootgridJS", BootgridUtil.renderJS(bootgridJS));
		model.addAttribute("renderBootgridGrid", BootgridUtil.renderGrid(bootgridJS));
		model.addAttribute("veiculo", new Veiculo());
		return "veiculo_listar";
	}
	
	@RequestMapping(value = "/listarjson", method = RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody BootgridData<Veiculo> listaVeiculoJson(@RequestBody String parametros) {
		return this.veiculoService.listaVeiculosBootgrid(BootgridUtil.makeParam(parametros));
	}

	@RequestMapping(value = "/inserir", method = RequestMethod.POST)
	public String insereVeiculo(
			@Valid Veiculo veiculo
			,BindingResult result) {
		
		if (result.hasErrors())
			return "veiculo_atualizar";
		if (veiculo.getId() == 0) {
			this.veiculoService.insereVeiculo(veiculo);
		} else {
			this.veiculoService.atualizaVeiculo(veiculo);
		}

		return "redirect:/veiculo/listar";
	}

	@RequestMapping(value = "/remover/{id}")
	public String removeVeiculo(@PathVariable("id") long id) {
		this.veiculoService.removeVeiculo(id);
		return "redirect:/veiculo/listar";
	}

	@RequestMapping(value = "/telaAtualizar/{id}")
	public String atualizarVeiculo(@PathVariable("id") long id, Model model) {
		model.addAttribute("veiculo", this.veiculoService.getVeiculoById(id));
		return "veiculo_atualizar";
	}
	
	@RequestMapping(value = "/telaInserir")
	public String telaInserirVeiculo(Model model) {
		model.addAttribute("veiculo", new Veiculo());
		return "veiculo_atualizar";
	}
}
