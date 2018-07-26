<%@page import="mvjsp.chap13.model.MemberListView"%>
<%@page import="mvjsp.chap13.service.MemberService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<title>회원리스트</title>
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
		<h3>회원 목록</h3>
		<a href="join"><button>회원등록</button></a>
		<a href="memberListExcel?pageNum=${pageNumber }" ><button>excel로 받기</button></a>
		<a href="memberListPdf?pageNum=${pageNumber }" ><button>pdf로 받기</button></a>
		<a href="memberList.xml?pageNum=${pageNumber }">
			<button>Xml출력</button>
		</a>
		<a href="memberList.json?pageNum=${pageNumber }">
			<button>Json출력</button>
		</a>
	</div>
	<br />
	<table>
		<tr>
			<th>번호</th>
			<th>아이디</th>
			<th>패스워드</th>
			<th>구분</th>
		</tr>
		<c:choose>
			<c:when test="${viewData.memberTotalCount>0 }">
				<c:forEach var="member" items="${viewData.memberList }"
					varStatus="status">
					<tr>
						<td>${viewData.firstRow +status.index}</td>
						<td>${member.id}</td>
						<td>${member.pwd }</td>
						<td><a href="deleteMember?memberId=${member.id }">
								삭제&nbsp;/</a> <a href="updateMember?memberId=${member.id }">
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
			<a href="memberList?page=${i}">[${i}] </a>
		</c:forEach>
	</div>
</div>




