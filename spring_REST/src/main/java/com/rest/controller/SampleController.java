package com.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rest.dto.SampleVO;

@Controller
@RequestMapping("/sample")
public class SampleController {

	@RequestMapping("/jspPage")
	public String jspPage(Model model) throws Exception {

		SampleVO vo = new SampleVO("kim", "mimi");
		model.addAttribute("model", vo);

		return "jsp";
	}

	@RequestMapping("/sendJson")
	public void sendJson()throws Exception{}
	
	@RequestMapping("/sendJsonArray")
	public void sendJsonArray(){}
	
	@RequestMapping("/sendJsonJson")
	public void sendJsonJson(){}
	
	@RequestMapping("/sendXml")
	public void sendXml(){}
	
	
}







