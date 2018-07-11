package com.rest.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


public class SampleVOList2 {
	

	private List<SampleVO> sample;

	public SampleVOList2(){}
	public SampleVOList2(List<SampleVO> sample) {
		super();
		this.sample = sample;
	}
	public List<SampleVO> getSample() {
		return sample;
	}
	public void setSample(List<SampleVO> sample) {
		this.sample = sample;
	}
	
	
	
}










