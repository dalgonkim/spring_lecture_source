<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
<spring:hasBindErrors name="memberInfo" />
<form method="post" action="join" >
	<label for="email">이메일</label>: 
	<input type="text" name="email" value="${memberInfo.email }" />
	<form:errors path="memberInfo.email" /> <br />
	
	<label for="name">이름</label>: 
	<input type="text" name="name" id="name" value="${memberInfo.name}" />
	<form:errors path="memberInfo.name"/> <br/>
	
	<label for="password">암호</label>: 
	<input type="password" name="password" id="password" value="${memberInfo.password}"/>
	<form:errors path="memberInfo.password"/> <br/> 
	
	<label for="confirmPassword">확인</label>: 
	<input type="password" name="confirmPassword" id="confirmPassword" 
		   value="${memberInfo.confirmPassword}"/>
	<form:errors path="memberInfo.confirmPassword"/> <br/>
	
	<label for="birthday">생일</label>: 형식: YYYYMMDD, 예: 20140101
	<input type="text" name="birthday" id="birthday" 
		value='<fmt:formatDate value="${memberInfo.birthday}" pattern="yyyyMMdd" />'/>
	<form:errors path="memberInfo.birthday"/> <br/>

	<input type="submit" value="가입" />
</form>
</body>
</html>








