package com.yedam.classes;

public class MethodExe3 {

	//구구단 출력
	static String gugudan(int num, int toNum) {
		//2 * 1 = 2
		//2 * 2 = 4
		// ...
		//2 * 9 = 18
		String str ="";
		for (int n=num; n <= toNum; n++) {
			int dan = n;
			for(int i=1; i<=9; i++) {	
//				System.out.println(dan + "*" + i + " = " + (dan*i));
				str += dan + " * " + i + " = " + (dan*i) + "\n";
			}		
		}	
		return str;
	}
	
	
	//int-줄 수 매개변수 str-모양 매개변수
//	String printStar(int cnt, String str) {
//		String st = "";
//		for(int i = 1; i<=cnt ; i++) {
//			for(int j = 1; j <=i; j++) {
//				st += str ;
//			}	
//			st += "\n";
//		}
//		return st;
//	}
//	String printStar(int cnt, String str) {
//		String st = "";
//		for(int i = 1; i<=cnt; i++) {
//			for(int j = 1; j <= i; j++) {
//				st += str;
//			}
//			st += "\n";
//		}		
//		return st;
//	}
//	
	static void printStar(int cnt, String str) {
		for(int j = 1; j <= cnt; j++) {
			for(int i = 1; i <= cnt; i++) {
				if (i <= j) {
					System.out.print(str); //기호
				}
			}
			System.out.println();//줄바꿈
		}		
	}
	
	
	
// 중복제거 전	
//	void printCard() {
//		//배열선언
//		int[] intAry = new int[16];
//		//1 ~ 16까지의 임의수 할당.
//		for (int i=0; i < intAry.length; i++) {
//			intAry[i] = (int) (Math.random() *16) +1;
//		}
//		for (int i =0; i <intAry.length; i++) {
//			System.out.printf("%3d ", intAry[i]);
//			if (i % 4 ==3) {
//				System.out.println();
//			}
//		}
//	}
//	//중복제거 후
	static void printCard() {
		//배열선언
		int[] intAry = new int[16];
		//1 ~ 16까지의 임의수 할당.
		for (int i=0; i < intAry.length; i++) {
			intAry[i] = (int) (Math.random() *16)+1;
			for(int j=0; j<i; j++) {
				if(intAry[i] == intAry[j]) {
					i--;
					break;
				}
			}
		}
		for (int i =0; i <intAry.length; i++) {
			System.out.printf("%3d ", intAry[i]);
			if (i % 4 ==3) {
				System.out.println();
			}
		}
	}
//	void printCard() {
//		//배열선언
//		int[] intAry = new int[16];
//		//1 ~ 16까지의 임의수 할당.
//		for (int i=0; i < intAry.length; i++) {
//			intAry[i] = (int) (Math.random() *16) +1;
//			//이전에 생성된 값인지 체크
//			boolean dup = false;
//			for (int j =0; j < i; j++) {
//				
//			}
//		}
//		for (int i =0; i <intAry.length; i++) {
//			System.out.printf("%3d ", intAry[i]);
//			if (i % 4 ==3) {
//				System.out.println();
//			}
//		}
//	}

	
	
	
}
