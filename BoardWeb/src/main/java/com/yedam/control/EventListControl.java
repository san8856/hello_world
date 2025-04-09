package com.yedam.control;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.yedam.common.Control;
import com.yedam.service.EventService;
import com.yedam.service.EventServiceImpl;


public class EventListControl implements Control {
	

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    EventService service = new EventServiceImpl();
	    List<Map<String, Object>> list = service.getEvents();

	    // JSON 변환
	    Gson gson = new Gson();
	    String json = gson.toJson(list);

	    // 응답 설정
	    resp.setContentType("application/json;charset=utf-8");
	    resp.getWriter().print(json);
		
	}

}
