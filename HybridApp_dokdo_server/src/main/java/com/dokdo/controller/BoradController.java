package com.dokdo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dokdo.dto.BoardList;
import com.dokdo.dto.BoardVO;
import com.dokdo.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoradController {

	@Autowired
	private BoardService boardSvc;

	public void setBoardSvc(BoardService boardService) {
		this.boardSvc = boardService;
	}

	@RequestMapping("/boardList")
	@ResponseBody
	public BoardList boardList(@RequestParam(value = "title", defaultValue = "") String title)
			throws Exception {
		List<BoardVO> boardList = boardSvc.getBoardList(title);

		return new BoardList(boardList);
	}

	@RequestMapping(value = "/addBoard", method = RequestMethod.POST)
	@ResponseBody
	public String addBoard(BoardVO board, HttpSession session) throws Exception {
		boardSvc.insertBoard(board);
		
		return "Success";
	}

	@RequestMapping("/detailBoard")
	@ResponseBody
	public BoardVO detailBoard(@RequestParam int board_id) throws Exception {
		BoardVO board = (BoardVO) boardSvc.getBoard(board_id);
		return board;
	}

	@RequestMapping(value = "/updateBoard", method = RequestMethod.POST)
	@ResponseBody
	public String updateBoard(BoardVO board, @RequestParam("nonmakeImg") String nonmakeImg,
			HttpServletRequest request) throws Exception {

		boardSvc.updateBoard(board);
		return "Success";

	}

	@RequestMapping("/deleteBoard")
	@ResponseBody
	public String deleteBoard(@RequestParam int board_id) throws Exception{
			boardSvc.deleteBoard(board_id);
		return "Success";
	}
	

}
