<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<form action="authentifycate.do" method="post">
  <table class="table">
    <tr>
      <th>현재비밀번호</th>
      <td><input type="password" name="current_pw required"></td>
    </tr>
    <tr>
      <th>새로운 비밀번호</th>
      <td><input type="password" name="new_pw required"></td>
    </tr>
    <tr>
      <th>비밀번호 확인</th>
      <td><input type="password" name="confirm_pw required"></td>
    </tr>
    <tr>
      <td colspan="2" align="center">
        <button type="submit">변경</button>
      </td>
    </tr>
  
  </table>

</form>