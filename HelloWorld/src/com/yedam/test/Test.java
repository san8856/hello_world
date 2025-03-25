package com.yedam.test;

import java.sql.Date;

public class Test {
    private int roomNumber;
    private String roomType;
	private String reserveDate;
	private String reserveName;
	private String reserveTel;
	
	
	public Test() {}
	
	public Test(int roomNumber, String roomType) {
		this.roomNumber = roomNumber;
		this.roomType = roomType;
	}

    public Test(int roomNumber, String reserveDate, String reserveName, String reserveTel) {
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


    public int getRoomNumber() {
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