package com.biavan.brend.controller;

import java.beans.PropertyEditorSupport;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import com.biavan.brend.enums.TipoPessoa;
import com.biavan.brend.model.Cliente;
import com.biavan.brend.model.TipoDocumento;
import com.biavan.brend.plugin.bootgrid.BootgridAction;
import com.biavan.brend.plugin.bootgrid.BootgridColumn;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridJS;
import com.biavan.brend.plugin.bootgrid.BootgridUtil;
import com.biavan.brend.service.ClienteService;
import com.biavan.brend.service.TipoDocumentoService;
import com.biavan.brend.util.Formatos;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

	private ClienteService clienteService;
	private TipoDocumentoService tipoDocumentoService;

	@Autowired(required = true)
	@Qualifier(value = "clienteService")
	public void setClienteService(ClienteService clienteService) {
		this.clienteService = clienteService;
	}
	
	@Autowired(required = true)
	@Qualifier(value = "tipoDocumentoService")
	public void setTipoDocumentoService(TipoDocumentoService tipoDocumentoService) {
		this.tipoDocumentoService = tipoDocumentoService;
	}	

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		
		binder.registerCustomEditor(Date.class, "nascimento", new PropertyEditorSupport() {
			@Override
			public void setAsText(String nascimento) {
				SimpleDateFormat dateFormat = Formatos.getFormatoDeData();
				try {
					setValue(dateFormat.parse(nascimento));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		});

		binder.registerCustomEditor(TipoDocumento.class, "tipoDocumento", new PropertyEditorSupport() {
			@Override
	        public void setAsText(String tipoId) {
				TipoDocumento tipoDocumento = tipoDocumentoService.getTipoDocumentoById(Long.valueOf(tipoId));
				setValue(tipoDocumento);
			}
		});
		
		binder.registerCustomEditor(TipoPessoa.class, "tipoPessoa", new PropertyEditorSupport() {
			@Override
	        public void setAsText(String tipoId) {
				setValue(TipoPessoa.valueOf(tipoId));
			}
		});
	}
	
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listaClientes(Model model) {

		Map<Integer, BootgridAction> botoes = new HashMap<Integer, BootgridAction>();
		botoes.put(1, new BootgridAction("alterar", "Alterar", "telaAtualizar"));
		botoes.put(2, new BootgridAction("excluir", "Excluir", "remover", true));
		
		Map<Integer, BootgridColumn> colunas = new HashMap<Integer, BootgridColumn>();
		colunas.put(1, new BootgridColumn("id", "ID"));
		colunas.put(2, new BootgridColumn("razao", "Razao social"));
		colunas.put(3, new BootgridColumn("fantasia", "Nome Fantasia"));
		colunas.put(4, new BootgridColumn("email", "E-mail"));
		
		BootgridJS bootgridJS = new BootgridJS();

		bootgridJS.setBotoes(botoes);
		bootgridJS.setColumns(colunas);
		bootgridJS.setCssId("grid-data");
		bootgridJS.setObjId("b0df282a-0d67-40e5-8558-c9e93b7befed");
		bootgridJS.setUrl("listarjson");
		bootgridJS.setCssClass("table table-condensed table-hover table-striped");

		model.addAttribute("renderBootgridJS", BootgridUtil.renderJS(bootgridJS));
		model.addAttribute("renderBootgridGrid", BootgridUtil.renderGrid(bootgridJS));
		model.addAttribute("cliente", new Cliente());
		return "cliente_listar";
	}
	
	@RequestMapping(value = "/listarjson", method = RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody BootgridData<Cliente> listaClienteJson(@RequestBody String parametros) {
		return this.clienteService.listaClientesBootgrid(BootgridUtil.makeParam(parametros));
	}

	@RequestMapping(value = "/inserir", method = RequestMethod.POST)
	public String insereCliente(@Valid Cliente cliente, BindingResult result, Model model) {

		if (result.hasErrors())	{
			model.addAttribute("fisicaSelecionada", ((cliente.getTipoPessoa() == TipoPessoa.Fisica) ? "selected" : ""));
			model.addAttribute("juridicaSelecionada", ((cliente.getTipoPessoa() == TipoPessoa.Juridica) ? "selected" : ""));
			model.addAttribute("Fisica", TipoPessoa.Fisica);
			model.addAttribute("Juridica", TipoPessoa.Juridica);
			model.addAttribute("listaTipoDocumentos", tipoDocumentoService.listaTipoDocumentoForDropDown());
			return "cliente_atualizar";
		}
		if (cliente.getId() == 0) {
			this.clienteService.insereCliente(cliente);
		} else {
			this.clienteService.atualizaCliente(cliente);
		}
		return "redirect:/cliente/listar";
	}	

	@RequestMapping(value = "/remover/{id}")
	public String removeCliente(@PathVariable("id") long id) {
		this.clienteService.removeCliente(id);
		return "redirect:/cliente/listar";
	}

	@RequestMapping(value = "/telaAtualizar/{id}")
	public String atualizarCliente(@PathVariable("id") long id, Model model) {
		Cliente cliente = clienteService.getClienteById(id);
		
		model.addAttribute("fisicaSelecionada", ((cliente.getTipoPessoa() == TipoPessoa.Fisica) ? "selected" : ""));
		model.addAttribute("juridicaSelecionada", ((cliente.getTipoPessoa() == TipoPessoa.Juridica) ? "selected" : ""));
		
		model.addAttribute("Fisica", TipoPessoa.Fisica);
		model.addAttribute("Juridica", TipoPessoa.Juridica);
		model.addAttribute("listaTipoDocumentos", tipoDocumentoService.listaTipoDocumentoForDropDown());
		model.addAttribute("cliente", cliente);
		return "cliente_atualizar";
	}
	
	@RequestMapping(value = "/telaInserir")
	public String telaInserirCliente(Model model) {
		model.addAttribute("Fisica", TipoPessoa.Fisica);
		model.addAttribute("Juridica", TipoPessoa.Juridica);
		model.addAttribute("listaTipoDocumentos", tipoDocumentoService.listaTipoDocumentoForDropDown());
		model.addAttribute("cliente", new Cliente());
		return "cliente_atualizar";
	}

}
