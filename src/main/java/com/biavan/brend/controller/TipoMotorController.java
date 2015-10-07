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

import com.biavan.brend.model.TipoMotor;
import com.biavan.brend.plugin.bootgrid.BootgridAction;
import com.biavan.brend.plugin.bootgrid.BootgridColumn;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridJS;
import com.biavan.brend.plugin.bootgrid.BootgridUtil;
import com.biavan.brend.service.TipoMotorService;

@Controller
@RequestMapping("/tipoMotor")
public class TipoMotorController {

	private TipoMotorService tipoMotorService;

	@Autowired(required = true)
	@Qualifier(value = "tipoMotorService")
	public void setTipoMotorService(TipoMotorService tipoMotorService) {
		this.tipoMotorService = tipoMotorService;
	}

	
	
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listaTipoMotors(Model model) {

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
		model.addAttribute("tipoMotor", new TipoMotor());
		return "tipo_motor_listar";
	}
	
	@RequestMapping(value = "/listarjson", method = RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody BootgridData<TipoMotor> listaTipoMotorJson(@RequestBody String parametros) {
		return this.tipoMotorService.listaTipoMotorsBootgrid(BootgridUtil.makeParam(parametros));
	}

	@RequestMapping(value = "/inserir", method = RequestMethod.POST)
	public String insereTipoMotor(
			@Valid TipoMotor tipoMotor
			,BindingResult result) {
		
		if(result.hasErrors())
			return "tipo_motor_atualizar";
		
		if (tipoMotor.getId() == 0) {
			this.tipoMotorService.insereTipoMotor(tipoMotor);
		} else {
			this.tipoMotorService.atualizaTipoMotor(tipoMotor);
		}

		return "redirect:/tipoMotor/listar";
	}

	@RequestMapping(value = "/remover/{id}")
	public String removeTipoMotor(@PathVariable("id") long id) {
		this.tipoMotorService.removeTipoMotor(id);
		return "redirect:/tipoMotor/listar";
	}

	@RequestMapping(value = "/telaAtualizar/{id}")
	public String atualizarTipoMotor(@PathVariable("id") long id, Model model) {
		//model.addAttribute("listaTipoMotors", tipoMotorService.listaTipoMotorForDropDown());
		model.addAttribute("tipoMotor", this.tipoMotorService.getTipoMotorById(id));
		return "tipo_motor_atualizar";
	}
	
	@RequestMapping(value = "/telaInserir")
	public String telaInserirTipoMotor(Model model) {
		//model.addAttribute("listaTipoMotors", tipoMotorService.listaTipoMotorForDropDown());
		model.addAttribute("tipoMotor", new TipoMotor());
		return "tipo_motor_atualizar";
	}
}
