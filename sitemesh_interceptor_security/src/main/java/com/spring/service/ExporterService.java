package com.spring.service;

import java.io.ByteArrayOutputStream;

import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;

public class ExporterService {
	
	public static final String MEDIA_TYPE_EXCEL="application/vnd.ms-excel";
	public static final String MEDIA_TYPE_PDF="application/pdf";
	public static final String EXTENSION_TYPE_EXCEL="xls";
	public static final String EXTENSION_TYPE_PDF="pdf";
	
	public HttpServletResponse export(String type, 
									  JasperPrint jp,
									  HttpServletResponse response,
									  ByteArrayOutputStream baos) {
		
		if(type.equalsIgnoreCase(EXTENSION_TYPE_EXCEL)) {
			exportXls(jp,baos);
			
			String fileName="MemberList.xls";
			response.setHeader("Content-Disposition", 
					"inline; filename="+fileName);
			response.setContentType(MEDIA_TYPE_EXCEL);
			response.setContentLength(baos.size());
			
			return response;
			
		}
		if(type.equalsIgnoreCase(EXTENSION_TYPE_PDF)) {
			exportPdf(jp,baos);
			
			String fileName="MemberList.pdf";
			response.setHeader("Content-Disposition", 
					"inline; filename="+fileName);
			response.setContentType(MEDIA_TYPE_PDF);
			response.setContentLength(baos.size());
			
			return response;
		}	
		
		throw new RuntimeException("No type set for type "+type);
		
	}
	
	public void exportXls(JasperPrint jp, 
						  ByteArrayOutputStream baos) {
		JRXlsExporter exporter=new JRXlsExporter();
		exporter.setExporterInput(new SimpleExporterInput(jp));
		exporter.setExporterOutput(
				new SimpleOutputStreamExporterOutput(baos));
		SimpleXlsReportConfiguration configuration=
				new SimpleXlsReportConfiguration();
		configuration.setOnePagePerSheet(true);
		exporter.setConfiguration(configuration);
		try {
			exporter.exportReport();
		}catch(JRException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void exportPdf(JasperPrint jp, 
			  			  ByteArrayOutputStream baos) {
		
		JRPdfExporter exporter=new JRPdfExporter();
		
		exporter.setExporterInput(new SimpleExporterInput(jp));
		exporter.setExporterOutput(
				new SimpleOutputStreamExporterOutput(baos));
		
		SimplePdfReportConfiguration configuration=				
				new SimplePdfReportConfiguration();		
		exporter.setConfiguration(configuration);
		
		try {
			exporter.exportReport();
		}catch(JRException e) {
			throw new RuntimeException(e);
		}
		
	}
}








