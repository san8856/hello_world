package com.yedam.variable;

import java.util.Scanner;

public class VarExe6 {
//	public static void test() {
//	    //임의의 정수 1~50 임의 의 값
//		//System.out.println((int) (Math.random() * 50) +1;
//		//학생의 점수 (30~100 사이의 점수)
//		// 정수(int) -> 5개 저장
//		
//		int[] scores = new int[500];
//		for (int i = 0; i < scores.length; i++) {
//			scores[i] = (int) (Math.random() * 71) + 30;
//		}
//		
//		
//		//홀수의 값을 출력
//		for (int i=0; i< scores.length; i++) {
//			if(scores[i] == 100) {
//				System.out.println(i + "번째 값: " + scores[i]);
//			}
//		}
//	}//end of main().
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		
		Member m1 = new Member(); //인스턴스 생성.
		m1.setMember("홍길동", 0);
		Member m2 = new Member(); //인스턴스 생성.
		m2.setMember("최민수", 0);
		Member m3 = new Member(); //인스턴스 생성.
		m3.setMember("김병수", 0);
		Member m4 = new Member(); //인스턴스 생성.
		m4.setMember("박인만", 0);
		
		//배열.
		Member[] members = {m1, m2, m3, m4};
		 //70에서 100 사이의 임의의 값으로 점수 지정.
		for (int i = 0; i< members.length; i++) {
			int randomScore = (int) (Math.random() * 31 ) + 70;
			members[i].setScore(randomScore);
		}
		
		//조회 이름을 입력 -> 점수 출력.
		System.out.println("조회할 이름 입력>> ");
		String search = scn.nextLine();
		
		//for 반복문 활용. 
		//30 == 30, members[i].name.equals(search)
		
		for (int i=0; i<members.length; i++) {
			if(members[i].getName().equals(search)) {
				System.out.println(members[i].getScore());
			}
		}
		
		
		
		
		// 점수가 가장 높은 사람의 이름 출력
		int max = members[0].getScore();
		String name = members[0].getName();
		for (int i = 0; i < members.length; i++) {
			System.out.println(members[i].getName()+","+members[i].getScore());
			if(max < members[i].getScore()) {
				max = members[i].getScore();
				name = members[i].getName();
			}
		}
		System.out.println("최고점수는 " + name);


	}//end of main().
}
