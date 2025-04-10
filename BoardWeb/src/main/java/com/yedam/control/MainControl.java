package com.yedam.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yedam.common.Control;
import com.yedam.service.MemberService;
import com.yedam.service.MemberServiceImpl;
import com.yedam.vo.MemberVO;

public class MainControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession();
		String logId = (String) session.getAttribute("logId");
		// null => 로그인정보가 없으면...
		if (logId == null) {
			resp.sendRedirect("loginForm.do");
			return;
		}
		MemberService svc = new MemberServiceImpl();
		MemberVO mvo = svc.getMember(logId);
		// 일반사용자 vs. 관리자용 템플릿.
		if (mvo.getResponsibility().equals("User")) {
			req.getRequestDispatcher("user/main.tiles").forward(req, resp);
		} else if (mvo.getResponsibility().equals("Admin")) {
			req.getRequestDispatcher("admin/main.tiles").forward(req, resp);
		}
	}

}
