package com.yedam.inheritance;
/*
 * 친구2: Friend 를 상속받는 클래스
 */          //자식 클래스         부모 클래스
public class UnivFriend extends Friend {
	//필드
	private String univName;
	private String major;
	//생성자
	public UnivFriend() {
		super(); 
	}
	public UnivFriend(String name, String tel, String univName, String major) {
		super(name, tel); //부모생성자
		this.univName = univName;
		this.major = major;
	}
	
	//부모의 메소드 -> 자식이 재정의. overriding.
	@Override
	public String toString() {
		return super.toString() + ", 학교는 " + univName + ", 학과는 " + major;
	}
	@Override  //컴파일 또는 실행하는 시점에 알려줌
	public String getName() {
		return super.getName();
	}
	
//	@Override
//	public void setName(String name) { 
//		//부모가 final인 메소드는 오버라이드 불가능	
//	}

	public String getUnivName() {
		return univName;
	}

	public void setUnivName(String univName) {
		this.univName = univName;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}
	
	
	
}
