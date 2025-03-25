package com.yedam.reserve;

import java.util.List;
import java.util.Scanner;

public class ReserveApp {
	private static Scanner scn = new Scanner(System.in);
	private static ReserveJdbc dao = new ReserveJdbc();
	private static String loginUser;

	public static void main(String[] args) {
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
				loginMenu();
				break;
			case 2: // 객실목록 조회(로그인 필요)
				if (loginUser == null) {
					System.out.println("로그인 후 이용해주세요.");
				} else {
					subMenu();
				}
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

	private static void loginMenu() {
		while (true) {
			System.out.println("-----------------------------------------");
			System.out.println("1.로그인 2.회원가입 3.비회원 객실조회 4.뒤로가기");
			System.out.println("_________________________________________");
			System.out.println("선택 >> ");
			int loginMenu = scn.nextInt();

			switch (loginMenu) {
			case 1: // 로그인
				login();
				if (loginUser != null) {
					subMenu();
				}
				return;
			case 2: // 회원가입
				signUp();
				break;
			case 3:
				guest();
				break;
			case 4:
				return;
			default:
				System.out.println("다시 입력해 주세요.");
			}
		}
	}

	private static void subMenu() {
		while (true) {
			System.out.println("-----------------------------------------");
			System.out.println("1.객실예약 \n2.예약조회 \n3.체크인 \n4.체크아웃 \n5.리뷰 게시판 \n9.로그아웃");
			System.out.println("_________________________________________");
			System.out.println("메뉴 선택 >> ");
			int reserveMenu = scn.nextInt();

			switch (reserveMenu) {
			case 1:
				reserveRoom();
				break;
			case 2:
				checkReserve();
				break;
			case 3:
				checkIn();
				break;
			case 4:
				checkOut();
				break;
			case 5:
				reviewMenu();
				break;
			case 9:
				System.out.println("로그아웃 되었습니다.");
				loginUser = null;
				return;
			default:
				System.out.println("다시 입력해주세요.");
			}
		}
	}

	private static void reviewMenu() {
		while (true) {
			System.out.println("-----------------------------------------");
			System.out.println("1.후기 작성\n 2.전체후기 조회\n 3.제목+내용으로 검색\n 4.게시글 삭제\n 9.종료");
			System.out.println("_________________________________________");
			System.out.println("선택 > ");
			int reviewMenu = scn.nextInt();
			
			switch (reviewMenu) {
			case 1:
				System.out.println("후기작성");
				writeReview();
				break;
			case 2:
				System.out.println("전체조회");
//				searchAll();
				break;
			case 3:
				System.out.println("제목+내용으로 검색");
//				searchReview();
				break;
			case 4:
				System.out.println("후기 삭제");
//				deleteReview();
				break;
			case 9:
				System.out.println("종료");
				return;
			default:
				System.out.println("다시 입력해 주세요.");
			}
		}
	}

	// 로그인 기능
	private static void login() {
		scn.nextLine();
		System.out.println("ID입력 > ");
		String id = scn.nextLine();
		System.out.println("password입력 > ");
		String pw = scn.nextLine();

		Customer customer = dao.login(id, pw);

		if (customer != null) {
			loginUser = customer.getCustomerName();
			System.out.println("로그인 성공" + "\n" + customer.getCustomerName() + "님, 어서오세요.");
		} else {
			System.out.println("아이디 또는 비밀번호를 확인하세요.");
		}
	}

	// 회원가입
	private static void signUp() {
		scn.nextLine();
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

	// 비회원 객실목록 출력
	private static void guest() {
		ReserveJdbc dao = new ReserveJdbc();
		List<Reservation> rooms = dao.roomList();
		System.out.println("-----------------------------------------");
		System.out.println("\t\t예약가능 객실");

		int count = 0;
		for (Reservation room : rooms) {
			System.out.printf("%d호: %s | ", room.getRoomNumber(), room.getRoomType());
			count++;
			if (count % 5 == 0) {
				System.out.println();
			}
		}
		System.out.println("예약가능: O 예약중: X 이용중: -");
		System.out.println("\t'비회원은 객실 조회만 가능합니다.'");
		System.out.println("뒤로가기: q");
		System.out.println("___________________________________________");
		System.out.print("입력 > ");
		String back = scn.next();

		while (!back.equalsIgnoreCase("q")) {
			System.out.println("다시 입력 해주세요. ");
			System.out.print("입력 > ");
			back = scn.next();
		}
	}

	// 객실예약
	private static void reserveRoom() {
		List<Reservation> rooms = dao.roomList();
		System.out.println("-----------------------------------------");
		System.out.println("\t객실현황");

		int count = 0;
		for (Reservation room : rooms) {
			String status = room.getRoomType();
			if (status.equals("O"))
				status = "O";
			else if (status.equals("X"))
				status = "X";
			else if (status.equals("-"))
				status = "-";

			System.out.printf("%d호: %s |", room.getRoomNumber(), status);
			count++;
			if (count % 5 == 0)
				System.out.println();
		}
		System.out.println("예약가능: O 예약중: X 이용중: -");
		System.out.println("___________________________________________");
		System.out.println("예약하실 객실번호를 입력하세요 > ");
		int roomNo = scn.nextInt();
		scn.nextLine();

		System.out.println("이름을 입력하세요 > ");
		String name = scn.nextLine();

		System.out.println("연락처를 입력하세요 > ");
		String tel = scn.nextLine();

		if (dao.reserveRoom(roomNo, name, tel)) {
			System.out.println("예약이 완료되었습니다.");
		} else {
			System.out.println("예약을 실패했습니다. 다시 시도하세요.");
		}
	}

	// 예약확인
	private static void checkReserve() {
	    scn.nextLine(); // 버퍼 비우기
	    System.out.println("예약자명 입력 > ");
	    String name = scn.nextLine();

	    System.out.println("예약자연락처 입력 > ");
	    String tel = scn.nextLine();

	    List<Reservation> reservations = dao.getReservations(name, tel);

	    if (!reservations.isEmpty()) {
	        System.out.println("-----------------------------------------");
	        System.out.println("총 예약 개수: " + reservations.size());
	        for (Reservation res : reservations) {
	            System.out.println("객실 번호: " + res.getRoomNumber() + "호");
	            System.out.println("예약자명: " + res.getReserveName());
	            System.out.println("연락처: " + res.getReserveTel());
	            System.out.println("예약일: " + res.getReserveDate());
	            System.out.println("-----------------------------------------");
	        }
	    } else {
	        System.out.println("예약정보를 다시 확인해주세요");
	    }
	}


	// 체크인 기능
	private static void checkIn() {
		System.out.println("객실번호를 입력해주세요 > ");
		int roomNo = scn.nextInt();
		scn.nextLine();

		System.out.println("예약자명을 입력해주세요 > ");
		String name = scn.nextLine();

		if (dao.checkIn(roomNo, name)) {
			System.out.println("체크인 되었습니다.");
		} else {
			System.out.println("예약정보를 다시 확인해주세요.");
		}
	}
	//체크아웃
	private static void checkOut() {
		System.out.println("객실번호를 입력해주세요 > ");
		int roomNo = scn.nextInt();
		scn.nextLine();

		System.out.println("예약자명을 입력해주세요 > ");
		String name = scn.nextLine();

		if (dao.checkOut(roomNo, name)) {
			System.out.println("체크아웃되었습니다. 안녕히가십시오.");
		} else {
			System.out.println("예약정보를 다시 확인해주세요.");
		}
	}
	// 후기작성
	private static void writeReview() {
		scn.nextLine();
		System.out.println("제목을 입력하세요 > ");
		String title = scn.nextLine();
		
		System.out.println("내용을 입력하세요 > ");
		String content = scn.nextLine();
		
		if(dao.writeReview(title, content, loginUser)) {
			System.out.println("게시글이 작성되었습니다.");
		}else {
			System.out.println("게시글이 작성되지 않았습니다.");
		}
	}

}
