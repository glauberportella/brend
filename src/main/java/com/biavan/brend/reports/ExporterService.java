package com.biavan.brend.reports;

import java.io.ByteArrayOutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;

@Service
public class ExporterService {
	public static final String MEDIA_TYPE_EXCEL = "application/vnd.ms-excel";
	public static final String MEDIA_TYPE_PDF = "application/pdf";
	public static final String EXTENSION_TYPE_EXCEL = "xls";
	public static final String EXTENSION_TYPE_PDF = "pdf";
	
	private String fileName;
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public HttpServletResponse export(String type, JasperPrint jp,
			HttpServletResponse response, ByteArrayOutputStream baos) {

		if (type.equalsIgnoreCase(EXTENSION_TYPE_EXCEL)) {
			// Export to output stream
			exportXls(jp, baos);

			// Set our response properties
			// Here you can declare a custom filename
			response.setHeader("Content-Disposition", "inline; filename="
					+ fileName + '.' + EXTENSION_TYPE_EXCEL);

			// Set content type
			response.setContentType(MEDIA_TYPE_EXCEL);
			response.setContentLength(baos.size());

			return response;
		}

		if (type.equalsIgnoreCase(EXTENSION_TYPE_PDF)) {
			// Export to output stream
			exportPdf(jp, baos);

			// Set our response properties
			// Here you can declare a custom filename
			response.setHeader("Content-Disposition", "inline; filename="
					+ fileName + '.' + EXTENSION_TYPE_PDF);

			// Set content type
			response.setContentType(MEDIA_TYPE_PDF);
			response.setContentLength(baos.size());

			return response;

		}

		throw new RuntimeException("No type set for type " + type);
	}

	public void exportXls(JasperPrint jp, ByteArrayOutputStream baos) {
		JRXlsExporter exporter = new JRXlsExporter();
		
		exporter.setExporterInput(new SimpleExporterInput(jp));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));
		
		SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
		configuration.setOnePagePerSheet(true);
		configuration.setDetectCellType(true);
		configuration.setCollapseRowSpan(false);
		exporter.setConfiguration(configuration);

		try {
			exporter.exportReport();

		} catch (JRException e) {
			throw new RuntimeException(e);
		}
	}

	public void exportPdf(JasperPrint jp, ByteArrayOutputStream baos) {
		JRPdfExporter exporter = new JRPdfExporter();

		exporter.setExporterInput(new SimpleExporterInput(jp));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));
		
		SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
		exporter.setConfiguration(configuration);

		try {
			exporter.exportReport();

		} catch (JRException e) {
			throw new RuntimeException(e);
		}
	}
}
