package com.spring.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dto.ReplyVO;
import com.spring.service.ReplyService;

//url : /replies
///replies/all/{bno}		GET방식 : bno번 게시글의 댓글 목록
///replies/{rno}			PUT,PATCH방식 : rno 댓글의 수정 
///replies/{rno}			DELETE 방식 : rno 댓글의 삭제
///replies 				POST 방식 : 댓글 입력
///replies/{bno}/{page} 	GET방식 : bno번 게시글의 페이징 처리된 댓글 목록

@RestController
@RequestMapping("/replies")
public class ReplyController {

	@Resource(name = "replyService")
	private ReplyService service;

	@RequestMapping(value = "/all/{bno}", method = RequestMethod.GET)
	public ResponseEntity<List<ReplyVO>> list(@PathVariable("bno") int bno) throws Exception {

		List<ReplyVO> replyList = service.getReplyList(bno);

		ResponseEntity<List<ReplyVO>> entity = new ResponseEntity<List<ReplyVO>>(replyList, HttpStatus.OK);
		return entity;
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<String> register(@RequestBody ReplyVO reply) {

		ResponseEntity<String> entity = null;

		try {

			service.createReply(reply);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return entity;
	}

	@RequestMapping(value = "/{bno}/{page}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> listPage(@PathVariable("bno") int bno, @PathVariable("page") int page)
			throws Exception {

		ResponseEntity<Map<String, Object>> entity = null;

		Criteria cri = new Criteria();
		cri.setPage(page);
		cri.setPerPageNum(5);

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.countReply(bno));

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageMaker", pageMaker);
		try {
			List<ReplyVO> replyList = service.getReplyListPage(bno, cri);
			map.put("list", replyList);
			entity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch (SQLException e) {
			e.printStackTrace();
			entity = new ResponseEntity<Map<String, Object>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return entity;
	}

	@RequestMapping(value = "/{rno}", method = { RequestMethod.PUT, 
												 RequestMethod.PATCH })
	public ResponseEntity<String> update(@PathVariable("rno") int rno,
										 @RequestBody ReplyVO reply) 
												 	throws Exception {
		ResponseEntity<String> entity = null;

		reply.setRno(rno);
		try {
			service.modifyReply(reply);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (SQLException e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return entity;
	}
	
	
	@RequestMapping(value="/{rno}",method=RequestMethod.DELETE)
	public ResponseEntity<String> remove(@PathVariable("rno") int rno)
									throws Exception{
		
		ResponseEntity<String> entity=null;
		try{
			service.deleteReply(rno);
			entity=new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
		}catch(SQLException e){
			e.printStackTrace();
			entity=new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return entity;
	}
}









