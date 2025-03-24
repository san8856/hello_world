package com.yedam.test;

public class Test {
    private String roomNumber;
    private String roomType;

    // 기본 생성자
    public Test() {}

    // 매개변수 생성자
    public Test(String roomNumber, String roomType) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
    }

    // Getter 및 Setter
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
