<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>    

<table class="table">
	<tr>
	  <td>안녕하세요 ${empty userName ? 'empty' : userName }님</td>
	  
	  <td>사용자권한: ${!empty logId ? responsibility == 'User' ? '일반사용자' : '관리자' : 'guest'}</td>
	</tr>
</table>
