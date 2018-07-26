<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>오류정보</title>
</head>
<body>
	<article>
		<div style="text-align:center;margin:0 auto;padding-top:50px;">
			<h1>${message }</h1><br /><br />
			<a href="<%=request.getContextPath() %>/${url }"><button>페이지 이동</button></a>
		</div>
	</article>
</body>
</html>