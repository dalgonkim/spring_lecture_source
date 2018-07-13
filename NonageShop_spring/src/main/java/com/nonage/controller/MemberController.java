package com.nonage.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nonage.dao.MemberDAO;
import com.nonage.dto.MemberVO;
import com.nonage.dto.OrderVO;
import com.nonage.mail.AbstractMailSender;
import com.nonage.mail.MailRequest;
import com.nonage.service.CommonService;
import com.nonage.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private MemberService memberService;

	@Autowired
	private CommonService commonService;

	@Autowired
	MemberDAO memberDAO;

	@RequestMapping("/loginForm.do")
	public String loginForm() {
		String url = "member/login";
		return url;
	}

	@RequestMapping("/login.do")
	public String login(@RequestParam String id, @RequestParam String pwd,
			HttpSession session, Model model) {
		String url = "redirect:login_success.do";
		String message = "";
		MemberVO member = null;
		try {
			member = memberDAO.getMember(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (member != null) {
			if (!member.getPwd().equals(pwd)) {
				url = "redirect:login_fail.do";
				message = "패스워드가 일치하지 않습니다.";
			} else {
				session.setAttribute("loginUser", member);
				message = "로그인에 성공했습니다.";
			}
		} else {
			url = "redirect:login_fail.do";
			message = "아이디가 존재하지 않습니다.";
		}

		model.addAttribute("message", message);
		return url;
	}

	// spring security : defualt-url : login_success.do
	@RequestMapping("/login_success.do")
	public String loginSuccess(Authentication auth, HttpSession session) {
		String url = "redirect:/main/index.do";
		
		auth=SecurityContextHolder.getContext().getAuthentication();
		String id=auth.getName();
		
		MemberVO member=memberService.getMember(id);
		session.setAttribute("loginUser",member);
		
		String loginId=(String)session.getAttribute("id");
		
		if (loginId!=null) session.removeAttribute("id");
		
		return url;
	}

	@RequestMapping("/login_fail.do")
	public String loginFail(HttpSession session,HttpServletRequest request) {
		String url = "member/login_fail";
		String id=request.getParameter("id");
		//session.setAttribute("id",id);
		System.out.println(id);
		return url;
	}

	@RequestMapping("/logout.do")
	public String logout(HttpSession session) {
		String url = "redirect:/main/index.do";
		session.invalidate();
		return url;
	}

	@RequestMapping("/joinForm.do")
	public String joinForm() {
		String url = "member/join";
		return url;
	}

	@RequestMapping("/join.do")
	public String join(MemberVO memberVO, HttpServletRequest request) {
		String url = "redirect:loginForm.do";

		memberVO.setAddress(request.getParameter("addr1")
				+ request.getParameter("addr2"));
		memberVO.setAuthority("ROLE_USER");
		commonService.joinMember(memberVO);

		HttpSession session = request.getSession();
		session.setAttribute("id", memberVO.getId());

		return url;
	}

	@RequestMapping("/contract.do")
	public String contract() {
		String url = "/member/contract";
		return url;
	}

	@RequestMapping("/mypage.do")
	public String mypage(HttpSession session, Model model) {
		String url = "mypage/mypage";
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		if (loginUser == null) {
			url = "redirect:loginForm.do";
		} else {
			ArrayList<OrderVO> orderList = memberService.getOrderList(loginUser
					.getId());
			model.addAttribute("title", "진행중인 주문 내역");
			model.addAttribute("orderList", orderList);
		}
		return url;
	}
	
	@RequestMapping(value="/mailToAdmin.do",method=RequestMethod.GET)
	public String mailToAdminForm(Model model){
		String url="mypage/mailForm";
		model.addAttribute("title","문의하기");
		return url;
	}
	
	@Autowired
	AbstractMailSender sender;
	
	@RequestMapping(value="/mailToAdmin.do",method=RequestMethod.POST)
	public String mailToAdmin(MailRequest mail,HttpSession session){
		String url="redirect:mypage/mypage.do";
		
		MemberVO member=(MemberVO)session.getAttribute("loginUser");
		
		sender.sendMail(member, mail);
		
		return url;
	}
}







