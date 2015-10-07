package com.biavan.brend.controller;

import java.beans.PropertyEditorSupport;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biavan.brend.model.Servico;
import com.biavan.brend.model.TarifarioServico;
import com.biavan.brend.plugin.bootgrid.BootgridAction;
import com.biavan.brend.plugin.bootgrid.BootgridColumn;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridJS;
import com.biavan.brend.plugin.bootgrid.BootgridUtil;
import com.biavan.brend.service.ServicoService;
import com.biavan.brend.service.TarifarioServicoService;
import com.biavan.brend.util.Formatos;

@Controller
@RequestMapping("/tarifarioServico")
public class TarifarioServicoController {

	private TarifarioServicoService tarifarioServicoService;
	private ServicoService servicoService;
	
	@Autowired(required = true)
	@Qualifier(value = "tarifarioServicoService")
	public void setTarifarioServicoService(TarifarioServicoService tarifarioServicoService) {
		this.tarifarioServicoService = tarifarioServicoService;
	}

	@Autowired(required = true)
	@Qualifier(value = "servicoService")
	public void setServicoService(ServicoService servicoService) {
		this.servicoService = servicoService;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {

		binder.registerCustomEditor(Date.class, "data", new PropertyEditorSupport() {
			@Override
			public void setAsText(String data) {
				SimpleDateFormat dateFormat = Formatos.getFormatoDeData();
				try {
					setValue(dateFormat.parse(data));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		});

		binder.registerCustomEditor(Servico.class, "servico", new PropertyEditorSupport() {
			@Override
	        public void setAsText(String servicoId) {
				Servico servico = servicoService.getServicoById(Long.valueOf(servicoId));
				setValue(servico);
			}
		});
	}
	
	@RequestMapping(value = "/listar/{servicoId}", method = RequestMethod.GET)
	public String listaServicos(@PathVariable("servicoId") long servicoId, Model model) {

		Map<Integer, BootgridAction> botoes = new HashMap<Integer, BootgridAction>();
		botoes.put(1, new BootgridAction("alterar", "Alterar", "../telaAtualizar"));
		botoes.put(2, new BootgridAction("excluir", "Excluir", "../remover", true));
		
		Map<Integer, BootgridColumn> colunas = new HashMap<Integer, BootgridColumn>();
		colunas.put(1, new BootgridColumn("id", "ID"));
		colunas.put(2, new BootgridColumn("dataFormatada", "Data"));
		colunas.put(3, new BootgridColumn("valor", "Valor"));
		
		
		BootgridJS bootgridJS = new BootgridJS();

		bootgridJS.setBotoes(botoes);
		bootgridJS.setColumns(colunas);
		bootgridJS.setCssId("grid-data");
		bootgridJS.setObjId("b0df282a-0d67-40e5-8558-c9e93b7befed");
		bootgridJS.setUrl("../listarjson/" + servicoId);
		bootgridJS.setCssClass("table table-condensed table-hover table-striped");
		
		model.addAttribute("renderBootgridJS", BootgridUtil.renderJS(bootgridJS));
		model.addAttribute("renderBootgridGrid", BootgridUtil.renderGrid(bootgridJS));
		model.addAttribute("servico", servicoService.getServicoById(servicoId));
		model.addAttribute("tarifarioServico", new TarifarioServico());
		return "tarifario_servico_listar";
	}
	
	
	
	@RequestMapping(value = "/listarjson/{servicoId}", method = RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody BootgridData<Servico> listaTarifarioServicoJson(@PathVariable("servicoId") long servicoId, 
			@RequestBody String parametros) {
		Servico servico = servicoService.getServicoById(servicoId);
		return tarifarioServicoService.listaTarifarioServicosBootgrid(servico, BootgridUtil.makeParam(parametros));
	}

	@RequestMapping(value = "/inserir", method = RequestMethod.POST)
	public String insereServico(
			@ModelAttribute("tarifarioServico") TarifarioServico tarifarioServico) {
		System.out.println(tarifarioServico);
		if (tarifarioServico.getId() == 0) {
			tarifarioServicoService.insereTarifarioServico(tarifarioServico);	
		} else {
			tarifarioServicoService.atualizaTarifarioServico(tarifarioServico);
		}

		return "redirect:/tarifarioServico/listar/" + tarifarioServico.getServico().getId();
	}

	@RequestMapping(value = "/remover/{id}")
	public String removeServico(@PathVariable("id") long id) {
		TarifarioServico tarifarioServico = tarifarioServicoService.getTarifarioServicoById(id);
		tarifarioServicoService.removeTarifarioServico(id);
		return "redirect:/tarifarioServico/listar/" + tarifarioServico.getServico().getId();
	}

	@RequestMapping(value = "/telaAtualizar/{id}")
	public String atualizarTarifarioServico(@PathVariable("id") long id, Model model) {
		model.addAttribute("tarifarioServico", tarifarioServicoService.getTarifarioServicoById(id));
		return "tarifario_servico_atualizar";
	}
	
	@RequestMapping(value = "/telaInserir/{servicoId}")
	public String telaInserirTarifarioServico(@PathVariable("servicoId") long servicoId, Model model) {
		TarifarioServico tarifarioServico = new TarifarioServico(); 
		tarifarioServico.setServico(servicoService.getServicoById(servicoId));
		model.addAttribute("tarifarioServico", tarifarioServico);
		return "tarifario_servico_atualizar";
	}
	
}
