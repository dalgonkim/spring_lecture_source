package com.spring.smarteditor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@RequestMapping(value="/write",method=RequestMethod.GET)
	public void writeBoardForm()throws Exception{}
	
	@RequestMapping(value="/writePost",method=RequestMethod.POST)
	public void writeBoardPost(BoardRequest board)throws Exception{
		System.out.println(board);
	}
}
