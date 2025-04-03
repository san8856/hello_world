<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!-- modifyBoard.jsp -->
<h3>수정화면(modifyForm.jsp)</h3>

  <form action="modifyBoard.do">
  <input type="hidden" name="bno" value="${board.boardNo }">
  <input type="hidden" name="page" value="${page }">
	<table class="table">
		<tr>
			<th>글번호</th>
			<td><c:out value="${board.boardNo }"/></td>
			<th>작성자</th>
			<td><c:out value="${board.writer }"/></td>
		</tr>
		<tr>
			<th>제목</th>
			<td ><input type="text" name="title" class="form-control" value="${board.title }">
			</td>
		</tr>
		<tr>
			<th>내용</th>
			<td colspan='3'><textarea name="content" class="form-control" cols='30' rows='3'>${board.content }</textarea>
			</td>
		</tr>
		<tr>
			<th>작성일시</th>
			<td colspan='3'><fmt:formatDate value="${board.writeDate }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
		</tr>
		<tr>
		  <td colspan="3" align="center">
		    <input type="submit" value="등록" class="btn btn-primary">
		    </td>
		    <td colspan="3" align="center">
		    <input type="reset" value="초기화" class="btn btn-danger">
		  </td>
		</tr>
<!-- <p></p>
BoardVO(boardNo=4, title=한글테스트, content=한글연습 중입니다, 
writer=userkr, writeDate=Tue Apr 01 09:21:25 KST 2025) -->
	</table>
	</form>



