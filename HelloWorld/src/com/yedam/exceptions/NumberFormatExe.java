package com.yedam.exceptions;

import java.util.Scanner;

public class NumberFormatExe {
	public static void main(String[] args) {
		try {
		exceptMethod();
		} catch(NumberFormatException e) {
			System.out.println("예외 떠넘기기 처리.");
		} finally {
			System.out.println("정상실행 or 예외발생 상관없이 반드시 실행할 코드.");
		}
		System.out.println("end of prog.");
	}
	
	// 예외 떠넘기기 (throws)
	static void exceptMethod() throws NumberFormatException{
		Scanner scn = new Scanner(System.in);
		
		System.out.println("정수를 입력하세요.");
		String strNo = scn.nextLine();
		int no = 1;
		no = Integer.parseInt(strNo); //에러
		System.out.println(no);
	}
}
