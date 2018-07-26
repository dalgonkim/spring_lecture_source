<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원리스트</title>
<link rel="stylesheet" type="text/css" href="../css/board.css" />
<script src="../js/member.js"></script>
<style>
div#mListWrap ul>li:nth-child(1) {width: 19%;}
div#mListWrap ul>li:nth-child(2) {width: 19%;}
div#mListWrap ul>li:nth-child(3) {width: 16%;}
div#mListWrap ul>li:nth-child(4) {width: 23%;}
div#mListWrap ul>li:nth-child(5) {width: 14%;}
div#mListWrap ul>li:nth-child(6) {width: 9%;}

div#btn{
	width:85%;
	min-width: 420px;
    max-width: 960px;
	margin:0 auto;
	text-align:right;
}
</style>
</head>
<body>
	<article>
		<div id="listHead">
			<h1>회원리스트</h1>
			<hr />
			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<button id="writeBtn" onclick="btnClick();">&nbsp;회원등록&nbsp;
				</button>
			</sec:authorize>

			<form>
				회원명 : <input type="text" name="key" />&nbsp; <input type="button"
					onclick="searchMember(this.form);" value="검색">
			</form>
		</div>
		<form method="post">
			<div id="mListWrap">
				<ul class="title">
					<li>아 이 디</li>
					<li>패스워드</li>
					<li>성 명</li>
					<li>이 메 일</li>
					<li>권 한</li>
					<li>선 택</li>
				</ul>

				<c:choose>
					<c:when test="${!empty memberList }">
						<c:forEach var="member" items="${memberList}">
							<ul class="list">
								<li>${member.userid }</li>
								<li>${member.password }</li>
								<li><a href="detailMember?userid=${member.userid }">
										${member.name } </a></li>
								<li>${member.email }</li>
								<li>${member.authority }</li>
								<li><input type="checkbox" name="userid" value="${member.userid }" /></li>
							</ul>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<ul>
							<li style="width: 100%;">데이터가 없습니다.</li>
						</ul>
					</c:otherwise>
				</c:choose>
			</div>
			<br />
			<div id="btn" >			
				<input type="button" value="email 작성" onclick="mail_btn(this.form);" />
			</div>
		</form>
	</article>
</body>
</html>





















