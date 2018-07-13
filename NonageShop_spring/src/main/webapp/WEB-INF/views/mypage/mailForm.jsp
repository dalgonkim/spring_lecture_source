<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ include file="sub_img.jsp"%>
<%@ include file="sub_menu.jsp"%>
<article>
	<h2>My Page(${title})</h2>
	<div class="mailForm" >
		<form method="post">
			<label for="title">제&nbsp;&nbsp;목 : </label>
				<input type="text" id="title" name="title" style="width:400px;"/><br />
			<label for="writer">작 성 자 : </label>
				<input type="text" id="writer" name="writer" 
					style="width:200px;background:#cfcfcf"
					value="${loginUser.id }" readonly /><br />
			<label for="message">내&nbsp;&nbsp;용 :</label>
			<textArea id="message" name="message" rows="10" cols="55">문의사항을 작성하세요.</textArea>
			<br />
			<input  type="submit" class="submit" value="보내기" />	
		</form>
	</div>
</article>










