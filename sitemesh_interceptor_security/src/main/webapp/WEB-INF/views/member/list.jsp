<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<title>회원리스트</title>
</head>
<body>
	<div class="content-header">
		<div class="container-fluid">
			<div class="row mb-2">
				<div class="col-sm-6">
					<h1 class="m-0 text-dark">회원리스트</h1>
				</div>
				<div class="col-sm-6">
					<ol class="breadcrumb float-sm-left">
						<li class="breadcrumb-item">
							<sec:authorize access="hasAuthority('ROLE_ADMIN')">
								<button class="btn btn-primary" type="button" id="registerBtn">회원등록</button>
							</sec:authorize>
						</li>
					</ol>
				</div>
				<!-- /.col -->
			</div>
		</div>
	</div>
	<br />

	<div class="content">
		<div class="row">
			<div class="col-md-12">
				<div class="box">
					<div class="box-body">
						<table class="table table-bordered">
							<tr class="">
								<th>아이디</th>
								<th>패스워드</th>
								<th>이름</th>
								<th>이메일</th>
								<th>등록일</th>
							</tr>
							<c:forEach var="member" items="${memberList }">
								<tr>
									<td><a href="detail?id=${member.id }">${member.id }</a></td>
									<td>${member.pwd }</td>
									<td>${member.name }</td>
									<td>${member.email }</td>
									<td><fmt:formatDate var="now" value="<%=new Date()%>"
											pattern="yyyy-MM-dd" /> <fmt:formatDate var="regDate"
											value="${member.regDate }" pattern="yyyy-MM-dd" /> <c:choose>
											<c:when test="${now eq regDate }">
												<fmt:formatDate value="${member.regDate }"
													pattern="HH:mm:ss" />
											</c:when>
											<c:otherwise>
												<fmt:formatDate value="${member.regDate }"
													pattern="yyyy-MM-dd" />
											</c:otherwise>
										</c:choose></td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
	<script type="text/javascript">
		$('#registerBtn').on('click', function(event) {
			location.href = "register";
		});
	</script>
</body>
</html>










