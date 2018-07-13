package com.nonage.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nonage.dto.MemberVO;
import com.nonage.dto.QnaVO;
import com.nonage.service.MemberService;

@Controller
@RequestMapping("/qna")
public class QnaController {

	@Autowired
	MemberService memberService;

	@RequestMapping("/qnaList.do")
	public String qnaList(HttpSession session, Model model) throws ServletException, IOException {
		String url = "qna/qnaList";

		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		ArrayList<QnaVO> qnaList = memberService.getQnaList(loginUser.getId());
		model.addAttribute("qnaList", qnaList);

		return url;
	}

	@RequestMapping("/qnaView.do")
	public String qnaView(@RequestParam("qseq") int qseq, HttpSession session, Model model)
			throws ServletException, IOException {

		String url = "qna/qnaView";
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

		QnaVO qnaVO = memberService.getQnaVO(qseq);
		model.addAttribute("qnaVO", qnaVO);

		return url;
	}

	@RequestMapping("/qnaWriteForm.do")
	public String qnaWriteForm(HttpSession session) throws ServletException, IOException {
		String url = "qna/qnaWrite";

		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

		return url;
	}

	@RequestMapping("/qnaWrite.do")
	public String qnaWrite(@RequestParam("subject") String subject, @RequestParam("content") String content,
			HttpSession session) throws ServletException, IOException {
		String url = "redirect:qnaList.do";

		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		QnaVO qnaVO = new QnaVO();
		qnaVO.setSubject(subject);
		qnaVO.setContent(content);
		memberService.insertQna(qnaVO, loginUser.getId());

		return url;
	}
}
