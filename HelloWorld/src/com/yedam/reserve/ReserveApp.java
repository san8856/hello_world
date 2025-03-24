package com.yedam.reserve;

import java.util.List;
import java.util.Scanner;

import com.yedam.test.Customer;
import com.yedam.test.TestJdbc;

public class ReserveApp {
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		ReserveJdbc dao = new ReserveJdbc();

		while (true) {
			System.out.println("=========================================");
			System.out.println("\t숙소예약");
			System.out.println("-----------------------------------------");
			System.out.println("1.로그인 2.객실예약 3.종료");
			System.out.println("_________________________________________");
			System.out.println("선택 >>");
			int mainMenu = scn.nextInt();

			switch (mainMenu) {
			case 1: // 로그인메뉴
				System.out.println("-----------------------------------------");
				System.out.println("1.로그인 2.회원가입 3.비회원 객실조회");
				System.out.println("_________________________________________");
				System.out.println("선택 >> ");

				int loginMenu = scn.nextInt();
				switch (loginMenu) {
				case 1: // 로그인
					login();
					break;
				case 2: // 회원가입
					signUp();
					break;
				case 3:
					roomList();
					break;
				default:
					System.out.println("다시 입력해 주세요.");
				}
				break;

			case 2: // 객실목록 조회(로그인 필요)
				System.out.println("-----------------------------------------");
				System.out.println("로그인 후 이용해주세요.");
				break;

			case 3:// 종료
				System.out.println("프로그램을 종료합니다.");
				scn.close();
				return;
			default:
				System.out.println("메뉴를 다시 선택하세요.");
			}
		}
	}

	// 로그인 기능
	private static void login() {
		Scanner scn = new Scanner(System.in);
		ReserveJdbc dao = new ReserveJdbc();
		System.out.println("ID입력 > ");
		String id = scn.nextLine();
		System.out.println("password입력 > ");
		String pw = scn.nextLine();

		Customer customer = dao.login(id, pw);

		if (customer != null) {
			System.out.println("로그인 성공" + "\n" + customer.getCustomerName() + "님, 어서오세요.");
		} else {
			System.out.println("아이디 또는 비밀번호를 확인하세요.");
		}
	}

	//회원가입
	private static void signUp() {
	    Scanner scn = new Scanner(System.in);
	    ReserveJdbc dao = new ReserveJdbc();

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

	// 객실목록 출력
	private static void roomList() {
		ReserveJdbc dao = new ReserveJdbc();
		List<Reservation> rooms = dao.roomList();
		System.out.println("-----------------------------------------");
		System.out.println("\t\t예약가능 객실");

		int count = 0;
		for (Reservation room : rooms) {
			System.out.printf("%s: %s | ", room.getRoomNumber(), room.getRoomType());
			count++;
			if (count % 5 == 0) {
				System.out.println();
			}
		}

		System.out.println("\t'비회원은 객실 조회만 가능합니다.'");
		System.out.println("뒤로가기: q");
		System.out.println("___________________________________________");
		System.out.print("입력 > ");

		Scanner scanner = new Scanner(System.in);
		String back = scanner.next();

		while (!back.equalsIgnoreCase("q")) {
			System.out.println("다시 선택해주세요.");
			System.out.print("입력 > ");
			back = scanner.next();
		}
	}
}