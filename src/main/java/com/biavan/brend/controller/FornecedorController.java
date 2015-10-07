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

import com.biavan.brend.model.Fornecedor;
import com.biavan.brend.plugin.bootgrid.BootgridAction;
import com.biavan.brend.plugin.bootgrid.BootgridColumn;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridJS;
import com.biavan.brend.plugin.bootgrid.BootgridUtil;
import com.biavan.brend.service.FornecedorService;

@Controller
@RequestMapping("/fornecedor")
public class FornecedorController {

	private FornecedorService fornecedorService;

	@Autowired(required = true)
	@Qualifier(value = "fornecedorService")
	public void setFornecedorService(FornecedorService fornecedorService) {
		this.fornecedorService = fornecedorService;
	}

	
	
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listaFornecedores(Model model) {

		Map<Integer, BootgridAction> botoes = new HashMap<Integer, BootgridAction>();
		botoes.put(1, new BootgridAction("alterar", "Alterar", "telaAtualizar"));
		botoes.put(2, new BootgridAction("remover", "Excluir", "remover", true));
		
		Map<Integer, BootgridColumn> colunas = new HashMap<Integer, BootgridColumn>();
		colunas.put(1, new BootgridColumn("id", "ID"));
		colunas.put(2, new BootgridColumn("razao", "Razão"));
		colunas.put(3, new BootgridColumn("fantasia", "Fantasia"));
				
		BootgridJS bootgridJS = new BootgridJS();

		bootgridJS.setBotoes(botoes);
		bootgridJS.setColumns(colunas);
		bootgridJS.setCssId("grid-data");
		bootgridJS.setObjId("b0df282a-0d67-40e5-8558-c9e93b7befed");
		bootgridJS.setUrl("listarjson");
		bootgridJS.setCssClass("table table-condensed table-hover table-striped");
		
		model.addAttribute("renderBootgridJS", BootgridUtil.renderJS(bootgridJS));
		model.addAttribute("renderBootgridGrid", BootgridUtil.renderGrid(bootgridJS));
		model.addAttribute("fornecedor", new Fornecedor());
		return "fornecedor_listar";
	}
	
	@RequestMapping(value = "/listarjson", method = RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody BootgridData<Fornecedor> listaFornecedorJson(@RequestBody String parametros) {
		return this.fornecedorService.listaFornecedoresBootgrid(BootgridUtil.makeParam(parametros));
	}

	@RequestMapping(value = "/inserir", method = RequestMethod.POST)
	public String insereFornecedor(
			@Valid Fornecedor fornecedor, BindingResult result, Model model) {
		
		System.out.println(fornecedor);
		
		if (result.hasErrors())
			return "fornecedor_atualizar";
		
		if (fornecedor.getId() == 0) {
			this.fornecedorService.insereFornecedor(fornecedor);
		} else {
			this.fornecedorService.atualizaFornecedor(fornecedor);
		}

		return "redirect:/fornecedor/listar";
	}

	@RequestMapping(value = "/remover/{id}")
	public String removeFornecedor(@PathVariable("id") long id) {
		this.fornecedorService.removeFornecedor(id);
		return "redirect:/fornecedor/listar";
	}

	@RequestMapping(value = "/telaAtualizar/{id}")
	public String atualizarFornecedor(@PathVariable("id") long id, Model model) {
		//model.addAttribute("listaFornecedores", fornecedorService.listaFornecedorForDropDown());
		model.addAttribute("fornecedor", this.fornecedorService.getFornecedorById(id));
		return "fornecedor_atualizar";
	}
	
	@RequestMapping(value = "/telaInserir")
	public String telaInserirFornecedor(Model model) {
		//model.addAttribute("listaFornecedores", fornecedorService.listaFornecedorForDropDown());
		model.addAttribute("fornecedor", new Fornecedor());
		return "fornecedor_atualizar";
	}
}
