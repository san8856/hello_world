package com.yedam.variable;

import java.util.Scanner;

// 예금과 출금, 잔고확인 기능
public class VarExe5 {
	public static void main(String[] args) {
		boolean run = true;
		int balance = 0;  //예금액을 저장하는 변수
		// 10만원이 최대 예금가능, 작액이 0보다 커야 함
		int amt = 0;
		Scanner scn = new Scanner(System.in);
		while(run) {
			System.out.println("1.예금 2.출금 3.잔고 4.종료");
			int menu = scn.nextInt();
			switch(menu) {
			case 1: 
				System.out.print("예금액을 입력 >>");
				amt = scn.nextInt();
				if (balance + amt >= 100000) {
					System.out.println("예금액이 10만원을 초과합니다");
				}else {
					balance = balance + amt;
					System.out.println(balance + "원이 입금되었습니다");
				}
				break;  //case1 에 대한 종료 구문.
			case 2:				
					System.out.print("출금액을 입력>>");
					amt = scn.nextInt();
					if (balance < amt) {
						System.out.println("잔액이 부족합니다");
					}else {
						balance = balance - amt;
						System.out.println(amt + "원이 인출되었습니다");
					}
				break;  //case2 에 대한 종료
			case 3:
				System.out.println("잔액: " + balance);
				break;  //case3 에 대한 종료
			case 4: 
				run = false; //종료
			}
		}
		System.out.println("end of prog.");
	}//end of main(). 
}
