<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%-- <%@ include file="/admin/header.jsp"%> --%>
<%@ include file="../sub_menu.jsp"%>

<article>
<h1>상품리스트</h1>	

<form name="frm" method="post">
<input type="hidden" name="tpage" value="${tpage }" />
<table style="width:800px;">
  <tr>
	  <td colspan="2">상품명<input type="text" name="key"></td>     
	  <td><input class="btn" type="button" name="btn_search" 
	  			value="검색" onClick="go_search()"></td>
	  <td><input class="btn" type="button" name="btn_total" 
	  			value="전체보기 " onClick="go_total()"></td>
	  <td><input class="btn" type="button" name="btn_write" 
	  			value="상품등록" onClick="go_wrt()"></td>
  </tr>
  <tr>
	  <td>다른 view로 전송</td>
	  <td><input class="btn" type="button" name="btn_excel" 
	  			value="Excel"onClick="go_excel()" /></td>
	  <td><input class="btn" type="button" name="btn_pdf" 
	  			value="PDF" onClick="go_pdf()"/></td>
	  <td><input class="btn" type="button" name="btn_xml" 
	  			value="XML" onClick="go_xml()"/></td>
	  <td><input class="btn" type="button" name="btn_json" 
	  			value="Json" onClick="go_json()"/></td>	  
  </tr>
</table>
<table id="productList">
    <tr>
        <th>번호</th><th>상품명</th><th>원가</th><th>판매가</th><th>등록일</th><th>사용유무</th>
    </tr>
    <c:choose>
    <c:when test="${productListSize<=0}">
    <tr>
      <td width="100%" colspan="7" align="center" height="23">
        등록된 상품이 없습니다.
      </td>      
    </tr>
    </c:when>
	<c:otherwise>
	<c:forEach items="${productList}" var="productVO">
    <tr>
      <td height="23" align="center" >${productVO.pseq}</td>
      <td style="text-align: left; padding-left: 50px; padding-right: 0px;">   
        <a href="#" onClick="go_detail(${productVO.pseq})">
    	 ${productVO.name}     
   		</a>
   	  </td>
      <td><fmt:formatNumber value="${productVO.price1}"/></td>
      <td><fmt:formatNumber value="${productVO.price2}"/></td>
      <td><fmt:formatDate value="${productVO.indate}"/></td>
      <td>
      	<c:choose>
   	 		<c:when test='${productVO.useyn=="y"}'>사용</c:when>
   	 		<c:otherwise>미사용</c:otherwise>   	 		
   	 	</c:choose>	 
   	  </td> 
    </tr>
    </c:forEach>
    <tr><td colspan="6" style="text-align: center;"> ${paging} </td></tr>
	</c:otherwise>	
</c:choose>  
</table>
</form> 
</article>
<%-- <%@ include file="/admin/footer.jsp"%> --%>
</body>
</html>