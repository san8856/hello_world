package com.yedam.inheritance;
/*
 * 추상 클래스
 * 추상 메소드: 선언부분만 존재.
 */
public abstract class Animal {
	abstract void sound(); // 자식클래스에 규칙을 지정.
	void eat() {
		System.out.println("먹는다.");
	}
}
