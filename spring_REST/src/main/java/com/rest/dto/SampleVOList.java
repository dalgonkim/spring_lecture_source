package com.rest.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="samples")
public class SampleVOList {
	
	@XmlElement(name="sample")
	private List<SampleVO> sampleList;

	public List<SampleVO> getSampleList() {
		return sampleList;
	}

	public void setSampleList(List<SampleVO> sampleList) {
		this.sampleList = sampleList;
	}

	@Override
	public String toString() {
		return "SampleVOList [sampleList=" + sampleList + "]";
	}
	
	
}
