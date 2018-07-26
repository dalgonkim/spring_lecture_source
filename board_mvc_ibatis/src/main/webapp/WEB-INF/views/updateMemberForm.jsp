<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<form action="updateMember" method="post" >
		아이디 : <input type="text" name="id" value="${member.id }" readonly /><br />
		패스워드 : <input type="password" name="pwd" value="${member.pwd }"/><br />
		<input type="submit" value="수정" />
		<input type="reset" value="초기화" />				
	</form>
</body>
</html>