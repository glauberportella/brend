package com.biavan.brend.controller;

import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

import com.biavan.brend.enums.StatusSolicitacaoRetirada;
import com.biavan.brend.model.Funcionario;
import com.biavan.brend.model.OrdemServico;
import com.biavan.brend.model.PecaOrdemServico;
import com.biavan.brend.model.SolicitacaoRetirada;
import com.biavan.brend.plugin.bootgrid.BootgridAction;
import com.biavan.brend.plugin.bootgrid.BootgridColumn;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridJS;
import com.biavan.brend.plugin.bootgrid.BootgridUtil;
import com.biavan.brend.service.FuncionarioService;
import com.biavan.brend.service.OrdemServicoService;
import com.biavan.brend.service.PecaOrdemServicoService;
import com.biavan.brend.service.SolicitacaoRetiradaService;

@Controller
@RequestMapping("/solicitacao")
public class SolicitacaoRetiradaController {

	private SolicitacaoRetiradaService solicitacaoRetiradaService;
	private FuncionarioService funcionarioService;
	private OrdemServicoService ordemServicoService;
	private PecaOrdemServicoService pecaOrdemServicoService;

	@Autowired(required = true)
	@Qualifier(value = "solicitacaoRetiradaService")
	public void setSolicitacaoRetiradaService(SolicitacaoRetiradaService solicitacaoRetiradaService) {
		this.solicitacaoRetiradaService = solicitacaoRetiradaService;
	}

	@Autowired(required = true)
	@Qualifier(value = "funcionarioService")
	public void setFuncionarioService(FuncionarioService funcionarioService) {
		this.funcionarioService = funcionarioService;
	}
	
	@Autowired(required = true)
	@Qualifier(value = "ordemServicoService")
	public void setOrdemServicoService(OrdemServicoService ordemServicoService) {
		this.ordemServicoService = ordemServicoService;
	}
	
	@Autowired(required = true)
	@Qualifier(value = "pecaOrdemServicoService")
	public void setPecaOrdemServicoService(PecaOrdemServicoService pecaOrdemServicoService) {
		this.pecaOrdemServicoService = pecaOrdemServicoService;
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
		
		binder.registerCustomEditor(Funcionario.class, "solicitante", new PropertyEditorSupport() {
			@Override
	        public void setAsText(String solicitanteId) {
				Funcionario solicitante = funcionarioService.getFuncionarioById(Long.valueOf(solicitanteId));
				setValue(solicitante);
			}
		});
		
	}		
	
	@RequestMapping(value = "/listarPendentes", method = RequestMethod.GET)
	public String listaSolicitacaoRetiradasPendente(Model model) {

		Map<Integer, BootgridAction> botoes = new HashMap<Integer, BootgridAction>();
		botoes.put(1, new BootgridAction("aprovar", "Aprovar", "telaAprovar"));
		botoes.put(2, new BootgridAction("remover", "Cancelar", "remover", true));
		
		Map<Integer, BootgridColumn> colunas = new HashMap<Integer, BootgridColumn>();
		colunas.put(1, new BootgridColumn("id", "ID"));
		colunas.put(2, new BootgridColumn("ordemServicoIdData", "OS"));
		colunas.put(3, new BootgridColumn("nomeSolicitante", "Solicitante"));
		colunas.put(4, new BootgridColumn("dataSolicitacaoFormatada", "Data de Solicitação"));
				
		BootgridJS bootgridJS = new BootgridJS();

		bootgridJS.setBotoes(botoes);
		bootgridJS.setColumns(colunas);
		bootgridJS.setCssId("grid-data");
		bootgridJS.setUrl("listarjson/pendente");
		bootgridJS.setCssClass("table table-condensed table-hover table-striped");
		
		model.addAttribute("subtitulo", "Pendente");
		model.addAttribute("renderBootgridJS", BootgridUtil.renderJS(bootgridJS));
		model.addAttribute("renderBootgridGrid", BootgridUtil.renderGrid(bootgridJS));
		model.addAttribute("solicitacaoRetirada", new SolicitacaoRetirada());
		return "solicitacao_listar";
	}
	
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listaSolicitacaoRetiradas(Model model) {

		Map<Integer, BootgridAction> botoes = new HashMap<Integer, BootgridAction>();
		botoes.put(1, new BootgridAction("alterar", "Visualizar", "telaVisualizar"));
		
		Map<Integer, BootgridColumn> colunas = new HashMap<Integer, BootgridColumn>();
		colunas.put(1, new BootgridColumn("id", "ID"));
		colunas.put(2, new BootgridColumn("ordemServicoIdData", "OS"));
		colunas.put(3, new BootgridColumn("nomeSolicitante", "Solicitante"));
		colunas.put(4, new BootgridColumn("dataSolicitacaoFormatada", "Data de Solicitação"));
		colunas.put(5, new BootgridColumn("nomeAutorizador", "Autorizador"));
		colunas.put(6, new BootgridColumn("dataAtendimentoFormatada", "Data de Autorização"));
				
		BootgridJS bootgridJS = new BootgridJS();

		bootgridJS.setBotoes(botoes);
		bootgridJS.setColumns(colunas);
		bootgridJS.setCssId("grid-data");
		bootgridJS.setUrl("listarjson/atendido");
		bootgridJS.setCssClass("table table-condensed table-hover table-striped");
		
		model.addAttribute("renderBootgridJS", BootgridUtil.renderJS(bootgridJS));
		model.addAttribute("renderBootgridGrid", BootgridUtil.renderGrid(bootgridJS));
		model.addAttribute("solicitacaoRetirada", new SolicitacaoRetirada());
		return "solicitacao_listar";
	}	
	
