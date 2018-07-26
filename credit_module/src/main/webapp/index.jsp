<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	
	<form action="credit/form" method="post">
		
		강의명 <input type='text' name='sndGoodname' value='당근10kg' size='30'><br/>
		수강료 <input type='text' name='sndAmount'  value='1004' size='15' maxlength='9'><br/>
		신청자 <input type='text' name='sndOrdername' value='김토끼' size='30'><br/>
		Email<input type='text' name='sndEmail' value='kspay@carrot.co.kr' size='30'><br/>
		전화번호 <input type='text' name='sndMobile' value='01112341234' size='12' maxlength='12'><br/>
		<input type="submit" value="전송" />
	</form>
	
</body>
</html>