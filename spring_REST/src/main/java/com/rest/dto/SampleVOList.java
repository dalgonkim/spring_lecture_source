package com.rest.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="sample-list")
public class SampleVOList {
	
	@XmlElement(name="sample")
	private List<SampleVO> sample;

	public SampleVOList(){}
	public SampleVOList(List<SampleVO> sample) {
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










