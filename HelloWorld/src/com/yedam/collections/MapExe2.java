package com.yedam.collections;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.yedam.variable.Member;

public class MapExe2 {
	public static void main(String[] args) {
		Map<Member, Double> map = new HashMap<Member, Double>();
		map.put(new Member("홍길동", 80), 174.8);
		map.put(new Member("김길동", 82), 179.4);
		map.put(new Member("박길동", 78), 184.1);
		//위 박길동과 아래 삭제를 위한 박길동은 물리적으로 서로 다른 객체, 
		//Member 클래스에서 Hashcode, equals @override를 통해 논리적으로 같은 객체.
		map.remove(new Member("박길동", 78));
		
		Set<Entry<Member, Double>> entryset = map.entrySet();
		
		for (Entry<Member, Double> entry : entryset) {
			System.out.println(entry.getKey() + " , " + entry.getValue());
		}
	}
}
