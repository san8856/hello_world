package com.yedam.variable;
import java.util.Scanner;

public class VarExe4 {
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int[] scores = new int[3]; // {0,0,0} 정수 값을 3개 담을 수 있는 배열 생성

		
		// 정수를 입력하세요
		for (int i=0; i<scores.length;i++) {
		System.out.print("점수를 입력하세요>");
		scores[i] = scn.nextInt();
		}
		// 값 입력.
		
		//입력한 합의 값을 콘솔에 출력.
		int sum = 0;
		double avg = 0.0;
		for (int i=0; i<scores.length; i++) {
			sum = sum + scores[i];
		}
		avg = sum * 1.0 / scores.length;
//		double avg = 0.0;
//		for (int i=0; i<scores.length; i++) {
//			avg = sum * 1.0 / scores.length;
//		}
//		
		//"합은 240 입니다."
		System.out.println("합은" + sum + "입니다.");
		System.out.println("평균은" + avg + "입니다.");
	}
}
