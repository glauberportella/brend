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

import com.biavan.brend.model.Marca;
import com.biavan.brend.plugin.bootgrid.BootgridAction;
import com.biavan.brend.plugin.bootgrid.BootgridColumn;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridJS;
import com.biavan.brend.plugin.bootgrid.BootgridUtil;
import com.biavan.brend.service.MarcaService;

@Controller
@RequestMapping("/marca")
public class MarcaController {

	private MarcaService marcaService;

	@Autowired(required = true)
	@Qualifier(value = "marcaService")
	public void setMarcaService(MarcaService marcaService) {
		this.marcaService = marcaService;
	}
	
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listaMarcas(Model model) {

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
		bootgridJS.setUrl("listarjson");
		bootgridJS.setCssClass("table table-condensed table-hover table-striped");
		
		model.addAttribute("renderBootgridJS", BootgridUtil.renderJS(bootgridJS));
		model.addAttribute("renderBootgridGrid", BootgridUtil.renderGrid(bootgridJS));
		model.addAttribute("marca", new Marca());
		return "marca_listar";
	}
	
	@RequestMapping(value = "/listarjson", method = RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody BootgridData<Marca> listaMarcaJson(@RequestBody String parametros) {
		return this.marcaService.listaMarcasBootgrid(BootgridUtil.makeParam(parametros));
	}

	@RequestMapping(value = "/inserir", method = RequestMethod.POST)
	public String insereMarca(
			@Valid Marca marca
			,BindingResult result
			,Model model) {
		
		if (result.hasErrors())
			return "marca_atualizar";
		if (marca.getId() == 0) {
			this.marcaService.insereMarca(marca);
		} else {
			this.marcaService.atualizaMarca(marca);
		}

		return "redirect:/marca/listar";
	}

	@RequestMapping(value = "/remover/{id}")
	public String removeMarca(@PathVariable("id") long id) {
		this.marcaService.removeMarca(id);
		return "redirect:/marca/listar";
	}

	@RequestMapping(value = "/telaAtualizar/{id}")
	public String atualizarMarca(@PathVariable("id") long id, Model model) {
		model.addAttribute("marca", this.marcaService.getMarcaById(id));
		return "marca_atualizar";
	}
	
	@RequestMapping(value = "/telaInserir")
	public String telaInserirMarca(Model model) {
		model.addAttribute("marca", new Marca());
		return "marca_atualizar";
	}
}
