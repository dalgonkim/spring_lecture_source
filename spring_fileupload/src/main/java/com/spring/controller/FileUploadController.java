package com.spring.controller;

import java.io.File;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
@RequestMapping("/")
public class FileUploadController {
		
	private String uploadPath=null;
	
	@RequestMapping(value="/multipartFile",method=RequestMethod.POST)
	public String uploadByMultipartFile(@RequestParam("title")String title,
										@RequestParam("file")MultipartFile multi,
										HttpServletRequest request,
										HttpServletResponse response,
										Model model)throws Exception{
		
		/*파일유무확인*/
		if(multi.isEmpty()){
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out=response.getWriter();			
			out.println("<script>alert('파일이 없습니다.!');</script>");
			out.println("<script>history.go(-1);</script>");
			return null;			
		}
		
		
		/*용량제한 5MB*/
		if(multi.getSize()>1024*1024*5){
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out=response.getWriter();			
			out.println("<script>alert('용량초과 입니다!');</script>");
			out.println("<script>history.go(-1);</script>");
			return null;
		 }
		 
		/*파일저장*/
		uploadPath=request.getSession().getServletContext()
				.getRealPath("resources/upload");
		
		/*파일명 중복방지*/
		String fileName=UUID.randomUUID().toString().replace("-", "")
						+"$$"+multi.getOriginalFilename();
		
		
		File file=new File(uploadPath,fileName);
		multi.transferTo(file);
		
		model.addAttribute("title",title);
		model.addAttribute("fileName",multi.getOriginalFilename());
		model.addAttribute("uploadPath",file.getAbsolutePath());
		
		return "fileUploaded";
	}
	
	
	@RequestMapping(value="/multipartHttpServletRequest",
					method=RequestMethod.POST)
	public String uploadByMultipartHttpServletRequest(
			MultipartHttpServletRequest request,
			HttpServletResponse response,
			Model model)
					throws Exception{
		
		String title=request.getParameter("title");
		MultipartFile multi=request.getFile("file");
		
		/*용량제한 5MB*/
		if(multi.getSize()>1024*1024*5){
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out=response.getWriter();			
			out.println("<script>alert('용량초과 입니다!');</script>");
			out.println("<script>history.go(-1);</script>");
			return null;
		 }
		 
		/*파일저장*/
		uploadPath=request.getSession().getServletContext()
				.getRealPath("resources/upload");
		
		/*파일명 중복방지*/
		String fileName=UUID.randomUUID().toString().replace("-", "")
						+"$$"+multi.getOriginalFilename();
		
		
		File file=new File(uploadPath,fileName);
		multi.transferTo(file);
		
		model.addAttribute("title",title);
		model.addAttribute("fileName",multi.getOriginalFilename());
		model.addAttribute("uploadPath",file.getAbsolutePath());
		
		return "fileUploaded";
		
	}
	
	@RequestMapping(value="/commandObject",method=RequestMethod.POST)
	public String uploadByCommandObject(FileCommand command,
										HttpServletRequest request,
										HttpServletResponse response,
										Model model)
											throws Exception{
		
		MultipartFile multi=command.getFile();
		String title = command.getTitle();
		
		/*용량제한 5MB*/
		if(multi.getSize()>1024*1024*5){
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out=response.getWriter();			
			out.println("<script>alert('용량초과 입니다!');</script>");
			out.println("<script>history.go(-1);</script>");
			return null;
		 }
		 
		/*파일저장*/
		uploadPath=request.getSession().getServletContext()
				.getRealPath("resources/upload");
		
		/*파일명 중복방지*/
		String fileName=UUID.randomUUID().toString().replace("-", "")
						+"$$"+multi.getOriginalFilename();
		
		
		File file=new File(uploadPath,fileName);
		multi.transferTo(file);
		
		model.addAttribute("title",title);
		model.addAttribute("fileName",multi.getOriginalFilename());
		model.addAttribute("uploadPath",file.getAbsolutePath());
		
		return "fileUploaded";
	}
}









