package com.yedam.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//인터페이스
public interface Control {
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}
