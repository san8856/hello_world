package com.yedam.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.Control;
import com.yedam.common.DataSource;
import com.yedam.mapper.BoardMapper;
import com.yedam.mapper.EventMapper;
import com.yedam.service.EventService;
import com.yedam.service.EventServiceImpl;
import com.yedam.vo.EventVO;

public class AddEventControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("text/json;charset=utf-8");
		
		String title = req.getParameter("title");
		String startDate = req.getParameter("sDate");
		String endDate = req.getParameter("eDate");
		
		EventVO evo = new EventVO();
		evo.setTitle(title);
		evo.setStartDate(startDate);
		evo.setEndDate(endDate);
		System.out.println(evo);
		
        EventService service = new EventServiceImpl();
        boolean result = service.addEvent(evo);

        String json = "";
        if (result) {
            json = "{\"retCode\": \"OK\"}";
        } else {
            json = "{\"retCode\": \"NG\"}";
        }
        resp.getWriter().print(json);
		
	}

}
