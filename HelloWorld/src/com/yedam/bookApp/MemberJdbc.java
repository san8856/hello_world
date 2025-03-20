package com.yedam.bookApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberJdbc {
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
	}// end of getConnect.
	
	//로그인처리.
	public User login(String id, String pw) {
		//id,pw 조회 후 값이 있으면 로그인성공, 실패.
		Connection conn = getConnect();
		String sql = "select * from tbl_member "//
				+ "where user_id = ? " //
				+ " and password = ? ";
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, pw);
			ResultSet rs = psmt.executeQuery();
			//조회결과 O
			if(rs.next()) {
				User user = new User(rs.getString("user_id"), rs.getString("user_name"), rs.getString("password"));
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//조회결과 x
		return null;
	}
	

	public List<Map<String, String>> memberList(){
		// [{userId: "user01"}, {password: "1111"}, {userName: "홍길동"}]
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Connection conn = getConnect(); //쿼리를 실행하기 위한 db session
		String sql = "select * from tbl_member ";
		
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {
				// userId, password, userName 저장할 컬렉션.
				Map<String, String> map = new HashMap<String, String>();
				map.put("userId", rs.getString("user_id"));
				map.put("paasword", rs.getString("password"));
				map.put("userName", rs.getString("user_name"));
				list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
}
