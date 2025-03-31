<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="includes/header.jsp"/>
	<!-- addForm.jsp -->
	<!-- 폼 태그 안에서 name 속성으로 구분, 서블릿작성?title=title&writer=user01&content=content ? 뒤에 name = 내용 -> key = value 형식-->

	<form action = "addBoard.do" method="post">  <!-- <form action = "addBoard"> -->
	  <table class="table">
	    <tr>
	      <th>글제목</th><td><input class="form-control" type="text" name="title"></td>
	    </tr>
	    <tr>
	      <th>작성자</th><td><input class="form-control" type="text" name="writer"></td>
	    </tr>
	    <tr>
	      <th>본문</th><td><textarea class="form-control" name="content" rows="3" cols="40"></textarea></td>
	    </tr>
	    <tr>
	      <td colspan="2" align = "center">
	        <input type = "submit" class="btn btn-primary">
	        <input type = "reset" class="btn btn-secondary">
	        </td>
	    </tr>
	  </table>
	</form>
<jsp:include page="includes/footer.jsp"/>