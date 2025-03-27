package com.yedam.reserve;

import java.util.List;
import java.util.Scanner;

public class ReserveApp {
	// Scanner 객체를 이용해 사용자 입력을 받기 위한 객체
	private static Scanner scn = new Scanner(System.in);
	// 데이터베이스 연동을 위한 ReserveJdbc 객체 (예약 관련 DB 작업 처리)
	private static ReserveJdbc dao = new ReserveJdbc();
	// 현재 로그인된 사용자의 ID를 저장하는 변수 (로그인 상태 관리용)
	private static String loginUser;

	// 메인 메뉴
	public static void main(String[] args) {
		while (true) {
			System.out.println("=========================================");
			System.out.println("\t숙소예약");
			System.out.println("-----------------------------------------");
			System.out.println("1.로그인 2.비회원 객실조회 3.종료");
			System.out.println("_________________________________________");
			System.out.print("선택 >> ");
			int mainMenu = scn.nextInt();

			switch (mainMenu) {
			case 1: // 로그인메뉴
				loginMenu();
				break;
			case 2: // 비회원 객실목록 조회
				guest();
				break;
			case 3:// 종료
				System.out.println("프로그램을 종료합니다.");
				scn.close();
				return;
			default:
				System.out.println("메뉴를 다시 선택하세요.");
			}
		}
	} // end of main().

	// 로그인 메뉴
	private static void loginMenu() {
		while (true) {
			System.out.println("-----------------------------------------");
			System.out.println("1.로그인 2.회원가입 3.뒤로가기");
			System.out.println("_________________________________________");
			System.out.print("선택 >> ");
			int loginMenu = scn.nextInt();

			switch (loginMenu) {
			case 1: // 로그인 기능 실행
				login();
				if (loginUser != null) {
					subMenu(); // 로그인 성공시 subMenu 메뉴 실행
				}
				return;
			case 2: // 회원가입 기능 실행
				signUp();
				break;

			case 3: // 뒤로가기
				return;
			default:
				System.out.println("다시 입력해 주세요.");
			}
		}
	}// end of loginMenu()

	// 객실예약 메뉴
	private static void subMenu() {
		while (true) {
			System.out.println("-----------------------------------------");
			System.out.println("1.객실조회 및 예약 \n2.예약조회 \n3.체크인 \n4.체크아웃 \n5.리뷰 게시판 \n9.로그아웃");
			System.out.println("_________________________________________");
			System.out.print("선택 >> ");
			int reserveMenu = scn.nextInt();

			switch (reserveMenu) {
			case 1:
				reserveRoom(); // 객실 조회 및 예약 기능
				break;
			case 2:
				checkReserve(); // 예약 조회 기능
				break;
			case 3:
				checkIn(); // 체크인 기능
				break;
			case 4:
				checkOut(); // 체크아웃 기능
				break;
			case 5:
				reviewMenu(); // 후기 게시판으로 이동하는 기능
				break;
			case 9:
				System.out.println("로그아웃 되었습니다.");
				loginUser = null; // 로그아웃 기능
				return;
			default:
				System.out.println("다시 입력해주세요.");
			}
		}
	}// end of subMenu().

	// 후기 게시판 메뉴
	private static void reviewMenu() {
		while (true) {
			System.out.println("\t\t 후기 게시판");
			System.out.println("-----------------------------------------");
			System.out.println("1.후기 작성\n2.전체후기 조회\n3.제목+내용으로 검색\n4.게시글 삭제\n9.뒤로가기");
			System.out.println("_________________________________________");
			System.out.print("선택 >> ");
			int reviewMenu = scn.nextInt();

			switch (reviewMenu) {
			case 1:
				writeReview(); // 후기 작성 기능
				break;
			case 2:
				searchAll(); // 후기 전체 검색 기능
				break;
			case 3:
				searchReview(); // 제목 또는 내용으로 조건 검색 기능
				break;
			case 4:
				deleteReview(); // 후기 삭제 기능
				break;
			case 9:
				return; // 뒤로가기
			default:
				System.out.println("다시 입력해 주세요.");
			}
		}
	}// end of reviewMenu().

	// 비회원 객실목록 출력
	private static void guest() {
		List<Reservation> rooms = dao.roomList(); // 데이터베이스에서 조회
		System.out.println("-----------------------------------------");
		System.out.println("\t\t객실 예약 현황");

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
	} // end of guest().

	// 로그인 기능
	// 사용자의 입력을 받아 데이터베이스를 통해 정보를 조회하여 로그인 검증 수행.
	// 로그인 성공 시 loginUser에 사용자 ID 저장.
	private static void login() {
		scn.nextLine();
		System.out.print("ID입력 > ");
		String id = scn.nextLine();
		System.out.print("password입력 > ");
		String pw = scn.nextLine();

		Customer customer = dao.login(id, pw); // 로그인 검증
		// 성공여부 확인
		if (customer != null) {
			loginUser = customer.getCustomerId();
			System.out.println("로그인 성공" + "\n" + customer.getCustomerName() + "님, 어서오세요.");
		} else {
			System.out.println("아이디 또는 비밀번호를 확인하세요.");
		}
	} // end of login().

