package com.yedam.api;


	import java.util.ArrayList;
	import java.util.List;

	public class WrapperExe {
		public static void main(String[] args) {
			int[] intAry = {10,20};
			List<Integer> list = new ArrayList<Integer>(); //<데이터타입>
			
			list.add(10); //int -> Integer 박싱. Integer -> int 언박싱.
			list.add(new Integer(20)); //원래 이런 방식으로 리스트에 담긴다.
			//
			int num1 = 10; // == (비교연산사) 사용해서 두개 값을 비교
			Integer num2 = new Integer(20); //참조변수  //== 주소값을 비교 . 항상 값이 false
			System.out.println(num1 == num2.intValue()); //intValue()로 기본값으로 변환 후 비교
		}
	}


