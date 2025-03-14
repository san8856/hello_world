package com.yedam.classes;
/*
 *  상품코드, 상품명, 가격에 대한 정보
 */


public class Product {
	//필드
	private String productCode;
	private String productName;
	private int price;
	//필드는 주로 인스턴스로 사용
	//기능은 인스턴스, 정적 둘다 사용
	
	//필드에 대한 생성자
	Product() {}
	Product(String pc, String pn, int price){
		productCode = pc;
		productName = pn;
		this.price = price;
	}
	
	//생성자에 대한 메소드
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	//목록을 보여주기 위한 메소드
	public String showList() {
		return productCode + " " + productName + " " + price;

	}
	
	
	
	
	
	
}
