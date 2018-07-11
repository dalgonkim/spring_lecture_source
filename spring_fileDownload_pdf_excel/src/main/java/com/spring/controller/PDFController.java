package com.spring.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.dto.MemberVO;
import com.spring.service.MemberService;
import com.spring.service.MemberServiceImpl;

@Controller
public class PDFController {
	
	private MemberService service= new MemberServiceImpl();
	
	@RequestMapping("/sboard/listPage/pdf")
	public ModelAndView listPdfView(ModelAndView modelnView)throws Exception{
		
		List<MemberVO> memberList=service.getMemberList();
		
		modelnView.setViewName("memberListPdf");
		modelnView.addObject("memberList",memberList);
		
		return modelnView;
	}
	
}







