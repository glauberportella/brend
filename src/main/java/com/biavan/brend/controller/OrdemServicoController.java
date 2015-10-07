package com.biavan.brend.controller;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biavan.brend.enums.StatusOS;
import com.biavan.brend.model.Cliente;
import com.biavan.brend.model.Funcionario;
import com.biavan.brend.model.Orcamento;
import com.biavan.brend.model.OrdemServico;
import com.biavan.brend.model.PecaOrcamento;
import com.biavan.brend.model.PecaOrdemServico;
import com.biavan.brend.model.ServicoOrcamento;
import com.biavan.brend.model.ServicoOrdemServico;
import com.biavan.brend.model.StatusOrdemServico;
import com.biavan.brend.model.TipoMotor;
import com.biavan.brend.model.Veiculo;
import com.biavan.brend.plugin.bootgrid.BootgridAction;
import com.biavan.brend.plugin.bootgrid.BootgridColumn;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridJS;
import com.biavan.brend.plugin.bootgrid.BootgridUtil;
import com.biavan.brend.service.ClienteService;
import com.biavan.brend.service.FuncionarioService;
import com.biavan.brend.service.OrcamentoService;
import com.biavan.brend.service.OrdemServicoService;
import com.biavan.brend.service.PecaOrcamentoService;
import com.biavan.brend.service.PecaOrdemServicoService;
import com.biavan.brend.service.PecaService;
import com.biavan.brend.service.ServicoOrcamentoService;
import com.biavan.brend.service.ServicoOrdemServicoService;
import com.biavan.brend.service.ServicoService;
import com.biavan.brend.service.StatusOrdemServicoService;
import com.biavan.brend.service.TipoMotorService;
import com.biavan.brend.service.VeiculoService;
import com.biavan.brend.util.Formatos;

@Controller
@RequestMapping("/ordemServico")
public class OrdemServicoController {

	private OrdemServicoService ordemServicoService;
	private TipoMotorService tipoMotorService;
	private VeiculoService veiculoService;
	private OrcamentoService orcamentoService;
	private PecaOrcamentoService pecaOrcamentoService;
	private PecaOrdemServicoService pecaOrdemServicoService;
	private ServicoOrcamentoService servicoOrcamentoService;
	private ServicoOrdemServicoService servicoOrdemServicoService;
	private PecaService pecaService;
	private ServicoService servicoService;
	private ClienteService clienteService;
	private StatusOrdemServicoService statusOrdemServicoService;
	private FuncionarioService funcionarioService;

	@Autowired(required = true)
	@Qualifier(value = "ordemServicoService")
	public void setOrdemServicoService(OrdemServicoService ordemServicoService) {
		this.ordemServicoService = ordemServicoService;
	}
	
	@Autowired(required = true)
	@Qualifier(value = "tipoMotorService")
	public void setTipoMotorService(TipoMotorService tipoMotorService) {
		this.tipoMotorService = tipoMotorService;
	}
	
	@Autowired(required = true)
	@Qualifier(value = "veiculoService")
	public void setVeiculoService(VeiculoService veiculoService) {
		this.veiculoService = veiculoService;
	}
	
	@Autowired(required = true)
	@Qualifier(value = "orcamentoService")
	public void setOrcamentoService(OrcamentoService orcamentoService) {
		this.orcamentoService = orcamentoService;
	}
	
	@Autowired(required = true)
	@Qualifier(value = "pecaOrcamentoService")
	public void setPecaOrcamentoService(PecaOrcamentoService pecaOrcamentoService) {
		this.pecaOrcamentoService = pecaOrcamentoService;
	}
	
	@Autowired(required = true)
	@Qualifier(value = "servicoOrcamentoService")
	public void setServicoOrcamentoService(ServicoOrcamentoService servicoOrcamentoService) {
		this.servicoOrcamentoService = servicoOrcamentoService;
	}
	
	@Autowired(required = true)
	@Qualifier(value = "servicoOrdemServicoService")
	public void setServicoOrdemServicoService(ServicoOrdemServicoService servicoOrdemServicoService) {
		this.servicoOrdemServicoService = servicoOrdemServicoService;
	}
	
