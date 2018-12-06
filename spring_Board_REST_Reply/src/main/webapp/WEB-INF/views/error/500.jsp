<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

<%@ include file="/WEB-INF/views/error/include/header.jsp" %>

<body>
	<h1>내부 오류가 발생했습니다.</h1>
	<h3>잠시 후 이용바랍니다.</h3>
</body>

<script>
	document.title="500 Error";
</script>
<%@ include file="/WEB-INF/views/error/include/footer.jsp" %>