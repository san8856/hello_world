package com.yedam.variable;

import java.util.Scanner;

//추가, 수정, 삭제, 목록 출력.

public class VarExe7 {
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		boolean run =  true;
		//Member 값을 저장할수 있도록 
		Member[] storage = new Member[100]; //{null,null, ...null}
		storage[0] = new Member("홍길동",83);
		storage[1] = new Member("김민혁",86);
		storage[2] = new Member("한수아",90);			
				
		while(run) {
			System.out.println("1.등록 2.수정 3.삭제 4.목록 5.평균 6.종료");
			System.out.print("선택>> ");
			int menu = Integer.parseInt(scn.nextLine());
			//int menu = scn.nextInt(); 
			//1 > enter. enter 키 값 처리 해야함
			
			switch(menu) {
			case 1: 
				System.out.print("이름을 입력하세요>> ");
				String name = scn.nextLine();
				System.out.print("점수를 입력하세요>> ");
				int score = Integer.parseInt(scn.nextLine());
				Member member = new Member(); //인스턴스 생성
				//member.name = name;
				//member.score = score;
				member.setMember(name, score);
				//빈 공간에 값을 할당.
				for (int i=0; i<storage.length; i++) {
					if (storage[i] == null) {
						storage[i] = member;
						break; //for 반복문 종료.
					}
				}
				break;  //case 1에 대한 종료
			case 2: 
				System.out.print("수정할 이름을 입력하세요>> ");
				name = scn.nextLine();
				boolean isExist = false; //존재여부를 체크.7
				for (int i=0; i<storage.length; i++) {
					if (storage[i] !=null && storage[i].getName().equals(name)) {
						System.out.print("이름을 입력하세요>> ");
						String rn = scn.nextLine();
						System.out.print("점수를 입력하세요>> ");
						int rs = Integer.parseInt(scn.nextLine());
//						storage[i].getName() = rn;
//						storage[i].getScore() = rs;
						storage[i].setMember(rn,rs);
						System.out.println("수정되었습니다");
						isExist = true; //체크확인
						break;
					}
				}
				if (!isExist) {						
					System.out.println("이름을 찾을 수 없습니다.");
				}
				break;
			case 3:	//삭제.이름입력, 조회, 해당 위치 값을 null 로 대입
				System.out.print("삭제할 이름을 입력하세요>> ");
				name = scn.nextLine();
				for(int i=0; i<storage.length; i++) {
					if (storage[i] != null && storage[i].getName().equals(name)) {   //null이 아닌값 선택, 저장된 이름과 같은지 조회,
						storage[i] = null;                                      //찾은 이름을 null로 대입
						System.out.println("삭제되었습니다.");
						break;
					}
				}
				break;
			case 4: //전체 목록 출력
				for(int i=0; i<storage.length; i++) {
					if(storage[i] != null) {
						System.out.println(storage[i].getName() + " " + storage[i].getScore());
					}
				}
				break;
			case 5:
				int sum = 0, count = 0;
				for(int i=0; i<storage.length; i++) {
					if(storage[i] !=null) {
						sum = sum + storage[i].getScore();
						count++;
					}
				}
				double avg = sum * 1.0 / count;
				System.out.println("평균은 " + avg + "입니다.");
				break;
			
			case 6:
				run =false;
			}
		}
		System.out.println("end of prog.");
		
		
		
		
		
		
		
	} //end of main().
}
