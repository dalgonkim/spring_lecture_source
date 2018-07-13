<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<sec:authorize access="!isAuthenticated()">
		<a href="<%=request.getContextPath()%>/member/loginForm">로그인</a>
		
	</sec:authorize>

	
		<div id="adminMenu">
			<a href="<%=request.getContextPath()%>
			/member/memberList"><button>회원관리</button></a>
			<a href="<%=request.getContextPath()%>
			/product/productList"><button>상품관리</button></a>
			<a href="<%=request.getContextPath()%>
			/order/orderList"><button>주문관리</button></a>
		</div>
	
	
</body>
</html>