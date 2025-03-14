package com.yedam;

import com.yedam.classes.MethodMain;

public class HelloExe {
	//기능(함수) => main 메소드. 반드시 있어야 함
   public static void main(String[] args) {
	   //1. 32000 값을 담는 변수 선언, 할당
	   int num = 32000;
	   //2.정수를 여러개 담은 변수 (34, 32, 88, 23)
	   int[] intAry = {34, 32, 88, 23};
	   //3. 문자: 32
	   String text;
	   text=("문자: " + 32);
	   //4. 정수 변수에 저장
	   int i = 5;
	   //5.hello, nice, good
	   String[] intStr = {"Hello","Nice","Good"};
	   //6. 정수 5개 저장.
	   //Math.random() -> 60~100
	   int[] ranNo = new int[5];
	   for(i=0; i < ranNo.length; i++) {
		   ranNo[i]=(int)(Math.random() *100)+61;
		   for (int j = 0; j < i; j++) {
				if (ranNo[i] == ranNo[j]) {
					i--;
					break;
				}
			}
	   }  
   }
	   //7. 이름, 연락처, 나이 데이터 타입 선언 
	   // 홍길동, 010-1234-1234, 20
   		String name = "홍길동";
   		String number = "010-1234-1234";
   		int age = 20;   					   
	   //8. 위에서 선언한 데이터 타입을 3개정도 담도록 공간지정, 
	   //홍길동, 010-1234-1234, 20
	   //김민식, 010-2222-2222, 22
	   //최문식, 010-2222-2222, 23

			
	   
	   //9. 나이가 제일 많은 사람의 이름을 출력
	   
	   
	   
   }

//System.out.println("Hello, World");
//
//String name;
//name = "김다산";
//
//System.out.println("이름은 " + name);
//
//int score = 100;
////연산을 할땐 타입이 같아야 함
//System.out.println("점수는 " + score + "점 입니다");
//System.out.println("수정된 부분");
//

//MethodMain mm = new MethodMain();
//mm.main(args); //public
//
//mm.officeApp(); //default