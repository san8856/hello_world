<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!-- menu.jsp -->
<%
      String logId = (String) session.getAttribute("logId");
    %>
 <div class="border-end bg-white" id="sidebar-wrapper">
                <div class="sidebar-heading border-bottom bg-light">게시판</div>
                <div class="list-group list-group-flush">
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="boardList.do">글목록</a>
                    <%if (logId == null) {%>
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="loginForm.do">로그인</a>
                    <%} else { %>
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="addBoard.do">글작성</a>
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="logout.do">로그아웃(<%=logId %>)</a>
                    <%} %>
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="signForm.do">회원가입</a>
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="eventForm.do">calender</a>
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="javascript.do">JS연습</a>
                </div>
            </div>