package com.rest.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.dto.MemberVO;
@RestController
@RequestMapping("/sample")
public class SampleController {

	@RequestMapping("/hello")
	//@ResponseBody
	public String hello(){
		return "<h1>hello</h1>";
	}
	
	@RequestMapping("/member")
	//@ResponseBody
	public MemberVO memberInfo(){
		return new MemberVO("mimi","mimi","mimi","mimi@mimi.com");
	}
	
	@RequestMapping("/memberList")
	//@ResponseBody
	public List<MemberVO> memberListinfo(){
		List<MemberVO> memberList=Arrays.asList(
				new MemberVO("1","2","3","4"),
				new MemberVO("5","6","7","8"));
				
		return memberList;				
	}
	
	@RequestMapping("/memberMap")
	//@ResponseBody
	public Map<String,MemberVO> memberMap(){
		 Map<String,MemberVO> memberMap=new HashMap<String,MemberVO>();
		 
		 memberMap.put("member1",new MemberVO("1","2","3","4"));
		 memberMap.put("member2",new MemberVO("5","6","7","8"));
		 
		 return memberMap;
	}
	
	@RequestMapping("/sendErrorAuth")
	public ResponseEntity<Void> sendListAuth(){
		return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping("/sendErrorNot")
	public ResponseEntity<List<MemberVO>> sendListNot(){
		List<MemberVO> memberList=new ArrayList<MemberVO>();
		
		for(int i=0;i<10;i++){
			MemberVO member=new MemberVO(
					"userid"+i,"password"+i,"username"+i,"email"+1
					);
			memberList.add(member);
		}
		
		return new ResponseEntity<List<MemberVO>>(memberList,
				HttpStatus.NOT_FOUND);
	}
}
































