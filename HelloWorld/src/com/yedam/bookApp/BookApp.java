package com.yedam.bookApp;

import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/*
 * 실행클래스(main메소드)
 */
public class BookApp {
	public static void main(String[] args) {
		
		
		
		// singleton 객체 호출.
		BookMain mainApp1 = BookMain.getInstance();
//		BookMain mainApp2 = BookMain.getInstance();
		mainApp1.main(args);
//		mainApp1.add(); // 1번에 등록.
//		mainApp1.list();

//		mainApp2.list(); // 2번 목록.

		// 숙제:
		// 1번) BookMain의 main메소드를 통해서만 기능활용하도록 하세요.
		// User 클래스를 생성하고 (공유폴더에 있음)
		// BookMain에 User[]을 선언해서 회원을 3명 등록하기.
		// BookMain에 login메소드를 선언하고 매개값으로 아이디와 비밀번호를 입력받도록 한다.
		// login메소드는 User[]에 등록된 회원중에서 입력받은 아이디와 비밀번호가 있으면 로그인성공 아니면 실패.
		// login성공 했을 경우에만 1번) main메소드를 실행하도록 한다

	}
}

//		MemberJdbc dao = new MemberJdbc();
//		List<Map<String, String>> list = dao.memberList();
//		Gson gson = new GsonBuilder().setPrettyPrinting().create();
//		String json = gson.toJson(list);
//		System.out.println(json);