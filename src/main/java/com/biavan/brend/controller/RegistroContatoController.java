package com.biavan.brend.controller;

import java.beans.PropertyEditorSupport;
import java.util.Date;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biavan.brend.enums.StatusOS;
import com.biavan.brend.model.OrdemServico;
import com.biavan.brend.model.RegistroContato;
import com.biavan.brend.model.StatusOrdemServico;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridUtil;
import com.biavan.brend.service.OrdemServicoService;
import com.biavan.brend.service.RegistroContatoService;
import com.biavan.brend.util.Formatos;

@Controller
@RequestMapping("/registroContato")
public class RegistroContatoController {

	private RegistroContatoService registroContatoService;
	private OrdemServicoService ordemServicoService;

	@Autowired(required = true)
	@Qualifier(value = "registroContatoService")
	public void setRegistroContatoService(RegistroContatoService registroContatoService) {
		this.registroContatoService = registroContatoService;
	}

	@Autowired(required = true)
	@Qualifier(value = "ordemServicoService")
	public void setOrdemServicoService(OrdemServicoService ordemServicoService) {
		this.ordemServicoService = ordemServicoService;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {

		binder.registerCustomEditor(OrdemServico.class, "ordemServico", new PropertyEditorSupport() {
			@Override
	        public void setAsText(String ordemServicoId) {
				OrdemServico ordemServico = ordemServicoService.getOrdemServicoById(Long.valueOf(ordemServicoId));
				setValue(ordemServico);
			}
		});
	}
	
	
	@RequestMapping(value = "/listarjson/{ordemServicoId}", method = RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody BootgridData<RegistroContato> listaRegistroContatoJson(
			@PathVariable("ordemServicoId") long ordemServicoId,
			@RequestBody String parametros) {
		
		OrdemServico ordemServico = ordemServicoService.getOrdemServicoById(ordemServicoId);
		
		return registroContatoService.listaRegistroContatosBootgridByOrdemServico(ordemServico,
				BootgridUtil.makeParam(parametros));
	}

	@RequestMapping(value = "/inserir", method = RequestMethod.POST)
	public @ResponseBody String insereRegistroContato(
			@ModelAttribute("registroContato") RegistroContato registroContato) {
		
		registroContato.setData(new Date());
		
		registroContatoService.insereRegistroContato(registroContato);

		return "OK";
	}

	
	@RequestMapping(value = "/alterarStatus", method = RequestMethod.POST)
	public String insereRegistroContatoAlterandoStatus(
			@RequestParam("status") String status, 
			@RequestParam("ordemServicoId") long ordemServicoId,
			@RequestParam("motivo") String descricao) {
		
		OrdemServico ordemServico = ordemServicoService.getOrdemServicoById(Long.valueOf(ordemServicoId));
		
		RegistroContato registroContato = new RegistroContato(); 
		registroContato.setData(new Date());
		registroContato.setOrdemServico(ordemServico);

		StatusOrdemServico statusOrdemServico = new StatusOrdemServico();
		statusOrdemServico.setDataInicio(new Date());
		statusOrdemServico.setDataFim(Formatos.getUltimaData());
		statusOrdemServico.setOrdemServico(ordemServico);
		
		if ("Finalizar".equals(status)) {
			registroContato.setDescricao("Finalização: " + descricao);
			statusOrdemServico.setDescricao("Finalização: " + descricao);
			statusOrdemServico.setStatusOS(StatusOS.FINALIZADA);
		}
		if ("Reabrir".equals(status)) {
			registroContato.setDescricao("Reabertura: " + descricao);
			statusOrdemServico.setDescricao("Reabertura: " + descricao);
			statusOrdemServico.setStatusOS(StatusOS.REABERTA);
		}
		if ("Cancelar".equals(status)) {
			registroContato.setDescricao("Cancelamento: " + descricao);
			statusOrdemServico.setDescricao("Cancelamento: " + descricao);
			statusOrdemServico.setStatusOS(StatusOS.CANCELADA);
		}

		registroContatoService.insereRegistroContato(registroContato, statusOrdemServico);

		return "redirect:/ordemServico/telaAtualizar/" + ordemServico.getId();
	}
	
	@RequestMapping(value = "/{telaStatus}/{ordemServicoId}")
	public String telaInserirMarca(@PathVariable("telaStatus") String telaStatus,
			@PathVariable("ordemServicoId") long ordemServicoId, Model model) {
		
		model.addAttribute("status", telaStatus.replace("tela", ""));
		model.addAttribute("ordemServicoId", ordemServicoId);
		model.addAttribute("statusOrdemServico", new StatusOrdemServico());
		
		return "status_ordem_servico_atualizar";
	}

}
