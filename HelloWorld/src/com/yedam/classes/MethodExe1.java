package com.yedam.classes;

public class MethodExe1 {
	//리턴타입 메소드 이름(매게변수 선언) { 
										//실행블록
	//}
	void printString(int times, String str) {
		for(int i = 1; i <= times; i++) {
			System.out.print(str);			
		}
		//라이브러리 용도의 클래스		
	}
	// 두 정수의 합
	int sum(int n1, int n2) {
		int result = 0;
		result = n1+n2;
		return result;
	}
	//두 실수의 합
	double sum(double n1, double n2) {
		return (n1 + n2);
	}
	// 같은 메소드(sum)을 여러번 정의 -> 메소드 오버로딩
	
	//배열의 합
	int sum(int[] ary) {
		int result = 0;
		for(int i = 0; i < ary.length; i++) {
			result += ary[i];
		}
		return result;
	}
//	
	//배열의 합(double[]을 매개값으로 받는 메소드)
	double sum(double[] ary) {
		double result = 0;
		for(int i=0; i<ary.length; i++) {
			result += ary[i];
		}
		return result;
	}
	
}
