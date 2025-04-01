package com.yedam.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.Control;
import com.yedam.common.DataSource;
import com.yedam.common.PageDTO;
import com.yedam.mapper.BoardMapper;
import com.yedam.vo.BoardVO;
import com.yedam.vo.SearchDTO;

public class BoardListControl implements Control{

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 글 목록정보를 가져와서 jsp에 화면 출력
		//parameter.
		String page = req.getParameter("page");
		page = page == null? "1" : page;
		String sc = req.getParameter("searchCondition");
		String kw = req.getParameter("keyword");
		
		SearchDTO search = new SearchDTO();
		search.setKeyword(kw);
		search.setSearchCondition(sc);
		search.setPage(Integer.parseInt(page));
		
		//글목록
		SqlSession sqlSession = DataSource.getInstance().openSession();	
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		List<BoardVO> list = mapper.selectBoard(search);
		req.setAttribute("blist", list);
		
		//페이징 계산.
		int totalCnt = mapper.selectTotal(search);
		PageDTO pageDTO = new PageDTO(totalCnt, Integer.parseInt(page));
		req.setAttribute("paging", pageDTO);
		req.setAttribute("searchCondition", sc);
		req.setAttribute("keyword", kw);
		
		//boardList.do -> jsp 출력. : 페이지 재지정.
		req.getRequestDispatcher("/WEB-INF/views/boardList.jsp").forward(req, resp);
		
		
	}

	
	
}