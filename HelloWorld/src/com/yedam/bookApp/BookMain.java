package com.yedam.bookApp;

import java.util.Scanner;

public class BookMain {
	
	static Book[] bookStore = new Book[100];
	static Scanner scn = new Scanner(System.in);
	
//	static String name; //static -> 인스턴트가 없어도 바로 실행 가능
//	public static void test() {
//		System.out.println("test");
//	}
	
	//순번생성 메소드
	public static int getSequnceNo() {
		int max = 0;
		for(int i=0;i<bookStore.length; i++) {
			if(bookStore[i] !=null //
					&& bookStore[i].getOrderNo() > max) {
				max = bookStore[i].getOrderNo();
			}
		}
		return max + 1; //현재 마지막 번호 +1;
	}
	
	//등록을 구현 하는 메소드
	//1. 이미 존재하는 제목은 입력불가
	
	public static void add() {
		
		System.out.print("제목 입력 >> ");
		String title =scn.nextLine();
		if(searchBook(title) != null) {
			System.out.println("이미 등록된 책입니다.");
			return;
		}
		System.out.print("저자 입력 >> ");
		String author =scn.nextLine();
		System.out.print("출판사 입력 >> ");
		String company =scn.nextLine();
		System.out.print("가격 입력 >> ");
		String price = scn.nextLine();
		//입력항목 확인
		if (title.isBlank() || author.isBlank() || company.isBlank() || price.isBlank()) {
			System.out.println("항목을 입력하세요");
			return;//메소드 종료
		}
				
		Book book = new Book(title, author, company, Integer.parseInt(price),getSequnceNo());
		for (int i=0; i<bookStore.length; i++) {
			if (bookStore[i] == null) {
				bookStore[i] = book;
				System.out.println("등록되었습니다.");
				break;
			}
		}
	}// end of add()
	
	public static void edit() {
		System.out.print("수정 할 책 제목을 입력 >> ");
		String title = scn.nextLine();
		boolean isExist = false;
		if (title.isBlank()) {
			System.out.println("책제목을 반드시 입력하세요");
			return;
		}
		for (int i=0; i<bookStore.length; i++) {
			if (bookStore[i] != null && bookStore[i].getTitle().equals(title)) {
				System.out.print("저자를 입력해주세요 >> ");
				String author = scn.nextLine();
				System.out.print("출판사를 입력해주세요 >> ");
				String company = scn.nextLine();
				System.out.print("금액을 입력해주세요 >> ");
				String price = scn.nextLine();					
				if (!author.isBlank()) {
					bookStore[i].setAuthor(author);
				}
				if (!company.isBlank()) {
					bookStore[i].setCompany(company);
				}
				if (!price.isBlank()) {
					bookStore[i].setPrice(Integer.parseInt(price));
				}
				isExist = true;
				System.out.println("수정되었습니다");				
			}
		}
		if(!isExist) {
			System.out.println("찾을수 없습니다.");
		}
	} //end of edit()
	
	public static void delete(){
		boolean isExist = false;
		String title = "";
		while (true) {
			System.out.print("삭제할 책 제목을 입력 >> ");
			title = scn.nextLine();
			if (!title.isBlank()) {
				break;
			}
			System.out.println("제목을 다시 입력하세요.");
		}
		isExist = false;
		for(int i=0; i<bookStore.length; i++) {
			if (bookStore[i] != null && bookStore[i].getTitle().equals(title)) {
				bookStore[i]  = null;
				System.out.println("삭제되었습니다.");
				isExist = true;
			}
		}
	}//end of delete()
	
	public static void list() {
		//순번을 기준으로 정렬
		//오름차순 순번1 > 순번2, 순번2(null)인 경우 제외, 순번1(null)인 경우 변경
		Book temp = null;
		for(int i=0; i<bookStore.length-1; i++) {
			for (int j=0; j<bookStore.length -1; j++) {
				if (bookStore[j+1] == null) { //변경X
					continue; //pass
				}
				if (bookStore[j] == null //변경O
						|| bookStore[j].getOrderNo() > bookStore[j+1].getOrderNo()) {
					temp = bookStore[j];
					bookStore[j] = bookStore[j+1];
					bookStore[j+1] = temp;
				}
			}
		}//end of for.
		int seqNo = 1;
		System.out.println("순번  제목    저자   가격");
		System.out.println("====================");
		Book[] list = searchList(null);

		for (Book bok : bookStore) {
			if (bok != null)
				System.out.println(seqNo++ + " " + bok.showList());
		}
		
	}//end of list()
	
