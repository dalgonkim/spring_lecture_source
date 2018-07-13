<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="decorator"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

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
<title>Nonage Shop</title>
<link rel="stylesheet" href="/css/shopping.css">
<script type="text/javascript" src="/js/product.js"></script>
<script type="text/javascript" src="/js/member.js"></script>
<script type="text/javascript" src="/js/mypage.js"></script>
<decorator:head />
</head>
<body>
	<div id="wrap">
		<!--헤더파일 들어가는 곳 시작 -->
		<header>
			<!--로고 들어가는 곳 시작--->
			<div id="logo">
				<a href="/main/index.do"> <img src="/images/logo.gif" width="180"
					height="100" alt="nonageshop">
				</a>
			</div>
			<!--로고 들어가는 곳 끝-->
			<nav id="catagory_menu">
				<ul>
					<c:choose>
						<c:when test="${empty sessionScope.loginUser}">
							<li><a href="/member/loginForm.do" style="width: 110px;">LOGIN&nbsp;(CUSTOMER</a>
								<a href="/admin/loginForm.do">| ADMIN)</a></li>
							<li>/</li>
							<li><a href="/member/contract.do">JOIN</a></li>
						</c:when>
						<c:otherwise>
							<li style="color: orange">
								${sessionScope.loginUser.name}(${sessionScope.loginUser.id})</li>
							<li><a href="/member/logout.do">LOGOUT</a></li>
						</c:otherwise>
					</c:choose>
					<li>/</li>
					<li><a href="<%=request.getContextPath() %>/cart/cartList.do">CART</a></li>
					<li>/</li>
					<li><a href="<%=request.getContextPath() %>/member/mypage.do">MY PAGE</a></li>
					<li>/</li>
					<li><a href="<%=request.getContextPath() %>/qna/qnaList.do">Q&amp;A(1:1)</a></li>
				</ul>
			</nav>

			<nav id="top_menu">
				<ul>
					<li><a href="/product/catagory.do?kind=1">Heels</a></li>
					<li><a href="/product/catagory.do?kind=2">Boots</a></li>
					<li><a href="/product/catagory.do?kind=3">Sandals</a></li>
					<li><a href="/product/catagory.do?kind=4">Sneakers</a></li>
					<li><a href="/product/catagory.do?kind=5">On Sale</a></li>
				</ul>
			</nav>
			<div class="clear"></div>
			<hr>
		</header>
		<!--헤더파일 들어가는 곳 끝 -->
		
		<decorator:body />
		

		<!-- 푸터 시작 -->
		<div class="clear"></div>

		<footer>
			<hr>
			<div id="copy">
				All contents Copyright 2013 Nonage Inc. all rights reserved<br>
				Contact mail : Nonage@Nonage.com Tel: +82 64 123 4315 Fax +82 64 123
				4321
			</div>
		</footer>
	</div>
</body>

</html>

