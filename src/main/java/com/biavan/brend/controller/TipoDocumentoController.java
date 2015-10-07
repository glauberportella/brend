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

import com.biavan.brend.model.TipoDocumento;
import com.biavan.brend.plugin.bootgrid.BootgridAction;
import com.biavan.brend.plugin.bootgrid.BootgridColumn;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridJS;
import com.biavan.brend.plugin.bootgrid.BootgridUtil;
import com.biavan.brend.service.TipoDocumentoService;

@Controller
@RequestMapping("/tipoDocumento")
public class TipoDocumentoController {

	private TipoDocumentoService tipoDocumentoService;

	@Autowired(required = true)
	@Qualifier(value = "tipoDocumentoService")
	public void setTipoDocumentoService(TipoDocumentoService tipoDocumentoService) {
		this.tipoDocumentoService = tipoDocumentoService;
	}

	
	
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listaTipoDocumentos(Model model) {

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
		model.addAttribute("tipoDocumento", new TipoDocumento());
		return "tipo_documento_listar";
	}
	
	@RequestMapping(value = "/listarjson", method = RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody BootgridData<TipoDocumento> listaTipoDocumentoJson(@RequestBody String parametros) {
		return this.tipoDocumentoService.listaTipoDocumentosBootgrid(BootgridUtil.makeParam(parametros));
	}

	@RequestMapping(value = "/inserir", method = RequestMethod.POST)
	public String insereTipoDocumento(
			@Valid TipoDocumento tipoDocumento
			,BindingResult result) {
		
		if(result.hasErrors())
			return "tipo_documento_atualizar";
		
		if (tipoDocumento.getId() == 0) {
			this.tipoDocumentoService.insereTipoDocumento(tipoDocumento);
		} else {
			this.tipoDocumentoService.atualizaTipoDocumento(tipoDocumento);
		}

		return "redirect:/tipoDocumento/listar";
	}

	@RequestMapping(value = "/remover/{id}")
	public String removeTipoDocumento(@PathVariable("id") long id) {
		this.tipoDocumentoService.removeTipoDocumento(id);
		return "redirect:/tipoDocumento/listar";
	}

	@RequestMapping(value = "/telaAtualizar/{id}")
	public String atualizarTipoDocumento(@PathVariable("id") long id, Model model) {
		//model.addAttribute("listaTipoDocumentos", tipoDocumentoService.listaTipoDocumentoForDropDown());
		model.addAttribute("tipoDocumento", this.tipoDocumentoService.getTipoDocumentoById(id));
		return "tipo_documento_atualizar";
	}
	
	@RequestMapping(value = "/telaInserir")
	public String telaInserirTipoDocumento(Model model) {
		//model.addAttribute("listaTipoDocumentos", tipoDocumentoService.listaTipoDocumentoForDropDown());
		model.addAttribute("tipoDocumento", new TipoDocumento());
		return "tipo_documento_atualizar";
	}
}
