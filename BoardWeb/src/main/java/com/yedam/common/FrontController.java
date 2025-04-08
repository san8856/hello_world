package com.yedam.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.control.AddBoardControl;
import com.yedam.control.AddReplyControl;
import com.yedam.control.AuthentifyControl;
import com.yedam.control.BoardControl;
import com.yedam.control.BoardListControl;
import com.yedam.control.ModifyBoardControl;
import com.yedam.control.ModifyFormControl;
import com.yedam.control.RLDatatableControl;
import com.yedam.control.RemoveReplyControl;
import com.yedam.control.ReplyCountControl;
import com.yedam.control.ReplyListControl;
import com.yedam.control.SignUpControl;
import com.yedam.control.MainControl;
import com.yedam.control.DeleteBoardControl;
import com.yedam.control.DeleteFormControl;
import com.yedam.control.JSControl;
import com.yedam.control.LoginControl;
import com.yedam.control.LoginFormControl;
import com.yedam.control.LogoutControl;

// *.do 로 끝나는 요청에 대해서 실행.
public class FrontController extends HttpServlet {

	// 요청했던 url <=> 실행할 컨트롤 등록.
	Map<String, Control> map;

	// 생성자
	public FrontController() {
		map = new HashMap<String, Control>();
	}

	// init
	@Override
	public void init(ServletConfig config) throws ServletException {
		map.put("/main.do", new MainControl()); //메인화면
		
		map.put("/board.do", new BoardControl());//상세화면
		map.put("/boardList.do", new BoardListControl());//글목록
		map.put("/addBoard.do", new AddBoardControl());//글등록 AddBoardControl
		map.put("/modifyForm.do", new ModifyFormControl());//수정 화면
		map.put("/modifyBoard.do", new ModifyBoardControl());//수정 기능
		map.put("/deleteForm.do", new DeleteFormControl());//삭제화면
		map.put("/deleteBoard.do", new DeleteBoardControl());//삭제기능
		//로그인 관련.
		map.put("/loginForm.do", new LoginFormControl()); //로그인 화면
		map.put("/login.do", new LoginControl()); //로그인처리
		map.put("/logout.do", new LogoutControl()); //로그아웃.
		
		map.put("/signForm.do", new SignUpControl()); //회원가입
		map.put("/signUp.do", new SignUpControl()); // 회원등록
		map.put("/authentify.do", new AuthentifyControl());
		
		map.put("/javascript.do", new JSControl()); //js 연습
		
		map.put("/replyList.do", new ReplyListControl());//댓글데이터
		map.put("/replyListDatatable.do", new RLDatatableControl());
		map.put("/removeReply.do", new RemoveReplyControl()); //삭제
		map.put("/addReply.do", new AddReplyControl());//추가
		map.put("/replyCount.do", new ReplyCountControl());//댓글수
	}

	// service
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		  // url vs. uri
		  // http://localhost:8080[/BoardWeb/board.do] []-> uri
		String uri = req.getRequestURI();
//		System.out.println("요청 URI: " + uri); // /BoardWeb/board.do
		String context = req.getContextPath();
		String page = uri.substring(context.length()); // "/board.do"
		System.out.println(page);

		Control sub = map.get(page); // 키(url) => control 반환.
		sub.exec(req, resp);

	}
}
