package com.yedam.bookApp;

/*
 * 도서제목, 저자, 출판사, 가격
 */
public class Book {
	// 필드.
	private String title;
	private String author;
	private String company;
	private int price;
	private int orderNo; // 1,2,3
	private String bookCode;
	
	// 생성자.
	public Book() {}
	public Book(String title, String author, String company, int price) {
		this.title = title;
		this.author = author;
		this.company = company;
		this.price = price;
	}
	public Book(String title, String author, String company, int price, int orderNo) {
//		this.title = title;
//		this.author = author;
//		this.company = company;
//		this.price = price;
		this(title, author, company, price); // this: 생성된 인스턴스.
		this.orderNo = orderNo;
	}
	// 메소드.
	public String showList() {
		return bookCode + " " + title + " " + author + " " + price;
	}
	public String showListWithNo() {
		return orderNo + " " + title + " " + author + " " + price;
	}
	public String showBookInfo() {
		String msg = "제목은 " + title + "/ 저자는 " + author +"\n출판사는 " + company + "/ 가격은 " + price;
		return msg;
	}
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
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public String getBookCode() {
		return bookCode;
	}
	public void setBookCode(String bookCode) {
		this.bookCode = bookCode;
	}
}
