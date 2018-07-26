<%@ page contentType="text/html; charset=utf-8" %>
<%@ page errorPage="errorView.jsp" %>

<html>
<head>
	<title>회원정보 삭제함</title>
</head>
<body>
<%
boolean invalidPassowrd=
(Boolean)request.getAttribute("invalidPassowrd");
if (!invalidPassowrd) { %>
회원정보를 삭제하였습니다.
<%  } else { %>
입력한 암호가 올바르지 않습니다. 암호를 확인해주세요.
<%  }%>
<br/>
<a href="memberList">[목록 보기]</a>
</body>
</html>




