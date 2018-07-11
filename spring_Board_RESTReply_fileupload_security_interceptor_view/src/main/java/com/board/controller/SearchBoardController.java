package com.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.board.dto.BoardVO;
import com.board.dto.PageMaker;
import com.board.dto.SearchCriteria;
import com.board.service.BoardService;

@Controller
@RequestMapping("/sboard")
public class SearchBoardController {
	
	@Autowired
	private BoardService service;
	
	@RequestMapping(value="/listPage",method=RequestMethod.GET)
	public void listPage(@ModelAttribute("cri")SearchCriteria cri,
						 Model model) throws Exception{
		List<BoardVO> boardList=service.listSearch(cri);
		model.addAttribute("list",boardList);
		
		PageMaker pageMaker=new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.readSearchBoardCount(cri));
		model.addAttribute(pageMaker);
	}
	@RequestMapping(value="/listPage/pdf",method=RequestMethod.GET)
	public ModelAndView listPagePDF(@ModelAttribute("cri")SearchCriteria cri,
						 ModelAndView modelnView) throws Exception{
		List<BoardVO> boardList=service.listSearch(cri);
		
		modelnView.addObject("boardList",boardList);
		modelnView.setViewName("boardListPdf");
		
		return modelnView;
	}
	@RequestMapping(value="/listPage/excel",method=RequestMethod.GET)
	public ModelAndView listPageExcel(@ModelAttribute("cri")SearchCriteria cri,
						 ModelAndView modelnView) throws Exception{
		List<BoardVO> boardList=service.listSearch(cri);
		
		modelnView.addObject("boardList",boardList);
		modelnView.setViewName("boardListExcel");
		
		return modelnView;
	}
	
	@RequestMapping(value="/readPage",method=RequestMethod.GET)
	public void readPage(@ModelAttribute("cri")SearchCriteria cri,
						 int bno, Model model)throws Exception{
		BoardVO board=service.read(bno);
		model.addAttribute(board);
	}
	
	@RequestMapping(value="/modifyPage",method=RequestMethod.GET)
	public void modifyPage(@ModelAttribute("cri")SearchCriteria cri,
							int bno, Model model)throws Exception{
		 BoardVO board=service.readByBno(bno);
		 model.addAttribute(board);
	}
	
	@RequestMapping(value="/modifyPage",method=RequestMethod.POST)
	public String modfifyPagePOST(BoardVO board,
								  @ModelAttribute("cri") SearchCriteria cri,
								  Model model)
									throws Exception{
		service.modify(board);
		
		model.addAttribute("bno",board.getBno());
		model.addAttribute("msg","SUCCESS");
		
		return "sboard/taskSuccess";
	}
	
	@RequestMapping(value="/removePage",method=RequestMethod.POST)
	public String removePage(int bno,
							@ModelAttribute("cri") SearchCriteria cri,
							Model model)
							throws Exception{
		service.remove(bno);
		model.addAttribute("bno",bno);
		model.addAttribute("msg","SUCCESS");		
		return "sboard/taskSuccess";
	}
	
	@RequestMapping(value="/register",method=RequestMethod.GET)
	public void registerGET()throws Exception{}
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public String registerPOST(BoardVO board,Model model)
								throws Exception{
				
		int bno=service.create(board);
		
		model.addAttribute("bno",bno);
		model.addAttribute("msg","SUCCESS");
		
		return "sboard/taskSuccess";
		
	}
	
	@RequestMapping(value="/getAttach/{bno}",method=RequestMethod.GET)
	@ResponseBody
	public List<String> getAttach(@PathVariable("bno")int bno)
									throws Exception{
		return service.getAttach(bno);
	}
}









