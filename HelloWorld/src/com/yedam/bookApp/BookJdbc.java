package com.yedam.bookApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * ojdbc 활용.
 */
public class BookJdbc {
	
	// Connection 생성
	Connection getConnect() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String userId = "scott";
		String userPw = "tiger";
		
		try {
			Connection conn = DriverManager.getConnection(url, userId, userPw);
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//추가.
	public boolean insert(Book book) {
		Connection conn = getConnect();
		String sql = "insert into tbl_book (book_code, book_title, author, company, price)\r\n"
				+ "values(book_seq.nextval, '"+book.getTitle()+"', '"+book.getAuthor()+"', '"+book.getCompany()+"', "+book.getPrice()+")";
		try {
			Statement stmt = conn.createStatement();
			int r = stmt.executeUpdate(sql);
			if (r>0) {
				return true; // 등록성공
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false; // 등록실패
	}
	
	//수정.
	
	//삭제.
	
	//목록.
}
