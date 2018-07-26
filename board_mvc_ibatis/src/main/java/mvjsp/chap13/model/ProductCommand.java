package mvjsp.chap13.model;

import org.springframework.web.multipart.MultipartFile;

public class ProductCommand {
	
	//상품(상품번호,상품명,원가,판매가,개수,이미지)
	//product(proNum,proName,price1,price2,amount,image)
	
	private int proNum;
	private String proName;
	private int price1;
	private int price2;
	private int amount;
	private MultipartFile image;
	
	
	public MultipartFile getImage() {
		return image;
	}
	public void setImage(MultipartFile image) {
		this.image = image;
	}
	public int getProNum() {
		return proNum;
	}
	public void setProNum(int proNum) {
		this.proNum = proNum;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
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
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	
	

}
