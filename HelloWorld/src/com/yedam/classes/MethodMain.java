package com.yedam.classes;

import java.util.List;
import java.util.Scanner;

public class MethodMain {
	
	public static void main(String[] args) {
		Scanner scn;
		officeApp();

	} //end of main.
	

	
	static void officeApp() {
		//목록,추가,수정,삭제...
		MethodExe2 m2 = new MethodExe2(); // 기능정의.
		Scanner scn = new Scanner(System.in);		
		Product prd = new Product();
		//사용자 입력 받아서 1.목록,2.추가,3.수정,4.삭제,9.종료 구현.
		//입력 메세지 정의 구현.
		boolean run = true;
		while(run) {
			System.out.println("1.상품목록 2.상품추가 3.상품수정 4.상품삭제 9.종료");
			System.out.println("선택 > ");
			int menu = Integer.parseInt(scn.nextLine());
			switch(menu) {
			case 1: 
				prd.setProductName("ALL");
				List<Product> list = m2.productList(prd);
				for(int i=0; i<list.size(); i++) {
					if(list.get(i) != null){
						System.out.println(list.get(i).showList());						
					}
				}
				break;
			case 2:
				System.out.println("코드 입력 > ");
				String code = scn.nextLine();
				System.out.println("제품 입력 > ");
				String name = scn.nextLine();
				System.out.println("가격 입력 > ");
				String price = scn.nextLine();
				Product store = new Product(code, name, Integer.parseInt(price));
				if(m2.add(store)) {
					System.out.println("등록되었습니다.");					
				}
				break;
			case 3:				
				System.out.println("수정할 제품의 코드 입력 > ");
				String reCode = scn.nextLine();
				System.out.println("제품 입력 > ");
				String reName = scn.nextLine();
				System.out.println("가격 입력 > ");
				String rePrice = scn.nextLine();
				Product prod = new Product(reCode, reName, Integer.parseInt(rePrice));
				if(m2.modify(prod)) {
					System.out.println("수정되었습니다.");					
				}
				break;
			case 4:
				System.out.println("삭제할 제품의 코드 입력 > ");
				String decode = scn.nextLine();
				if(m2.remove(decode)) {
					System.out.println("삭제되었습니다.");
				}
				break;
			case 9:
				run =false;
				System.out.println("프로그램을 종료합니다.");
				break;
			default:
				System.out.println("메뉴를 다시 선택해 주세요");				
			}			
		}
		System.out.println("end of prog.");
	}

	
	
	
	
	
	
	
	
	
	
	
		void method4() {		//
			//인스턴스(m3).메소드 -> 인스턴스 멤버
			
			//클래스이름(MethodExe3).메소드 -> 정적 멤버	
				
			//인스턴스 생성, 사용 / static 사용, 인스턴스 비생성/미비용	
			
				MethodExe4 m4 = new MethodExe4();
//				m4.main(); 
				//인스턴스 - 인스턴스
//				MethodExe4.main();
				//정적 - 정적
				m4.main();
				//정적 - 인스턴스
				//인스턴스 - 정적 (불가능)
		}
		
	
		void method3() {
//			MethodExe3 m3 = new MethodExe3();
//			System.out.println(m3.gugudan(3, 7));
			
//			System.out.println(m3.printStar(5, "★"));
//			
//			System.out.println(m3.printStar(4,"*"));
		
			MethodExe3.printCard();
//		중복 안되게 수정
		}	
	
	
	
	
		void method2() {
			//상품코드: M001, 상품명: 만년필, 가격: 10000
			MethodExe2 m2 = new MethodExe2();
//			if (m2.add(new Product("M001", "만연필", 10000))) {
//				System.out.println("등록성공");
//			}; //add가 false/true를 반환하기 때문에 if 안에 들어갈수있음
			
			//삭제
//			if (m2.remove("A001")) {
//				System.out.println("삭제성공");
//			};
			
			//수정
			//상품코드, 상품명, 가격  modify(Product prod)
//			Product prod = new Product("A001" , null , 16000);
//			if (m2.modify(prod)) {
//				System.out.println("수정성공");
//			};
			
			Product search = new Product();
			search.setProductName("ALL");//
			search.setPrice(1000); //
			
			List<Product> list = m2.productList(search);
			for (int i = 0; i < list.size(); i++) {
					System.out.println(list.get(i).showList());
			}	
		//end of main.
		}
	
	
	
	
		void method1() {
			MethodExe1 m1 = new MethodExe1();	
			///MethodExe1의 실체 생성
//			System.out.print(m1);
		//com.yedam.classes.MethodExe1@279f2327 / MethodExe1의 주소값 참조
			///메소드 호출
			int n =5;
			m1.printString(n, "★");
			//[void printString(int times)] 를 참조에 ()에 넣는 숫자에 따라 별 갯수를 찍어줌
			
			double result = m1.sum(n, 10);
			//sum은 result의 결과를 반환해줌.
			//double 타입 따로 int sum 추가
			//int > double 이기때문에 int값이 출력 -> 실수로 출력하려면 int를 double로 변경하여 출력
			System.out.println(result);
			
			int sum = m1.sum(new int[] {10, 20, 30});	//정수 배열 합
			result = m1.sum(new double[] {12.5, 22.1, 34.5});	//실수 배열 합
			System.out.println(sum);
			System.out.println(result);
			
					
			}			
		}

	

