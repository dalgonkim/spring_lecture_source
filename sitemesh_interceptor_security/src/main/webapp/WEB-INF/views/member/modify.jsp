<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h1>회원 수정</h1>
	<form method="post" action="modify">
		<table border="1">
			<tr>
				<td>아이디</td>
				<td>
					<input type="text" name="id" value="${member.id }" readonly/></td>
			</tr>
			<tr>
				<td>패스워드</td>
				<td><input type="password" name="pwd" value="${member.pwd }"/></td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type='text' name="name" value="${member.name }"/></td>
			</tr>
			<tr>
				<td>이메일</td>
				<td><input type="email" name="email" value="${member.email }"/></td>
			</tr>
			<tr>
				<td>등록일</td>
				<td><input type="text" 
				value='<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${member.regDate }"/>' 
				disabled /></td>
			</tr>
		</table>
		<br/>
		<button type="submit">저 장</button>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<button type="button" onclick="list_go()">취 소</button>
	</form>
	
	<script>
		function list_go(){
			self.location="list";
		}
	</script>
</body>
</html>






