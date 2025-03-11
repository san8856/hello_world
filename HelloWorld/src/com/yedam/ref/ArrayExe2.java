package com.yedam.ref;

import com.yedam.variable.Member;

public class ArrayExe2 {
	public static void main(String[] args) {
		String[] strAry = { "Hello", "World", "20", "23.4" };
		// 확장 for 문
//		for (String str : strAry) {
//			System.out.println(str);
//		}
		//크기 지정.
		strAry = new String[10]; //{null, null, null, ..., null,}
		strAry[0] = new String("Nice");
		for (String str : strAry) {
//			System.out.println(str);
		}
		// Member  클래스
		Member[] memAry = new Member[10];  //{null, null, null, ..., null,}
		memAry[0] = new Member(); //인스턴스생성(객체 생성) - 생성자
		memAry[0].setMember("홍길동", 80); //80->90
		memAry[0].setScore(-90);
		memAry[0].showInfo();
		
		memAry[1] = new Member();
		memAry[1].setMember("고길동", 90); //90>95
		memAry[1].setScore(195);
		memAry[1].showInfo();
		//오류 - java.lang.NullPointerException 참조하고 있는 메소드가 없음
		
		memAry[2] = new Member("최민혁", 77);
		memAry[2].showInfo();
		
		
		
		for (int i=0; i<memAry.length; i++) {
			if (memAry[i] != null) {
//			memAry[i].showInfo();
			}
		}//3번째 이후부터 null값이 참조되기 때문에 null값을 제외하는 조건문을 입력
	}
}
