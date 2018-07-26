package mvjsp.chap13.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mvjsp.chap13.exception.ServiceException;
import mvjsp.chap13.model.Member;
import mvjsp.chap13.model.MemberListJson;
import mvjsp.chap13.model.MemberListView;
import mvjsp.chap13.model.MemberListXml;
import mvjsp.chap13.service.MemberService;
import mvjsp.chap13.validator.MemberValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	MemberService memberSvc;

	public void setMemberSvc(MemberService memberSvc) {
		this.memberSvc = memberSvc;
	}
	
	
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String joinForm(@ModelAttribute("member") Member member) {
		String url = "joinForm";
		return url;
	}

	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(@ModelAttribute("member") Member member,
					Model model, BindingResult bindResult) {

		String url = "joinSuccess";

		new MemberValidator().validate(member, bindResult);

		if (bindResult.hasErrors()) {
			url = "joinForm";
		} else {
			String message = null;
			memberSvc.joinMember(member);
			message = "회원가입을 축하합니다.";
			model.addAttribute("message", message);
		}
		
		return url;
	}

	@RequestMapping(value = "/memberList")
	public String memberList(
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			Model model) {
		String url = "memberList";
		MemberListView viewData = null;

		try {
			viewData = memberSvc.getMemberList(pageNumber);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		model.addAttribute("viewData", viewData);
		model.addAttribute("pageNumber", pageNumber);

		return url;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginForm(@ModelAttribute("member") Member memberReq ) {
		return "loginForm";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute("member") Member memberReq,
			HttpSession session, Model model,BindingResult bindResult) {		
		String url = "loginSuccess";
		String message = null;
		new MemberValidator().validate(memberReq, bindResult);
		
		if(bindResult.hasErrors()){
			return "loginForm";
		}
		

		Member member = memberSvc.loginMember(memberReq.getId(), memberReq.getPwd());
		if (member != null) {
			if (member.getPwd().equals(memberReq.getPwd())) {
				message = "로그인 성공";
				session.setAttribute("loginUser", member);
			} else {
				message = "패스워드가 일치하지 않습니다.";
				url = "loginForm";
			}
		} else {
			message = "아이디가 존재하지 않습니다.";
			url = "loginForm";
		}

		model.addAttribute("message", message);
		return url;

	}

	@RequestMapping(value = "/logout")
	public String logout(HttpSession session, HttpServletRequest req) {
		session.invalidate();
		return "redirect:/index";
	}

	@RequestMapping(value = "/updateMember", method = RequestMethod.GET)
	public String updateMemberForm(@RequestParam(value = "memberId") String id,
			Model model) {
		String url = "updateMemberForm";

		Member member = memberSvc.getMember(id);

		model.addAttribute(member);

		return url;
	}

	@RequestMapping(value = "/updateMember", method = RequestMethod.POST)
	public String updateMember(Member member) {
		String url = "redirect:memberList";

		try {
			memberSvc.updateMember(member);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return url;
	}

	@RequestMapping(value = "/deleteMember", method = RequestMethod.GET)
	public String deleteMemberConfirm() {
		String url = "confirmAdmin";
		return url;
	}

	@RequestMapping(value = "/deleteMember", method = RequestMethod.POST)
	public String deleteMember(@RequestParam(value = "memberId") String id,
			@RequestParam(value = "password") String pwd, Model model) {

		String url = "deleteMember";

		Member admin = memberSvc.getMember("admin");

		boolean invalidPassowrd = false;

		if (pwd.equals(admin.getPwd())) {
			try {
				memberSvc.deleteMember(id);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			invalidPassowrd = true;
		}
		model.addAttribute("invalidPassowrd", invalidPassowrd);

		return url;
	}
	
	@RequestMapping("/memberListExcel")
	public String memberListExcel(@RequestParam(defaultValue="1") int pageNumber,
								  Model model){
		String url="memberListExcelView";
		
		try {
			MemberListView memberListView=memberSvc.getMemberList(pageNumber);
			List<Member> memberList=memberListView.getMemberList();
			model.addAttribute("memberList",memberList);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return url;
	}
	
	@RequestMapping("/memberListPdf")
	public String memberListPdf(@RequestParam(defaultValue="1") int pageNumber,
								  Model model){
		String url="memberListPdfView";
		
		try {
			MemberListView memberListView=memberSvc.getMemberList(pageNumber);
			List<Member> memberList=memberListView.getMemberList();
			model.addAttribute("memberList",memberList);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return url;
	}

	@RequestMapping("/memberList.xml")
	@ResponseBody
	public MemberListXml getMemberListXml(@RequestParam(defaultValue="1") 
											int pageNumber){
		MemberListXml memListXml=null;
		try {
			MemberListView memberListView=memberSvc.getMemberList(pageNumber);
			List<Member> memberList=memberListView.getMemberList();
			memListXml=new MemberListXml(memberList);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return memListXml;
		
	}
	
	@RequestMapping("/memberList.json")
	@ResponseBody
	public MemberListJson getMemberListJson(@RequestParam(defaultValue="1") 
											int pageNumber){
		MemberListJson memListJson=null;
		try {
			MemberListView memberListView=memberSvc.getMemberList(pageNumber);
			List<Member> memberList=memberListView.getMemberList();
			memListJson=new MemberListJson(memberList);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return memListJson;
		
	}
	
}








