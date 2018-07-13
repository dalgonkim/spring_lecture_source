<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원등록</title>
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
</head>
<body>
<article>
	<div id="header">
		<h1>회원등록</h1>
		<div id="btn">
			<a href="<%=request.getContextPath()%>/member/memberList"><button>회원목록</button></a>
		</div>
	</div>
	<div id="section">
		<form method="post">
			<ul>
				<li>아이디</li>
				<li><input type="text" name="userid" /></li>
			</ul>
			<ul>
				<li>패스워드</li>
				<li><input type="password" name="password" /></li>
			</ul>
			<ul>
				<li>이름</li>
				<li><input type="text" name="name" /></li>
			</ul>
			<ul>
				<li>이메일</li>
				<li><input type="email" name="email" /></li>
			</ul>

			<ul>
				<li>권한</li>
				<li><input id="user" class="radio" type="radio" name="authority" value="ROLE_USER"><label for="user">일반사용자</label>
					&nbsp;&nbsp;&nbsp;				
					<input id="admin" class="radio" type="radio" name="authority" value="ROLE_ADMIN"><label for="admin">관리자</label>
				</li>
			</ul>
			<ul>
				<li class="submit"><input  type="submit" value="등록" /></li>
			</ul>			
		</form>
		
	</div>
</article>
</body>
</html>








