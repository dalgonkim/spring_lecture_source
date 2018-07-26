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

<form action="writeMessage" method="post">
이름: <input type="text" name="guest_name" /> <br />
암호: <input type="password" name="password" /> <br />
메시지: <textarea name="message" cols="30" row="3"></textarea> <br />
<input type="submit" value="메시지 남기기" />
</form>
<hr>

</body>
</html>