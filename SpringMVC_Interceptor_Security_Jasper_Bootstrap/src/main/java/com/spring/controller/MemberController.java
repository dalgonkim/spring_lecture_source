package com.spring.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.dto.MemberVO;
import com.spring.service.DownloadService;
import com.spring.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private MemberService service;
	
	@Autowired
	private DownloadService downloadService;
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public void list(Model model)throws Exception{
		List<MemberVO> memberList=service.getMemberList();
		model.addAttribute("memberList",memberList);		
	}
	
	@RequestMapping(value="/register",method=RequestMethod.GET)
	public void register()throws Exception{}
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public String register(MemberVO member)throws Exception{
	
		String view="redirect:/member/list";
		
		service.register(member);
			
		return view;
	}
	
	@RequestMapping(value="/detail",method=RequestMethod.GET)
	public void detail(String id,Model model)throws Exception{
		MemberVO member = service.getMemberById(id);
		model.addAttribute("member",member);
	}
	
	@RequestMapping(value="/modify",method=RequestMethod.GET)
	public void modify(String id,Model model)throws Exception{
		MemberVO member = service.getMemberById(id);
		model.addAttribute("member",member);
	}
	
	@RequestMapping(value="/modify",method=RequestMethod.POST)
	public String modify(MemberVO member)throws Exception{
		String view="redirect:/member/list";
		service.modify(member);
		return view;
	}
	
	@RequestMapping(value="/remove",method=RequestMethod.POST)
	public String remove(String id)throws Exception{
		String view="redirect:/member/list";
		service.remove(id);
		return view;
	}
	
	@RequestMapping(value="/download/{type}",
					method=RequestMethod.GET)
	public void jasperReport(@PathVariable String type,
			@RequestParam(defaultValue="")String id,
			HttpServletRequest request,
			HttpServletResponse response)
					throws Exception{
		
		String imagePath="";
		if(!id.isEmpty()) {
			imagePath=request.getSession().getServletContext().
					getRealPath("/resources/images/");
			imagePath+="1.jpg";
		}
		
		downloadService.download(type, id, imagePath, response);
	}
	
	@RequestMapping(value="/jaejik/{type}",method=RequestMethod.GET)
	public String jaejik(@PathVariable String type,
						 @RequestParam String id,
						 HttpServletRequest request,
						 HttpServletResponse response,
						 Model model) throws Exception{
		
		model.addAttribute("type",type);
		model.addAttribute("id",id);
		
		String imagePath=request.getSession().getServletContext().
				getRealPath("/resources/images/jaejik.jpg");
		
		model.addAttribute("imagePath",imagePath);
		
		
		return "jaejikReportView";
	}
}















