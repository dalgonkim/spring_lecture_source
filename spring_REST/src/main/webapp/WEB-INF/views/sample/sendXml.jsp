<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<script
  src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
  
  
  <script>
  	var xml='<?xml version="1.0" encoding="UTF-8" standalone="yes"?><samples><sample><firstName>kim0</firstName><lastName>mimi0</lastName></sample><sample><firstName>kim1</firstName><lastName>mimi1</lastName></sample><sample><firstName>kim2</firstName><lastName>mimi2</lastName></sample><sample><firstName>kim3</firstName><lastName>mimi3</lastName></sample><sample><firstName>kim4</firstName><lastName>mimi4</lastName></sample></samples>';
  	
  	$.ajax({
  		url:"<%=request.getContextPath()%>/rs/receiveXml",
  		method:"post",
  		contentType:"text/xml",
  		data:xml,
  		success:function(data){
  			alert(data);
  		},
  		error:function(error){}
  	});
  	
  	
  </script>