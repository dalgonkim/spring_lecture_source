package com.nonage.controller.admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nonage.dto.MemberVO;
import com.nonage.service.MemberService;

@Controller
@RequestMapping("/admin")
public class AdminMemberController {
	
	@Autowired
	MemberService memberService;
	
	@RequestMapping(value = "/memberList.do")
	public String adminMemberList(@RequestParam(value = "key", defaultValue = "") String key, Model model)
			throws ServletException, IOException {

		String url = "admin/member/memberList";

		ArrayList<MemberVO> memberList = memberService.getMemberList(key);				

		model.addAttribute("memberList", memberList);

		return url;
	}

}
