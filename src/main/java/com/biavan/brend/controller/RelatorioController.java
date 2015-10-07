package com.biavan.brend.controller;

import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.biavan.brend.reports.ExporterService;
import com.biavan.brend.reports.PrintReportService;
import com.biavan.brend.service.PecaService;

@Controller
@RequestMapping("/relatorio")
public class RelatorioController {

	private PrintReportService printReportService;
	private PecaService pecaService;

	@Autowired(required = true)
	@Qualifier(value = "printReportService")
	public void setDownloadService(PrintReportService printReportService) {
		this.printReportService = printReportService;
	}

	@Autowired(required = true)
	@Qualifier(value = "pecaService")
	public void setPecaService(PecaService pecaService) {
		this.pecaService = pecaService;
	}

	@RequestMapping(value = "/estoque")
	public void download(HttpServletResponse response) {
		printReportService.printReport("Estoque de peças", "estoque_de_pecas",
				ExporterService.EXTENSION_TYPE_PDF, response,
				new JRBeanCollectionDataSource(pecaService.listaPecas()));

	}
}
