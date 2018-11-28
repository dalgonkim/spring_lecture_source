package com.spring.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dto.SampleVO;
import com.spring.dto.SampleVOList;

@RestController
@RequestMapping("/rs")
public class SampleRestController {

	@RequestMapping("/jspPage")
	public String jspPage(Model model) throws Exception {

		SampleVO vo = new SampleVO("kim", "mimi");
		model.addAttribute("model", vo);
		
		return "jsp";
	}

	@RequestMapping("/jsonData")
	public SampleVO jsonData() throws Exception {
		SampleVO vo = new SampleVO("kim", "mimi");
		return vo;
	}

	// JSON sender : 1 depth
	@RequestMapping(value = "/jsonList", method = RequestMethod.GET)
	public List<SampleVO> jsonList() throws Exception {

		List<SampleVO> list = new ArrayList<SampleVO>();
		for (int i = 0; i < 5; i++) {
			SampleVO vo = new SampleVO("kim" + i, "mimi" + i);
			list.add(vo);
		}

		return list;
	}
	
	@RequestMapping(value = "/jsonMap", method = RequestMethod.GET)
	public Map<String,List<SampleVO>> jsonMap() throws Exception {
		
		Map<String,List<SampleVO>> jsonMap = new HashMap<String,List<SampleVO>>();
		
		List<SampleVO> list = new ArrayList<SampleVO>();
		for (int i = 0; i < 5; i++) {
			SampleVO vo = new SampleVO("kim" + i, "mimi" + i);
			list.add(vo);
		}
		
		jsonMap.put("samples",list);
		
		return jsonMap;
	}
	
	@RequestMapping("/receiveJson")
	public String receiveJson(SampleVO sample)throws Exception{
		System.out.println(sample);
		
		return "Success";
	}
	
	@RequestMapping(value="/receiveJsonArray",method=RequestMethod.POST)
	public String receiveJsonArray(@RequestBody List<SampleVO> sampleList)throws Exception{
		System.out.println(sampleList);
		return "Success";
	}
	
	@RequestMapping(value="/receiveJsonMap",method=RequestMethod.POST)
	public String receiveJsonMap(@RequestBody Map<String,List<SampleVO>> sampleMap)
									throws Exception{
		System.out.println(sampleMap);
		return "Success";
	}
	
	@RequestMapping("/xmlData")
	public SampleVOList sendXML()throws Exception{
		List<SampleVO> sampleList = new ArrayList<SampleVO>();
		for (int i = 0; i < 5; i++) {
			SampleVO vo = new SampleVO("kim" + i, "mimi" + i);
			sampleList.add(vo);
		}

		SampleVOList voList = new SampleVOList();
		voList.setSampleList(sampleList);

		return voList;
	}
	
	@RequestMapping(value="/receiveXml",method=RequestMethod.POST)
	public String receiveXml(@RequestBody SampleVOList sampleList){
		System.out.println(sampleList);
		
		return "Success";
	}
}





