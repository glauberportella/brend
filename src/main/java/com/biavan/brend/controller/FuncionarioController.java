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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biavan.brend.model.Funcionario;
import com.biavan.brend.model.TipoFuncionario;
import com.biavan.brend.model.Usuario;
import com.biavan.brend.plugin.bootgrid.BootgridAction;
import com.biavan.brend.plugin.bootgrid.BootgridColumn;
import com.biavan.brend.plugin.bootgrid.BootgridData;
import com.biavan.brend.plugin.bootgrid.BootgridJS;
import com.biavan.brend.plugin.bootgrid.BootgridUtil;
import com.biavan.brend.service.FuncionarioService;
import com.biavan.brend.service.TipoFuncionarioService;
import com.biavan.brend.service.UsuarioService;
import com.biavan.brend.util.Formatos;

@Controller
@RequestMapping("/funcionario")
public class FuncionarioController {

	private FuncionarioService funcionarioService;
	private TipoFuncionarioService tipoFuncionarioService;
	private UsuarioService usuarioService;
	
	private Map<String, Papel> getPapeis() {
		Map<String, Papel> listaPapeis = new HashMap<String, Papel>();
		
		listaPapeis.put("ROLE_ATE", new Papel("Atendente",""));
		listaPapeis.put("ROLE_ADM", new Papel("Equipe administrativa",""));
		listaPapeis.put("ROLE_ALM", new Papel("Almoxarife",""));
		listaPapeis.put("ROLE_MEC", new Papel("Mecânico",""));
		listaPapeis.put("ROLE_GER", new Papel("Gerente",""));
		
		return listaPapeis;
	}

	@Autowired(required = true)
	@Qualifier(value = "funcionarioService")
	public void setFuncionarioService(FuncionarioService funcionarioService) {
		this.funcionarioService = funcionarioService;
	}
	
	@Autowired(required = true)
	@Qualifier(value = "tipoFuncionarioService")
	public void setTipoFuncionarioService(TipoFuncionarioService tipoFuncionarioService) {
		this.tipoFuncionarioService = tipoFuncionarioService;
	}	
	
	@Autowired(required = true)
	@Qualifier(value = "usuarioService")
	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
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

