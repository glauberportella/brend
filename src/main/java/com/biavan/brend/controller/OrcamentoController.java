package com.biavan.brend.controller;

import java.beans.PropertyEditorSupport;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biavan.brend.model.Cliente;
import com.biavan.brend.model.Orcamento;
import com.biavan.brend.model.PecaOrcamento;
import com.biavan.brend.model.ServicoOrcamento;
import com.biavan.brend.model.TipoMotor;
import com.biavan.brend.model.Veiculo;
import com.biavan.brend.plugin.bootgrid.BootgridAction;
import com.biavan.brend.plugin.bootgrid.BootgridColumn;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridJS;
import com.biavan.brend.plugin.bootgrid.BootgridUtil;
import com.biavan.brend.service.ClienteService;
import com.biavan.brend.service.OrcamentoService;
import com.biavan.brend.service.OrdemServicoService;
import com.biavan.brend.service.PecaOrcamentoService;
import com.biavan.brend.service.PecaService;
import com.biavan.brend.service.ServicoOrcamentoService;
import com.biavan.brend.service.ServicoService;
import com.biavan.brend.service.TipoMotorService;
import com.biavan.brend.service.VeiculoService;
import com.biavan.brend.util.Formatos;

@Controller
@RequestMapping("/orcamento")
public class OrcamentoController {

	private OrcamentoService orcamentoService;
	private TipoMotorService tipoMotorService;
	private VeiculoService veiculoService;
	private PecaOrcamentoService pecaOrcamentoService;
	private PecaService pecaService;
	private ServicoService servicoService;
	private ServicoOrcamentoService servicoOrcamentoService;
	private OrdemServicoService ordemServicoService;
	private ClienteService clienteService;

	@Autowired(required = true)
	@Qualifier(value = "orcamentoService")
	public void setOrcamentoService(OrcamentoService orcamentoService) {
		this.orcamentoService = orcamentoService;
	}
	
	@Autowired(required = true)
	@Qualifier(value = "tipoMotorService")
	public void setTipoMotorService(TipoMotorService tipoMotorService) {
		this.tipoMotorService = tipoMotorService;
	}
	
	@Autowired(required = true)
	@Qualifier(value = "clienteService")
	public void setClienteService(ClienteService clienteService) {
		this.clienteService = clienteService;
	}
	
	@Autowired(required = true)
	@Qualifier(value = "veiculoService")
	public void setVeiculoService(VeiculoService veiculoService) {
		this.veiculoService = veiculoService;
	}
	
	@Autowired(required = true)
	@Qualifier(value = "pecaOrcamentoService")
	public void setPecaOrcamentoService(PecaOrcamentoService pecaOrcamentoService) {
		this.pecaOrcamentoService = pecaOrcamentoService;
	}
	
	@Autowired(required = true)
	@Qualifier(value = "pecaService")
	public void setPecaService(PecaService pecaService) {
		this.pecaService = pecaService;
	}
	
	@Autowired(required = true)
	@Qualifier(value = "servicoService")
	public void setServicoService(ServicoService servicoService) {
		this.servicoService = servicoService;
	}
	
	@Autowired(required = true)
	@Qualifier(value = "servicoOrcamentoService")
	public void setServicoOrcamentoService(ServicoOrcamentoService servicoOrcamentoService) {
		this.servicoOrcamentoService = servicoOrcamentoService;
	}
	
