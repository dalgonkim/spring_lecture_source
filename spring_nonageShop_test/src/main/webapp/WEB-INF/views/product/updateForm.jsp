<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	position: relative;
}

div#header>h1 {
	text-align: center;
}

div#header>div#btn {
	position: absolute;
	bottom: 10px;
	right: 0;
	width: 100%;
	margin: 0 auto;
	overflow: hidden;
	text-align: right;
}

div#section ul {
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

div#section li>input:not(.radio){
	width: 97%;
	height: 30px;
	background: #eeeeff;
	font-size: 15px;
	border: none;
}

div#section li>input {
	margin: 0 10px;
}

div#section ul:last-child {
	width: 80%;
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
			<h1>상품수정</h1>
			<div id="btn">
				<a href="<%=request.getContextPath()%>/product/productList"><button>상품목록</button></a>
			</div>
		</div>
		<div id="section">
			<form method="post" enctype="multipart/form-data">
				<input type="hidden" name="pseq" value="${product.pseq}">
				<input type="hidden" name="nonmakeImg" value="${product.image}">
				<ul>
					<li>상품명</li>
					<li><input type="text" name="name" value="${product.name}" /></li>
				</ul>
				<ul>
					<li>상품종류</li>
					<li><select name="kind">
							<c:forEach items="${kindList}" var="kind" varStatus="status">
								<c:choose>
									<c:when test="${product.kind==status.count}">
										<option value="${status.count}" selected="selected">${kind}</option>
									</c:when>
									<c:otherwise>
										<option value="${status.count}">${kind}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
					</select></li>
				</ul>
				<ul>
					<li>원가</li>
					<li><input type="text" name="price1"
						value="${product.price1 }" /></li>
				</ul>
				<ul>
					<li>판매가</li>
					<li><input type="text" name="price2"
						value="${product.price2 }" /></li>
				</ul>
				<ul>
					<li>상품설명</li>
					<li><input type="text" name="content"
						value="${product.content }" /></li>
				</ul>

				<ul>
					<li>이미지</li>
					<li><img
						src="<%=request.getContextPath() %>/product_images/${product.image}"
						width="200pt"><br /> <input type="file" name="image" /></li>
				</ul>
				<ul>
					<li class="submit"><input type="submit" value="수  정" /></li>
				</ul>
			</form>

		</div>
	</article>
</body>
</html>








