<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ page import="mvjsp.chap13.model.Product"%>
<%@ page import="mvjsp.chap13.model.ProductListView"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
<title>방명록</title>
<style>
* {
	margin: 0;
	padding: 0;
}

table {
	width: 100%;
	background: black;
}

td, th {
	background: white;
	text-align: center;
	padding: 0.2em;
}
/*			<th>상품 번호</th>
			<th>이미지</th>
			<th>상 품 명</th>
			<th>원   가</th>
			<th>판 매 가</th>
			<th>수  량</th>
			<th>구  분</th> */
div#wrap>table th:nth-child(1) { 
	width: 7%;
}

div#wrap>table th:nth-child(2) {
	width: 5%;
}

div#wrap>table th:nth-child(4) {
	width:10%;
}
div#wrap>table th:nth-child(5) {
	width:10%;
}
div#wrap>table th:nth-child(6) {
	width:7%;
}
div#wrap>table th:nth-child(7) {
	width:15%;
}
div#wrap {
	width: 80%;
	min-width: 500px;
	max-width: 850px;
	margin: 0 auto;
}

div#pageNum {
	padding: 10px;
	text-align: center;
}

div#boardHeader>h3 {
	text-align: center;
	color: green;
}

div#boardHeader {
	overflow: hidden;
}

div#boardHeader button {
	float: right;
}

span.productThumnail{
	display:block;
	width:50px;
	height:50px;
	background-position:center;
	background-repeat:no-repeat;
	background-size:cover;	
}
</style>
</head>


<br />
<hr />
<br />
<div id="wrap">
	<div id="boardHeader">
		<h3>상품 게시판</h3>
		<a href="writeProduct"><button>상품등록</button></a>
		<%-- <a href="productListExcel?pageNumber=${pageNumber }">
				<button>Excel로 받기</button>
			</a>
			<a href="productListPdf?pageNumber=${pageNumber }">
				<button>Pdf로 받기</button>
			</a>
			<a href="productList.xml?pageNumber=${pageNumber }">
				<button>Xml로 출력</button>
			</a>
			<a href="productList.json?pageNumber=${pageNumber }">
				<button>Json로 출력</button>
			</a> --%>
	</div>
	<br>

	<table>
		<tr>			
			<th>상품 번호</th>
			<th>이미지</th>
			<th>상 품 명</th>
			<th>원   가</th>
			<th>판 매 가</th>
			<th>수  량</th>
			<th>구  분</th>
		</tr>
		<c:choose>
			<c:when test="${viewData.productTotalCount>0 }">
				<c:forEach var="product" items="${viewData.productList }">
					<tr>
						<td>${product.proNum }</td>	
						<td><span class="productThumnail" 
						style="background-image:url(<%=request.getContextPath() %>/product-images/${product.image })"></span>					
						<td>${product.proName}</td>
						<td>${product.price1}</td>
						<td>${product.price2}</td>
						<td>${product.amount}</td>
						<td><a href="deleteProduct?proNum=${product.proNum }">
								삭제&nbsp;/</a> <a href="updateProduct?proNum=${product.proNum }">
								&nbsp;수정</a></td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="7" style="text-align: center;">내용이 없습니다.</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>

	<div id="pageNum">

		<c:forEach var="i" begin="1" end="${viewData.pageTotalCount}">
			<a href="productList?page=${i}">[${i}] </a>
		</c:forEach>

	</div>
</div>


</body>
</html>
