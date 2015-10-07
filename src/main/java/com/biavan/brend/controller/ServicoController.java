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

import com.biavan.brend.model.Servico;
import com.biavan.brend.model.TipoServico;
import com.biavan.brend.plugin.bootgrid.BootgridAction;
import com.biavan.brend.plugin.bootgrid.BootgridColumn;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridJS;
import com.biavan.brend.plugin.bootgrid.BootgridUtil;
import com.biavan.brend.service.ServicoService;
import com.biavan.brend.service.TipoServicoService;

@Controller
@RequestMapping("/servico")
public class ServicoController {
	
	private ServicoService servicoService;
	private TipoServicoService tipoServicoService;
	
	@Autowired(required = true)
	@Qualifier(value = "servicoService")
	public void setServicoService(ServicoService servicoService) {
		this.servicoService = servicoService;
	}
	
	@Autowired(required = true)
	@Qualifier(value = "tipoServicoService")
	public void setTipoServicoService(TipoServicoService tipoServicoService) {
		this.tipoServicoService = tipoServicoService;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		
		binder.registerCustomEditor(TipoServico.class, "tipoServico", new PropertyEditorSupport() {
			@Override
	        public void setAsText(String tipoServicoId) {
				TipoServico tipoServico = tipoServicoService.getTipoServicoById(
						Long.valueOf(tipoServicoId));
				setValue(tipoServico);
			}
		});
	}	
	
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listaServicos(Model model) {

		Map<Integer, BootgridAction> botoes = new HashMap<Integer, BootgridAction>();
		botoes.put(1, new BootgridAction("alterar", "Alterar", "telaAtualizar"));
		botoes.put(2, new BootgridAction("remover", "Excluir", "remover", true));
		
		Map<Integer, BootgridColumn> colunas = new HashMap<Integer, BootgridColumn>();
		colunas.put(1, new BootgridColumn("id", "ID"));
		colunas.put(2, new BootgridColumn("nome", "Nome"));
		colunas.put(3, new BootgridColumn("descricao", "Descrição"));
		colunas.put(4, new BootgridColumn("preco", "Preço"));
		
		
		BootgridJS bootgridJS = new BootgridJS();

		bootgridJS.setBotoes(botoes);
		bootgridJS.setColumns(colunas);
		bootgridJS.setCssId("grid-data");
		bootgridJS.setObjId("b0df282a-0d67-40e5-8558-c9e93b7befed");
		bootgridJS.setUrl("listarjson");
		bootgridJS.setCssClass("table table-condensed table-hover table-striped");
		
		model.addAttribute("renderBootgridJS", BootgridUtil.renderJS(bootgridJS));
		model.addAttribute("renderBootgridGrid", BootgridUtil.renderGrid(bootgridJS));
		model.addAttribute("servico", new Servico());
		return "servico_listar";
	}
	
	@RequestMapping(value = "/listarjson", method = RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody BootgridData<Servico> listaServicoJson(@RequestBody String parametros) {
		return this.servicoService.listaServicosBootgrid(BootgridUtil.makeParam(parametros));
	}

	@RequestMapping(value = "/inserir", method = RequestMethod.POST)
	public String insereServico(
			@Valid Servico servico, BindingResult result, Model model) {
		if (result.hasErrors())
		{
			model.addAttribute("listaTipoServico", tipoServicoService.listaTipoServicoForDropDown());
			return "servico_atualizar";
		}
		if (servico.getId() == 0) {
			this.servicoService.insereServico(servico);
		} else {
			this.servicoService.atualizaServico(servico);
		}

		return "redirect:/servico/listar";
	}

	@RequestMapping(value = "/remover/{id}")
	public String removeServico(@PathVariable("id") long id) {
		this.servicoService.removeServico(id);
		return "redirect:/servico/listar";
	}

	@RequestMapping(value = "/telaAtualizar/{id}")
	public String atualizarServico(@PathVariable("id") long id, Model model) {
		model.addAttribute("listaTipoServico", tipoServicoService.listaTipoServicoForDropDown());
		model.addAttribute("servico", this.servicoService.getServicoById(id));
		return "servico_atualizar";
	}
	
	@RequestMapping(value = "/telaInserir")
	public String telaInserirServico(Model model) {
		model.addAttribute("listaTipoServico", tipoServicoService.listaTipoServicoForDropDown());
		model.addAttribute("servico", new Servico());
		return "servico_atualizar";
	}
}
