<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	요청한 페이지에 대한 권한이 부적절합니다.<br />
	<a href="<%=request.getContextPath() %>/main/index">
		<button>첫화면으로 돌아가기</button>
	</a>
</body>
</html>