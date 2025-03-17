package com.yedam.bookApp;
/*
 * 실행클래스(main 메소드)
 */
public class BookApp {
	public static void main(String[] args) {
		
		BookMain mainApp1 = BookMain.getInstance();
		mainApp1.main(args);
		
//		BookMain mainApp2 = BookMain.getInstance();
		//이제 app1과 2는 같은 값을 가진다
		
		
//		mainApp1.add(); //1번에 등록
//		mainApp1.list();
//		
//		mainApp2.list(); //2번 목록
		
	}
}
