package com.nonage.controller.admin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nonage.dto.QnaVO;
import com.nonage.service.QnaService;

@Controller
@RequestMapping("/admin")
public class AdminQnaController {

	@Autowired
	QnaService qnaService;

	@RequestMapping("qnaList.do")
	public String adminQnaList(Model model) throws ServletException, IOException {

		String url = "admin/qna/qnaList";
		ArrayList<QnaVO> qnaList=qnaService.getQnaList();
		model.addAttribute("qnaList", qnaList);
		return url;
	}

	@RequestMapping("/qnaDetail.do")
	public String adminQnaDetail(@RequestParam("qseq") String qseq, Model model) throws ServletException, IOException {

		String url = "admin/qna/qnaDetail";
		QnaVO qnaVO=qnaService.getQnaDetail(qseq);		
		model.addAttribute("qnaVO", qnaVO);
		return url;
	}

	@RequestMapping("/qnaResave.do")
	public String adminQnaResave(@RequestParam("qseq")String qseq,
								 @RequestParam("reply")String reply,Model model)
			throws ServletException, IOException {

		String url = "qnaList.do";
		QnaVO qnaVO = new QnaVO();
		qnaVO.setQseq(Integer.parseInt(qseq));
		qnaVO.setReply(reply);
		qnaService.updateQnaVO(qnaVO);

		return url;
	}
}
