package com.yedam.test;

import java.util.List;
import java.util.Scanner;

public class TestRun {
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		TestJdbc dao = new TestJdbc(); // 데이터베이스 접근 객체 생성

		while (true) {
			System.out.println("=========================");
			System.out.println("\t숙소예약");
			System.out.println("-----------------------------------------");
			System.out.println("1. 로그인  2. 객실목록  3. 종료");
			System.out.println("_________________________________________");
			System.out.print("페이지 선택>> ");
			int pageChoice = scn.nextInt();

			switch (pageChoice) {
			case 1: // 로그인 메뉴
				System.out.println("-----------------------------------------");
				System.out.println("1. 로그인  2. 회원가입  3. 비회원 객실 조회");
				System.out.println("_________________________________________");
				System.out.print("선택>> ");
				int loginChoice = scn.nextInt();

				switch (loginChoice) {
				case 1: // 로그인
					login();
					break;
				case 2: // 회원가입
					signUp();
					break;
				case 3: // 비회원 객실 조회
					roomList();
					break;
				default:
					System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
				}
				break;

			case 2: // 객실 목록 조회 (로그인 필요)
				System.out.println("-----------------------------------------");
				System.out.println("로그인 후 이용해주세요.");
				break;

			case 3: // 종료
				System.out.println("프로그램을 종료합니다.");
				scn.close();
				return;

			default:
				System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
			}
		}
	}

	//로그인
	private static void login() {
		Scanner scn = new Scanner(System.in);
		TestJdbc dao = new TestJdbc();
		System.out.print("id입력>> ");
		String id = scn.nextLine();
		System.out.print("password입력>> ");
		String pw = scn.nextLine();
		
		Customer customer = dao.login(id,pw);
		
		if(customer != null) {
			System.out.println(customer.getCustomerName() + "님, 어서오세요");
		} else {
			System.out.println("아이디 또는 비밀번호를 확인하세요.");
		}
	}
	
	//회원가입
	private static void signUp() {
	    Scanner scn = new Scanner(System.in);
	    TestJdbc dao = new TestJdbc();

	    System.out.print("새로운 ID 입력>> ");
	    String id = scn.nextLine();
	    System.out.print("새로운 Password 입력>> ");
	    String pw = scn.nextLine();
	    System.out.print("이름 입력>> ");
	    String name = scn.nextLine();

	    if (dao.signUp(new Customer(id, name, pw))) {
	        System.out.println("회원가입 성공! " + name + "님, 환영합니다!");
	    } else {
	        System.out.println("회원가입 실패. 이미 존재하는 ID입니다.");
	    }
	}
	
	// 객실 목록 출력 메서드
	private static void roomList() {
		TestJdbc dao = new TestJdbc(); // 데이터베이스 접근 객체 생성
		List<Test> rooms = dao.roomList();
		System.out.println("-----------------------------------------");
		System.out.println("현재 객실");

		int count = 0;
		for (Test room : rooms) {
			System.out.printf("%s: %s | ", room.getRoomNumber(), room.getRoomType());
			count++;
			if (count % 5 == 0) {
				System.out.println();
			}
		}

		System.out.println("나가기: 1");
		System.out.println("___________________________________________");
		System.out.print("입력> ");
		Scanner scanner = new Scanner(System.in);
		int exitChoice = scanner.nextInt();
		if (exitChoice != 1) {
			System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
		}
	}
	
}
