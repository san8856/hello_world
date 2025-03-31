package com.yedam.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.DataSource;
import com.yedam.mapper.BoardMapper;
import com.yedam.vo.BoardVO;

@WebServlet("/addBoard") //("/views/addBoard")
public class AddBoardServ extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//addForm.jsp -> 3개의 값을 전달(title, writer, content)
		// ?title=title&writer=user01&content=content // ?뒤 -> 쿼리문자문
		String title = req.getParameter("title");
		String writer = req.getParameter("writer");
		String content = req.getParameter("content");
		
		BoardVO board = new BoardVO(); //보드VO에 들어갈 매개값 선언
		board.setTitle(title);
		board.setWriter(writer);
		board.setContent(content);
		
		//mybatis를 활용해서 jdbc 처리.                                 //openSession: true -> 자동 커밋 false,공백 -> 수동으로 커밋 입력 필요.
		SqlSession sqlSession = DataSource.getInstance().openSession(true);
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		int r = mapper.insertBoard(board);
		
		
		resp.getWriter().print(r + "건 처리.");
		
	}// end of service.
}//end of class.