		binder.registerCustomEditor(TipoFuncionario.class, "tipoFuncionario", new PropertyEditorSupport() {
			@Override
	        public void setAsText(String tipoId) {
				TipoFuncionario tipoFuncionario = tipoFuncionarioService.getTipoFuncionarioById(Long.valueOf(tipoId));
				setValue(tipoFuncionario);
			}
		});
	}
	
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listaFuncionarios(Model model) {

		Map<Integer, BootgridAction> botoes = new HashMap<Integer, BootgridAction>();
		botoes.put(1, new BootgridAction("alterar", "Alterar", "telaAtualizar"));
		botoes.put(2, new BootgridAction("excluir", "Excluir", "remover", true));
		
		Map<Integer, BootgridColumn> colunas = new HashMap<Integer, BootgridColumn>();
		colunas.put(1, new BootgridColumn("id", "ID"));
		colunas.put(2, new BootgridColumn("nome", "Nome"));
		colunas.put(3, new BootgridColumn("email", "E-mail"));
		colunas.put(4, new BootgridColumn("tipoFuncionarioNome", "Tipo"));
		
		
		BootgridJS bootgridJS = new BootgridJS();

		bootgridJS.setBotoes(botoes);
		bootgridJS.setColumns(colunas);
		bootgridJS.setCssId("grid-data");
		bootgridJS.setObjId("b0df282a-0d67-40e5-8558-c9e93b7befed");
		bootgridJS.setUrl("listarjson");
		bootgridJS.setCssClass("table table-condensed table-hover table-striped");
		
		model.addAttribute("renderBootgridJS", BootgridUtil.renderJS(bootgridJS));
		model.addAttribute("renderBootgridGrid", BootgridUtil.renderGrid(bootgridJS));
		model.addAttribute("funcionario", new Funcionario());
		return "funcionario_listar";
	}
	
	@RequestMapping(value = "/listarjson", method = RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody BootgridData<Funcionario> listaFuncionarioJson(@RequestBody String parametros) {
		return this.funcionarioService.listaFuncionariosBootgrid(BootgridUtil.makeParam(parametros));
	}

	@RequestMapping(value = "/inserir", method = RequestMethod.POST)
	public String insereFuncionario(
			@Valid Funcionario funcionario,
			BindingResult result,
			@RequestParam String login, @RequestParam String senha,
			@RequestParam String[] papeis, @RequestParam int destino,
			Model model) {
		
		Usuario usuario = new Usuario(login, senha, true);
		usuario.setFuncionario(funcionario);
		
		
		if(result.hasErrors())
		{
			model.addAttribute("destino", 2); // depois de salvar vai para a lista de funcionarios
			model.addAttribute("destinoCancela", "/funcionario/listar");
			
			model.addAttribute("listaTipoFuncionarios", tipoFuncionarioService.listaTipoFuncionarioForDropDown());
			model.addAttribute("funcionario", funcionario);
			model.addAttribute("usuario", usuario);
			return "funcionario_atualizar";
		}
		
		if (funcionario.getId() == 0) {
			funcionarioService.insereFuncionario(funcionario);
		} else {
			funcionarioService.atualizaFuncionario(funcionario);
		}
		
		if (usuario.getLogin() != null && !"".equals(usuario.getLogin())) {
			Usuario usuarioAtual = usuarioService.getUsuarioByFuncionario(usuario.getFuncionario());
			if (usuarioAtual == null) {
				usuarioService.insereUsuario(usuario);
			} else {
				if (usuario.getSenha() == null || "".equals(usuario.getSenha()) ) {
					usuario.setSenha(usuarioAtual.getSenha());
					usuarioService.atualizaUsuario(usuario);
				} else {
					usuarioService.atualizaUsuario(usuario, senha);
				}
			}
		
		}
		
		if (destino == 1) {
			model.addAttribute("destino", 1); // depois de salvar volta para a tela de atualização
			model.addAttribute("destinoCancela", "/home");
			model.addAttribute("sucesso", "Dados atualizados com sucesso!");
			return atualizarFuncionarioLogado(login, model);
		}
		
		return "redirect:/funcionario/listar";
		
	}

	@RequestMapping(value = "/remover/{id}")
	public String removeFuncionario(@PathVariable("id") long id) {
		
		funcionarioService.removeFuncionario(id);

		return "redirect:/funcionario/listar";
	}

	@RequestMapping(value = "/telaAtualizar/{id}")
	public String atualizarFuncionario(@PathVariable("id") long id, Model model) {
		
		Funcionario funcionario = funcionarioService.getFuncionarioById(id);
		Usuario usuario = usuarioService.getUsuarioByFuncionario(funcionario);
		
		model.addAttribute("destino", 2); // depois de salvar vai para a lista de funcionarios
		model.addAttribute("destinoCancela", "/funcionario/listar");
		
		model.addAttribute("listaTipoFuncionarios", tipoFuncionarioService.listaTipoFuncionarioForDropDown());
		model.addAttribute("funcionario", funcionario);
		model.addAttribute("usuario", usuario);
		
		return "funcionario_atualizar";
	}

	@RequestMapping(value = "/perfil/{login}")
	public String atualizarFuncionarioLogado(@PathVariable("login") String login, Model model) {
		if (!"Admin".equals(login) && !"admin".equals(login)) {
			Funcionario funcionario = funcionarioService.getFuncionarioByLogin(login);
			Usuario usuario = usuarioService.getUsuarioByFuncionario(funcionario);
			
			model.addAttribute("destino", 1); // depois de salvar volta para a tela de atualização
			model.addAttribute("destinoCancela", "/home");
			
			model.addAttribute("listaTipoFuncionarios", tipoFuncionarioService.listaTipoFuncionarioForDropDown());
			model.addAttribute("funcionario", funcionario);
			model.addAttribute("usuario", usuario);
			
			return "funcionario_atualizar";
		} else {
			return "redirect:/home";
		}
	}
	
	@RequestMapping(value = "/telaInserir")
	public String telaInserirFuncionario(Model model) {
		
		model.addAttribute("destino", 2); // depois de salvar vai para a lista de funcionarios
		model.addAttribute("destinoCancela", "/funcionario/listar");
		
		model.addAttribute("listaTipoFuncionarios", tipoFuncionarioService.listaTipoFuncionarioForDropDown());
		model.addAttribute("funcionario", new Funcionario());
		return "funcionario_atualizar";
	}

	public class Papel {
		private String label;
		private String selected;
		
		public Papel(String label, String selected) {
			super();
			this.label = label;
			this.selected = selected;
		}

		public String getLabel() {
			return label;
		}
		
		public void setLabel(String label) {
			this.label = label;
		}
		
		public String getSelected() {
			return selected;
		}
		
		public void setSelected(String selected) {
			this.selected = selected;
		}
	}
}
