package com.yedam.reserve;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReserveJdbc {
	Connection getConnect() { // DB 연결
		String url = "jdbc:oracle:thin:@192.168.0.29:1521:xe";
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
		// 고객 ID와 비밀번호를 기준으로 사용자 정보 조회하는 sql쿼리
		String sql = "SELECT * FROM TBL_CUSTOMER " + "WHERE CUSTOMER_ID = ? " + "AND CUSTOMER_PW = ?";
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, id); // 입력값을 쿼리에 바인딩
			psmt.setString(2, pw);
			ResultSet rs = psmt.executeQuery(); // 쿼리 실행
			if (rs.next()) {
				// 정보 조회 후 Customer 객체 생성
				Customer customer = new Customer(rs.getString("CUSTOMER_ID"), rs.getString("CUSTOMER_NAME"),
						rs.getString("CUSTOMER_PW"));
				return customer;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}// end of login()

	// 회원가입
	// 입력받은 로그인 정보를 데이터베이스에 저장, id가 겹치면 가입 실패
	public boolean signUp(Customer customer) {
		Connection conn = getConnect();
		// ID가 데이터베이스에 존재하지 않는 경우만 수행하는 쿼리
		String sql = "INSERT INTO TBL_CUSTOMER (CUSTOMER_ID, CUSTOMER_NAME, CUSTOMER_PW) "
				+ "SELECT ?, ?, ? FROM DUAL WHERE NOT EXISTS " + "(SELECT 1 FROM TBL_CUSTOMER WHERE CUSTOMER_ID = ?)";
		// PreparedStatement 객체 초기화
		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(sql);
			// 고객정보 sql쿼리에 세팅
			psmt.setString(1, customer.getCustomerId());
			psmt.setString(2, customer.getCustomerName());
			psmt.setString(3, customer.getCustomerPw());
			psmt.setString(4, customer.getCustomerId());

			return psmt.executeUpdate() > 0; // 삽입 성공 여부 반환
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (psmt != null)
					psmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}// end of signUp()

	// 객실목록 조회
	// 데이터베이스에서 모든 객실 번호와 객실상태를 조회하여 Reservation 객체에 저장 후 리스트로 반환
	public List<Reservation> roomList() {
		// 결과를 저장할 리스트 생성
		List<Reservation> list = new ArrayList<>();
		Connection conn = getConnect();
		// 객실 정보를 조회할 SQL 쿼리
		String sql = "SELECT room_no, room_type FROM tbl_room ORDER BY room_no";

		PreparedStatement psmt = null;
		ResultSet rs = null; // SQL 쿼리 실행 결과를 담을 ResultSet 객체
		try {
			psmt = conn.prepareStatement(sql);// SQL 쿼리 준비
			rs = psmt.executeQuery();// 쿼리 실행
			// 쿼리 결과를 리스트에 추가
			while (rs.next()) {
				// 각 행에서 객실 번호(room_no)와 객실 유형(room_type)을 가져와 Reservation 객체 생성
				list.add(new Reservation(rs.getInt("room_no"), rs.getString("room_type")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (psmt != null)
					psmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	} // end of roomList()

	// 객실예약
	public boolean reserveRoom(int roomNo, String name, String tel) {
		Connection conn = getConnect();
		// 객실 상태 확인 쿼리
		String checkSql = "SELECT room_type FROM tbl_room WHERE room_no = ?";
		// 예약 정보 삽입 쿼리
		String insertSql = "INSERT INTO tbl_reserve (room_no, reserve_name, reserve_tel) VALUES (?, ?, ?)";
		// 객실 상태 업데이트 쿼리
		String updateSql = "UPDATE tbl_room SET room_type = 'X' WHERE room_no = ? AND room_type = 'O'";

		PreparedStatement psmtCheck = null;
		PreparedStatement psmtInsert = null;
		PreparedStatement psmtUpdate = null;
		ResultSet rs = null;

		try { // 트랜잭션 시작
			conn.setAutoCommit(false);
			// 객실상태 확인
			psmtCheck = conn.prepareStatement(checkSql);
			psmtCheck.setInt(1, roomNo);
			rs = psmtCheck.executeQuery();
			// 예약 정보 추가
			psmtInsert = conn.prepareStatement(insertSql);
			psmtInsert.setInt(1, roomNo);
			psmtInsert.setString(2, name);
			psmtInsert.setString(3, tel);
			int insertResult = psmtInsert.executeUpdate();

			// 객실 상태 업데이트
			psmtUpdate = conn.prepareStatement(updateSql);
			psmtUpdate.setInt(1, roomNo);
			int updateResult = psmtUpdate.executeUpdate();

			// 예약 성공 여부 확인
			if (insertResult > 0 && updateResult > 0) {
				conn.commit(); // 커밋
				return true;
			} else {
				conn.rollback(); // 예약 실패 시 롤백
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (psmtCheck != null)
					psmtCheck.close();
				if (psmtInsert != null)
					psmtInsert.close();
				if (psmtUpdate != null)
					psmtUpdate.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}// end of reserveRoom()

	// 예약확인
	// 예약자명과 연락처를 이용하여 예약 정보를 조회
	public Reservation getReservation(String name, String tel) {
		Connection conn = getConnect();
		// 예약 정보를 조회하기 위한 SQL 쿼리
		String sql = "SELECT room_no, reserve_name, reserve_tel, reserve_time FROM tbl_reserve "
				+ "WHERE reserve_name = ? AND reserve_tel = ?";
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, name);// 쿼리에 예약자명, 연락처 바인딩
			psmt.setString(2, tel);
			rs = psmt.executeQuery();

			if (rs.next()) {
				// 예약 정보가 존재하면 객체 생성하여 반환
				return new Reservation(rs.getInt("room_no"), rs.getString("reserve_name"), rs.getString("reserve_tel"),
						rs.getString("reserve_time"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (psmt != null)
					psmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null; // 예약 정보가 없을 경우 null 반환
	} // end of getReservation()

	// 체크인
	// 예약된 객실에 대해 체크인 처리, 예약이 존재하면 객실 상태를 '이용중(-)'으로 업데이트
	public boolean checkIn(int roomNo, String name) {
		Connection conn = getConnect();
		// 예약 정보 조회 SQL
		String checkSql = "SELECT * FROM tbl_reserve WHERE room_no = ? AND reserve_name = ?";
		// 객실 상태 업데이트 SQL
		String updateSql = "UPDATE tbl_room SET room_type = '-' WHERE room_no = ?";

		PreparedStatement checkStmt = null;
		PreparedStatement updateStmt = null;
		ResultSet rs = null;

		try {
			checkStmt = conn.prepareStatement(checkSql);
			checkStmt.setInt(1, roomNo);// 예약정보조회 쿼리에 객실번호, 예약자이름 바인딩
			checkStmt.setString(2, name);
			rs = checkStmt.executeQuery();

			if (rs.next()) { // 예약정보가 존재하면 객실상태 이용중으로 업데이트
				updateStmt = conn.prepareStatement(updateSql);
				updateStmt.setInt(1, roomNo); // 엽데이트 쿼리에 객실번호바인딩, 상태 업데이트
				updateStmt.executeUpdate();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (checkStmt != null)
					checkStmt.close();
				if (updateStmt != null)
					updateStmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}// end of checkIn()

	// 체크아웃
	// 체크아웃 시 예약 정보를 삭제하고 객실 상태를 예약 가능 으로 변경
	public boolean checkOut(int roomNo, String name) {
		Connection conn = getConnect();
		// 예약 정보 확인 SQL
		String checkSql = "SELECT * FROM tbl_reserve WHERE room_no = ? AND reserve_name = ?";
		// 예약 삭제 SQL
		String deleteSql = "DELETE FROM tbl_reserve WHERE room_no = ? AND reserve_name = ?";
		// 객실 상태 업데이트 SQL
		String updateSql = "UPDATE tbl_room SET room_type = 'O' WHERE room_no = ?";

		PreparedStatement checkStmt = null;
		PreparedStatement deleteStmt = null;
		PreparedStatement updateStmt = null;
		ResultSet rs = null;

		try {// 예약 정보 확인 준비
			checkStmt = conn.prepareStatement(checkSql);
			checkStmt.setInt(1, roomNo);
			checkStmt.setString(2, name);
			rs = checkStmt.executeQuery();

			if (rs.next()) {// 예약 정보 삭제
				deleteStmt = conn.prepareStatement(deleteSql);
				deleteStmt.setInt(1, roomNo);
				deleteStmt.setString(2, name);
				deleteStmt.executeUpdate();
				// 객실 상태를 '예약 가능'으로 업데이트
				updateStmt = conn.prepareStatement(updateSql);
				updateStmt.setInt(1, roomNo);
				updateStmt.executeUpdate();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (checkStmt != null)
					checkStmt.close();
				if (deleteStmt != null)
					deleteStmt.close();
				if (updateStmt != null)
					updateStmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}// end of checkOut()

	// 후기 작성
	// 사용자가 작성한 후기를 tbl_review 테이블에 삽입
	public boolean writeReview(String title, String content, String customerId) {
		Connection conn = getConnect();
		// 후기 작성 SQL 쿼리
		String sql = "INSERT INTO tbl_review (review_no, title, content, customer_id, write_date, view_cnt) "
				+ "VALUES (review_seq.NEXTVAL, ?, ?, ?, SYSDATE, 0)";

		PreparedStatement psmt = null;
		try {// SQL 쿼리 준비
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, title);
			psmt.setString(2, content);
			psmt.setString(3, customerId);
			// SQL 실행
			int result = psmt.executeUpdate();
			return result > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (psmt != null)
					psmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}// end of writeReview()

	// 전체조회
	// tbl_review 테이블에서 모든 리뷰를 조회하고, 각 리뷰에 대한 정보를 Review 객체로 저장한 후 반환
	public List<Review> getAllReviews() {
		List<Review> reviewList = new ArrayList<>();
		Connection conn = getConnect();
		// 전체 리뷰 조회 SQL 쿼리
		String sql = "SELECT review_no, title, customer_id, TO_CHAR(write_date, 'YY/MM/DD') AS write_date, view_cnt "
				+ "FROM tbl_review ORDER BY review_no DESC";

		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			psmt = conn.prepareStatement(sql); // 쿼리준비
			rs = psmt.executeQuery(); // 쿼리실행

			while (rs.next()) { // 결과처리
				// ResultSet에서 값 추출 후 Review 객체 생성
				Review review = new Review(rs.getInt("review_no"), rs.getString("title"), rs.getString("customer_id"),
						rs.getString("write_date"), rs.getInt("view_cnt"));
				// 리스트에 추가
				reviewList.add(review);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (psmt != null)
					psmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return reviewList;
	}// end of getAllReviews()

	// 상세조회
	// reviewNo에 해당하는 리뷰 정보를 tbl_review 테이블에서 조회하고, 조회된 정보를 Review 객체로 반환
	public Review getReview(int reviewNo) {
		Connection conn = getConnect();
		// 특정 리뷰 조회 SQL 쿼리
		String sql = "SELECT review_no, title, content, customer_id, TO_CHAR(write_date, 'YY/MM/DD') AS write_date, view_cnt "
				+ "FROM tbl_review WHERE review_no = ?";
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			psmt = conn.prepareStatement(sql);// 쿼리준비
			psmt.setInt(1, reviewNo);// 후기번호 세팅
			rs = psmt.executeQuery();// 쿼리 실행
			// 결과가 존재하면 Review 객체 반환
			if (rs.next()) {
				return new Review(rs.getInt("review_no"), rs.getString("title"), rs.getString("content"),
						rs.getString("customer_id"), rs.getString("write_date"), rs.getInt("view_cnt"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (psmt != null)
					psmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}// end of getReview()

	// 조회수 증가
	// 리뷰 번호(reviewNo)에 해당하는 리뷰의 조회수(view_cnt)를 1 증가
	public void updateViewCount(int reviewNo) {
		Connection conn = getConnect();
		// 조회수 증가 쿼리
		String sql = "UPDATE tbl_review SET view_cnt = view_cnt + 1 WHERE review_no = ?";

		PreparedStatement psmt = null;

		try {
			psmt = conn.prepareStatement(sql); // 쿼리준비
			psmt.setInt(1, reviewNo); // ?에 reviewNo 값을 설정
			psmt.executeUpdate(); // 업데이트 실행
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (psmt != null)
					psmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}// end of updateViewCount().

	// 제목 또는 내용 검색기능
	// 키워드로 제목 또는 내용에서 검색하여 일치하는 리뷰 목록을 반환
	public List<Review> searchKeyword(String keyword) {
		List<Review> reviewList = new ArrayList<>();
		Connection conn = getConnect();
		// 제목 또는 내용에 키워드가 포함된 리뷰 검색 쿼리
		String sql = "SELECT review_no, title, customer_id, TO_CHAR(write_date, 'YY/MM/DD') AS write_date, view_cnt "
				+ "FROM tbl_review " + "WHERE title Like ? OR content LIKE ? " + "ORDER BY review_no DESC";
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			psmt = conn.prepareStatement(sql);// 쿼리 준비
			psmt.setString(1, "%" + keyword + "%");// 첫 번째 ?에 키워드 바인딩 (제목에서 검색)
			psmt.setString(2, "%" + keyword + "%"); // 두 번째 ?에 키워드 바인딩 (내용에서 검색)
			rs = psmt.executeQuery(); // 쿼리 실행

			while (rs.next()) {
				Review review = new Review(rs.getInt("review_no"), rs.getString("title"), rs.getString("customer_id"),
						rs.getString("write_date"), rs.getInt("view_cnt"));
				reviewList.add(review); // 일치하는 리뷰를 목록에 추가
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (psmt != null)
					psmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return reviewList;
	}//end of searchKeyword()

	// 삭제
	//특정 리뷰를 삭제, 삭제 권한이 있는지 확인 후 해당 리뷰를 삭제
	public boolean deleteReview(int reviewNo, String userId) {
		Connection conn = getConnect();
		String checkSql = "SELECT customer_id FROM tbl_review WHERE review_no = ?";
		String deleteSql = "DELETE FROM tbl_review WHERE review_no = ?";

		PreparedStatement checkPsmt = null;
		PreparedStatement deletePsmt = null;
		ResultSet rs = null;

		try {
			conn.setAutoCommit(false); // 트랜잭션 시작

			// 리뷰의 작성자 확인
			checkPsmt = conn.prepareStatement(checkSql);
			checkPsmt.setInt(1, reviewNo);
			rs = checkPsmt.executeQuery();

			if (rs.next()) {
				String writerId = rs.getString("customer_id");
				if (!writerId.equals(userId)) {
					System.out.println("삭제 권한이 없습니다.");
					return false;
				}
			} else {
				System.out.println("존재하지 않는 리뷰 번호입니다.");
				return false;
			}

			// 리뷰 삭제
			deletePsmt = conn.prepareStatement(deleteSql);
			deletePsmt.setInt(1, reviewNo);
			int rowsAffected = deletePsmt.executeUpdate();

			if (rowsAffected > 0) {
				conn.commit(); // 삭제 성공 시 커밋
				return true;
			} else {
				conn.rollback(); // 실패 시 롤백
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException rollbackEx) {
				rollbackEx.printStackTrace();
			}
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (checkPsmt != null)
					checkPsmt.close();
				if (deletePsmt != null)
					deletePsmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

}//end of deleteReview()
