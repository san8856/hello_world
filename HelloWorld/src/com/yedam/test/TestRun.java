package com.yedam.test;

import java.util.List;
import java.util.Scanner;

import com.yedam.reserve.Review;

public class TestRun {
	private static Scanner scn = new Scanner(System.in);
	private static TestJdbc dao = new TestJdbc();
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
				checkReservation();
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
				return;
			default:
				System.out.println("다시 입력해주세요.");
			}
		}
	}


	private static void reviewMenu() {
		while (true) {
			System.out.println("-----------------------------------------");
			System.out.println("1.후기 작성\n2.전체후기 조회\n3.제목+내용으로 검색\n4.게시글 삭제\n9.종료");
			System.out.println("_________________________________________");
			System.out.println("선택 > ");
			int reviewMenu = scn.nextInt();

			switch (reviewMenu) {
			case 1:
				writeReview();
				break;
			case 2:
				searchAll();
				break;
			case 3:
				searchReview();
				break;
			case 4:
				deleteReview();
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

	// 객실목록 출력
	private static void guest() {
		TestJdbc dao = new TestJdbc();
		List<Test> rooms = dao.roomList();
		System.out.println("-----------------------------------------");
		System.out.println("\t\t예약가능 객실");

		int count = 0;
		for (Test room : rooms) {
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
			System.out.println("다시 선택해주세요.");
			System.out.print("입력 > ");
			back = scn.next();
		}
	}

	// 객실 예약 기능 추가
	private static void reserveRoom() {
		List<Test> rooms = dao.roomList();
		System.out.println("-----------------------------------------------");
		System.out.println("\t\t객실 예약 현황");

		int count = 0;
		for (Test room : rooms) {
			String status = room.getRoomType();
			if (status.equals("O"))
				status = "O";
			else if (status.equals("X"))
				status = "X";
			else if (status.equals("-"))
				status = "-";

			System.out.printf("%d호%s  ", room.getRoomNumber(), status);
			count++;
			if (count % 5 == 0)
				System.out.println();
		}
		System.out.println("예약가능: O 예약중: X 이용중: -");
		System.out.println("\n-----------------------------------------------");
		System.out.print("예약하실 객실번호를 입력하세요 > ");
		int roomNo = scn.nextInt();
		scn.nextLine();

		System.out.print("이름> ");
		String name = scn.nextLine();

		System.out.print("연락처> ");
		String phone = scn.nextLine();

		if (dao.reserveRoom(roomNo, name, phone)) {
			System.out.println("예약이 완료되었습니다.");
		} else {
			System.out.println("예약에 실패했습니다. 다시 시도해주세요.");
		}
	}

	private static void checkReservation() {
		scn.nextLine(); // 버퍼 비우기
		System.out.print("예약하신 분의 이름 입력> ");
		String name = scn.nextLine();

		System.out.print("연락처 입력> ");
		String tel = scn.nextLine();

		Test test = dao.getTest(name, tel);

		if (test != null) {
			System.out.println("-----------------------------------------");
			System.out.println("예약한 방: " + test.getRoomNumber() + "호");
			System.out.println("예약한 날짜: " + test.getReserveDate());
			System.out.println("예약자: " + test.getReserveName());
			System.out.println("연락처: " + test.getReserveTel());
			System.out.println("-----------------------------------------");
		} else {
			System.out.println("예약 정보를 찾을 수 없습니다.");
		}
	}

	// 체크인 기능
	private static void checkIn() {
		System.out.print("방번호를 입력해주세요> ");
		int roomNo = scn.nextInt();
		scn.nextLine();

		System.out.print("예약자명을 입력해주세요> ");
		String name = scn.nextLine();

		if (dao.checkIn(roomNo, name)) {
			System.out.println("체크인 되었습니다.");
		} else {
			System.out.println("체크인 실패! 예약 정보를 확인하세요.");
		}
	}

	// 체크아웃 기능
	private static void checkOut() {
		System.out.print("방번호를 입력해주세요> ");
		int roomNo = scn.nextInt();
		scn.nextLine();

		System.out.print("예약자명을 입력해주세요> ");
		String name = scn.nextLine();

		if (dao.checkOut(roomNo, name)) {
			System.out.println("체크아웃 되었습니다.");
		} else {
			System.out.println("체크아웃 실패! 예약 정보를 확인하세요.");
		}
	}
	//후기작성
	private static void writeReview() {
	    scn.nextLine();
	    System.out.print("글 제목을 입력해주세요> ");
	    String title = scn.nextLine();

	    System.out.print("내용을 입력해주세요 > ");
	    String content = scn.nextLine();

	    if (loginUser == null) {
	        System.out.println("로그인 후 이용해주세요.");
	        return;
	    }

	    if (dao.writeReview(title, content, loginUser)) {
	        System.out.println("게시글이 작성되었습니다.");
	    } else {
	        System.out.println("게시글 작성에 실패했습니다.");
	    }
	}
	//전체조회
	private static void searchAll() {
	    List<Review> reviews = dao.getAllReviews();

	    System.out.println("----------------------------------------------");
	    System.out.println("번호\t제목\t작성자\t작성일\t조회수");

	    for (Review review : reviews) {
	        System.out.printf("%d\t%s\t%s\t%s\t%d\n",
	                review.getReviewNo(), review.getTitle(), review.getCustomerId(),
	                review.getWriteDate(), review.getViewCnt());
	    }

	    System.out.println("열람할 글의 번호 입력 > (번호)  메뉴로 이동 : q");
	    System.out.print("입력> ");
	    String input = scn.next();

	    if (!input.equalsIgnoreCase("q")) {
	        try {
	            int reviewNo = Integer.parseInt(input);
	            readReview(reviewNo);
	        } catch (NumberFormatException e) {
	            System.out.println("잘못된 입력입니다. 메뉴로 돌아갑니다.");
	        }
	    }
	}
	
	private static void readReview(int reviewNo) {
	    dao.updateViewCount(reviewNo); // 조회수 증가
	    Review review = dao.getReview(reviewNo); // 증가된 조회수 반영하여 다시 가져오기

	    if (review == null) {
	        System.out.println("해당 번호의 게시글이 존재하지 않습니다.");
	        return;
	    }

	    System.out.println("----------------------------------------------");
	    System.out.println("번호: " + review.getReviewNo());
	    System.out.println("제목: " + review.getTitle());
	    System.out.println("작성자: " + review.getCustomerId());
	    System.out.println("작성일: " + review.getWriteDate());
	    System.out.println("조회수: " + review.getViewCnt());
	    System.out.println("내용: \n" + review.getContent());
	    System.out.println("----------------------------------------------");

	    System.out.println("뒤로 가려면 'm'을 입력하세요.");
	    System.out.print("입력> ");
	    String input = scn.next();

	    while (!input.equalsIgnoreCase("m")) {
	        System.out.println("잘못된 입력입니다. 뒤로 가려면 'm'을 입력하세요.");
	        System.out.print("입력> ");
	        input = scn.next();
	    }
	}
	private static void searchReview() {
		scn.nextLine();
		System.out.println("검색할 제목 또는 내용을 입력하세요 > ");
		String keyword = scn.nextLine();

		List<Review> reviews = dao.searchKeyword(keyword);

		if (reviews.isEmpty()) {
			System.out.println("일치하는 결과가 없습니다.");
			return;
		}
		System.out.println("----------------------------------------------");
		System.out.println("번호\t제목\t작성자\t작성일\t조회수");

		for (Review review : reviews) {
			System.out.printf("%d\t%S\t%s\t%s\t%d\n", review.getReviewNo(), review.getTitle(), review.getCustomerId(),
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
		}
	
	// 리뷰 삭제 기능
	private static void deleteReview() {
	    System.out.print("삭제할 게시글의 번호를 입력하세요 > ");
	    
	    if (!scn.hasNextInt()) {
	        System.out.println("게시글 번호를 확인해주세요.");
	        scn.next(); // 잘못된 입력 제거
	        return;
	    }
	    
	    int reviewNo = scn.nextInt();
	    
	    // 삭제 확인 메시지
	    System.out.print("정말 삭제하시겠습니까? (y/n) > ");
	    String confirm = scn.next();

	    if (!confirm.equalsIgnoreCase("y")) {
	        System.out.println("삭제가 취소되었습니다.");
	        return;
	    }

	    boolean result = dao.deleteReview(reviewNo, loginUser); // DAO에서 삭제 실행

	    if (result) {
	        System.out.println("리뷰가 삭제되었습니다.");
	    }
	}


}