	// 회원가입 기능
	// ID, 비밀번호, 이름을 입력받아 받은 정보를 기반으로 Customer 객체 생성 후 데이터베이스에 저장
	private static void signUp() {
		scn.nextLine();
		System.out.print("새로운 ID 입력 > ");
		String id = scn.nextLine();
		System.out.print("새로운 Password 입력 > ");
		String pw = scn.nextLine();
		System.out.print("이름 입력 > ");
		String name = scn.nextLine();

		if (dao.signUp(new Customer(id, name, pw))) {
			System.out.println("회원가입을 완료했습니다. " + name + "님, 환영합니다.");
		} else {
			System.out.println("이미 존재하는 ID입니다.");
		}
	} // end of signUp().

	// 객실조회 및 예약 기능
	// 객실 예약 현황을 출력
	// 1을 입력하면 예약 진행, q를 입력하면 메뉴로 복귀
	private static void reserveRoom() {
		List<Reservation> rooms = dao.roomList(); // 객실목록 조회
		System.out.println("-----------------------------------------");
		System.out.println("\t객실 예약 현황");

		int count = 0;
		for (Reservation room : rooms) { // 객실목록 출력
			String status = room.getRoomType();
			System.out.printf("%d호: %s |", room.getRoomNumber(), status);
			count++;
			if (count % 5 == 0)
				System.out.println();
		}
		System.out.println("예약가능: O 예약중: X 이용중: -");
		System.out.println("예약하기 : 1	뒤로가기 : q ");
		System.out.println("___________________________________________");

		String input;
		while (true) {
			System.out.print("입력 > ");
			input = scn.next();

			if (input.equalsIgnoreCase("q")) {
				System.out.println("메뉴로 돌아갑니다.");
				return;
			} else if (input.equals("1")) {
				break;
			} else {
				System.out.println("1 또는 q 를 입력하세요.");
			}
		}
		// 예약 진행
		System.out.print("예약하실 객실번호를 입력하세요 > ");
		int roomNo = scn.nextInt();
		scn.nextLine();

		System.out.print("이름을 입력하세요 > ");
		String name = scn.nextLine();

		System.out.print("연락처를 입력하세요 > ");
		String tel = scn.nextLine();

		if (dao.reserveRoom(roomNo, name, tel)) {
			System.out.println("예약이 완료되었습니다.");
		} else {
			System.out.println("예약을 실패했습니다. 다시 시도하세요.");
		}

	} // end of reserveRoom().

	// 예약 확인
	// 예약자명과 연락처를 입력하면 해당 예약 정보 조회
	private static void checkReserve() {
		scn.nextLine();
		System.out.print("예약자명 입력 > ");
		String name = scn.nextLine();
		System.out.print("예약자연락처 입력 > ");
		String tel = scn.nextLine();

		Reservation reservation = dao.getReservation(name, tel); // 예약정보 조회
		if (reservation != null) {
			System.out.println("-----------------------------------------");
			System.out.println("객실 번호: " + reservation.getRoomNumber() + "호");
			System.out.println("예약자명: " + reservation.getReserveName());
			System.out.println("연락처: " + reservation.getReserveTel());
			System.out.println("예약일: " + reservation.getReserveDate());
		} else {
			System.out.println("예약정보를 다시 확인해주세요");
		}
	} // end of checkReserve().

	// 체크인 기능
	// 객실 번호와 예약자명을 입력하여 체크인 처리
	private static void checkIn() {
		System.out.print("객실번호를 입력해주세요 > ");
		int roomNo = scn.nextInt();
		scn.nextLine();

		System.out.print("예약자명을 입력해주세요 > ");
		String name = scn.nextLine();

		if (dao.checkIn(roomNo, name)) {
			System.out.println("체크인 되었습니다.");
		} else {
			System.out.println("예약정보를 다시 확인해주세요.");
		}
	} // end of checkIn().

	// 체크아웃 기능
	// 객실 번호와 예약자명을 입력하여 체크아웃 처리
	private static void checkOut() {
		System.out.print("객실번호를 입력해주세요 > ");
		int roomNo = scn.nextInt();
		scn.nextLine();

		System.out.print("예약자명을 입력해주세요 > ");
		String name = scn.nextLine();

		if (dao.checkOut(roomNo, name)) {
			System.out.println("체크아웃되었습니다. 안녕히가십시오.");
		} else {
			System.out.println("예약정보를 다시 확인해주세요.");
		}
	} // end of checkOut().

	// 후기 작성
	//제목과 내용을 입력하여 후기 게시글을 작성합니다.
	private static void writeReview() {
		scn.nextLine();
		System.out.print("제목을 입력하세요 > ");
		String title = scn.nextLine();

		System.out.println("내용을 입력하세요 > ");
		String content = scn.nextLine();

		if (dao.writeReview(title, content, loginUser)) { //후기를 DB에 저장
			System.out.println("게시글이 작성되었습니다.");
		} else {
			System.out.println("게시글이 작성되지 않았습니다.");
		}
	} // end of writeReview().

