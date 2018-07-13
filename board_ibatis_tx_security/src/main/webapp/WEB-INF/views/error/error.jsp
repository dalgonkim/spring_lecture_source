<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page  trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<article>
		<div style="text-align:center;margin:0 auto;padding-top:50px;" >
			<h1>${message }</h1>
			<a href="<%=request.getContextPath()%>/${url}"><button>홈으로 이동</button></a>
		</div>
	</article>
</body>
</html>