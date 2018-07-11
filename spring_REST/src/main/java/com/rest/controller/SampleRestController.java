package com.rest.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.dto.SampleVO;

@RestController
@RequestMapping("/rs")
public class SampleRestController {
	
	@RequestMapping("/hello")
	public String sayHello(){
		return "Hello World";
	}
	
	@RequestMapping("/sendVO")
	public SampleVO sendVO(){
		SampleVO vo=new SampleVO("길동","홍");
		
		return vo;
	}
	
	@RequestMapping("/sendList")
	public List<SampleVO> sendList(){
		List<SampleVO> list=new ArrayList<SampleVO>();
		for(int i=0;i<5;i++){
			SampleVO vo=new SampleVO("kim"+i, "mimi"+i);
			list.add(vo);
		}
		
		return list;
	}
	
	@RequestMapping("/sendMap")
	public Map<String,SampleVO> sendMap(){
		Map<String,SampleVO> map=new HashMap<String,SampleVO>();
		
		for (int i=0;i<5;i++){
			SampleVO vo=new SampleVO("kim"+i,"mimi"+i);
			map.put(i+"", vo);
		}
		
		return map;
	}
	
	@RequestMapping("/sendErrorAuth")
	public ResponseEntity<Void> sendListAuth(){
		return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping("/sendErrorNot")
	public ResponseEntity<List<SampleVO>> sendListNot(){
		List<SampleVO> list=new ArrayList<SampleVO>();
		for(int i=0;i<5;i++){
			SampleVO vo=new SampleVO("kim"+i, "mimi"+i);
			list.add(vo);
		}
		
		ResponseEntity<List<SampleVO>> entity;
		
		if(list==null){
			entity=
			new ResponseEntity<List<SampleVO>>(list,HttpStatus.INTERNAL_SERVER_ERROR);			
		}else{
			entity=
			new ResponseEntity<List<SampleVO>>(list,HttpStatus.OK);
		}
		
		return entity;
		
	}
}














