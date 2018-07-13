package com.nonage.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nonage.dto.AddressVO;
import com.nonage.service.CommonService;

@Controller
public class CommonController {

	@Autowired
	CommonService commonService;	
	
	@RequestMapping("/main/index.do")
	public String main(Model model) {
		String url = "index";
		Map<String, ArrayList> result = commonService.getNewBestList();

		model.addAllAttributes(result);
		return url;
	}
	
	@RequestMapping("/idCheckForm.do")
	public String idCheckForm(@RequestParam(value="id",defaultValue="")String id,
						  Model model)
			throws ServletException, IOException {
		String url = "/member/idcheck";		
		
		int message=commonService.idCheck(id);
		
		model.addAttribute("message", message);
		model.addAttribute("id", id);
		
		return url;
	}
	
	@RequestMapping("/findZipNum.do")
	public String findZipNum(@RequestParam(value="dong",defaultValue="대흥동")String dong,
							 HttpServletRequest request,
							 HttpServletResponse response,
							 Model model)
									 throws ServletException, IOException {

		String url = "member/findZipNum";		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		ArrayList<AddressVO> addressList=commonService.getFindZipNum(dong);
		
		model.addAttribute("addressList",addressList);
		
		return url;
	}
	
	
}










