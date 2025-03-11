package com.yedam.ref;

public class ArrayExe3Calendar {
	//2025년을 기준으로 월 정보 => 1일의 위치를 반환해주는 메소드
	public static int getFirstDay(int month) {
		switch(month) {
		case 1:
			return 3;
		case 2:
			return 6;
		case 3:
			return 6;
		case 4:
			return 2;
		default:
			return 1;
		}
	}
	//2025년을 기준으로 월의 마지막날을 반환해주는 메소드
	public static int getLastDay(int month) {
		int date = 31; //31일인 달
		switch(month) {
		case 2:
			date = 28;
			break;
		case 4:
		case 6:			
		case 9:			
		case 11:
			date = 30;
		}
			return date;
	}	
	
	
	
	public static void main(String[] args) {
		// 1~31 콘솔출력(print - 줄바꿈 X / println - 줄바꿈O		
		
		
		String[] days = {"Sun","Mun","Tue","Wed","Thu","Fri","Sat"};
		for(String day : days) {
			System.out.print(" " + day);
		}
		System.out.println();//줄바꿈
		int month = 3;
		int space = getFirstDay(month); //1일의 위치값
		int lastDate = getLastDay(month); //마지막날
		// 공백갯수많큼 빈칸
		for(int i = 0; i < space; i++) {		
			System.out.print("    ");
		}
		//날짜 출력 "1" -> :integer.parseInt()
		for(int d=1; d<=lastDate; d++) {
			if(String.valueOf(d).length() == 1) {
			System.out.print("   " + d);
			}else if(String.valueOf(d).length() == 2) {
			System.out.print("  " + d);
			}	
			//줄 바꿈.
			if ((space+d) % 7 == 0) {
				System.out.println();
			}
			if (month == 3 && d == 21) {
			System.out.print("test");
			}
		}
	}// end of main()
}//커밋