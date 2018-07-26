<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<style>
	div#header{
		width:80%;
		height:100px;	
		margin:0 auto;
	}
	div#header>h1{
		text-align:center;	
	}
	div#btn{
		width:70%;
		margin:0 auto;
		padding-top:10px;	
		text-align:right;	
	}
		
	div#section{
		width:70%;
		margin:0 auto;	
	}
	div#section>ul{
		overflow:hidden;
	}
	div#section>ul>li{
		float:left;
		box-sizing:border-box;
		border:1px #eeeeff solid;		
	}
	
	div#section>ul>li:nth-child(1){
		width:25%;
		text-align:center;
	}
	div#section>ul>li:nth-child(2){
		width:75%;
		padding-left:10px;
	}
	
</style>
</head>
<body>
	<article>
	<div id="header">
		<h1>회원정보</h1>		
	</div>
	<div id="section">
		<ul>
			<li>아이디</li>
			<li>${member.userid }</li>
		</ul>
		<ul>
			<li>패스워드</li>
			<li>${member.password }</li>
		</ul>
		<ul>
			<li>이름</li>
			<li>${member.name }</li>
		</ul>
		<ul>
			<li>이메일</li>
			<li>${member.email }</li>
		</ul>
		<ul>
			<li>가입날짜</li>
			<li><fmt:formatDate value="${member.indate }" type="date" /></li>
		</ul>
		<ul>
			<li>사용여부</li>
			<li>${member.enabled }</li>
		</ul>
		<ul>
			<li>권한</li>
			<li>${member.authority }</li>
		</ul>
	</div>
	<div id="btn">
			<a href="<%=request.getContextPath()%>/member/memberList"><button>회원목록</button></a>
			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<a href="updateMember?userid=${member.userid }"><button>정보수정</button></a>
				<a href="deleteMember?userid=${member.userid }"><button>회원삭제</button></a>
			</sec:authorize>
	</div>
	</article>
</body>
</html>








