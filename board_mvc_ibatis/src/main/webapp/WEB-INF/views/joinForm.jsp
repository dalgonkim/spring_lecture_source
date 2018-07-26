<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<spring:hasBindErrors name="member" />
	<form action="join" method="post" >
		아이디 : <input type="text" name="id" value="${member.id }"/><br />
		<form:errors path="member.id" /><br />
		패스워드 : <input type="password" name="pwd" value="${member.pwd }"/><br />
		<form:errors path="member.pwd" /><br />
		<input type="submit" value="가입" />
		<input type="reset" value="초기화" />				
	</form>
</body>
</html>