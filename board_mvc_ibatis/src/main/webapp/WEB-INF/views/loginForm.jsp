<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h2>로그인페이지</h2>
	<form:form action="login" method="post" commandName="member">
		아이디 : <form:input path="id" />
		<form:errors path="id" /><br />
		패스워드 : <form:password path="pwd" />
		<form:errors path="pwd" /><br />
		${message }<br />
		<input type="submit" value="로그인" />
		<input type="reset" value="초기화" />
	</form:form>
</body>
</html>




