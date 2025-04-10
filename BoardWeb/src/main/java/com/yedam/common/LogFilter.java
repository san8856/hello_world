package com.yedam.common;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.yedam.service.MemberService;
import com.yedam.service.MemberServiceImpl;
import com.yedam.vo.LogVO;

public class LogFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String host = request.getRemoteAddr() + " - " + request.getRemoteHost() + " - " + request.getRemotePort();

		HttpServletRequest req = (HttpServletRequest) request;

		LogVO info = new LogVO();
		info.setExecTime(new Date());
		info.setExecIp(host);
		String uri = req.getRequestURI();
		String context = req.getContextPath();
		String page = uri.substring(context.length());
		info.setExecPage(page);

		MemberService svc = new MemberServiceImpl();
		if (!request.getRemoteAddr().startsWith("0:0:0:0:0:0:0:1")) {
			svc.logWrite(info);
		}
		chain.doFilter(req, response);
	} // end of doFilter.

}
