package com.yedam.classes;
/*
 * 정적(static) 멤버의 사용
 */
public class MethodExe4 {
	
//	void test(){
//		System.out.println("인스턴스 메소드");
//	}
	
//	void main() {
//		test();
//	
	
//	static void test(){
//		System.out.println("정적 메소드");
//	}
//	static void main() {
//		test();
//	}
//	
//}
	
	static void test(){
		System.out.println("정적 메소드");
	}
	void main() {
		test();
	}
	//정적메소드에서 인스턴스 메소드 호출은 가능
}