<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%-- <%@ include file="/admin/header.jsp"%> --%>
<%@ include file="../sub_menu.jsp"%>
<script type="text/javascript">
	function go_search() {
		document.frm.action = "memberList.do";
		document.frm.submit();
	}
</script>
<style>
table#memberTable>th:nth-child(1){
	width:15%;
}
table#memberTable>th:nth-child(2){
	width:10%;
}
table#memberTable>th:nth-child(3){
	width:20%;
}
table#memberTable>th:nth-child(4){
	width:15%;
}
table#memberTable>th:nth-child(5){
	width:15%;
}
table#memberTable>th:nth-child(6){
	width:20%;
}
</style>


<article>
	<h1>회원리스트</h1>
	<form name="frm" method="post">
		<table id="memberTable" style="float: right;">
			<tr>
				<td>회원 이름 <input type="text" name="key"> <input
					class="btn" type="button" value="검색" onclick="go_search()">
				</td>
			</tr>
		</table>
		<br>
		<table id="orderList">
			<tr>
				<th>아이디(탈퇴여부)</th>
				<th>이름</th>
				<th>이메일</th>			
				<th>전화</th>
				<th>권한여부</th>
				<th>가입일</th>
				
			</tr>
			<c:forEach items="${memberList}" var="memberVO">
				<tr>
					<td>${memberVO.id}<c:choose>
							<c:when test='${memberVO.useyn=="y"}'>
								<input type="checkbox" name="useyn" disabled="disabled">
							</c:when>
							<c:otherwise>
								<input type="checkbox" name="useyn" checked="checked"
									disabled="disabled">
							</c:otherwise>
						</c:choose>
					</td>
					<td>${memberVO.name}</td>
					<td>${memberVO.email}</td>
					<td>${memberVO.phone}</td>
					<td>${memberVO.authority }
					<td><fmt:formatDate value="${memberVO.indate}" /></td>
				</tr>
			</c:forEach>
		</table>
	</form>
</article>
<%-- <%@ include file="/admin/footer.jsp"%> --%>
</body>
</html>