	@RequestMapping(value = "/listarjson/{status}", method = RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody BootgridData<SolicitacaoRetirada> listaSolicitacaoRetiradaJson(
			@PathVariable("status") String status, @RequestBody String parametros) {
		return this.solicitacaoRetiradaService.listaSolicitacaoRetiradasBootgrid(BootgridUtil.makeParam(parametros), status);
	}
	
	@RequestMapping(value = "/listarjson/pecasordemservico", method = RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody List<PecaOrdemServico> getListaPecasById(@RequestParam long ordemServicoId) {
		OrdemServico ordemServico = null;
		try {
			ordemServico = ordemServicoService.getOrdemServicoById(ordemServicoId);
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		List<PecaOrdemServico> pecasOrdemServico = new ArrayList<PecaOrdemServico>();
		if(ordemServico != null) {
			
			pecasOrdemServico = pecaOrdemServicoService.listaPecaOrdemServicoByOrdemServico(ordemServico);
			
			if (pecasOrdemServico != null) {
				for(PecaOrdemServico po : pecasOrdemServico) {
					po.getPeca().setPrecos(null);
					po.setOrdemServico(null);
				}
			}
		}
		
		return pecasOrdemServico;
		
	}

	@RequestMapping(value = "/inserirSolicitacao", method = RequestMethod.POST)
	public String insereSolicitacao(
			@ModelAttribute("solicitacao") SolicitacaoRetirada solicitacao) {
		
		solicitacao.setDataSolicitacao(new Date());
		solicitacao.setStatus(StatusSolicitacaoRetirada.PENDENTE);
		
		solicitacaoRetiradaService.insereSolicitacaoRetirada(solicitacao);

		return "redirect:/solicitacao/listar";
	}

	@RequestMapping(value = "/inserirAprovacao", method = RequestMethod.POST)
	public String insereAprovacao(
		@ModelAttribute("solicitacao") SolicitacaoRetirada solicitacao) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		solicitacao = solicitacaoRetiradaService.getSolicitacaoRetiradaById(solicitacao.getId());
	
	    solicitacao.setAutorizador(funcionarioService.getFuncionarioByLogin(auth.getName()) );
		solicitacao.setDataAtendimento(new Date());
		solicitacao.setStatus(StatusSolicitacaoRetirada.ATENDIDO);
		
		solicitacaoRetiradaService.atualizaSolicitacaoRetirada(solicitacao);

		return "redirect:/solicitacao/listar";
	}

	@RequestMapping(value = "/remover/{id}")
	public String removeSolicitacaoRetirada(@PathVariable("id") long id) {
		this.solicitacaoRetiradaService.removeSolicitacaoRetirada(id);
		return "redirect:/solicitacao/listarPendentes";
	}

	@RequestMapping(value = "/telaAprovar/{id}")
	public String atualizarSolicitacaoRetirada(@PathVariable("id") long id, Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    Funcionario autorizador = funcionarioService.getFuncionarioByLogin(auth.getName()); 
		
	    SolicitacaoRetirada solicitacao = solicitacaoRetiradaService.getSolicitacaoRetiradaById(id);
	    solicitacao.setAutorizador(autorizador);
		
		OrdemServico ordemServico = ordemServicoService.getOrdemServicoById(solicitacao.getOrdemServico().getId());
		
		List<PecaOrdemServico> pecasOrdemServico = pecaOrdemServicoService.listaPecaOrdemServicoByOrdemServico(ordemServico);
		
		if (pecasOrdemServico != null) {
			for(PecaOrdemServico po : pecasOrdemServico) {
				po.getPeca().setPrecos(null);
			}
		}
		
		model.addAttribute("listaPecas", pecasOrdemServico);
		model.addAttribute("solicitacao", solicitacao);
		
		return "solicitacao_aprovar";
	}
	
	@RequestMapping(value = "/telaSolicitar")
	public String telaInserirSolicitacaoRetirada(Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    Funcionario solicitante = funcionarioService.getFuncionarioByLogin(auth.getName()); 
		
	    SolicitacaoRetirada solicitacao = new SolicitacaoRetirada();
	    solicitacao.setSolicitante(solicitante);
	    
		model.addAttribute("solicitacao", solicitacao);
		return "solicitacao_solicitar";
	}
	
	@RequestMapping(value = "/telaVisualizar/{id}")
	public String telaVisualizarSolicitacaoRetirada(@PathVariable("id") long id, Model model) {
		
		SolicitacaoRetirada solicitacao = solicitacaoRetiradaService.getSolicitacaoRetiradaById(id);
		
		OrdemServico ordemServico = ordemServicoService.getOrdemServicoById(solicitacao.getOrdemServico().getId());
		
		List<PecaOrdemServico> pecasOrdemServico = pecaOrdemServicoService.listaPecaOrdemServicoByOrdemServico(ordemServico);
		
		if (pecasOrdemServico != null) {
			for(PecaOrdemServico po : pecasOrdemServico) {
				po.getPeca().setPrecos(null);
			}
		}

		if (solicitacao.getStatus() == StatusSolicitacaoRetirada.PENDENTE) {
			model.addAttribute("status", "Solicitação Pendente");
			model.addAttribute("tipoAlerta", "warning");
		} else {
			model.addAttribute("status", "Solicitação Atendida");
			model.addAttribute("tipoAlerta", "success");
		}
		
		model.addAttribute("listaPecas", pecasOrdemServico);
		model.addAttribute("solicitacao", solicitacao);
				
		return "solicitacao_visualizar";
	}
}
