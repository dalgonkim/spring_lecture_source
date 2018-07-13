<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="decorator"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<link rel="stylesheet" href="/admin/css/admin.css">
<script type="text/javascript" src="/admin/js/product.js"></script>

</head>
<body>
	<div id="wrap">
		<header>
			<div id="logo">
				<a href="/admin/loginForm.do"> <img style="width: 800px"
					src="/admin/images/bar_01.gif"> <img
					src="/admin/images/text.gif">
				</a>
			</div>
			<sec:authorize access="isAuthenticated()">
				<input class="btn" type="button" value="logout"
						style="float: right;" onClick="location.href='/admin/logout.do'">
			</sec:authorize>
		</header>
		<div class="clear"></div>
		
		<decorator:body />

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

