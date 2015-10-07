package com.biavan.brend.controller;

import java.beans.PropertyEditorSupport;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biavan.brend.model.Marca;
import com.biavan.brend.model.Peca;
import com.biavan.brend.plugin.bootgrid.BootgridAction;
import com.biavan.brend.plugin.bootgrid.BootgridColumn;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridJS;
import com.biavan.brend.plugin.bootgrid.BootgridUtil;
import com.biavan.brend.service.MarcaService;
import com.biavan.brend.service.PecaService;

@Controller
@RequestMapping("/peca")
public class PecaController {
	
	private PecaService pecaService;
	private MarcaService marcaService;
	
	@Autowired(required = true)
	@Qualifier(value = "pecaService")
	public void setPecaService(PecaService pecaService) {
		this.pecaService = pecaService;
	}
	
	@Autowired(required = true)
	@Qualifier(value = "marcaService")
	public void setMarcaService(MarcaService marcaService) {
		this.marcaService = marcaService;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		
		binder.registerCustomEditor(Marca.class, "marca", new PropertyEditorSupport() {
			@Override
	        public void setAsText(String marcaId) {
				Marca marca = marcaService.getMarcaById(Long.valueOf(marcaId));
				setValue(marca);
			}
		});
	}	
	
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listaPecas(Model model) {

		Map<Integer, BootgridAction> botoes = new HashMap<Integer, BootgridAction>();
		botoes.put(1, new BootgridAction("alterar", "Alterar", "telaAtualizar"));
		botoes.put(2, new BootgridAction("remover", "Excluir", "remover", true));
		
		Map<Integer, BootgridColumn> colunas = new HashMap<Integer, BootgridColumn>();
		colunas.put(1, new BootgridColumn("id", "ID"));
		colunas.put(2, new BootgridColumn("nome", "Nome"));
		colunas.put(3, new BootgridColumn("descricao", "Descricao"));
		colunas.put(4, new BootgridColumn("preco", "Preço de Custo", false));
		colunas.put(5, new BootgridColumn("precoVenda", "Preço de Venda", false));
		colunas.put(6, new BootgridColumn("quantidade", "Estoque", false));
		
		
		BootgridJS bootgridJS = new BootgridJS();

		bootgridJS.setBotoes(botoes);
		bootgridJS.setColumns(colunas);
		bootgridJS.setCssId("grid-data");
		bootgridJS.setUrl("listarjson");
		bootgridJS.setCssClass("table table-condensed table-hover table-striped");
		
		model.addAttribute("renderBootgridJS", BootgridUtil.renderJS(bootgridJS));
		model.addAttribute("renderBootgridGrid", BootgridUtil.renderGrid(bootgridJS));
		model.addAttribute("peca", new Peca());
		return "peca_listar";
	}
	
	@RequestMapping(value = "/listarjson", method = RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody BootgridData<Peca> listaPecaJson(@RequestBody String parametros) {
		return this.pecaService.listaPecasBootgrid(BootgridUtil.makeParam(parametros));
	}

	@RequestMapping(value = "/inserir", method = RequestMethod.POST)
	public String inserePeca(
			//@ModelAttribute("peca") Peca peca
			@Valid Peca peca
			,BindingResult result
			,Model model) {
		
		if (result.hasErrors())
		{
			model.addAttribute("listaMarca", marcaService.listaMarcaForDropDown());
			return "peca_atualizar";
		}
		
		if (peca.getId() == 0) {
			this.pecaService.inserePeca(peca);
		} else {
			this.pecaService.atualizaPeca(peca);
		}

		return "redirect:/peca/listar";
	}

	@RequestMapping(value = "/remover/{id}")
	public String removePeca(@PathVariable("id") long id) {
		this.pecaService.removePeca(id);
		return "redirect:/peca/listar";
	}

	@RequestMapping(value = "/telaAtualizar/{id}")
	public String atualizarPeca(@PathVariable("id") long id, Model model) {
		model.addAttribute("listaMarca", marcaService.listaMarcaForDropDown());
		model.addAttribute("peca", this.pecaService.getPecaById(id));
		return "peca_atualizar";
	}
	
	@RequestMapping(value = "/telaInserir")
	public String telaInserirPeca(Model model) {
		model.addAttribute("listaMarca", marcaService.listaMarcaForDropDown());
		model.addAttribute("peca", new Peca());
		return "peca_atualizar";
	}
}
