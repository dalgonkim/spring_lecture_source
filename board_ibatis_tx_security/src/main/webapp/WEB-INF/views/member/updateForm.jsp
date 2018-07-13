<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원수정</title>
<style>
div#header {
	width: 80%;
	height: 100px;
	margin: 0 auto;
	position: relative;
}

div#header>h1 {
	text-align: center;
}

div#header>div#btn {
	position: absolute;
	bottom: 10px;
	right:0;
	width: 100%;
	margin:0 auto;
	overflow: hidden;
	text-align: right;
}



div#section ul {
	width: 80%;
	margin: 0 auto;
	overflow: hidden;
}


div#section ul>li {
	float: left;
	box-sizing:border-box;
	border: 1px #eeeeff solid;
	
}

div#section ul>li:nth-child(1) {
	width: 25%;
	text-align: center;
}

div#section ul>li:nth-child(2) {
	width: 75%;
}

div#section li>input:not(.radio){
	width:97%;	
	height:30px;
	background:#eeeeff;
	font-size:15px;
	border:none;

	
}
div#section li>input{
	margin:0 10px;
}
div#section ul:last-child{
	width:80%;
	margin-top:10px;
}

div#section ul:last-child>li{
	width:100%;
	float:none;
	border:none;	
}


div#section li.submit>input[type=submit]{	
	font-size:16px;	
	margin:0;
	height:50px;
	width:100%;
}
</style>

<script>
window.onload=function(){
	document.getElementsByName("enabled")[${member.enabled-1}].checked=true;	
	document.getElementsByName("authority")[${authNum}].checked=true;	
};
</script>
</head>
<body>
<article>
	<div id="header">
		<h1>회원수정</h1>
		<div id="btn">
			<a href="<%=request.getContextPath()%>/member/memberList"><button>회원목록</button></a>
		</div>
	</div>
	<div id="section">
		<form method="post" action="updataMember">
			<ul>
				<li>아이디</li>
				<li><input type="text" readonly name="userid" value="${member.userid}"/></li>
			</ul>
			<ul>
				<li>패스워드</li>
				<li><input type="password" name="password" value="${member.password }"/></li>
			</ul>
			<ul>
				<li>이름</li>
				<li><input type="text" name="name" value="${member.name}"/></li>
			</ul>
			<ul>
				<li>이메일</li>
				<li><input type="email" name="email" value="${member.email}"/></li>
			</ul>
			<ul>
				<li>사용유무</li>
				<li>
					<input class="radio" class="enabled" type="radio" name="enabled" value="1"><label for="user">유</label>
					&nbsp;&nbsp;&nbsp;				
					<input class="radio" class="enabled" type="radio" name="enabled" value="2"><label for="admin">무</label>
				</li>
			</ul>
			<ul>
				<li>권한</li>
				<li>
					<input class="radio" type="radio" name="authority" value="ROLE_USER"><label for="user">일반사용자</label>
					&nbsp;&nbsp;&nbsp;				
					<input class="radio" type="radio" name="authority" value="ROLE_ADMIN"><label for="admin">관리자</label>
					&nbsp;&nbsp;&nbsp;	<span>(현재 : ${member.authority })</span>
				</li>
			</ul>
			<ul>
				<li class="submit"><input  type="submit" value="수   정" /></li>
			</ul>			
		</form>
		
	</div>
</article>
</body>
</html>








