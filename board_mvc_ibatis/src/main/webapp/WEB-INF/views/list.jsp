<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ page import="mvjsp.chap13.model.Message"%>
<%@ page import="mvjsp.chap13.model.MessageListView"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
<title>방명록</title>
<style>
* {
	margin: 0;
	padding: 0;
}

table {
	width: 100%;
	background: black;
}

td, th {
	background: white;
	text-align: center;
	padding: 0.2em;
}

div#wrap>table td:nth-child(1) {
	width: 15%;
}

div#wrap>table td:nth-child(2) {
	width: 15%;
}

div#wrap>table td:nth-child(3) {
	width: 50%;
}

div#wrap {
	width: 80%;
	min-width: 500px;
	max-width: 850px;
	margin: 0 auto;
}

div#pageNum {
	padding: 10px;
	text-align: center;
}

div#boardHeader>h3 {
	text-align: center;
	color: green;
}

div#boardHeader {
	overflow: hidden;
}

div#boardHeader button {
	float: right;
}
</style>
</head>


<br />
<hr />
<br />
<div id="wrap">
	<div id="boardHeader">
		<h3>
			방명록 게시판
			</h1>
			<a href="writeMessage"><button>글쓰기</button></a>
			<a href="messageListExcel?pageNumber=${pageNumber }">
				<button>Excel로 받기</button>
			</a>
			<a href="messageListPdf?pageNumber=${pageNumber }">
				<button>Pdf로 받기</button>
			</a>
			<a href="messageList.xml?pageNumber=${pageNumber }">
				<button>Xml로 출력</button>
			</a>
			<a href="messageList.json?pageNumber=${pageNumber }">
				<button>Json로 출력</button>
			</a>
	</div>
	<br>

	<table>
		<tr>
			<th>메시지 번호</th>
			<th>손님 이름</th>
			<th>메시지</th>
			<th>구분</th>
		</tr>
		<c:choose>
			<c:when test="${viewData.messageTotalCount>0 }">
				<c:forEach var="message" items="${viewData.messageList }">
					<tr>
						<td>${message.message_id }</td>
						<td>${message.guest_name}</td>
						<td>${message.message}</td>
						<td><a href="deleteMessage?messageId=${message.message_id }">
								삭제&nbsp;/</a> <a href="updateMessage?messageId=${message.message_id }">
								&nbsp;수정</a></td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="4" style="text-align: center;">내용이 없습니다.</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>

	<div id="pageNum">

		<c:forEach var="i" begin="1" end="${viewData.pageTotalCount}">
			<a href="messageList?page=${i}">[${i}] </a>
		</c:forEach>
		
	</div>
</div>


</body>
</html>