	// 전체 리뷰 조회
	//데이터베이스에서 모든 리뷰를 조회하여 번호, 제목, 작성자, 작성일, 조회수 등을 출력
	//하나를 선택해 상세조회 기능으로 이동
	private static void searchAll() {
		List<Review> reviews = dao.getAllReviews(); //dao로 모든 후기 목록 가져옴
		System.out.println("----------------------------------------------");
		System.out.println("번호\t제목\t작성자\t작성일\t\t조회수");

		for (Review review : reviews) {
			System.out.printf("%d\t%s\t%s\t%s\t%d\n", review.getReviewNo(), review.getTitle(), review.getCustomerId(),
					review.getWriteDate(), review.getViewCnt());
		}

		System.out.println("열람할 글의 번호를 입력하세요.    메뉴로 이동 : q");
		System.out.print("입력 > ");
		String input = scn.next();

		if (!input.equalsIgnoreCase("q")) {
			try {
				int reviewNo = Integer.parseInt(input); //입력받은 정수로 후기 상세조회 실행
				readReview(reviewNo);
			} catch (NumberFormatException e) {
				System.out.println("다시 입력해주세요.");
			}
		}
	} // end of searchAll().

	// 상세조회
	// 정수를 입력받아 해당 후기의 상세 내용을 출력하고, 조회수를 증가
	private static void readReview(int reviewNo) {
		dao.updateViewCount(reviewNo); //조회수 증가
		Review review = dao.getReview(reviewNo); //정수로 게시글 정보 가져옴

		if (review == null) {
			System.out.println("게시글의 번호를 확인해주세요.");
			return;
		}
		System.out.println("----------------------------------------------");
		System.out.println("번호: " + review.getReviewNo());
		System.out.println("제목: " + review.getTitle());
		System.out.println("내용: \n" + review.getContent());
		System.out.println(" ");
		System.out.println("작성자: " + review.getCustomerId());
		System.out.println("작성일: " + review.getWriteDate());
		System.out.println("조회수: " + review.getViewCnt());
		System.out.println("______________________________________________");
		System.out.println("메뉴로 이동: q");
		System.out.print("입력 > ");
		String input = scn.next();

		while (!input.equalsIgnoreCase("q")) {
			System.out.println("뒤로 가려면 'q' 를 입력하세요.");
			System.out.print("입력 > ");
			input = scn.next();
		}
	} // end of readReview().

	// 제목+내용으로검색
	// 입력한 키워드로 제목이나 내용에 해당하는 리뷰를 검색하여 결과를 출력, 원하는 게시글 상세조회
	private static void searchReview() {
		scn.nextLine();
		System.out.print("검색할 제목 또는 내용을 입력하세요 > ");
		String keyword = scn.nextLine();//키워드 입력

		List<Review> reviews = dao.searchKeyword(keyword); //키워드와 겹치는 게시글 가져옴

		if (reviews.isEmpty()) {
			System.out.println("일치하는 결과가 없습니다.");
			return;
		}
		System.out.println("----------------------------------------------");
		System.out.println("번호\t제목\t작성자\t작성일\t\t조회수");

		for (Review review : reviews) {
			System.out.printf("%d\t%s\t%s\t%s\t%d\n", review.getReviewNo(), review.getTitle(), review.getCustomerId(),
					review.getWriteDate(), review.getViewCnt());
		}
		System.out.println("______________________________________________");
		System.out.println("열람할 글의 번호를 입력하세요.    메뉴로 이동 : q");
		System.out.print("입력 > ");
		String input = scn.next();

		if (input.equalsIgnoreCase("q")) {
			return;
		}

		try {
			int reviewNo = Integer.parseInt(input);
			readReview(reviewNo); // 상세 조회 기능 호출
			return;
		} catch (NumberFormatException e) {
			System.out.println("다시 입력해주세요.");
		}
	} // end of searchReview().

	// 리뷰 삭제 기능
	// 입력받은 정수에 해당하는 후기 삭제.
	// 삭제전 확인 메세지 출력, 작성자가 동일하지 않을 시 삭제 불가
	private static void deleteReview() {
		System.out.print("삭제할 게시글의 번호를 입력하세요 > ");

		if (!scn.hasNextInt()) {
			System.out.println("게시글 번호를 확인해주세요.");
			scn.next();
			return;
		}

		int reviewNo = scn.nextInt();

		
		System.out.print("정말 삭제하시겠습니까? (y/n) > ");
		String confirm = scn.next();

		if (!confirm.equalsIgnoreCase("y")) {
			System.out.println("취소되었습니다.");
			return;
		}

		boolean result = dao.deleteReview(reviewNo, loginUser); //작성자(ID)가 동일하면 삭제, 다르면 실패
		
		if (result) {
			System.out.println("리뷰가 삭제되었습니다.");
		}
	} // end of deleteReview().

}
