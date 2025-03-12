package com.yedam.ref;
//크기 순서로 정렬
public class ArrayExe4 {
	public static void main(String[] args) {
		int[] intAry = {8, 7, 6, 5, 4, 3 ,2 ,1 };
		//위치변경
		int temp = intAry[0];
		intAry[0] = intAry[1];
		intAry[1] = temp;
		for(int j=0; j < intAry.length -1; j++) {
			for(int i=0; i< intAry.length -1; i++) {
//				System.out.println(intAry[i]);
				// i , i+1번째 값이 내림차순일때 올림차순으로 변
				if (intAry[i]>intAry[i+1]) {
					temp = intAry[i];
					intAry[i] = intAry[i+1];
					intAry[i+1] = temp;
					
				}					
			}		
		}
		System.out.println("오름차순 정렬");
		for(int i=0; i<intAry.length; i++) {
			System.out.print(intAry[i] + " ");
		}
	}
}
