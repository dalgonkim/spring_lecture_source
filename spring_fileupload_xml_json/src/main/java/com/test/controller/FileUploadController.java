package com.test.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.test.dto.FileCommand;

@Controller
public class FileUploadController {
	
	/*
	private String uploadPath = System.getProperty("java.io.tmpdir"); //시스템임시폴더
	*/
	private String uploadPath = null;
	
	
	@RequestMapping(value="/upload/form",method=RequestMethod.GET)
	public String form(){
		return "upload/fileUploadForm";
	}
	
	@RequestMapping(value="/upload/multipartFile",method=RequestMethod.POST)
	public String uploadByMultipartFile(@RequestParam("f") MultipartFile multipartFile,
										@RequestParam("title") String title, 
										Model model,HttpServletRequest request) 
										throws IOException {
		if(multipartFile.getSize()>5248000){
			return "upload/error";
		}
		uploadPath=request.getSession().getServletContext()
				.getRealPath("resources/upload");
		
		if (!multipartFile.isEmpty()) {
			File file = new File(uploadPath, multipartFile.getOriginalFilename()
					+"$$"+System.currentTimeMillis());
			multipartFile.transferTo(file); // 실제저장.
			model.addAttribute("title", title);
			model.addAttribute("fileName", multipartFile.getOriginalFilename());
			model.addAttribute("uploadPath", file.getAbsolutePath());
			return "upload/fileUploaded";
		}
		return "upload/noUploadFile";
	}
	
	@RequestMapping(value="/upload/multipartHttpServletRequest", 
			method=RequestMethod.POST)
	public String uploadByMultipartHttpServletRequest(
					MultipartHttpServletRequest request, 
					Model model) throws IOException {
		
		uploadPath=request.getSession().getServletContext()
				.getRealPath("resources/upload");
		
		MultipartFile multipartFile = request.getFile("f");
		if (!multipartFile.isEmpty()) {
			File file = new File(uploadPath, multipartFile.getOriginalFilename());
			multipartFile.transferTo(file);
			model.addAttribute("title", request.getParameter("title"));
			model.addAttribute("fileName", multipartFile.getOriginalFilename());
			model.addAttribute("uploadPath", file.getAbsolutePath());
			return "upload/fileUploaded";
		}
		return "upload/noUploadFile";
	}
	
	@RequestMapping(value = "/upload/commandObject", method = RequestMethod.POST)
	public String uploadByCommandObject(			
			FileCommand command, 
			Model model, HttpServletRequest request) throws IOException {
		
		uploadPath=request.getSession().getServletContext()
				.getRealPath("resources/upload");
		
		MultipartFile multipartFile = command.getF();
		if (!multipartFile.isEmpty()) {
			File file = new File(uploadPath, multipartFile.getOriginalFilename());
			multipartFile.transferTo(file);
			model.addAttribute("title", command.getTitle());
			model.addAttribute("fileName", multipartFile.getOriginalFilename());
			model.addAttribute("uploadPath", file.getAbsolutePath());
			return "upload/fileUploaded";
		}
		return "upload/noUploadFile";
	}
	
}













