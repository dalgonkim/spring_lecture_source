<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<c:if test="${!empty msg }">
		<script>
			alert("${msg}");
		</script>
	</c:if>
	<h1>로그인 페이지</h1>
	<form action="login" method="post">
		아이디 : <input type="text" name="id" value="${param.id }"/><br/>
		패스워드 : <input type="password" name="pwd" value="${param.pwd }" /><br/>
		<input type="submit" value="로그인" />
	</form>
	
</body>
</html>