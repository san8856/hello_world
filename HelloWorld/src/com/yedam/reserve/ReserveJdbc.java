package com.yedam.reserve;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReserveJdbc {
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

	// 로그인
	public Customer login(String id, String pw) {
		Connection conn = getConnect();
		String sql = "SELECT * FROM TBL_CUSTOMER " + "WHERE CUSTOMER_ID = ? " + "AND CUSTOMER_PW = ?";
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, pw);
			ResultSet rs = psmt.executeQuery();
			if (rs.next()) {
				Customer customer = new Customer(rs.getString("CUSTOMER_ID"), rs.getString("CUSTOMER_NAME"),
						rs.getString("CUSTOMER_PW"));
				return customer;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 회원가입
	public boolean signUp(Customer customer) {
		Connection conn = getConnect();
		String sql = "INSERT INTO TBL_CUSTOMER (CUSTOMER_ID, CUSTOMER_NAME, CUSTOMER_PW) "
				+ "SELECT ?, ?, ? FROM DUAL WHERE NOT EXISTS " + "(SELECT 1 FROM TBL_CUSTOMER WHERE CUSTOMER_ID = ?)";

		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, customer.getCustomerId());
			psmt.setString(2, customer.getCustomerName());
			psmt.setString(3, customer.getCustomerPw());
			psmt.setString(4, customer.getCustomerId());

			return psmt.executeUpdate() > 0; // 삽입 성공 여부 반환
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// 객실목록 조회
	public List<Reservation> roomList() {
		List<Reservation> list = new ArrayList<>();
		Connection conn = getConnect();
		String sql = "SELECT room_no, room_type FROM tbl_room ORDER BY room_no";

		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new Reservation(rs.getInt("room_no"), rs.getString("room_type")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// 객실예약
	public boolean reserveRoom(int roomNo, String name, String tel) {
		Connection conn = getConnect();
		String insertSql = "INSERT INTO tbl_reserve (room_no, reserve_name, reserve_tel) VALUES (?, ?, ?)";
		String updateSql = "UPDATE tbl_room SET room_type = 'X' WHERE room_no = ? AND room_type = 'O'";

		try {
			conn.setAutoCommit(false);

			PreparedStatement psmtInsert = conn.prepareStatement(insertSql);
			psmtInsert.setInt(1, roomNo);
			psmtInsert.setString(2, name);
			psmtInsert.setString(3, tel);
			int insertResult = psmtInsert.executeUpdate();

			PreparedStatement psmtUpdate = conn.prepareStatement(updateSql);
			psmtUpdate.setInt(1, roomNo);
			int updateResult = psmtUpdate.executeUpdate();

			if (insertResult > 0 && updateResult > 0) {
				conn.commit();
				return true;
			} else {
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	//예약확인
	public List<Reservation> getReservations(String name, String tel) {
		Connection conn = getConnect();
	    List<Reservation> reservations = new ArrayList<>();
	    String sql = "SELECT * FROM tbl_reserve WHERE reserve_name = ? AND reserve_tel = ?";
	    
	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, name);
	        pstmt.setString(2, tel);
	        ResultSet rs = pstmt.executeQuery();

	        while (rs.next()) {
	            reservations.add(new Reservation(
	                rs.getInt("room_number"),
	                rs.getString("reserve_name"),
	                rs.getString("reserve_tel"),
	                rs.getString("reserve_time")
	            ));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return reservations;
	}

	
	//체크인
	public boolean checkIn(int roomNo, String name) {
		Connection conn = getConnect();
		String checkSql = "SELECT * FROM tbl_reserve WHERE room_no = ? AND reserve_name = ?";
		String updateSql = "UPDATE tbl_room SET room_type = '-' WHERE room_no = ?";
		
		try {
			PreparedStatement checkStmt = conn.prepareStatement(checkSql);
			checkStmt.setInt(1, roomNo);
			checkStmt.setString(2, name);
			ResultSet rs = checkStmt.executeQuery();
			
			if (rs.next()) {
				PreparedStatement updateStmt = conn.prepareStatement(updateSql);
				updateStmt.setInt(1, roomNo);
				updateStmt.executeUpdate();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	//체크아웃
	public boolean checkOut(int roomNo, String name) {
		Connection conn = getConnect();
		String checkSql = "SELECT * FROM tbl_reserve WHERE room_no = ? AND reserve_name = ?";
		String deleteSql = "DELETE FROM tbl_reserve WHERE room_no = ? AND reserve_name = ?";
		String updateSql = "UPDATE tbl_room SET room_type = 'O' WHERE room_no = ?";
		
		try {
			PreparedStatement checkStmt = conn.prepareStatement(checkSql);
			checkStmt.setInt(1, roomNo);
			checkStmt.setString(2, name);
			ResultSet rs = checkStmt.executeQuery();
			
			if (rs.next()) {
				PreparedStatement deleteStmt = conn.prepareStatement(deleteSql);
				deleteStmt.setInt(1, roomNo);
				deleteStmt.setString(2, name);
				deleteStmt.executeUpdate();
				
				PreparedStatement updateStmt = conn.prepareStatement(updateSql);
				updateStmt.setInt(1, roomNo);
				updateStmt.executeUpdate();
				return true;	
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	//후기작성
	public boolean writeReview(String title, String content, String customerId) {
		Connection conn = getConnect();
		String sql = "INSERT INTO tbl_review (review_no, title, content, customer_id, write_date, view_cnt" +
		"VALUES (review_seq.NEXTVAL, ?, ?, ?, SYSDATE, 0";
		
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, title);
			psmt.setString(2, content);
			psmt.setString(3, customerId);
			
			int result = psmt.executeUpdate();
			return result > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	
	
	
	
}
