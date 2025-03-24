package com.yedam.reserve;

public class Reservation {

	private String roomNumber;
	private String roomType;
	//기본생성자
	public Reservation() {}
	//매개변수 생성자
	public Reservation(String roomNumber, String roomType) {
		this.roomNumber = roomNumber;
		this.roomType = roomType;
	}
	
	
	//getter setter
	public String getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	
	
}
