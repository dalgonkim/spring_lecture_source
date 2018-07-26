<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메일보내기</title>
<link rel="stylesheet" type="text/css" href="../css/board.css" />
<script src="../js/member.js"></script>
<style>
div#mListWrap ul>li:nth-child(1) {
	width: 20%;
}

div#mListWrap ul>li:nth-child(2) {
	width: 80%;
}
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
			<h1>메일보내기</h1>
			<hr />
		</div>
		<form method="post">
			<div id="mListWrap">
				<ul>
					<li>받는 회원</li>
					<li><c:forEach items="${memberList }" var="member"
							varStatus="status">
							<input id="${member.userid }" type="checkbox" name="userid" value="${member.userid }" checked />
							<label for="${member.userid }">${member.name }(${member.email })</label>&nbsp;
					</c:forEach></li>
				</ul>
				<ul>
					<li>제 목</li>
					<li><input type="text" name="title" 
						style="width: 95%; margin: 0 auto; height: 30px; line-height: 30px; background: #f5f5ff; border: none;"
						/></li>
				</ul>
				<ul>
					<li style="height: 150px; line-height: 150px;">내 용</li>
					<li
						style="height: 150px; line-height: 150px; overflow: hidden; padding: 10px;">
						<textarea name="message"
							style="width: 100%; height: 100%; border: none; background: #f5f5ff;"></textarea>
					</li>
				</ul>

			</div>
			<br />
			<div id="btn">
				<input type="button" value="email 보내기" onclick="mail_send(this.form);" />
			</div>
		</form>
	</article>
</body>
</html>