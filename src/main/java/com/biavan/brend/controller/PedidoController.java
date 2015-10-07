package com.biavan.brend.controller;

import java.beans.PropertyEditorSupport;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

import com.biavan.brend.model.ConfigEmpresa;
import com.biavan.brend.model.Fornecedor;
import com.biavan.brend.model.Peca;
import com.biavan.brend.model.PecaPedido;
import com.biavan.brend.model.Pedido;
import com.biavan.brend.plugin.bootgrid.BootgridAction;
import com.biavan.brend.plugin.bootgrid.BootgridColumn;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridJS;
import com.biavan.brend.plugin.bootgrid.BootgridUtil;
import com.biavan.brend.plugin.mail.MessageProperty;
import com.biavan.brend.plugin.mail.SendMailSSL;
import com.biavan.brend.service.ConfigEmpresaService;
import com.biavan.brend.service.FornecedorService;
import com.biavan.brend.service.PecaPedidoService;
import com.biavan.brend.service.PecaService;
import com.biavan.brend.service.PedidoService;
import com.biavan.brend.util.Formatos;

@Controller
@RequestMapping("/pedido")
public class PedidoController {

	private PedidoService pedidoService;
	private FornecedorService fornecedorService;
	private PecaPedidoService pecaPedidoService;
	private PecaService pecaService;
	private ConfigEmpresaService configEmpresaService;

	@Autowired(required = true)
	@Qualifier(value = "pedidoService")
	public void setPedidoService(PedidoService pedidoService) {
		this.pedidoService = pedidoService;
	}
	
	@Autowired(required = true)
	@Qualifier(value = "fornecedorService")
	public void setFornecedorService(FornecedorService fornecedorService) {
		this.fornecedorService = fornecedorService;
	}	
	
	@Autowired(required = true)
	@Qualifier(value = "pecaPedidoService")
	public void setPecaPedidoService(PecaPedidoService pecaPedidoService) {
		this.pecaPedidoService = pecaPedidoService;
	}
	
	@Autowired(required = true)
	@Qualifier(value = "pecaService")
	public void setPecaService(PecaService pecaService) {
		this.pecaService = pecaService;
	}

	@Autowired(required = true)
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

		binder.registerCustomEditor(Date.class, "dataCompra", new PropertyEditorSupport() {
			@Override
			public void setAsText(String dataCompra) {
				SimpleDateFormat dateFormat = Formatos.getFormatoDeData();

				if (Formatos.validaData(dataCompra)) {

					try {
						setValue(dateFormat.parse(dataCompra));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				} else {
					setValue(null);
				}
			}
		});
		
		binder.registerCustomEditor(Fornecedor.class, "fornecedor", new PropertyEditorSupport() {
			@Override
	        public void setAsText(String fornecedorId) {
				Fornecedor fornecedor = fornecedorService.getFornecedorById(Long.valueOf(fornecedorId));
				setValue(fornecedor);
			}
		});
		
	}
	
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listaPedidos(Model model) {

		Map<Integer, BootgridAction> botoes = new HashMap<Integer, BootgridAction>();
		botoes.put(1, new BootgridAction("alterar", "Alterar", "telaAtualizar"));
		botoes.put(2, new BootgridAction("excluir", "Excluir", "remover", true));
		
		Map<Integer, BootgridColumn> colunas = new HashMap<Integer, BootgridColumn>();
		colunas.put(1, new BootgridColumn("id", "ID"));
		colunas.put(2, new BootgridColumn("dataFormatada", "Data"));
		colunas.put(3, new BootgridColumn("dataCompraFormatada", "Data da compra"));
		colunas.put(3, new BootgridColumn("notaFiscal", "Nota Fiscal"));
		colunas.put(4, new BootgridColumn("razaoFornecedor", "Fornecedor"));
		
		BootgridJS bootgridJS = new BootgridJS();

		bootgridJS.setBotoes(botoes);
		bootgridJS.setColumns(colunas);
		bootgridJS.setCssId("grid-data");
		bootgridJS.setUrl("listarjson");
		bootgridJS.setCssClass("table table-condensed table-hover table-striped");

		model.addAttribute("renderBootgridJS", BootgridUtil.renderJS(bootgridJS));
		model.addAttribute("renderBootgridGrid", BootgridUtil.renderGrid(bootgridJS));
		model.addAttribute("pedido", new Pedido());
		return "pedido_listar";
	}
	
