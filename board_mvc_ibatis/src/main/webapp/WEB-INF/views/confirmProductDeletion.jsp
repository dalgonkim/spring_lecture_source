<%@ page contentType="text/html; charset=utf-8" %>
<html>
<head>
	<title>상품 삭제 확인</title>
</head>
<body>

<form action="deleteProduct" method="post">
<input type="hidden" name="proNum" value="${param.proNum }" />
상품를 삭제하시려면 암호를 입력하세요:<br/>
암호: <input type="password" name="password" /> <br />
<input type="submit" value="상품 삭제하기" />
</form>
</body>
</html>