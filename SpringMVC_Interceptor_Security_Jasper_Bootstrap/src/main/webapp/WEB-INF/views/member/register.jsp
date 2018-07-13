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
	<form action="register" method="post" >
		아이디 : <input type='text' name="id" /><br/>
		패스워드 : <input type="password" name="pwd" /><br/>
		이름 : <input type='text' name="name"/><br/>
		이메일 : <input type="email" name="email" /><br/>
		<button type="submit" >가입</button> 
		&nbsp;&nbsp;&nbsp;&nbsp;
		<button type='button' onclick="cancel_go()">취소</button>		
		
	</form> 
	
	<script>
		function cancel_go(){
			self.location="list";
		};
	</script>
</body>
</html>










