<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

<script
  src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
	
	
	
<script>
	var json=[
		{
			firstName: "kim0",
			lastName: "mimi0"
			},
			{
			firstName: "kim1",
			lastName: "mimi1"
			},
			{
			firstName: "kim2",
			lastName: "mimi2"
			},
			{
			firstName: "kim3",
			lastName: "mimi3"
			},
			{
			firstName: "kim4",
			lastName: "mimi4"
			}
			];
	$.ajax({
		url:"<%=request.getContextPath()%>/rs/receiveJsonArray",
		method:"post",
		data:JSON.stringify(json),
		contentType:'application/json',
		success:function(data){
			alert(data);
		},
		error:function(error){}
	});
</script>



