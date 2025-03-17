package com.yedam.interfaces;
/*
 * 실행 클래스. 
 */
public class ServiceApp {
	public static void main(String[] args) {
//		MysqlDao dao = new MysqlDao();
//		OracleDao  dao = new OracleDao();    //sql이던 oracle이던 dao를 구현하는 클래스로 만들어져야 함
		
		Dao dao = new OracleDao(); //인터페이스 타입변수 = 구현객체.
		
		//등록
//		dao.insert(); - sql
//		dao.add(); - oracle
		dao.insert(); // - dao
		
		//수정
//		dao.modify();
		dao.update();
		
		//삭제
//		dao.remove();
		dao.delete();
	}
}
