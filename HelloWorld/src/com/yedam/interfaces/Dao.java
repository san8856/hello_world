package com.yedam.interfaces;

/*
 * Data Access Object
 * 인터페이스에서 선언되는 모든메소드는 추상메소드.
 * 
 * MySql을 활용해 1차 프로젝트 진행.
 * Oracle을 활용해 2차 프로젝트 진행.
 * 
 */


public interface Dao { 
	void insert();
	void update();
	void delete();
	
}
