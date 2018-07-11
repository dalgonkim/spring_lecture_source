<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<html>
<head>
<title>인덱스 페이지</title>
<style>
	a{
		text-decoration:none;
	}
</style>
</head>
<body>
	<sec:authorize access="isAuthenticated()">
		${loginUser.id }님 환영합니다.
</sec:authorize>
	<ul>
		<li><a href="<c:url value='/home/main' />">/home/main</a></li>
		<li><a href="<c:url value='/member/main' />">/member/main</a></li>
		<li><a href="<c:url value='/manager/main' />">/manager/main</a></li>
		<li><a href="<c:url value='/admin/main' />">/admin/main</a></li>
	</ul>
	<sec:authorize access="!isAuthenticated()">
		<a href="commons/loginForm">
			<button type="button">Login</button>			
		</a>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="admin/commons/loginForm">
			<button type="button">Admin Login</button>			
		</a>
	</sec:authorize>	
	<sec:authorize access="isAuthenticated()">
		<sec:authorize access="!hasAuthority('ROLE_ADMIN')">
			<a href="<c:url value='/commons/logout' />">
		</sec:authorize>
		<sec:authorize access="hasAuthority('ROLE_ADMIN')">
			<a href="<c:url value='/admin/commons/logout' />">
		</sec:authorize>
			<button type="button">Logout</button>
		</a>
	</sec:authorize>

</body>
</html>
