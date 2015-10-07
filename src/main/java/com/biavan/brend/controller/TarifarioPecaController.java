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

import com.biavan.brend.model.Peca;
import com.biavan.brend.model.TarifarioPeca;
import com.biavan.brend.plugin.bootgrid.BootgridAction;
import com.biavan.brend.plugin.bootgrid.BootgridColumn;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridJS;
import com.biavan.brend.plugin.bootgrid.BootgridUtil;
import com.biavan.brend.service.ConfigEmpresaService;
import com.biavan.brend.service.PecaService;
import com.biavan.brend.service.TarifarioPecaService;
import com.biavan.brend.util.Formatos;

@Controller
@RequestMapping("/tarifarioPeca")
public class TarifarioPecaController {

	private TarifarioPecaService tarifarioPecaService;
	private PecaService pecaService;
	private ConfigEmpresaService configEmpresaService;
	
	@Autowired(required = true)
	@Qualifier(value = "tarifarioPecaService")
	public void setTarifarioPecaService(TarifarioPecaService tarifarioPecaService) {
		this.tarifarioPecaService = tarifarioPecaService;
	}

	@Autowired(required = true)
	@Qualifier(value = "pecaService")
	public void setPecaService(PecaService pecaService) {
		this.pecaService = pecaService;
	}
	
	@Autowired
	@Qualifier(value = "configEmpresaService")
	public void setConfigEmpresaService(ConfigEmpresaService configEmpresaService) {
		this.configEmpresaService = configEmpresaService;
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

		binder.registerCustomEditor(Peca.class, "peca", new PropertyEditorSupport() {
			@Override
	        public void setAsText(String pecaId) {
				Peca peca = pecaService.getPecaById(Long.valueOf(pecaId));
				setValue(peca);
			}
		});
	}
	
	@RequestMapping(value = "/listar/{pecaId}", method = RequestMethod.GET)
	public String listaPecas(@PathVariable("pecaId") long pecaId, Model model) {

		Map<Integer, BootgridAction> botoes = new HashMap<Integer, BootgridAction>();
		botoes.put(1, new BootgridAction("alterar", "Alterar", "../telaAtualizar"));
		botoes.put(2, new BootgridAction("excluir", "Excluir", "../remover", true));
		
		Map<Integer, BootgridColumn> colunas = new HashMap<Integer, BootgridColumn>();
		colunas.put(1, new BootgridColumn("id", "ID"));
		colunas.put(2, new BootgridColumn("dataFormatada", "Data"));
		colunas.put(3, new BootgridColumn("valor", "Valor"));
		colunas.put(4, new BootgridColumn("markup", "Markup"));
		colunas.put(5, new BootgridColumn("valorVenda", "Valor de Venda"));
		
		
		BootgridJS bootgridJS = new BootgridJS();

		bootgridJS.setBotoes(botoes);
		bootgridJS.setColumns(colunas);
		bootgridJS.setCssId("grid-data");
		bootgridJS.setObjId("b0df282a-0d67-40e5-8558-c9e93b7befed");
		bootgridJS.setUrl("../listarjson/" + pecaId);
		bootgridJS.setCssClass("table table-condensed table-hover table-striped");
		
		model.addAttribute("renderBootgridJS", BootgridUtil.renderJS(bootgridJS));
		model.addAttribute("renderBootgridGrid", BootgridUtil.renderGrid(bootgridJS));
		model.addAttribute("peca", pecaService.getPecaById(pecaId));
		model.addAttribute("tarifarioPeca", new TarifarioPeca());
		return "tarifario_peca_listar";
	}
	
	@RequestMapping(value = "/listarjson/{pecaId}", method = RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody BootgridData<Peca> listaTarifarioPecaJson(@PathVariable("pecaId") long pecaId, 
			@RequestBody String parametros) {
		Peca peca = pecaService.getPecaById(pecaId);
		return tarifarioPecaService.listaTarifarioPecasBootgrid(peca, BootgridUtil.makeParam(parametros));
	}

	@RequestMapping(value = "/inserir", method = RequestMethod.POST)
	public String inserePeca(
			@ModelAttribute("tarifarioPeca") TarifarioPeca tarifarioPeca){
  
		if (tarifarioPeca.getId() == 0) {
			tarifarioPecaService.insereTarifarioPeca(tarifarioPeca);	
		} else {
			tarifarioPecaService.atualizaTarifarioPeca(tarifarioPeca);
		}

		return "redirect:/tarifarioPeca/listar/" + tarifarioPeca.getPeca().getId();
	}

	@RequestMapping(value = "/remover/{id}")
	public String removePeca(@PathVariable("id") long id) {
		TarifarioPeca tarifarioPeca = tarifarioPecaService.getTarifarioPecaById(id);
		tarifarioPecaService.removeTarifarioPeca(id);
		return "redirect:/tarifarioPeca/listar/" + tarifarioPeca.getPeca().getId();
	}

	@RequestMapping(value = "/telaAtualizar/{id}")
	public String atualizarTarifarioPeca(@PathVariable("id") long id, Model model) {
		model.addAttribute("tarifarioPeca", tarifarioPecaService.getTarifarioPecaById(id));
		return "tarifario_peca_atualizar";
	}
	
	@RequestMapping(value = "/telaInserir/{pecaId}")
	public String telaInserirTarifarioPeca(@PathVariable("pecaId") long pecaId, Model model) {
		
		TarifarioPeca tarifarioPeca = new TarifarioPeca(); 
		tarifarioPeca.setPeca(pecaService.getPecaById(pecaId));
		model.addAttribute("markup", configEmpresaService.getConfigEmpresaById(1).getMarkup());
		model.addAttribute("tarifarioPeca", tarifarioPeca);
		return "tarifario_peca_atualizar";
	}
	
}
