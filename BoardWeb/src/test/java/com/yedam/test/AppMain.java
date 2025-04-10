package com.yedam.test;

import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yedam.service.EtcService;
import com.yedam.service.EtcServiceImpl;

public class AppMain {
	public static void main(String[] args) {

		EtcService svc = new EtcServiceImpl();
		List<Map<String, Object>> list = svc.eventList();

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(list);

		System.out.println(json);

	}
//	Map<String, Object> map = new HashMap<>();
//	map.put("title", "Meeting2");
//	map.put("start", "2025-06-01");
//	map.put("end", "2025-06-03");
}
