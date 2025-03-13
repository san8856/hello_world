package com.yedam.bookApp;
//bookapp
//book: 도서제목, 저자, 출판사, 가격 - 데이터 타입을 담는 클래스
//bookmain: main 메소드

public class Book {
	//필드
	private String title; 
	private String author;
	private String company;
	private int price;
	private int orderNo; //저장된 도서를 넘버링 정렬
	
	//생성자
	public Book() {}
	public Book(String title, String author, String company, int price) {
		this.title = title;
		this.author = author;
		this.company = company;
		this.price = price;
	}
	
	public Book(String title, String author, String company, int price, int orderNo) {
//		super();//부모클래스의 생성자를 불러옴 (상속)
//		this.title = title;
//		this.author = author;
//		this.company = company;
//		this.price = price;
		this(title, author, company, price); //this: 생성된 인스턴스 //첫번째 줄에만 사용
		this.orderNo = orderNo;
	}
	
	//메소드
	public String showList() {
		return title + " " + author + " " + price;
	}
	public String showListWithNo() {
		return orderNo + " " + title + " " + author + " " + price;
	}
	public String showListPublisher() {
		return orderNo + " " + title + " " + author + " " + company + " "+ price;
	}
	public String showBookInfo() {
		String msg = "제목은 " + title + "/ 저자는 " + author + "\n출판사는 " + company + "/ 가격은 " + price;
		return msg;
	}

	//필드의 타이틀 값을 반환해주는 메소드
	public String getTitle() {
		return title;
	}
	public String getAuthor() {
		return author;
	}
	public String getCompany() {
		return company;
	}
	public int getPrice() {
		return price;
	}
	public int getOrderNo() {
		return orderNo;
	}
	
	//메소드
	public void setTitle(String title) {
		this.title = title;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
//aa
	
}	
	

