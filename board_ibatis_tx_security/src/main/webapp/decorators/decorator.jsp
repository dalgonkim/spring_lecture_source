<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="decorator"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.addHeader("Cache-Control", "no-store");
	response.setDateHeader("Expires", 1L);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><decorator:title /></title>
<link rel="stylesheet" type="text/css" href="../css/main.css" />
<link rel="stylesheet" type="text/css" href="../css/board.css" />
<decorator:head />
<style>
div#headWrap {
	position: relative;
	width: 80%;
	height: 100px;
	min-width: 450px;
	max-width: 850px;
	margin: 0 auto;
	overflow: hidden;
}

div#topMenu {
	float: right;
}

div#adminMenu {
	position: absolute;
	left: 0;
	bottom: 0;
}
</style>
</head>
<body>
	<div id="headWrap">
		<sec:authorize access="isAuthenticated()">

			<div id="topMenu">
				<span>${loginUser}님 반갑습니다.</span> <a
					href="<%=request.getContextPath()%>/member/logout">
					<button>로그아웃</button>
				</a>
			</div>

			<div id="adminMenu">
				<a href="<%=request.getContextPath()%>
			/member/memberList"><button>회원관리</button></a>
				<a href="<%=request.getContextPath()%>
			/product/productList"><button>상품관리</button></a>
				<a href="<%=request.getContextPath()%>
			/order/orderList"><button>주문관리</button></a>
			</div>
		</sec:authorize>

	</div>

	<decorator:body />
</body>
</html>