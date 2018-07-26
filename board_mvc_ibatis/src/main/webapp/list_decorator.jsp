<%@page import="mvjsp.chap13.model.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="decorator"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><decorator:title /></title>
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
<decorator:head />
</head>
<body>
	<%-- <c:if test="${empty sessionScope.loginUser }">
		${sessionScope.loginUser eq null}
		<c:redirect url="loginForm.jsp" />
		<script>
			location.href="<%=request.getContextPath()%>/member/login";
		</script>
	</c:if> --%>

	<div id="headWrap">
		<div id="topMenu">
			<span>${sessionScope.loginUser.id }님 반갑습니다.</span> 
			<a href="<%=request.getContextPath() %>/member/logout"><button>로그아웃</button></a>
		</div>
		<c:if test="${loginUser.id=='admin' }">
			<div id="adminMenu">
				<a href="<%=request.getContextPath() %>/member/memberList"><button>회원관리</button></a> 
				<a href="<%=request.getContextPath() %>/message/messageList"><button>방명록관리</button></a>
				<a href="<%=request.getContextPath() %>/product/productList"><button>상품관리</button></a>
			</div>
		</c:if>

	</div>
	<decorator:body />
</body>
</html>














