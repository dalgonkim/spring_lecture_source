<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<title>회원 상세</title>
</head>
<body>
	<h1>회원 상세</h1>
	<table border="1">
		<tr>
			<td>아이디</td>
			<td>${member.id }</td>
		</tr>
		<tr>
			<td>패스워드</td>
			<td>${member.pwd }</td>
		</tr>
		<tr>
			<td>이름</td>
			<td>${member.name }</td>
		</tr>
		<tr>
			<td>이메일</td>
			<td>${member.email }</td>
		</tr>
		<tr>
			<td>등록일</td>
			<td>${member.regDate }</td>
		</tr>
	</table>
	<br/>
	<button type="button" id="modifyBtn" >수 정</button>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<button type="button" id="removeBtn" >삭 제</button>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<button type="button" id="listBtn">목 록</button>
	
	<form role="form" method='post'>
		<input type="hidden" name="id" value="${member.id }" />
	</form>
	
	<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
	<script>
	
		var form=$('form[role="form"]');
		
		$('#modifyBtn').on('click',function(){
			/* self.location="modify?id=${member.id}"; */
			form.attr("action","modify");
			form.attr("method","get");
			form.submit();
		});
		$('#removeBtn').on('click',function(){
			form.attr("action","remove");
			form.submit();
		});
		$('#listBtn').on('click',function(){
			self.location="list";
		});
	</script>
</body>
</html>

















