package com.yedam.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.Control;
import com.yedam.common.DataSource;
import com.yedam.mapper.BoardMapper;
import com.yedam.vo.BoardVO;

public class DeleteBoardControl implements Control {
	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		String bno = req.getParameter("bno");

		SqlSession sqlSession = DataSource.getInstance().openSession(true);
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		
		int result = mapper.deleteBoard(Integer.parseInt(bno));

		if (result > 0) {
			resp.sendRedirect("boardList.do"); // 삭제 성공 시 목록으로 이동
		} else {
			resp.getWriter().write("<script>alert('삭제 실패했습니다.'); history.back();</script>");
		}
	}
}