	@Autowired(required = true)
	@Qualifier(value = "ordemServicoService")
	public void setOrdemServicoService(OrdemServicoService ordemServicoService) {
		this.ordemServicoService = ordemServicoService;
	}
	

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		
		binder.registerCustomEditor(Date.class, "data", new PropertyEditorSupport() {
			@Override
			public void setAsText(String data) {
				SimpleDateFormat dateFormat = Formatos.getFormatoDeData();
				
				if (Formatos.validaData(data)) {
					try {
						setValue(dateFormat.parse(data));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				} else {
					setValue(null);
				}
			}
		});
		
		binder.registerCustomEditor(Date.class, "validade", new PropertyEditorSupport() {
			@Override
			public void setAsText(String validade) {
				SimpleDateFormat dateFormat = Formatos.getFormatoDeData();
				
				if (Formatos.validaData(validade)) {
					try {
						setValue(dateFormat.parse(validade));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				} else {
					setValue(null);
				}
			}
		});


		binder.registerCustomEditor(TipoMotor.class, "tipoMotor", new PropertyEditorSupport() {
			@Override
	        public void setAsText(String tipoId) {
				TipoMotor tipoMotor = tipoMotorService.getTipoMotorById(Long.valueOf(tipoId));
				setValue(tipoMotor);
			}
		});
		
		binder.registerCustomEditor(Veiculo.class, "veiculo", new PropertyEditorSupport() {
			@Override
	        public void setAsText(String tipoId) {
				Veiculo veiculo = veiculoService.getVeiculoById(Long.valueOf(tipoId));
				setValue(veiculo);
			}
		});
		
		binder.registerCustomEditor(Cliente.class, "cliente", new PropertyEditorSupport() {
			@Override
	        public void setAsText(String tipoId) {
				Cliente cliente = clienteService.getClienteById(Long.valueOf(tipoId));
				setValue(cliente);
			}
		});
	}
	
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listaOrcamentos(Model model) {

		Map<Integer, BootgridAction> botoes = new HashMap<Integer, BootgridAction>();
		botoes.put(1, new BootgridAction("alterar", "Alterar", "telaAtualizar"));
		botoes.put(2, new BootgridAction("excluir", "Excluir", "remover", true));
		
		Map<Integer, BootgridColumn> colunas = new HashMap<Integer, BootgridColumn>();
		colunas.put(1, new BootgridColumn("id", "ID"));
		colunas.put(2, new BootgridColumn("clienteNome", "Cliente"));
		colunas.put(3, new BootgridColumn("modeloVeiculo", "Veiculo"));
		colunas.put(4, new BootgridColumn("ano", "Ano"));
		colunas.put(5, new BootgridColumn("valorTotalFormatado", "Total"));
		
		
		BootgridJS bootgridJS = new BootgridJS();

		bootgridJS.setBotoes(botoes);
		bootgridJS.setColumns(colunas);
		bootgridJS.setCssId("grid-data");
		bootgridJS.setObjId("b0df282a-0d67-40e5-8558-c9e93b7befed");
		bootgridJS.setUrl("listarjson");
		bootgridJS.setCssClass("table table-condensed table-hover table-striped");

		model.addAttribute("renderBootgridJS", BootgridUtil.renderJS(bootgridJS));
		model.addAttribute("renderBootgridGrid", BootgridUtil.renderGrid(bootgridJS));
		model.addAttribute("orcamento", new Orcamento());
		return "orcamento_listar";
	}
	
	@RequestMapping(value = "/listarjson", method = RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody BootgridData<Orcamento> listaOrcamentoJson(@RequestBody String parametros) {
		return this.orcamentoService.listaOrcamentosBootgrid(BootgridUtil.makeParam(parametros));
	}

	@RequestMapping(value = "/inserir", method = RequestMethod.POST)
	public String insereOrcamento(@Valid Orcamento orcamento, BindingResult result 
			, @RequestParam("idsPeca") String[] idsPeca
			, @RequestParam("qtdesPeca") String[] qtdesPeca
			, @RequestParam("unitsPeca") String[] unitsPeca
			, @RequestParam("idsServico") String[] idsServico
			, @RequestParam("qtdesServico") String[] qtdesServico
			, @RequestParam("unitsServico") String[] unitsServico
			, Model model) {
			
		if (result.hasErrors()) {

			double totalPecasOrcamento = 0;
			double totalServicosOrcamento = 0;
			
			List<PecaOrcamento> listaPecasOrcamento = new ArrayList<PecaOrcamento>();
			if (Long.valueOf(idsPeca[0]) > 0) {
				for(int i = 0; i < idsPeca.length; i++) {
					
					PecaOrcamento pecaOrcamento = new PecaOrcamento();
					pecaOrcamento.setOrcamento(orcamento);
					pecaOrcamento.setPeca(pecaService.getPecaById(Long.valueOf(idsPeca[i])));
					pecaOrcamento.setQtde(Integer.valueOf(qtdesPeca[i]));
					pecaOrcamento.setValor(Double.valueOf(unitsPeca[i]));
					
					listaPecasOrcamento.add(pecaOrcamento);
					
					totalPecasOrcamento += pecaOrcamento.getQtde() * pecaOrcamento.getValor();
				}
			}
			
			List<ServicoOrcamento> listaServicosOrcamento = new ArrayList<ServicoOrcamento>();
			for(int i = 0; i < idsServico.length; i++) {
				
				ServicoOrcamento servicoOrcamento = new ServicoOrcamento();
				servicoOrcamento.setOrcamento(orcamento);
				servicoOrcamento.setServico(servicoService.getServicoById(Long.valueOf(idsServico[i])));
				servicoOrcamento.setQtde(Integer.valueOf(qtdesServico[i]));
				servicoOrcamento.setValor(Double.valueOf(unitsServico[i]));
				
				listaServicosOrcamento.add(servicoOrcamento);
				
				totalServicosOrcamento += servicoOrcamento.getQtde() * servicoOrcamento.getValor();
			}
	
			model.addAttribute("listaPecasOrcamento", listaPecasOrcamento);
			model.addAttribute("listaServicosOrcamento", listaServicosOrcamento);
			model.addAttribute("countLinesPeca", listaPecasOrcamento.size());
			model.addAttribute("countLinesServico", listaServicosOrcamento.size());
			model.addAttribute("totalPecasOrcamento", totalPecasOrcamento);
			model.addAttribute("totalServicosOrcamento", totalServicosOrcamento);
			model.addAttribute("listaPecas", pecaService.listaPecas());
			model.addAttribute("listaServicos", servicoService.listaServicos());
			model.addAttribute("listaTipoMotor", tipoMotorService.listaTipoMotorForDropDown());
			model.addAttribute("listaVeiculos", veiculoService.listaVeiculoForDropDown());
			model.addAttribute("listaClientes", clienteService.listaClientesForDropDown());
			model.addAttribute("orcamento", orcamento);
			
			return "orcamento_atualizar";
		}
		
		
		
		List<PecaOrcamento> pecasOrcamento = new ArrayList<PecaOrcamento>();
		if (Long.valueOf(idsPeca[0]) > 0) {
			for(int i = 0; i < idsPeca.length; i++) {
				
				PecaOrcamento pecaOrcamento = new PecaOrcamento();
				pecaOrcamento.setOrcamento(orcamento);
				pecaOrcamento.setPeca(pecaService.getPecaById(Long.valueOf(idsPeca[i])));
				pecaOrcamento.setQtde(Integer.valueOf(qtdesPeca[i]));
				pecaOrcamento.setValor(Double.valueOf(unitsPeca[i]));
				
				pecasOrcamento.add(pecaOrcamento);
			}
		}
		
		List<ServicoOrcamento> servicosOrcamento = new ArrayList<ServicoOrcamento>();
		for(int i = 0; i < idsServico.length; i++) {
			
			ServicoOrcamento servicoOrcamento = new ServicoOrcamento();
			servicoOrcamento.setOrcamento(orcamento);
			servicoOrcamento.setServico(servicoService.getServicoById(Long.valueOf(idsServico[i])));
			servicoOrcamento.setQtde(Integer.valueOf(qtdesServico[i]));
			servicoOrcamento.setValor(Double.valueOf(unitsServico[i]));
			
			servicosOrcamento.add(servicoOrcamento);
		}
		
		String tipoMsg = null;
		if (orcamento.getId() == 0) {
			orcamentoService.insereOrcamento(orcamento, pecasOrcamento, servicosOrcamento);
			tipoMsg = "inserir";
		} else { 
			orcamentoService.atualizaOrcamento(orcamento, pecasOrcamento, servicosOrcamento);
			tipoMsg = "atualizar";
		}

		return "redirect:/orcamento/telaAtualizar/" + tipoMsg + "/" + orcamento.getId();
	}
		
	@RequestMapping(value = "/remover/{id}")
	public String removeOrcamento(@PathVariable("id") long id, Model model) {
		Orcamento orcamento = orcamentoService.getOrcamentoById(id);
		this.pecaOrcamentoService.removePecaOrcamentoByOrcamento(orcamento);
		this.servicoOrcamentoService.removeServicoOrcamentoByOrcamento(orcamento);
		this.orcamentoService.removeOrcamento(id);
		return "redirect:/orcamento/listar";
	}

	@RequestMapping(value = "/telaAtualizar/{tipoMsg}/{id}")
	public String telaAtualizarOrcamento(@PathVariable("tipoMsg") String tipoMsg,
			@PathVariable("id") long id, Model model) {
		
		if ("inserir".equals(tipoMsg)) {
			model.addAttribute("sucesso", "Orçamento criado com sucesso.");
		}
		
		if ("atualizar".equals(tipoMsg)) {
			model.addAttribute("sucesso", "Orçamento atualizado com sucesso.");
		}
		
		return telaAtualizarOrcamento(id, model);
	}
	
	@RequestMapping(value = "/telaAtualizar/{id}")
	public String telaAtualizarOrcamento(@PathVariable("id") long id, Model model) {
		Orcamento orcamento = orcamentoService.getOrcamentoById(id);
		
		List<PecaOrcamento> listaPecasOrcamento = pecaOrcamentoService.listaPecaOrcamentoByOrcamento(orcamento);
		List<ServicoOrcamento> listaServicosOrcamento = servicoOrcamentoService.listaServicoOrcamentoByOrcamento(orcamento);

		double totalPecasOrcamento = 0;
		double totalServicosOrcamento = 0;
		
		for(PecaOrcamento pp : listaPecasOrcamento) {
			totalPecasOrcamento += pp.getSubTotal(); 
		}
		
		for(ServicoOrcamento pp : listaServicosOrcamento) {
			totalServicosOrcamento += pp.getSubTotal(); 
		}
		
		if (ordemServicoService.getOrdemServicoByOrcamento(orcamento) != null)
			model.addAttribute("temOrdemServico", true);
		
		model.addAttribute("listaPecasOrcamento", listaPecasOrcamento);
		model.addAttribute("listaServicosOrcamento", listaServicosOrcamento);
		model.addAttribute("countLinesPeca", listaPecasOrcamento.size());
		model.addAttribute("countLinesServico", listaServicosOrcamento.size());
		model.addAttribute("totalPecasOrcamento", totalPecasOrcamento);
		model.addAttribute("totalServicosOrcamento", totalServicosOrcamento);
		model.addAttribute("listaPecas", pecaService.listaPecas());
		model.addAttribute("listaServicos", servicoService.listaServicos());
		model.addAttribute("listaTipoMotor", tipoMotorService.listaTipoMotorForDropDown());
		model.addAttribute("listaVeiculos", veiculoService.listaVeiculoForDropDown());
		model.addAttribute("listaClientes", clienteService.listaClientesForDropDown());
		model.addAttribute("orcamento", this.orcamentoService.getOrcamentoById(id));
		
		return "orcamento_atualizar";
	}
	
	@RequestMapping(value = "/imprimir/{id}")
	public String telaImprimirOrcamento(@PathVariable("id") long id, Model model) {
		Orcamento orcamento = orcamentoService.getOrcamentoById(id);
		
		List<PecaOrcamento> listaPecasOrcamento = pecaOrcamentoService.listaPecaOrcamentoByOrcamento(orcamento);
		List<ServicoOrcamento> listaServicosOrcamento = servicoOrcamentoService.listaServicoOrcamentoByOrcamento(orcamento);

		double totalPecasOrcamento = 0;
		double totalServicosOrcamento = 0;
		
		for(PecaOrcamento pp : listaPecasOrcamento) {
			totalPecasOrcamento += pp.getSubTotal(); 
		}
		
		for(ServicoOrcamento pp : listaServicosOrcamento) {
			totalServicosOrcamento += pp.getSubTotal(); 
		}
		
		if (ordemServicoService.getOrdemServicoByOrcamento(orcamento) != null)
			model.addAttribute("temOrdemServico", true);
		
		model.addAttribute("listaPecasOrcamento", listaPecasOrcamento);
		model.addAttribute("listaServicosOrcamento", listaServicosOrcamento);
		model.addAttribute("countLinesPeca", listaPecasOrcamento.size());
		model.addAttribute("countLinesServico", listaServicosOrcamento.size());
		model.addAttribute("totalPecasOrcamento", totalPecasOrcamento);
		model.addAttribute("totalServicosOrcamento", totalServicosOrcamento);
		model.addAttribute("listaPecas", pecaService.listaPecas());
		model.addAttribute("listaServicos", servicoService.listaServicos());
		model.addAttribute("listaTipoMotor", tipoMotorService.listaTipoMotorForDropDown());
		model.addAttribute("listaVeiculos", veiculoService.listaVeiculoForDropDown());
		model.addAttribute("listaClientes", clienteService.listaClientesForDropDown());
		model.addAttribute("orcamento", this.orcamentoService.getOrcamentoById(id));
		
		return "orcamento_imprimir";
	}	
	
	@RequestMapping(value = "/telaInserir")
	public String telaInserirOrcamento(Model model) {
		model.addAttribute("countLinesPeca", 0);
		model.addAttribute("totalPecasOrcamento", 0);
		
		model.addAttribute("countLinesServico", 0);
		model.addAttribute("totalServicosOrcamento", 0);
		
		model.addAttribute("listaPecas", pecaService.listaPecas());
		model.addAttribute("listaServicos", servicoService.listaServicos());
		
		model.addAttribute("listaTipoMotor", tipoMotorService.listaTipoMotorForDropDown());
		model.addAttribute("listaVeiculos", veiculoService.listaVeiculoForDropDown());
		model.addAttribute("listaClientes", clienteService.listaClientesForDropDown());
		model.addAttribute("orcamento", new Orcamento());
		return "orcamento_atualizar";
	}
	
	@RequestMapping(value = "/listarOS/{id}")
	public String listarOrcamento(@PathVariable("id") long id, Model model) {
		model.addAttribute("listaTipoMotor", tipoMotorService.listaTipoMotorForDropDown());
		model.addAttribute("listaVeiculos", veiculoService.listaVeiculoForDropDown());
		model.addAttribute("listaClientes", clienteService.listaClientesForDropDown());
		model.addAttribute("orcamento", this.orcamentoService.getOrcamentoById(id));
		return "orcamento_atualizar";
	}

}