	public static void listCompany() {
		System.out.println("조회할 출판사 입력 >> ");
		String company = scn.nextLine();
		
		
		int seqNo = 1;
		System.out.println("순번  제목    저자   가격");
		System.out.println("====================");
		Book[] list = searchList(company);
		for (Book bok : bookStore) {
			if (bok != null) {
				if (bok.getCompany().equals(company)) {
					System.out.println(seqNo++ + " " + bok.showList());					
				}
			}
		}
	} //end of listCompany().
	
	public static void bookInfo() {
		//반드시 값을 입력하도록
		String title = "";
		while (true) {
			System.out.print("조회할 책 제목을 입력 >> ");
			title = scn.nextLine();
			if (!title.isBlank()) {
				break;
			}
			System.out.println("제목을 다시 입력하세요.");
		}
		Book result = searchBook(title);
		if (result == null) {
			System.out.println("조회결과가 없습니다.");
			return;
		}
		System.out.println(result.showBookInfo());		
	}//end of bookinfo()
	
	///list와 listCompany에서 활용할 공통 메소드.
	
	public static Book[] searchList(String keyword) {
		Book[] list = new Book[100];
		int idx =0;
		for (int i=0; i< bookStore.length; i++) {
			if(bookStore[i] != null) {
				if (keyword == null || bookStore[i].getCompany().equals(keyword)) {
					list[idx++] = bookStore[i];		
				}
			}
		}
		return list;
	}//end of searchList.
	
	
	//도서명으로 조회하는 기능
	public static Book searchBook(String title) {
		for(int i=0; i<bookStore.length; i++) {
			if (bookStore[i] != null && bookStore[i].getTitle().equals(title)) {
				return bookStore[i];
				}
			}
		return null;
		}	//조회결과가 있으면 book 없으면 null 반환 
	
	//출판사 명으로 조회
	public static void searchPublisher() {
		System.out.println("조회할 출판사를 입력 >> ");
		String publisher = scn.nextLine();
		boolean isExist = false;
		System.out.println("출판사: " + publisher);
		for (Book bok : bookStore) {
			if (bok != null && bok.getCompany().equals(publisher)) {
				System.out.println(bok.showListPublisher());
				isExist =true;
			}
		}
		if (!isExist) {
			System.out.println("조회된 책이 없습니다.");
		}
	}//end of searchPublisher. 
		
	//등록, 수정, 삭제, 목록
	public static void main(String[] args) {
//		Book book = new Book("이것이 자바다", "신용권", "한빛미디어", 20000);  //생성자
//		book.setTitle("이것이 자바다");
//		book.setAuthor("신용권"); //setter
//		book.setCompany("한빛미디어");
//		book.setPrice(20000);	
		
//		System.out.println("제목:  " + book.getTitle());
//		System.out.println(book.showBookInfo());
		
		//저장공간
		
		
		init();
		boolean run = true;
		while(run) {
			System.out.println("1. 도서등록 2. 수정 3. 삭제 4. 목록 5. 상세조회 6.목록조회(출판사) 9. 종료");
			System.out.println("선택 >> ");
			int menu = Integer.parseInt(scn.nextLine());
			switch(menu) {
			case 1:    //등록
				add(); break;
				
			case 2:    //수정 도서명으로 검색, 금액을 수정.
				edit();	break;
			
			case 3:    //삭제 도서명으로 검색 후 삭제.
				delete(); break;

			case 4:    //목록
				list();	break;
			case 5:    //상세조회
				bookInfo(); break;	
			case 6:
				listCompany(); break;
			case 9:    //종료
				run =false;
				break;
			default:
				System.out.println("메뉴를 다시 선택해 주세요");
				
			}
		}
		System.out.println("프로그램을 종료합니다.");
	}//end of main().
	public static void init() {
		bookStore[0] = new Book("이것이자바다", "신용권", "한빛출", 20000, 1);
		bookStore[1] = new Book("스크립트기초", "박기초", "우리출", 26000, 2);
		bookStore[2] = new Book("HTML,SCC", "김하늘", "가람출", 25000, 3);
		bookStore[3] = new Book("이것이자바다2", "신용권", "한빛출", 20000, 1);
		bookStore[4] = new Book("스크립트기초2", "박기초", "우리출", 26000, 2);
		bookStore[5] = new Book("HTML,SCC2", "김하늘", "가람출", 25000, 3);	
	}
}









