<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품리스트</title>
<link rel="stylesheet" type="text/css" href="../css/board.css" />
<script src="../js/product.js"></script>

</head>
<body>
	<div id="listHead">
		<h1>상품리스트</h1>
		<hr />
		<div style="text-align:right;">
		<a href="productListExcel?key=${key}"><button>Excel로 받기</button></a>
		<a href="productListPdf?key=${key}"><button>Pdf로 받기</button>	</a>
		<a href="productListXml?key=${key}"><button>Xml로 출력</button></a>
		<a href="productListJson?key=${key}"><button>Json로 출력</button></a>
		</div>
		<br />
		<sec:authorize access="hasRole('ROLE_ADMIN')">
			<button id="writeBtn" onclick="btnClick();">&nbsp;상품등록&nbsp;
			</button>
		</sec:authorize>

		<form name="searchBar">
			상품명 : <input type="text" name="key" />&nbsp; 
			<input type="button"
				onclick="searchProduct(this.form);" value="검색">
		</form>
	</div>
	<div id="mListWrap">
		<ul class="title">
			<li>상품번호</li>
			<li>상품명</li>
			<li>종 류</li>
			<li>원 가</li>
			<li>판매가</li>
			
		</ul>
		<c:choose>
			<c:when test="${!empty productList }">
				<c:forEach var="product" items="${productList}">
					<ul class="list">
						<li>${product.pseq }</li>
						<li><a href="detailProduct?pseq=${product.pseq }">
								${product.name } </a></li>
						<li id="kind${product.pseq}">
							<script>
								product_kind(${product.pseq},${product.kind});  
							</script>
						</li>
						<li>${product.price1 }</li>
						<li>${product.price2 }</li>
					</ul>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<ul>
					<li style="width:100%;">데이터가 없습니다.</li>
				</ul>
			</c:otherwise>

		</c:choose>
	</div>
	
	
</body>
</html>






















