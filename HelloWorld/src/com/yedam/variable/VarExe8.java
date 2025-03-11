package com.yedam.variable;

public class VarExe8 {
	public static void main(String[] args) {
		int n1 =10;
		byte b1 =120;
		byte result = (byte) (b1 + 130);  // -128 ~ 127   //서로 타입이 달라서 에러가 남. ->형변환(casting) 해준다.
		System.out.println("더한 결과: " + result);      //127보다 큰 값이 들어가면 다시 작은값부터 시작.
		// byte-> int (작은값의 범위를 큰 값으로 넘겨줄때 > 자동 형변환: promotion
		n1 = b1;  //(byte) (b1 + 130);처럼 넣지 않아도 b1>byte n1>int를 같게 바꿔줌 )
		System.out.println(n1);
		n1 = 200;
		b1 = (byte) n1;
		System.out.println(b1);
		
//		for(int i=1; i < 15; i++) {
//			System.out.println(b1++);
//		}
		
	}
}
