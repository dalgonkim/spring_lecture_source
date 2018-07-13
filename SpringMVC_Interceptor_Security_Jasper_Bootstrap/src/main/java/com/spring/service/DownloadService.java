package com.spring.service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class DownloadService {
	
	protected static Logger logger=
			Logger.getLogger(DownloadService.class);
	
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
		this.dataSource=dataSource;
	}
	
	private ExporterService exporter;
	public void setExporter(ExporterService exporter) {
		this.exporter=exporter;
	}
	
	public void download(String type, String id,String imagePath,
						 HttpServletResponse response)
							throws Exception{
		String jrFileName=null;
		
		try {
			//1. 파라메터 추가
			Map<String,Object> params=new HashMap<String,Object>();
			params.put("imagePath", imagePath);
			if(!id.isEmpty()) {
				params.put("id",id);
				jrFileName="memberDetail_report.jrxml";
			}else {
				jrFileName="memberList_report.jrxml";
			}
			//2. jrxml 템플릿 결정/로딩
			InputStream	reportStream=
			this.getClass().getResourceAsStream("/jrxml/"+jrFileName);
			//3. 템플릿을 JasperDesign으로 변환
			JasperDesign jd=JRXmlLoader.load(reportStream);
			
			//4. jrxml 컴파일
			JasperReport jr=JasperCompileManager.compileReport(jd);
			
			//5. JasperPrint 생성
			JasperPrint jp=JasperFillManager.fillReport(
					jr,params,dataSource.getConnection());
			//6. outputstream 생성
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			
			//7.Exporter 지정
			exporter.export(type, jp, response, baos);
			
			//8.response 이용한 전송.
			write(response,baos);
			
		}catch(JRException jre) {
			logger.error("Unable to process download");
			throw new RuntimeException(jre);
		}
		
	}
	
	private void write(HttpServletResponse response,
					   ByteArrayOutputStream baos) {
		try {
			
			logger.debug(baos.size());
			
			ServletOutputStream outputStream=
					response.getOutputStream();
			baos.writeTo(outputStream);
			outputStream.flush();
			
		}catch(Exception e) {
			logger.error("Unable to write report to the"
					+ " output steam");
			throw new RuntimeException(e);
		}
	}
	
	
}