	@Autowired(required = true)
	@Qualifier(value = "pecaOrdemServicoService")
	public void setPecaOrdemServicoService(PecaOrdemServicoService pecaOrdemServicoService) {
		this.pecaOrdemServicoService = pecaOrdemServicoService;
	}
	
	@Autowired(required = true)
	@Qualifier(value = "servicoService")
	public void setServicoService(ServicoService servicoService) {
		this.servicoService = servicoService;
	}
	
	@Autowired(required = true)
	@Qualifier(value = "pecaService")
	public void setPecaService(PecaService pecaService) {
		this.pecaService = pecaService;
	}
	
	@Autowired(required = true)
	@Qualifier(value = "clienteService")
	public void setClienteService(ClienteService clienteService) {
		this.clienteService = clienteService;
	}
	
	@Autowired(required = true)
	@Qualifier(value = "statusOrdemServicoService")
	public void setStatusOrdemServicoService(StatusOrdemServicoService statusOrdemServicoService) {
		this.statusOrdemServicoService = statusOrdemServicoService;
	}

	@Autowired(required = true)
	@Qualifier(value = "funcionarioService")
	public void setFuncionarioService(FuncionarioService funcionarioService) {
		this.funcionarioService = funcionarioService;
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
		
		binder.registerCustomEditor(Date.class, "prazo", new PropertyEditorSupport() {
			@Override
			public void setAsText(String validade) {
				SimpleDateFormat dateFormat = Formatos.getFormatoDeData();
				try {
					setValue(dateFormat.parse(validade));
				} catch (ParseException e) {
					e.printStackTrace();
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
	        public void setAsText(String veiculoId) {
				Veiculo veiculo = veiculoService.getVeiculoById(Long.valueOf(veiculoId));
				setValue(veiculo);
			}
		});
		
		binder.registerCustomEditor(Cliente.class, "cliente", new PropertyEditorSupport() {
			@Override
	        public void setAsText(String clienteId) {
				Cliente cliente = clienteService.getClienteById(Long.valueOf(clienteId));
				setValue(cliente);
			}
		});
		
		binder.registerCustomEditor(Funcionario.class, "mecanico", new PropertyEditorSupport() {
			@Override
	        public void setAsText(String mecanicoId) {
				Funcionario mecanico = funcionarioService.getFuncionarioById(Long.valueOf(mecanicoId));
				setValue(mecanico);
			}
		});
	}

	@RequestMapping(value = "/listar/mecanico/{id}", method = RequestMethod.GET)
	public String listaOrdemServicoByMecanico(@PathVariable("id") long mecanicoId, Model model) {

		Funcionario mecanico = funcionarioService.getFuncionarioById(mecanicoId);
		
		BootgridJS bootgridJS = configListaOrdemServico(); 

		Map<Integer, BootgridAction> botoes = new HashMap<Integer, BootgridAction>();
		botoes.put(1, new BootgridAction("visualizar", "Visualizar", "../../telaAtualizar"));
		
		bootgridJS.setBotoes(botoes);
		bootgridJS.addExtraParam("mecanico", String.valueOf(mecanico.getId()));
		bootgridJS.setUrl("../../listarjson");
		
		model.addAttribute("mecanico", mecanico);
		model.addAttribute("renderBootgridJS", BootgridUtil.renderJS(bootgridJS));
		model.addAttribute("renderBootgridGrid", BootgridUtil.renderGrid(bootgridJS));
		model.addAttribute("ordemServico", new OrdemServico());
		
		return "ordem_servico_listar";
	}
	
	@RequestMapping(value = "/listar/{status}", method = RequestMethod.GET)
	public String listaOrdemServico(@PathVariable("status") String status, Model model) {

		BootgridJS bootgridJS = configListaOrdemServico(); 

		Map<Integer, BootgridAction> botoes = new HashMap<Integer, BootgridAction>();
		botoes.put(1, new BootgridAction("visualizar", "Visualizar", "../telaAtualizar"));
		
		bootgridJS.setBotoes(botoes);
		bootgridJS.addExtraParam("statusOS", status);
		bootgridJS.setUrl("../listarjson");
		
		model.addAttribute("status", status);
		model.addAttribute("renderBootgridJS", BootgridUtil.renderJS(bootgridJS));
		model.addAttribute("renderBootgridGrid", BootgridUtil.renderGrid(bootgridJS));
		model.addAttribute("ordemServico", new OrdemServico());
		
		return "ordem_servico_listar";
	}
	
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listaOrdemServico(Model model) {

		BootgridJS bootgridJS = configListaOrdemServico();

		Map<Integer, BootgridAction> botoes = new HashMap<Integer, BootgridAction>();
		botoes.put(1, new BootgridAction("visualizar", "Visualizar", "telaAtualizar"));
		
		bootgridJS.setBotoes(botoes);
		bootgridJS.setUrl("listarjson");

		model.addAttribute("renderBootgridJS", BootgridUtil.renderJS(bootgridJS));
		model.addAttribute("renderBootgridGrid", BootgridUtil.renderGrid(bootgridJS));
		model.addAttribute("ordemServico", new OrdemServico());
		return "ordem_servico_listar";
	}
	
	@RequestMapping(value = "/listarjson", method = RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody BootgridData<OrdemServico> listaOrdemServicoJson(@RequestBody String parametros) {
		
		return this.ordemServicoService.listaOrdemServicosBootgrid(BootgridUtil.makeParam(parametros));
	}

	@RequestMapping(value = "/listarjson/{ordemServicoIdPai}", method = RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody BootgridData<OrdemServico> listaOrdemServicoAdicionaisJson(
			@PathVariable("ordemServicoIdPai") long ordemServicoIdPai,
			@RequestBody String parametros) {
		OrdemServico ordemServicoPai = ordemServicoService.getOrdemServicoById(ordemServicoIdPai);
		
		return this.ordemServicoService.listaOrdemServicosAdicionaisBootgrid(ordemServicoPai,
				BootgridUtil.makeParam(parametros));
	}
	
	@RequestMapping(value = "/atualizar", method = RequestMethod.POST)
	public String insereOrdemServico(
			@ModelAttribute("ordemServico") OrdemServico ordemServico,
			@RequestParam("orcamentoId") long orcamentoId,
			@RequestParam("ordemServicoIdPai") long ordemServicoIdPai,
			@RequestParam("idsPeca") String[] idsPeca,
 			@RequestParam("qtdesPeca") String[] qtdesPeca,
			@RequestParam("unitsPeca") String[] unitsPeca,
			@RequestParam("idsServico") String[] idsServico,
			@RequestParam("qtdesServico") String[] qtdesServico,
			@RequestParam("unitsServico") String[] unitsServico,
			@RequestParam("alteraStatus") String alteraStatus
			) {
		
		List<PecaOrdemServico> pecasOrdemServico = new ArrayList<PecaOrdemServico>();
		if (Long.valueOf(idsPeca[0]) > 0) {
			for(int i = 0; i < idsPeca.length; i++) {
				
				PecaOrdemServico pecaOrdemServico = new PecaOrdemServico();
				pecaOrdemServico.setOrdemServico(ordemServico);
				pecaOrdemServico.setPeca(pecaService.getPecaById(Long.valueOf(idsPeca[i])));
				pecaOrdemServico.setQtde(Integer.valueOf(qtdesPeca[i]));
				pecaOrdemServico.setValor(Double.valueOf(unitsPeca[i]));
				
				pecasOrdemServico.add(pecaOrdemServico);
			}
		}
		
		List<ServicoOrdemServico> servicosOrdemServico = new ArrayList<ServicoOrdemServico>();
		for(int i = 0; i < idsServico.length; i++) {
			
			ServicoOrdemServico servicoOrdemServico = new ServicoOrdemServico();
			servicoOrdemServico.setOrdemServico(ordemServico);
			servicoOrdemServico.setServico(servicoService.getServicoById(Long.valueOf(idsServico[i])));
			servicoOrdemServico.setQtde(Integer.valueOf(qtdesServico[i]));
			servicoOrdemServico.setValor(Double.valueOf(unitsServico[i]));
			
			servicosOrdemServico.add(servicoOrdemServico);
		}
		
		if (orcamentoId != 0) {
			ordemServico.setOrcamento(orcamentoService.getOrcamentoById(orcamentoId));
			ordemServico.setOrdemServicoPai(ordemServicoService.getOrdemServicoById(ordemServicoIdPai));
			ordemServicoService.atualizaOrdemServico(ordemServico, pecasOrdemServico, servicosOrdemServico);
		}
		
		if ("S".equals(alteraStatus)) {
			StatusOrdemServico statusOrdemServico = new StatusOrdemServico();
			statusOrdemServico.setDataInicio(new Date());
			statusOrdemServico.setDataFim(Formatos.getUltimaData());
			statusOrdemServico.setDescricao("Atribuição do mecânico " 
					+ ordemServico.getMecanico().getNome() + " para atender a OS.");
			statusOrdemServico.setStatusOS(StatusOS.EM_ANDAMENTO);
			statusOrdemServico.setOrdemServico(ordemServico);
			
			statusOrdemServicoService.insereStatusOrdemServico(statusOrdemServico);
		}

		return "redirect:/ordemServico/msgAtualizar/" + ordemServico.getId();
	}
	
	@RequestMapping(value = "/inserirOSAdicional", method = RequestMethod.POST)
	public String insereOrdemServicoAdicional(
			@ModelAttribute("ordemServicoAdicional") OrdemServico ordemServicoAdicional
			, @RequestParam("idsPeca") String[] idsPeca
 			, @RequestParam("qtdesPeca") String[] qtdesPeca
			, @RequestParam("unitsPeca") String[] unitsPeca
			, @RequestParam("idsServico") String[] idsServico
			, @RequestParam("qtdesServico") String[] qtdesServico
			, @RequestParam("unitsServico") String[] unitsServico
			, @RequestParam("ordemServicoIdPai") Long ordemServicoIdPai) {
		
		List<PecaOrdemServico> pecasOrdemServico = new ArrayList<PecaOrdemServico>();
		if (Long.valueOf(idsPeca[0]) > 0) {
			for(int i = 0; i < idsPeca.length; i++) {
				
				PecaOrdemServico pecaOrdemServico = new PecaOrdemServico();
				pecaOrdemServico.setOrdemServico(ordemServicoAdicional);
				pecaOrdemServico.setPeca(pecaService.getPecaById(Long.valueOf(idsPeca[i])));
				pecaOrdemServico.setQtde(Integer.valueOf(qtdesPeca[i]));
				pecaOrdemServico.setValor(Double.valueOf(unitsPeca[i]));
				
				pecasOrdemServico.add(pecaOrdemServico);
			}
		}
		
		List<ServicoOrdemServico> servicosOrdemServico = new ArrayList<ServicoOrdemServico>();
		for(int i = 0; i < idsServico.length; i++) {
			
			ServicoOrdemServico servicoOrdemServico = new ServicoOrdemServico();
			servicoOrdemServico.setOrdemServico(ordemServicoAdicional);
			servicoOrdemServico.setServico(servicoService.getServicoById(Long.valueOf(idsServico[i])));
			servicoOrdemServico.setQtde(Integer.valueOf(qtdesServico[i]));
			servicoOrdemServico.setValor(Double.valueOf(unitsServico[i]));
			
			servicosOrdemServico.add(servicoOrdemServico);
		}
		ordemServicoAdicional.setOrdemServicoPai(ordemServicoService.getOrdemServicoById(ordemServicoIdPai));
        	
		ordemServicoService.insereOrdemServico(ordemServicoAdicional, pecasOrdemServico, servicosOrdemServico);

		return "redirect:/ordemServico/msgInserirAdicional/" + ordemServicoIdPai;
	}

	@RequestMapping(value = "/inserirNovaOS", method = RequestMethod.POST)
    public String insereNovaOS(
                     @ModelAttribute("ordemServico") OrdemServico ordemServico,
                     @RequestParam("orcamentoId") long orcamentoId
                    ,@RequestParam("idsPeca") String[] idsPeca
         			,@RequestParam("qtdesPeca") String[] qtdesPeca
        			,@RequestParam("unitsPeca") String[] unitsPeca
        			,@RequestParam("idsServico") String[] idsServico
        			,@RequestParam("qtdesServico") String[] qtdesServico
        			,@RequestParam("unitsServico") String[] unitsServico) {

            
		 List<PecaOrdemServico> pecasOrdemServico = new ArrayList<PecaOrdemServico>();
			if (Long.valueOf(idsPeca[0]) > 0) {
				for(int i = 0; i < idsPeca.length; i++) {
					
					PecaOrdemServico pecaOrdemServico = new PecaOrdemServico();
					pecaOrdemServico.setOrdemServico(ordemServico);
					pecaOrdemServico.setPeca(pecaService.getPecaById(Long.valueOf(idsPeca[i])));
					pecaOrdemServico.setQtde(Integer.valueOf(qtdesPeca[i]));
					pecaOrdemServico.setValor(Double.valueOf(unitsPeca[i]));
					
					pecasOrdemServico.add(pecaOrdemServico);
				}
			}
			
			List<ServicoOrdemServico> servicosOrdemServico = new ArrayList<ServicoOrdemServico>();
			for(int i = 0; i < idsServico.length; i++) {
				
				ServicoOrdemServico servicoOrdemServico = new ServicoOrdemServico();
				servicoOrdemServico.setOrdemServico(ordemServico);
				servicoOrdemServico.setServico(servicoService.getServicoById(Long.valueOf(idsServico[i])));
				servicoOrdemServico.setQtde(Integer.valueOf(qtdesServico[i]));
				servicoOrdemServico.setValor(Double.valueOf(unitsServico[i]));
				
				servicosOrdemServico.add(servicoOrdemServico);
			}
		 	ordemServico.setOrcamento(orcamentoService.getOrcamentoById(orcamentoId));
            
			ordemServicoService.insereOrdemServico(ordemServico, pecasOrdemServico, servicosOrdemServico);

            return "redirect:/ordemServico/msgInserir/" + ordemServico.getId();
     }
		
	
	@RequestMapping(value = "/remover/{id}")
	public String removeOrdemServico(@PathVariable("id") long id) {
		this.ordemServicoService.removeOrdemServico(id);
		return "redirect:/ordemServico/listar";
	}

	
	@RequestMapping(value = "/{tipoMsg}/{id}")
	public String telaAtualizarOrdemServico(@PathVariable("tipoMsg") String tipoMsg,
			@PathVariable("id") long id, Model model) {
		
		if ("msgAtualizar".equals(tipoMsg)) {
			model.addAttribute("sucesso", "Ordem de serviço atualizada com sucesso.");
		}
		
		if ("msgInserir".equals(tipoMsg)) {
			model.addAttribute("sucesso", "Ordem de serviço criada com sucesso.");
		} 
		
		if ("msgInserirAdicional".equals(tipoMsg)) {
			model.addAttribute("sucesso", "Ordem de serviço adicional criada com sucesso.");
		}
		
		return telaAtualizarOrdemServico(id, model);
	}
	
	@RequestMapping(value = "/telaAtualizar/{id}")
	public String telaAtualizarOrdemServico(@PathVariable("id") long id
										, Model model) {
		
		OrdemServico ordemServico = ordemServicoService.getOrdemServicoById(id);
		
		List<PecaOrdemServico> listaPecasOrdemServico = pecaOrdemServicoService.listaPecaOrdemServicoByOrdemServico(ordemServico);
		List<ServicoOrdemServico> listaServicosOrdemServico = servicoOrdemServicoService.listaServicoOrdemServicoByOrdemServico(ordemServico);
		
		double totalPecasOrdemServico = 0;
		double totalServicosOrdemServico = 0;
		
		for(PecaOrdemServico pp : listaPecasOrdemServico) {
			totalPecasOrdemServico += pp.getSubTotal(); 
		}
		
		for(ServicoOrdemServico pp : listaServicosOrdemServico) {
			totalServicosOrdemServico += pp.getSubTotal(); 
		}
		
		listaOrdemServicosAdicionais(model, id);
		listaRegistrosContato(model, id);
		
		model.addAttribute("historico", statusOrdemServicoService.listaStatusOrdensServicoByOrdemServico(ordemServico));
		model.addAttribute("statusOrdemServico", statusOrdemServicoService.getStatusOrdemServicoByOrdemServico(ordemServico));
		model.addAttribute("isPai", (ordemServico.getOrdemServicoPai() == null) ? true : false);
		model.addAttribute("orcamentoId", (ordemServico.getOrcamento() != null) ? ordemServico.getOrcamento().getId() : null);
		model.addAttribute("listaTipoMotor", tipoMotorService.listaTipoMotorForDropDown());
		model.addAttribute("listaVeiculos", veiculoService.listaVeiculoForDropDown());
		model.addAttribute("listaPecasOrdemServico", listaPecasOrdemServico);
		model.addAttribute("listaServicosOrdemServico", listaServicosOrdemServico);
		model.addAttribute("countLinesPeca", listaPecasOrdemServico.size());
		model.addAttribute("countLinesServico", listaServicosOrdemServico.size());
		model.addAttribute("totalPecasOrdemServico", totalPecasOrdemServico);
		model.addAttribute("totalServicosOrdemServico", totalServicosOrdemServico);
		model.addAttribute("listaClientes", clienteService.listaClientesForDropDown());
		model.addAttribute("listaMecanicos", funcionarioService.listaMecanicosForDropDown());
		model.addAttribute("ordemServico", this.ordemServicoService.getOrdemServicoById(id));
		
		model.addAttribute("addAction", "/omservicos/ordemServico/atualizar");
		
		return "ordem_servico_atualizar";
		
	}

	@RequestMapping(value = "/imprimir/{id}")
	public String telaImprimirOrdemServico(@PathVariable("id") long id, Model model) {
		
		OrdemServico ordemServico = ordemServicoService.getOrdemServicoById(id);
		
		List<PecaOrdemServico> listaPecasOrdemServico = pecaOrdemServicoService.listaPecaOrdemServicoByOrdemServico(ordemServico);
		List<ServicoOrdemServico> listaServicosOrdemServico = servicoOrdemServicoService.listaServicoOrdemServicoByOrdemServico(ordemServico);
		
		double totalPecasOrdemServico = 0;
		double totalServicosOrdemServico = 0;
		
		for(PecaOrdemServico pp : listaPecasOrdemServico) {
			totalPecasOrdemServico += pp.getSubTotal(); 
		}
		
		for(ServicoOrdemServico pp : listaServicosOrdemServico) {
			totalServicosOrdemServico += pp.getSubTotal(); 
		}
		
		listaOrdemServicosAdicionais(model, id);
		listaRegistrosContato(model, id);
		
		model.addAttribute("statusOrdemServico", statusOrdemServicoService.getStatusOrdemServicoByOrdemServico(ordemServico));
		model.addAttribute("isPai", (ordemServico.getOrdemServicoPai() == null) ? true : false);
		model.addAttribute("orcamentoId", (ordemServico.getOrcamento() != null) ? ordemServico.getOrcamento().getId() : null);
		model.addAttribute("listaTipoMotor", tipoMotorService.listaTipoMotorForDropDown());
		model.addAttribute("listaVeiculos", veiculoService.listaVeiculoForDropDown());
		model.addAttribute("listaPecasOrdemServico", listaPecasOrdemServico);
		model.addAttribute("listaServicosOrdemServico", listaServicosOrdemServico);
		model.addAttribute("countLinesPeca", listaPecasOrdemServico.size());
		model.addAttribute("countLinesServico", listaServicosOrdemServico.size());
		model.addAttribute("totalPecasOrdemServico", totalPecasOrdemServico);
		model.addAttribute("totalServicosOrdemServico", totalServicosOrdemServico);
		model.addAttribute("ordemServico", ordemServico);
		
		model.addAttribute("addAction", "/omservicos/ordemServico/atualizar");
		
		return "ordem_servico_imprimir";
		
	}
	
	
	@RequestMapping(value = "/telaNovaOS/{orcamentoId}")
	public String novaOrdemServico(@PathVariable("orcamentoId") long orcamentoId,
								   Model model) {

		Orcamento orcamento = orcamentoService.getOrcamentoById(orcamentoId);
		
		OrdemServico ordemServico = new OrdemServico(); 
		ordemServico.setData(new Date());
		ordemServico.setCliente(orcamento.getCliente());
		ordemServico.setVeiculo(orcamento.getVeiculo());
		ordemServico.setAno(orcamento.getAno());
		ordemServico.setTipoMotor(orcamento.getTipoMotor());
		ordemServico.setValorTotal(orcamento.getValorTotal());
		ordemServico.setValorSubTotal(orcamento.getValorTotal());
		ordemServico.setValorTotalAdicionais(0);
		
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
        
        StatusOrdemServico statusOrdemServico = new StatusOrdemServico();
        statusOrdemServico.setDataInicio(new Date());
        statusOrdemServico.setStatusOS(StatusOS.PENDENTE);
        
        model.addAttribute("novaOS", true);
        model.addAttribute("statusOrdemServico", statusOrdemServico);
        model.addAttribute("orcamentoId", orcamentoId);
        model.addAttribute("listaPecasOrdemServico", listaPecasOrcamento);
        model.addAttribute("listaServicosOrdemServico", listaServicosOrcamento);
        model.addAttribute("countLinesPeca", listaPecasOrcamento.size());
        model.addAttribute("countLinesServico", listaServicosOrcamento.size());
        model.addAttribute("totalPecasOrdemServico", totalPecasOrcamento);
        model.addAttribute("totalServicosOrdemServico", totalServicosOrcamento);
        model.addAttribute("listaPecas", pecaService.listaPecas());
        model.addAttribute("listaServicos", servicoService.listaServicos());
        model.addAttribute("listaTipoMotor", tipoMotorService.listaTipoMotorForDropDown());
        model.addAttribute("listaVeiculos", veiculoService.listaVeiculoForDropDown());
        model.addAttribute("listaClientes", clienteService.listaClientesForDropDown());
         
        model.addAttribute("ordemServico", ordemServico );
         
        model.addAttribute("addAction", "/omservicos/ordemServico/inserirNovaOS");
        
        return "ordem_servico_atualizar";
		
	}
	
	@RequestMapping(value = "/telaOSAdicional/{ordemServicoIdPai}")
	public String telaInserirOrcamento(@PathVariable("ordemServicoIdPai") Long ordemServicoIdPai, Model model) {
		
		OrdemServico ordemServicoPai = ordemServicoService.getOrdemServicoById(ordemServicoIdPai);
		
		OrdemServico ordemServicoAdicional = new OrdemServico();
		ordemServicoAdicional.setAno(ordemServicoPai.getAno());
		ordemServicoAdicional.setCliente(ordemServicoPai.getCliente());
		ordemServicoAdicional.setData(new Date());
		ordemServicoAdicional.setPrazo(ordemServicoPai.getPrazo());
		ordemServicoAdicional.setTipoMotor(ordemServicoPai.getTipoMotor());
		ordemServicoAdicional.setVeiculo(ordemServicoPai.getVeiculo());
		ordemServicoAdicional.setOrdemServicoPai(ordemServicoPai);
		
		model.addAttribute("countLinesPeca", 0);
		model.addAttribute("totalPecasOrdemServico", 0);
		
		model.addAttribute("countLinesServico", 0);
		model.addAttribute("totalServicosOrdemServico", 0);
		
		model.addAttribute("listaPecas", pecaService.listaPecas());
		model.addAttribute("listaServicos", servicoService.listaServicos());
		
		model.addAttribute("listaTipoMotor", tipoMotorService.listaTipoMotorForDropDown());
		model.addAttribute("listaVeiculos", veiculoService.listaVeiculoForDropDown());
		model.addAttribute("listaClientes", clienteService.listaClientesForDropDown());
		model.addAttribute("ordemServicoAdicional", ordemServicoAdicional);
		return "ordem_servico_adicional";
	}

	private Model listaOrdemServicosAdicionais(Model model, long ordemServicoIdPai) {

		Map<Integer, BootgridAction> botoes = new HashMap<Integer, BootgridAction>();
		botoes.put(1, new BootgridAction("visualizar", "Visualizar", "../telaAtualizar"));
		
		Map<Integer, BootgridColumn> colunas = new HashMap<Integer, BootgridColumn>();
		colunas.put(1, new BootgridColumn("id", "ID"));
		colunas.put(2, new BootgridColumn("clienteNome", "Cliente"));
		colunas.put(3, new BootgridColumn("nomeVeiculo", "Veiculo"));
		colunas.put(4, new BootgridColumn("valorTotalFormatado", "Valor Total" ));
		
		BootgridJS bootgridJS = new BootgridJS();

		bootgridJS.setBotoes(botoes);
		bootgridJS.setColumns(colunas);
		bootgridJS.setCssId("grid-data-os-adicional");
		bootgridJS.setUrl("../listarjson/" + ordemServicoIdPai);
		bootgridJS.setCssClass("table table-condensed table-hover table-striped");
		
		model.addAttribute("renderBootgridJSOSAdicional", BootgridUtil.renderJS(bootgridJS));
		model.addAttribute("renderBootgridGridOSAdicional", BootgridUtil.renderGrid(bootgridJS));
		
		return model;
	}

	
	private Model listaRegistrosContato(Model model, long ordemServicoId) {

		Map<Integer, BootgridAction> botoes = new HashMap<Integer, BootgridAction>();
		
		Map<Integer, BootgridColumn> colunas = new HashMap<Integer, BootgridColumn>();
		colunas.put(1, new BootgridColumn("dataFormatada", "Data"));
		colunas.put(2, new BootgridColumn("descricao", "Descrição"));
		colunas.put(3, new BootgridColumn("vistoriaFormatada", " ", false));
		
		BootgridJS bootgridJS = new BootgridJS();

		bootgridJS.setBotoes(botoes);
		bootgridJS.setColumns(colunas);
		bootgridJS.setCssId("grid-data-followup");
		bootgridJS.setUrl("../../registroContato/listarjson/" + ordemServicoId);
		bootgridJS.setCssClass("table table-condensed table-hover table-striped");
		
		model.addAttribute("renderBootgridJSFollowup", BootgridUtil.renderJS(bootgridJS));
		model.addAttribute("renderBootgridGridFollowup", BootgridUtil.renderGrid(bootgridJS));
		
		return model;
	}
	
	private BootgridJS configListaOrdemServico() {
		Map<Integer, BootgridColumn> colunas = new HashMap<Integer, BootgridColumn>();
		colunas.put(1, new BootgridColumn("id", "ID"));
		colunas.put(2, new BootgridColumn("clienteNome", "Cliente"));
		colunas.put(3, new BootgridColumn("nomeVeiculo", "Veiculo"));
		colunas.put(4, new BootgridColumn("statusAtualDescricao", "Status"));
		colunas.put(5, new BootgridColumn("valorTotalFormatado", "Valor Total" ));
		
		BootgridJS bootgridJS = new BootgridJS();
		
		bootgridJS.setColumns(colunas);
		bootgridJS.setCssId("grid-data");
		bootgridJS.setCssClass("table table-condensed table-hover table-striped");

		return bootgridJS;
	}

}
