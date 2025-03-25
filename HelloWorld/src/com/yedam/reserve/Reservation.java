package com.yedam.reserve;

public class Reservation {

	private int roomNumber;
	private String roomType;
	private String reserveDate;
	private String reserveName;
	private String reserveTel;
	
	//기본생성자
	public Reservation() {}
	//매개변수 생성자
	
	
	public Reservation(int roomNumber, String roomType) {
		this.roomNumber = roomNumber;
		this.roomType = roomType;
	}
	
	
	public Reservation(String reserveName, String reserveTel) {
		super();
		this.reserveName = reserveName;
		this.reserveTel = reserveTel;
	}


	public Reservation(int roomNumber, String reserveDate, String reserveName, String reserveTel) {
		super();
		this.roomNumber = roomNumber;
		this.reserveDate = reserveDate;
		this.reserveName = reserveName;
		this.reserveTel = reserveTel;
	}
	public String getReserveDate() {
		return reserveDate;
	}
	public void setReserveDate(String reserveDate) {
		this.reserveDate = reserveDate;
	}
	public String getReserveName() {
		return reserveName;
	}
	public void setReserveName(String reserveName) {
		this.reserveName = reserveName;
	}
	public String getReserveTel() {
		return reserveTel;
	}
	public void setReserveTel(String reserveTel) {
		this.reserveTel = reserveTel;
	}

	
	
	//getter setter
	public Integer getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	
	
}
