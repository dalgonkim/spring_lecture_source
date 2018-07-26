<%@page import="mvjsp.chap13.model.Message"%>
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

<form action="updateMessage" method="post">
<input type="hidden" name="message_id" value="${message.message_id}" />
이름: <input type="text" name="guest_name" 
value="${message.guest_name}"/> <br />
암호: <input type="password" name="password"
value="${message.password}" /> <br />
메시지: <textarea name="message" cols="30" row="3">
${message.message}</textarea> <br />
<input type="submit" value="수정" />
</form>
<hr>

</body>
</html>





