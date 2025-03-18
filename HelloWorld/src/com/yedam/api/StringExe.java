package com.yedam.api;

import java.io.UnsupportedEncodingException;
import java.security.UnrecoverableEntryException;

public class StringExe {
	public static void main(String[] args) {
		String str = "Hello, World";
		str = new String("Hello, World");
		byte[] bAry = str.getBytes();
		for(int i=0; i<bAry.length; i++) {
			System.out.println(bAry[i]); 
		}
		
		byte[] bstr = {72,101,108,108,111,44,32,87,111,114,108,100};
		String msg = new String(bstr, 7, 5); //7번째부터 5개의 글자를 불러옴 (World)
		System.out.println(msg);
		char result = msg.charAt(0); //정수형 캐릭터타입 문자값을 출력
		System.out.println((int)result); 
		
		// String: "" (문자열) , char: '' (캐릭터타입)
		if (result == 'W') {
			//비교 구문.
			
		}
		//찾을 문자열에서 문자열의 위치를 리턴
		int idx = msg.indexOf("o");
		if(idx != -1) {
			//처리하는 코드
		}
		//길
		String[] names = {"홍길동", "홍길승", "김민길", "박홍길"};
//		int cnt = 0;
//		for(int i=0; i<names.length; i++) {
//			if(names[i].indexOf("길") != -1) {
//				cnt++;
	//		}
	//	}
	//	System.out.println("\"길\"이들어가는 이름의 갯수: " + cnt);
			//성씨가 홍인 사람의 수
		int cnt = 0;
		for(int i=0; i<names.length; i++) {
			if(names[i].indexOf("홍") == 0) {
				cnt++;
			}
		}
		System.out.println("\"홍\"을 성으로 갖는 이름의 갯수: " + cnt);
		
		System.out.println("Hello, World".substring(3,8));
		
		System.out.println("====================================");
		
		
		
		
		
		
		
		//charAt()
		String ssn = "010624-2230123";
		char sex = ssn.charAt(7);
		switch (sex) {
			case '1':
			case '3':
				System.out.println("남자입니다.");
				break;
			case '2':
			case '4':
				System.out.println("여자입니다.");
				break;
		}
		
		System.out.println("====================================");
		
		//equals
		String strVar1 = new String("신민철");
		String strVar2 = "김민철";
		
		if(strVar1 == strVar2) {
			System.out.println("같은 String 객체를 참조");
		} else {
			System.out.println("다른 String 객체를 참조");
		}
		
		if(strVar1.equals(strVar2)) {
			System.out.println("같은 String 객체를 참조");
		} else {
			System.out.println("다른 String 객체를 참조");
		}
		System.out.println("====================================");
		//getBytes()
		String strg = "안녕하세요";
		byte[] bytes1 = strg.getBytes();
		System.out.println("bytes1.length: " + bytes1.length);
		String str1 = new String(bytes1);
		System.out.println("bytes1->String" + str1);
		
		try {
			byte[] bytes2 = strg.getBytes("EUC-KR");
			System.out.println("bytes2.length: " + bytes2.length);
			String str2 = new String(bytes2, "EUC-KR");
			System.out.println("bytes1->String" + str2);
			
			byte[] bytes3 = strg.getBytes("UTF-8");
			System.out.println("bytes3.length: " + bytes3.length);
			String str3 = new String(bytes3, "UTF-8");
			System.out.println("bytes1->String" + str3);
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		System.out.println("====================================");
		
		
		
		
		
		
		
		
		
	}
}




















