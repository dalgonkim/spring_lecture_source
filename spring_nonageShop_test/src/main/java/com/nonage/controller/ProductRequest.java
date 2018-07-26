package com.nonage.controller;

import org.springframework.web.multipart.MultipartFile;

import com.nonage.dto.ProductVO;

public class ProductRequest {
	private String name;
	private String kind;
	private int price1;
	private int price2;
	private MultipartFile image;
	private String content;
	
	
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
	
	public ProductVO toProductVO(){
		ProductVO product=new ProductVO();
		product.setContent(content);
		product.setKind(kind);
		product.setName(name);
		product.setPrice1(price1);
		product.setPrice2(price2);
		product.setImage(image.getOriginalFilename());
		
		return product;
	}
	
}











