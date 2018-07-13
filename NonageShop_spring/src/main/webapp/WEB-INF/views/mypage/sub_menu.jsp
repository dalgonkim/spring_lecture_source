<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page trimDirectiveWhitespaces="true" %>


<nav id="sub_menu">
<ul>
<li><a href="<%=request.getContextPath() %>/cart/cartList.do">장바구니(cart)내역</a></li>
<li><a href="<%=request.getContextPath() %>/member/mypage.do">진행중인 주문내역</a></li>
<li><a href="<%=request.getContextPath() %>/order/orderAll.do">총 주문내역</a></li>
</ul>
</nav>