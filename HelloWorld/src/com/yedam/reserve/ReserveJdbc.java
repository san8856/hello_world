package com.yedam.reserve;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yedam.test.Customer;

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
	//로그인
	public Customer login(String id, String pw) {
		Connection conn = getConnect();
		String sql = "SELECT * FROM TBL_CUSTOMER "
                + "WHERE CUSTOMER_ID = ? "
                + "AND CUSTOMER_PW = ?";
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, pw);
			ResultSet rs = psmt.executeQuery();
			if(rs.next()) {
				Customer customer = new Customer(rs.getString("CUSTOMER_ID"), 
												 rs.getString("CUSTOMER_NAME"), 
												 rs.getString("CUSTOMER_PW"));
				return customer;
			}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}
    //회원가입
    public boolean signUp(Customer customer) {
        Connection conn = getConnect();
        String sql = "INSERT INTO TBL_CUSTOMER (CUSTOMER_ID, CUSTOMER_NAME, CUSTOMER_PW) " +
                     "SELECT ?, ?, ? FROM DUAL WHERE NOT EXISTS " +
                     "(SELECT 1 FROM TBL_CUSTOMER WHERE CUSTOMER_ID = ?)";

        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1, customer.getCustomerId());
            psmt.setString(2, customer.getCustomerName());
            psmt.setString(3, customer.getCustomerPw());
            psmt.setString(4, customer.getCustomerId());

            return psmt.executeUpdate() > 0;  // 삽입 성공 여부 반환
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
	
	
	

	// 객실목록 조회
	public List<Reservation> roomList(){
		List<Reservation> list = new ArrayList<>();
		Connection conn = getConnect();
		String sql = "SELECT room_no, room_type FROM tbl_room ORDER BY room_no";
		
        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery();
			
            while (rs.next()) {
            	Reservation room = new Reservation();
            	room.setRoomNumber(rs.getString("room_no"));
            	room.setRoomType(rs.getString("room_type"));
            	list.add(room);
            }
            } catch (SQLException e) {
            	e.printStackTrace();
            }

			return list;
		}
	}
