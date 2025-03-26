package com.yedam.reserve;

public class Reservation {

	private int roomNumber;
	private String reserveName;
	private String reserveTel;
	private String reserveDate;
	private String roomType;
	
	//기본생성자
	public Reservation() {}
	//매개변수 생성자
	
	
	public Reservation(int roomNumber, String reserveName, String reserveTel, String reserveDate) {
        this.roomNumber = roomNumber;
        this.reserveName = reserveName;
        this.reserveTel = reserveTel;
        this.reserveDate = reserveDate;
    }
	
	public Reservation(int roomNumber, String roomType) {
		this.roomNumber = roomNumber;
		this.roomType = roomType;
	}
	
	
	public Reservation(String reserveName, String reserveTel) {
		super();
		this.reserveName = reserveName;
		this.reserveTel = reserveTel;
	}


	public int getRoomNumber() {
		return roomNumber;
	}


	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
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


	public String getReserveDate() {
		return reserveDate;
	}


	public void setReserveDate(String reserveDate) {
		this.reserveDate = reserveDate;
	}


	public String getRoomType() {
		return roomType;
	}


	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}




	
	
}
