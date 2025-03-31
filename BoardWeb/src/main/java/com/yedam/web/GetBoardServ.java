package com.yedam.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.DataSource;
import com.yedam.mapper.BoardMapper;
import com.yedam.vo.BoardVO;

//form태그(jsp) -> 서블릿
//서블릿 -> jsp 

@WebServlet("/getBoard")
public class GetBoardServ extends HttpServlet {
	// http://localhost/BoardWeb/getBoard?board_no=13

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		String boardNo = req.getParameter("board_no");

		// mybatis를 활용해서 jdbc 처리. //openSession: true -> 자동 커밋 false,공백 -> 수동으로 커밋 입력 vlfdy.
		
		SqlSession sqlSession = DataSource.getInstance().openSession(true);
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		BoardVO board = mapper.selectOne(Integer.parseInt(boardNo));
		
		PrintWriter out = resp.getWriter();
		String html = "<h3>상세조회</h3>";
		html += "글번호: " + board.getBoardNo() ;
		html += "제목: " + board.getTitle();
		html += "내용: " + board.getContent();
		html += "작성자: " + board.getWriter();
		html += "시간: " + board.getWriteDate();
		
		
		html += "<p><a href = 'mainservlet'>목록으로</a></p>";
		out.print(html);

	}

}
