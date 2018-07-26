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

<form action="writeProduct" method="post" enctype="multipart/form-data" >
	상 품 명 : <input type="text" name="proName" /> <br />
	원 가 : <input type="text" name="price1" /> <br />
	판매가 : <input type="text" name="price2" /> <br />
	수 량 : <input type="text" name="amount" /> <br />
	이미지 : <input type="file" name="image" /><br />

<input type="submit" value="상품등록" />
</form>
<hr>

</body>
</html>