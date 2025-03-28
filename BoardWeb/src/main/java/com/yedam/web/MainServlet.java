package com.yedam.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.yedam.common.DataSource;
import com.yedam.dao.BoardDAO;
import com.yedam.vo.BoardVO;

/*
 * tomcat(WAS) 의 규칙에 따라 작성.
 * 1. url 패턴 호출 ex) http://localhost/BoardWeb/welcome
 * 2. 서블릭 클래스 생성 (호출 시 -> init() -> service() -> destroy())
 */
public class MainServlet extends HttpServlet {
	// 생성자.
	public MainServlet() {
		System.out.println("MainServlet() 호출.");
	}

	// 서블릿의 생명주기(init)
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("최초호출 한번만 실행되는 init() 메소드입니다. ");
	}

	@Override
	public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		System.out.println("호출될 때 마다 실행되는 service() 메소드입니다. ");
//		BoardDAO dao = new BoardDAO();
//		List<BoardVO> list = dao.boardList();

		SqlSessionFactory sqlSessionFactory = DataSource.getInstence();
		try (SqlSession session = sqlSessionFactory.openSession()) {
			List<BoardVO> list = session.selectList("com.yedam.mapper.BoardMapper.selectBoard");

			PrintWriter out = resp.getWriter();
			String html = "<h3>게시글목록</h3>";
			html += "<table border='2'>";
			html += "<thead><tr><th>글번호</th><th>내용</th><th>작성자</th><th>작성일시</th></tr></thead>";
			html += "<tbody>";
			for (BoardVO board : list) {
				html += "<tr>";
				html += "<td>" + board.getBoardNo() + "</td>";
				html += "<td>" + board.getTitle() + "</td>";
				html += "<td>" + board.getWriter() + "</td>";
				html += "<td>" + board.getWriteDate() + "</td>";
				html += "</tr>";
			}

			html += "</tbody></table>";
			out.print(html);
		} // end of try.
	}// end of service.

	@Override
	public void destroy() {
		System.out.println("서버 종료시 실행되는 destroy() 메소드입니다. ");
	}
}
