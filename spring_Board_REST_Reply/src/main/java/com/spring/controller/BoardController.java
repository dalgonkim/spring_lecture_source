package com.spring.controller;

import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.dto.BoardVO;
import com.spring.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService service;

	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	public void listAll(Model model) throws Exception {
		List<BoardVO> boardList = service.listAll();
		model.addAttribute("boardList", boardList);
	}

	@RequestMapping(value = "/listCri", method = RequestMethod.GET)
	public void listCri(Criteria cri, Model model) throws Exception {
		List<BoardVO> boardList = service.listCriteria(cri);
		model.addAttribute("list", boardList);
	}

	@RequestMapping(value = "/listPage", method = RequestMethod.GET)
	public void listPage(Criteria cri, Model model) throws Exception {
		List<BoardVO> boardList = service.listCriteria(cri);

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		int totalCount = service.listAll().size();
		pageMaker.setTotalCount(totalCount);

		model.addAttribute("list", boardList);
		model.addAttribute("pageMaker", pageMaker);
	}

	@RequestMapping(value = "/readPage", method = RequestMethod.GET)
	public void readPage(@ModelAttribute("cri") Criteria cri, int bno, Model model) throws Exception {
		BoardVO board = service.read(bno);
		model.addAttribute(board);
	}

	@RequestMapping(value = "/modifyPage", method = RequestMethod.GET)
	public void modifyPage(@ModelAttribute("cri") Criteria cri,
			@RequestParam(value = "bno", defaultValue = "-1") int bno, Model model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		if (bno > 0) {
			BoardVO board = service.readByBno(bno);
			request.setAttribute("boardVO", board);
			request.setAttribute("cri", cri);
			request.getRequestDispatcher("/WEB-INF/views/board/modifyPage.jsp").forward(request, response);
		} else {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.write("<script>");
			out.write("alert('게시물이 존재하지않습니다.');");
			out.println("history.go(-1);");
			out.write("</script>");
		}

	}

	@RequestMapping(value = "/modifyPage", method = RequestMethod.POST)
	public String modifyPagePOST(BoardVO board, Criteria cri,
									RedirectAttributes rttr) throws Exception {

		
		board.setUpdatedate(new Date());
		
		service.modify(board);

		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addFlashAttribute("msg", "SUCCESS");
		
		return "redirect:/board/listPage";
	}
	
	@RequestMapping(value="/removePage",method=RequestMethod.POST)
	public String removePage(int bno,RedirectAttributes rttr,
							 Criteria cri) throws Exception{
		service.remove(bno);
		
		rttr.addAttribute("page",cri.getPage());
		rttr.addAttribute("perPageNum",cri.getPerPageNum());
		rttr.addFlashAttribute("msg","SUCCESS");
		
		return "redirect:/board/listPage";		
	}
}
