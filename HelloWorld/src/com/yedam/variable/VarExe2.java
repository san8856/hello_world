package com.yedam.variable;

//데이터 유형(data type)
class Student {
	
	String name;
	int score;
	double height;
}

public class VarExe2 {
	public static void main(String[] args) {
		String name = "홍길동";
		int score = 100;
		
		
		Student s1 = new Student(); //인스턴스 생성.
		s1.name = "김민수";
		s1.score = 90;
		s1.height = 178.9;
		
		Student s2 = new Student();
		s2.name = "최우식";
		s2.score = 92;
		s2.height = 168.9;
		
		////////////////
		int[] scores = { 89, 77, 60, score };
		//정수를 담을수 있는 배열 생성
		
		int sum = 0;
		for(int i=0; i<scores.length; i++ ) {
			sum = sum + scores[i];
		}//sum 의 값을 구하는 반복문
		
		System.out.println("합:" + sum);
		
		String[] names = { "박승호", "김주승", name };
		//문자를 담을수 있는 배열 데이터
		for(int i=0; i<names.length; i++) {
			System.out.println("이름 =>" + names[i]);
		}
		
		Student[] students = {s1, s2};
		//student 라는 데이터를 담을수 있는 배열 {}
		
		for(int i=0; i<students.length; i++) {
			System.out.println("이름: "+ students[i].name
					+ ", 점수: " + students[i].score
					+ ",키: " + students[i].height);
		}
				

		
		
	}
} //end of main.
