package com.test.board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.board.dto.MemberVO;
import com.test.board.mail.SimpleRegistrationNotifier;
import com.test.board.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private MemberService memberSvc;
	@Autowired
	private SimpleRegistrationNotifier mailSender;

	@RequestMapping("memberList")
	public String getMemberList(@RequestParam(value = "name", defaultValue = "") String name, Model model) {
		String url = "member/memberList";
		List<MemberVO> memberList = null;
		try {
			memberList = memberSvc.getMemberList(name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		model.addAttribute("memberList", memberList);
		return url;
	}

	@RequestMapping("/detailMember")
	public String detailMember(@RequestParam String userid, Model model) {
		String url = "member/detailMember";
		MemberVO member = null;
		try {
			member = (MemberVO) memberSvc.getMember(userid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		model.addAttribute("member", member);
		return url;
	}

	/*@RequestMapping("/login_success")
	public String loginSuccess(HttpSession session, Authentication auth) {
		String url = "redirect:/index";

		String userid = auth.getName();

		MemberVO member = null;
		try {
			member = memberSvc.getMember(userid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		session.setAttribute("loginUser", member);

		return url;

	}*/

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		String url = "redirect:/index";
		session.invalidate();
		return url;
	}

	@RequestMapping("/loginForm")
	public String loginForm(HttpServletRequest request,HttpSession session) {
		String url = "member/loginForm";	
		return url;
	}

	
	@RequestMapping("/searchMember")
	public String searchMember(@RequestParam String key, Model model) {
		String url = "member/memberList";
		List<MemberVO> memberList = null;
		try {
			memberList = memberSvc.getMemberList(key);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		model.addAttribute("memberList", memberList);
		return url;
	}

	@RequestMapping(value = "/addMember", method = RequestMethod.GET)
	public String addMemberForm() {
		String url = "member/joinForm";
		return url;
	}

	@RequestMapping(value = "/addMember", method = RequestMethod.POST)
	public String addMember(MemberVO member, Model model) {
		String url = "redirect:memberList";
		try {
			memberSvc.insertMember(member);
		} catch (SQLException e) {
			e.printStackTrace();
			url = "error/error";
			model.addAttribute("message", "회원 등록에 실패했습니다.");
			model.addAttribute("url", "main/index");
		}
		return url;
	}

	@RequestMapping(value = "/updateMember", method = RequestMethod.GET)
	public String updateMemberForm(@RequestParam String userid, Model model) {
		String url = "member/updateForm";
		MemberVO member = null;
		try {
			member = memberSvc.getMember(userid);

		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		String[] authArr = { "ROLE_USER", "ROLE_ADMIN" };
		int authNum = 0;
		for (int i = 0; i < authArr.length; i++) {
			if (authArr[i].equals(member.getAuthority()))
				authNum = i;
		}
		model.addAttribute("authNum", authNum);
		model.addAttribute("member", member);
		return url;
	}

	@RequestMapping(value = "/updateMember", method = RequestMethod.POST)
	public String updateMember(MemberVO member) {
		String url = "redirect:memberList";
		try {
			memberSvc.updateMember(member);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return url;
	}

	@RequestMapping("/deleteMember")
	public String deleteMember(@RequestParam String userid) {
		String url = "redirect:memberList";
		try {
			memberSvc.deleteMember(userid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return url;
	}

	@RequestMapping(value = "/mailToMember")
	public String mailToMember(@RequestParam(value = "userid") List<String> useridList, Model model) {
		String url = "member/mailForm";
		List<MemberVO> memberList = new ArrayList<MemberVO>();
		for (String userid : useridList) {
			try {
				memberList.add(memberSvc.getMember(userid));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		model.addAttribute("memberList", memberList);
		return url;
	}

	@RequestMapping(value = "/sendToMember", method = RequestMethod.POST)
	public void mailToMember(MailRequest mailReq, HttpServletResponse res) {
		String url = "redirect:/member/memberList";
		res.setContentType("text/html;charset=utf-8");
		mailSender.sendMail(mailReq);
		PrintWriter out = null;
		try {
			out = res.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.println("<script>alert('메일을 보냈습니다.');location.href='memberList';</script>");
	}
}
