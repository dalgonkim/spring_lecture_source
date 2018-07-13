<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품수정</title>
<style>
div#header {
	width: 80%;
	height: 100px;
	margin: 0 auto;
}

div#header>h1 {
	text-align: center;
}

div#btn {
	width: 80%;
	margin: 0 auto;
	padding-top: 10px;
	text-align: right;
}

div#section {
	width: 80%;
	margin: 0 auto;
	overflow: hidden;
}

div#section ul>li {
	float: left;
	box-sizing: border-box;
	border: 1px #eeeeff solid;
}

div#section ul>li:nth-child(1) {
	width: 25%;
	text-align: center;
}

div#section ul>li:nth-child(2) {
	width: 75%;
}

div
#section
 
li
>
input
:not
 
(
.radio
 
){
width
:
 
97%;
height
:
 
30
px
;

	
background
:
 
#eeeeff
;

	
font-size
:
 
15
px
;

	
border
:
 
none
;


}
div#section li>input {
	margin: 0 10px;
}

div#section ul:last-child {
	margin-top: 10px;
}

div#section ul:last-child>li {
	width: 100%;
	float: none;
	border: none;
}

div#section li.submit>input[type=submit] {
	font-size: 16px;
	margin: 0;
	height: 50px;
	width: 100%;
}
</style>
</head>
<body>
	<article>
		<div id="header">
			<h1>상품정보</h1>
			<div id="btn">
				<a href="<%=request.getContextPath()%>/product/productList"><button>상품목록</button></a>
			</div>
		</div>
		<div id="section">
			<ul>
				<li>상품명</li>
				<li>${product.name}</li>
			</ul>
			<ul>
				<li>상품종류</li>
				<li>${kind}</li>
			</ul>
			<ul>
				<li>원가</li>
				<li>${product.price1 }</li>
			</ul>
			<ul>
				<li>판매가</li>
				<li>${product.price2 }</li>
			</ul>
			<ul>
				<li>상품설명</li>
				<li>${product.content }</li>
			</ul>

			<ul>
				<li>이미지</li>
				<li><img
					src="<%=request.getContextPath() %>/product_images/${product.image}"
					width="200pt"></li>
			</ul>
		</div>
		<sec:authorize access="hasRole('ROLE_ADMIN')">
			<div id="btn">
				<a href="updateProduct?pseq=${product.pseq }"><button>상품수정</button></a>
				<a href="deleteProduct?pseq=${product.pseq }"><button>상품삭제</button></a>
			</div>
		</sec:authorize>
	</article>
</body>
</html>





















