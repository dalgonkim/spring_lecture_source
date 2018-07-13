<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% 
	System.out.println("logout");
	session.invalidate();
	response.sendRedirect(request.getContextPath()+"/index");
%>