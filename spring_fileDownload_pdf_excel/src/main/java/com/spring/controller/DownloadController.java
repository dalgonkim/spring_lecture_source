package com.spring.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DownloadController {
	
	@RequestMapping("/file")
	public ModelAndView download(@RequestParam("file") String fileName,
								 HttpServletResponse response,
								 HttpServletRequest request)
										throws Exception{
		
		String baseDir=request.getSession().getServletContext()
				.getRealPath("/WEB-INF/files");		
		File downloadFile=new File(baseDir,fileName);		
				
		if(downloadFile.exists()){
			return new ModelAndView("download","downloadFile",downloadFile);
		}		
		response.sendError(HttpServletResponse.SC_NOT_FOUND);
		return null;
	}
}