	@RequestMapping(value = "/listarjson", method = RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody BootgridData<Pedido> listaPedidoJson(@RequestBody String parametros) {
		return this.pedidoService.listaPedidosBootgrid(BootgridUtil.makeParam(parametros));
	}
	
	@RequestMapping(value = "/getpecas", method = RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody List<Peca> getPecas(@RequestBody String parametros) {
		return pedidoService.getPecas(parametros);
	}

	@RequestMapping(value = "/inserir", method = RequestMethod.POST)
	public String inserePedido(@Valid Pedido pedido, BindingResult result 
			, @RequestParam("ids") String[] ids
			, @RequestParam("qtdes") String[] qtdes
			, @RequestParam("units") String[] units
			, @RequestParam("estoque") String estoque
			, Model model) {
			
		if (result.hasErrors()) {
			double totalPecasPedido = 0;
			Set<PecaPedido> pecasPedido = new HashSet<PecaPedido>();
			for(int i = 0; i < ids.length; i++) {
				
				PecaPedido pecaPedido = new PecaPedido();
				pecaPedido.setPedido(pedido);
				pecaPedido.setPeca(pecaService.getPecaById(Long.valueOf(ids[i])));
				pecaPedido.setQtde(Integer.valueOf(qtdes[i]));
				pecaPedido.setValor(Double.valueOf(units[i]));

				pecasPedido.add(pecaPedido);
				
				totalPecasPedido += Double.valueOf(units[i]);
			}
			
			model.addAttribute("listaPecasPedido", pecasPedido);
			model.addAttribute("countLines", pecasPedido.size());
			model.addAttribute("totalPecasPedido", totalPecasPedido);


			if ("estoque".equals(estoque))
				model.addAttribute("estoque", true);
			
			model.addAttribute("listaFornecedores", fornecedorService.listaFornecedorForDropDown());
			model.addAttribute("listaPecas", pecaService.listaPecaForDropDown());
			model.addAttribute("pedido", pedido);
			
			return "pedido_atualizar";
		}
		
		Set<PecaPedido> pecasPedido = new HashSet<PecaPedido>();
		for(int i = 0; i < ids.length; i++) {
			
			PecaPedido pecaPedido = new PecaPedido();
			pecaPedido.setPedido(pedido);
			pecaPedido.setPeca(pecaService.getPecaById(Long.valueOf(ids[i])));
			pecaPedido.setQtde(Integer.valueOf(qtdes[i]));
			pecaPedido.setValor(Double.valueOf(units[i]));

			pecasPedido.add(pecaPedido);
		}

		if (!"estoque".equals(estoque)) {
			pedido.setDataCompra(pedido.getData());
		}
		
		if (pedido.getId() == 0) {
			pedidoService.inserePedido(pedido, pecasPedido);
		} else { 
			pedidoService.atualizaPedido(pedido, pecasPedido);
		}
		
		if ("estoque".equals(estoque)) {
			pedidoService.atualizaEstoque(pedido, pecasPedido);
		} else {
			enviarEmail(pedido, pecasPedido);
		}

		return "redirect:/pedido/listar";
	}

	@RequestMapping(value = "/remover/{id}")
	public String removePedido(@PathVariable("id") long id) {
		Pedido pedido = pedidoService.getPedidoById(id);
		pecaPedidoService.removePecaPedidoByPedido(pedido);
		pedidoService.removePedido(id);
		return "redirect:/pedido/listar";
	}

	@RequestMapping(value = "/telaAtualizar/{id}")
	public String atualizarPedido(@PathVariable("id") long id, Model model) {
		
		Pedido pedido = pedidoService.getPedidoById(id);
		
		List<PecaPedido> listaPecasPedido = pecaPedidoService.listaPecaPedidosByPedido(pedido);
		
		double totalPecasPedido = 0;
		
		for(PecaPedido pp : listaPecasPedido) {
			totalPecasPedido += pp.getSubTotal(); 
		}

		model.addAttribute("listaPecasPedido", listaPecasPedido);
		model.addAttribute("countLines", listaPecasPedido.size());
		model.addAttribute("totalPecasPedido", totalPecasPedido);
		
		model.addAttribute("listaFornecedores", fornecedorService.listaFornecedorForDropDown());
		model.addAttribute("listaPecas", pecaService.listaPecaForDropDown());
		model.addAttribute("pedido", pedido);
		
		return "pedido_atualizar";
	}
	
	@RequestMapping(value = "/telaAtualizar/{id}/estoque")
	public String atualizarPedidoEstoque(@PathVariable("id") long id, Model model) {
		
		model.addAttribute("estoque", true);
		
		return atualizarPedido(id, model);
	}
	
	
	@RequestMapping(value = "/telaInserir")
	public String telaInserirPedido(Model model) {
		model.addAttribute("countLines", 0);
		model.addAttribute("totalPecasPedido", 0);
		
		model.addAttribute("listaFornecedores", fornecedorService.listaFornecedorForDropDown());
		model.addAttribute("listaPecas", pecaService.listaPecaForDropDown());
		model.addAttribute("pedido", new Pedido());
		
		return "pedido_atualizar";
	}
	
	@RequestMapping(value = "/telaEstoque")
	public String telaInserirEstoque(Model model) {
		
		model.addAttribute("estoque", true);
		
		return telaInserirPedido(model);
	}

	@RequestMapping(value = "/enviarEmail", method = RequestMethod.POST)
	public @ResponseBody String ajaxEnviaEmail(@RequestParam("pedidoId") long pedidoId) {
		Pedido pedido = pedidoService.getPedidoById(pedidoId);
		List<PecaPedido> pecasPedido = pecaPedidoService.listaPecaPedidosByPedido(pedido);
		
		if (enviarEmail(pedido, pecasPedido))
			return "Mensagem enviada!";
		else
			return "E-mail não pode ser enviado. \nFavor tente mais tarde.";
	}

	public boolean enviarEmail(Pedido pedido, Collection<PecaPedido> pecasPedido) {
		ConfigEmpresa configEmpresa = configEmpresaService.getConfigEmpresaById(1);
		
		StringBuffer mensagem = new StringBuffer(); 
		
		mensagem.append("Prezado " + pedido.getRazaoFornecedor() + ",\n\n"
				+ "Solicitamos as peças abaixo referente ao pedido #" 
				+ pedido.getId() + ":\n\n");
		
		for(PecaPedido pp : pecasPedido) {
			mensagem.append("Peça #" + pp.getPeca().getId()
					+ " - " + pp.getPeca().getNome() + "/" + pp.getPeca().getNomeMarca()
					+ " (" + pp.getQtde() + ")\n");
		}
		
		mensagem.append("\n\n\nAtenciosamente equipe " + configEmpresa.getNomeFantasia());
		
		MessageProperty messageProperty = new MessageProperty();
		messageProperty.setFrom(configEmpresa.getEmail());
		messageProperty.setSubject("Pedido de compra de peças automitiva #"	+ pedido.getId());
		messageProperty.setMessage(mensagem.toString());
		messageProperty.addRecipient(configEmpresa.getEmail());
		messageProperty.addRecipient(pedido.getFornecedor().getEmail());
		
		try {
			SendMailSSL.send(messageProperty, configEmpresaService.getConfigEmpresaById(1));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
}
