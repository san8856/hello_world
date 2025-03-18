package com.yedam.api;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


public class ClassExe {
	public static void main(String[] args) {		
		
		
		try {
			Class cls = Class.forName("com.yedam.bookApp.Book"); //ClassNotFoundException. 
			
			Method[] methods = cls.getDeclaredMethods();
			//클래스의 메소드 변환
			for(int i=0; i<methods.length; i++) {
				System.out.println(methods[i].getName());				
			}
			System.out.println("----------------------------");
			Field[] fary = cls.getDeclaredFields();
			//클래스의 필드 반환.
			for(int i=0; i<fary.length; i++) {
				System.out.println(fary[i].getName());				
			}
			System.out.println("----------------------------");
//			Constructor[] fcon
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 

		
		
	}
}
