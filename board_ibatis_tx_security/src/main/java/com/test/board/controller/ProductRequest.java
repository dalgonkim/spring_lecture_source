package com.test.board.controller;

import java.sql.Timestamp;

import org.springframework.web.multipart.MultipartFile;

import com.test.board.dto.ProductVO;

public class ProductRequest {
	private int pseq;
	private String name;
	private String kind;
	private int price1;
	private int price2;
	private MultipartFile image;
	private String content;
	private Timestamp indate;
	
	
	
	public int getPseq() {
		return pseq;
	}
	public void setPseq(int pseq) {
		this.pseq = pseq;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	
	
	public int getPrice1() {
		return price1;
	}
	public void setPrice1(int price1) {
		this.price1 = price1;
	}
	public int getPrice2() {
		return price2;
	}
	public void setPrice2(int price2) {
		this.price2 = price2;
	}
	public MultipartFile getImage() {
		return image;
	}
	public void setImage(MultipartFile image) {
		this.image = image;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getIndate() {
		return indate;
	}
	public void setIndate(Timestamp indate) {
		this.indate = indate;
	}
	
	public ProductVO toProductVO(){
		ProductVO productVO=new ProductVO();
		productVO.setIndate(this.indate);
		productVO.setPseq(this.pseq);
		productVO.setContent(this.content);
		productVO.setKind(this.kind);
		productVO.setName(this.name);
		productVO.setPrice1(this.price1);
		productVO.setPrice2(this.price2);
		return productVO;		
	}
	
}








