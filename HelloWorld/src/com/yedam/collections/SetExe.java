package com.yedam.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.yedam.variable.Member;

public class SetExe {
	public static void main(String[] args) {
		Set<String> set = new HashSet<String>();
		set.add("Hello");
		set.add("World");
//		set.add(100);
		set.add("Hello");
		
		Iterator<String> iter = set.iterator(); //반복자
		while(iter.hasNext()) {
			String result = iter.next();
			System.out.println(result); //중복X
		}
	//Set<Member>
		Set<Member> members = new HashSet<>();
		members.add(new Member("홍길동", 80));
		members.add(new Member("김민규", 85));
		members.add(new Member("홍길동", 80));
		
		Iterator<Member> miter = members.iterator();
		while(miter.hasNext()) {
			Member result = miter.next();
			System.out.println(result.toString());
		}
		
		//int => Integer
		int[] intAry = {10,20,30,40,20,10};
		//int 배열에서 중복된 값을 제거 한 후에 결과를 List컬렉션에 추가
		//List 반복문 활용 출력
		Set<Integer> iset = new HashSet<>();
		
		Arrays.stream(intAry).forEach(num -> iset.add(num));
		//set 컬렉션에 값을 저장(중복제거)
		for(int i=0; i<intAry.length; i++)
			iset.add(intAry[i]);
		//반복문 - List 저장.
		List<Integer> ilist = new ArrayList<>();
		Iterator<Integer> iterator = iset.iterator(); //반복자
		while(iterator.hasNext()) {
			ilist.add(iterator.next());
		}
		//반복문 출력.
		ilist.forEach(num -> System.out.println(num));		
	}
}
