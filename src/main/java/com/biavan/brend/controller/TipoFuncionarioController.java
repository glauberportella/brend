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

import com.biavan.brend.model.TipoFuncionario;
import com.biavan.brend.plugin.bootgrid.BootgridAction;
import com.biavan.brend.plugin.bootgrid.BootgridColumn;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridJS;
import com.biavan.brend.plugin.bootgrid.BootgridUtil;
import com.biavan.brend.service.TipoFuncionarioService;

@Controller
@RequestMapping("/tipoFuncionario")
public class TipoFuncionarioController {

	private TipoFuncionarioService tipoFuncionarioService;

	@Autowired(required = true)
	@Qualifier(value = "tipoFuncionarioService")
	public void setTipoFuncionarioService(TipoFuncionarioService tipoFuncionarioService) {
		this.tipoFuncionarioService = tipoFuncionarioService;
	}

	
	
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listaTipoFuncionarios(Model model) {

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
		model.addAttribute("tipoFuncionario", new TipoFuncionario());
		return "tipo_funcionario_listar";
	}
	
	@RequestMapping(value = "/listarjson", method = RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody BootgridData<TipoFuncionario> listaTipoFuncionarioJson(@RequestBody String parametros) {
		return this.tipoFuncionarioService.listaTipoFuncionariosBootgrid(BootgridUtil.makeParam(parametros));
	}

	@RequestMapping(value = "/inserir", method = RequestMethod.POST)
	public String insereTipoFuncionario(
			@Valid TipoFuncionario tipoFuncionario
			,BindingResult result) {
		
		if (result.hasErrors())
			return "tipo_funcionario_atualizar";
		
		if (tipoFuncionario.getId() == 0) {
			this.tipoFuncionarioService.insereTipoFuncionario(tipoFuncionario);
		} else {
			this.tipoFuncionarioService.atualizaTipoFuncionario(tipoFuncionario);
		}

		return "redirect:/tipoFuncionario/listar";
	}

	@RequestMapping(value = "/remover/{id}")
	public String removeTipoFuncionario(@PathVariable("id") long id) {
		this.tipoFuncionarioService.removeTipoFuncionario(id);
		return "redirect:/tipoFuncionario/listar";
	}

	@RequestMapping(value = "/telaAtualizar/{id}")
	public String atualizarTipoFuncionario(@PathVariable("id") long id, Model model) {
		TipoFuncionario tipoFuncionario = tipoFuncionarioService.getTipoFuncionarioById(id);
		model.addAttribute("isMecanico", tipoFuncionario.isMecanico() ? "Sim" : "Não");
		model.addAttribute("tipoFuncionario", tipoFuncionario);
		return "tipo_funcionario_atualizar";
	}
	
	@RequestMapping(value = "/telaInserir")
	public String telaInserirTipoFuncionario(Model model) {
		model.addAttribute("tipoFuncionario", new TipoFuncionario());
		return "tipo_funcionario_atualizar";
	}
}
