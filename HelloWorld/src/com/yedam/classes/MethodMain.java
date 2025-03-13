package com.yedam.classes;

public class MethodMain {
	public static void main(String[] args) {
		MethodExe3 m3 = new MethodExe3();
//		System.out.println(m3.gugudan(3, 7));
		
		System.out.println(m3.printStar(5, "★"));
		
	
	
//		m3.printCard();
	//중복 안되게 수정
		
	
	
	
	
	
	
	
	
	
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
			
			Product[] list = m2.productList(search);
			for (int i = 0; i < list.length; i++) {
				if (list[i] != null) {
					System.out.println(list[i].showList());
				}
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

	

