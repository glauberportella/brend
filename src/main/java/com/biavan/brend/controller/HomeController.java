package com.biavan.brend.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biavan.brend.model.Funcionario;
import com.biavan.brend.model.InformacaoStatusOrdemServico;
import com.biavan.brend.plugin.chart.ChartData;
import com.biavan.brend.plugin.chart.ChartUtil;
import com.biavan.brend.plugin.chart.Chartable;
import com.biavan.brend.plugin.chart.PieData;
import com.biavan.brend.service.FuncionarioService;
import com.biavan.brend.service.OrdemServicoService;
import com.biavan.brend.service.StatusOrdemServicoService;
import com.biavan.brend.util.Formatos;

@Controller
public class HomeController {

	private FuncionarioService funcionarioService;
	private StatusOrdemServicoService statusOrdemServicoService;
	private OrdemServicoService ordemServicoService;
	
	@Autowired
	@Qualifier(value = "funcionarioService")
	public void setFuncionarioService(FuncionarioService funcionarioService) {
		this.funcionarioService = funcionarioService;
	}
	
	@Autowired
	@Qualifier(value = "statusOrdemServicoService")
	public void setStatusOrdemServicoService(StatusOrdemServicoService statusOrdemServicoService) {
		this.statusOrdemServicoService = statusOrdemServicoService;
	}
	
	@Autowired
	@Qualifier(value = "ordemServicoService")
	public void setOrdemServicoService (OrdemServicoService ordemServicoService) {
		this.ordemServicoService = ordemServicoService;
	}
		
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {

		ChartData chartData = new ChartData();
		chartData.setChartId("pie-chart");
		chartData.setType("Pie");
		
		List<InformacaoStatusOrdemServico> listaInfo = statusOrdemServicoService.percentuais();

		List<Chartable> dadosChart = new ArrayList<Chartable>();

		for(InformacaoStatusOrdemServico info : listaInfo) {
			Chartable dado = new PieData();
			try {
				dado.setValue(Formatos.formatDecimal(info.getPercentual()));
			} catch (NumberFormatException e) {
				dado.setValue(0);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			dado.setLabel(info.getStatusOS().toString());
			dado.setColor(info.getCor());
			dado.setHighlight(info.getDestaque());
			
			dadosChart.add(dado);
			
		}

		chartData.setListData(dadosChart);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    Funcionario funcionario = funcionarioService.getFuncionarioByLogin(auth.getName());
	    
	    if (funcionario != null && funcionario.getTipoFuncionario().isMecanico()) {
	    	model.addAttribute("isMecanico", true);
	    	model.addAttribute("qtdeByMecanico", ordemServicoService.qtdeOrdemServicoByMecanico(funcionario));
	    	model.addAttribute("mecanico", funcionario);
	    }
		
		
		model.addAttribute("renderJS", ChartUtil.renderJS(chartData));
		
		model.addAttribute("listaInfo", listaInfo);
		
		return "home";
	}
	
}
