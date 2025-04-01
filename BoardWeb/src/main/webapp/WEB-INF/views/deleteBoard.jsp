<%@page import="com.yedam.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="includes/header.jsp" />
<h3>게시글 삭제</h3>
<% 
  BoardVO board = (BoardVO) request.getAttribute("board");
%>

<form action="deleteBoard.do" method="post">
  <input type="hidden" name="bno" value="<%=board.getBoardNo()%>">
	<table class="table">
		<tr>
			<th>글번호</th>
			<td><%=board.getBoardNo()%></td>
			<th>작성자</th>
			<td><%=board.getWriter()%></td>
		</tr>
		<tr>
			<th>제목</th>
			<td colspan="3"><%=board.getTitle()%></td>
		</tr>
		<tr>
			<th>내용</th>
			<td colspan="3"><textarea class="form-control" cols='30' rows='3' readonly><%=board.getContent()%></textarea>
			</td>
		</tr>
		<tr>
			<th>작성일시</th>
			<td colspan="3"><%=board.getWriteDate()%></td>
		</tr>
		<tr>
		  <td colspan="3" align="center">
		    <input type="submit" value="삭제" class="btn btn-danger">
		    <a href="boardList.do" class="btn btn-secondary">취소</a>
		  </td>
		</tr>
	</table>
</form>



<jsp:include page="includes/footer.jsp" />
