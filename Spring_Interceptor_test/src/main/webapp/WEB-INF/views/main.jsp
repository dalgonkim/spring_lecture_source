<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<button onclick="javascript:location.href='page1';">page1</button>
	<button onclick="javascript:location.href='page2';">page2</button>

	<c:choose>
		<c:when test="${empty sessionScope.loginUser }">
			<button onclick="javascript:location.href='loginForm';">login</button>
		</c:when>
		
		<c:otherwise>
			<button onclick="javascript:location.href='logout';">logout</button>
		</c:otherwise>
		
	</c:choose>
</body>
</html>







