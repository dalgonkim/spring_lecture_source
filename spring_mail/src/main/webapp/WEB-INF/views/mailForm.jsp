<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h2>신규가입자 스펨보내기</h2>
	<form action="mail" method="get">
		이름 : <input type="text" name="name" /><br/>
		메일주소 : <input type="email" name="email" /><br/>
		<input type="submit" value="메일보내기" />
	</form>
</body>
</html>








