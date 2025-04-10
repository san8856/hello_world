package com.yedam.test;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yedam.common.DataSource;
import com.yedam.mapper.EtcMapper;


public class test {
	public static void main(String[] args) {
		
		SqlSession sqlSession = DataSource.getInstance().openSession();
		EtcMapper mapper = sqlSession.getMapper(EtcMapper.class);
		
		List<Map<String, Object>> list = mapper.selectWriter();
		
		//출력결과
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(list);
		
		System.out.println(json);
		
	}
//	Map<String, Object> map = new HashMap<>();
//	map.put("title", "Meeting2");
//	map.put("start", "2025-06-01");
//	map.put("end", "2025-06-03");
}
