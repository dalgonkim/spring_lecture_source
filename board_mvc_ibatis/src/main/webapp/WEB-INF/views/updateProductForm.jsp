<%@page import="mvjsp.chap13.model.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품정보 변경</title>
<style>
div#productImg{
	width:200px;
	height:200px;
	background-image:url(<%=request.getContextPath() %>/product-images/${product.image });
	background-position:center;
	background-size:cover;
	background-repeat:no-repeat;
}
</style>
</head>
<body>

<form action="updateProduct" method="post" enctype="multipart/form-data" >
<input type="hidden" name="nonmakeImg" value="${product.image }" />
<input type="hidden" name="proNum" value="${product.proNum}" />
상 품 명 : <input type="text" name="proName" value="${product.proName}"/> <br />
원 가 : <input type="text" name="price1" value="${product.price1}"/> <br />
판매가 : <input type="text" name="price2" value="${product.price2}"/> <br />
수 량 : <input type="text" name="amount" value="${product.amount}"/> <br />
이미지 : <input type="file" name="image" /><br />
<div id="productImg" ></div>
<input type="submit" value="수정" />
</form>
<hr>

</body>
</html>





