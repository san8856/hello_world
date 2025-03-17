package com.yedam.interfaces;
/*
 * 1차 프로젝트.
 * 등록(insert), 수정(modify), 삭제(remove)로 정의
 * 
 * 
 */
public class MysqlDao implements Dao {
	@Override
	public void insert() {
		System.out.println("MySql 용 등록.");
	}
	@Override
	public void update() {
		System.out.println("MySql 용 수정.");
	}
	@Override
	public void delete() {
		System.out.println("MySql 용 삭제.");
	}
}
