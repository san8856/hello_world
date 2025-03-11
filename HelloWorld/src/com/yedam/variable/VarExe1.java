package com.yedam.variable;

//관례: 클래스의 이름은 대문자로 시작한다.
public class VarExe1 {
	// 연산은 두 변수의 유형(datatype)이 반드시 동일 해야 한다.
	public static void main(String[] args) {
		int number1 = 70;
		int number2 = 80;
		number1 = 71;
		
		
		int sum = number1 + number2;
		
		System.out.println("두 점수의 합은 " + sum + "입니다.");
		
		double avg = (double)sum / 2.0;
		//소수점까지 처리 하게 하려면  2의타입을 변경하면 sum도 더블타입으로 변경시켜줌, (double)-> 임시로 타입 변환
		//자바에서는 양쪽 연산을 하려면 타입이 같아야 한다.
		
		System.out.println("두 점수의 평균은 " + avg + "입니다.");
		
		
		
		
		
	}

}
