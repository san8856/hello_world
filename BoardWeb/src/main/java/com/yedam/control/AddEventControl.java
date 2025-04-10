package com.yedam.control;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.service.EtcService;
import com.yedam.service.EtcServiceImpl;

public class AddEventControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8"); //요청방식: POST ,한글로 지정
		// title, start, end
		String title = req.getParameter("title");
		String start = req.getParameter("start");
		String end = req.getParameter("end");

		Map<String, Object> map = new HashMap<>();
		map.put("title", title);
		map.put("start", start);
		map.put("end", end);

		EtcService svc = new EtcServiceImpl();
		if (svc.addEvent(map)) {
			resp.getWriter().print("{\"retCode\": \"OK\"}");
		} else {
			resp.getWriter().print("{\"retCode\": \"NG\"}");
		}

	}

}
