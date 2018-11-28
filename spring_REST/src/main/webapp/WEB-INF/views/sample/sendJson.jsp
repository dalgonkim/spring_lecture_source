<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

<script
  src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
	
	
	
<script>
	var json={
			firstName: "kim",
			lastName: "mimi"
			};
	$.ajax({
		url:"<%=request.getContextPath()%>/rs/receiveJson",
		method:"get",
		data:json,
		success:function(data){
			alert(data);
		},
		error:function(error){}
	});
</script>



