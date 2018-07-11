package com.rest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rest.dto.SampleVO;
import com.rest.dto.SampleVOList;
import com.rest.dto.SampleVOList2;

@Controller
@RequestMapping("/sample")
public class SampleController {

	@RequestMapping("/jspPage")
	public String jspPage(Model model) throws Exception {

		SampleVO vo = new SampleVO("kim", "mimi");
		model.addAttribute("model", vo);

		return "jsp";
	}

	@RequestMapping(value = "/jsonData", method = RequestMethod.GET)
	@ResponseBody
	public SampleVO jsonData() throws Exception {
		SampleVO vo = new SampleVO("kim", "mimi");
		return vo;
	}

	// JSON sender : 1 depth
	@RequestMapping(value = "jsonData", method = RequestMethod.POST)
	@ResponseBody
	public List<SampleVO> jsonDataList() throws Exception {

		List<SampleVO> list = new ArrayList<SampleVO>();
		for (int i = 0; i < 5; i++) {
			SampleVO vo = new SampleVO("kim" + i, "mimi" + i);
			list.add(vo);
		}

		return list;
	}

	@RequestMapping(value = "/formData", method = RequestMethod.POST)
	@ResponseBody
	public SampleVO formData(SampleVO vo, Model model) {
		System.out.println(vo);

		return vo;
	}

	// XML sender
	@RequestMapping(value = "/xml", method = RequestMethod.GET)
	@ResponseBody
	public SampleVOList sendXML() {
		List<SampleVO> sampleList = new ArrayList<SampleVO>();
		for (int i = 0; i < 5; i++) {
			SampleVO vo = new SampleVO("kim" + i, "mimi" + i);
			sampleList.add(vo);
		}

		SampleVOList voList = new SampleVOList(sampleList);

		return voList;
	}
	
	//XML->JSON
	@RequestMapping(value = "/stringToXML", method = RequestMethod.POST)
	@ResponseBody
	public SampleVOList2 stringToXML(@RequestBody SampleVOList sampleList) {
		List<SampleVO> list=sampleList.getSample();
		
		SampleVOList2 sampleList2 = new SampleVOList2(list);
		
		return sampleList2;
	}
	
	// JSON sender : 2 depth
	@RequestMapping(value="/sendJSON",method=RequestMethod.GET)
	@ResponseBody
	public SampleVOList2 senderJSON(){
		List<SampleVO> sampleList = new ArrayList<SampleVO>();
		for (int i = 0; i < 5; i++) {
			SampleVO vo = new SampleVO("kim" + i, "mimi" + i);
			sampleList.add(vo);
		}
		
		SampleVOList2 sample2=new SampleVOList2(sampleList);
		
		return sample2;
	}
	
	@RequestMapping(value="/jsonToXML",method=RequestMethod.POST)
	@ResponseBody
	public SampleVOList jsonToXML(@RequestBody SampleVOList2 sampleList){
		
		List<SampleVO> list=sampleList.getSample();
		SampleVOList xmlList=new SampleVOList(list);
		
		return xmlList;
	}
}









