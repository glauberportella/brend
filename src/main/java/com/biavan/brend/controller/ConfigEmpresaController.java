package com.biavan.brend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biavan.brend.model.ConfigEmpresa;
import com.biavan.brend.service.ConfigEmpresaService;

@Controller
@RequestMapping("/empresa")
public class ConfigEmpresaController {

	private ConfigEmpresaService configEmpresaService;
	
	@Autowired(required = true)
	@Qualifier(value = "configEmpresaService")
	public void setConfigEmpresaService(ConfigEmpresaService configEmpresaService) {
		this.configEmpresaService = configEmpresaService;
	}
	
	@RequestMapping(value = "/atualizar", method = RequestMethod.POST)
	public String alteraConfigEmpresa(
			@ModelAttribute("configEmpresa") ConfigEmpresa configEmpresa, Model model) {
		
		this.configEmpresaService.atualizaConfigEmpresa(configEmpresa);
	
		model.addAttribute("sucesso", "Configurações da empresa atualizado com sucesso!");
		model.addAttribute("configEmpresa", this.configEmpresaService.getConfigEmpresaById(1));
		return "empresa_atualizar";
	}

	@RequestMapping(value = "/telaAtualizar")
	public String atualizarConfigEmpresa(Model model) {
		model.addAttribute("configEmpresa", this.configEmpresaService.getConfigEmpresaById(1));
		return "empresa_atualizar";
	}

}
