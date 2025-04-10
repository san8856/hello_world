package com.yedam.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.yedam.service.EtcService;
import com.yedam.service.EtcServiceImpl;

// 클라이언트  - 필터(중간단계 로그작성/인코딩) - 서블릿. boardLsit.do
public class FilterOne implements Filter{
	
	public FilterOne() {
		System.out.println("FilterOne 생성자.");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("서블릿 실행 전");
		
		//요청하고있는 페이지와 클라이언트의 ip 받아오기
		String ip = request.getRemoteAddr();
		
		HttpServletRequest req = (HttpServletRequest) request;
		String uri = req.getRequestURI();
		String context = req.getContextPath();
		String reqPage= uri.substring(context.length());
		
		Map<String, String> map = new HashMap<>();
		map.put("page", reqPage);
		map.put("host", ip);
		
		//ip > return > 서블릿 차단으로 접속 막기
	//	List<String> blockList = new ArrayList<>();
	//	blockList.add("192.168.0.00");

		//로그저장.
		EtcService svc = new EtcServiceImpl();
		svc.logCreate(map);
		
	//	if(blockList.contains(ip)) {
	//		return;
	//	}
		chain.doFilter(request, response); // 이후에 또 다른 필터가 있으면 실행한다
		System.out.println("서블릿 실행 후"); 
		
		//-> web.xml 8행 필터 실행 로그 확인
		
		
	}
	
}
