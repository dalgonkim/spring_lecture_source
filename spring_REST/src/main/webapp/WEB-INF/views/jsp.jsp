<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
  src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
</head>
<body>
	<p id="date"></p>
	

	<button type="button" id="jsonDataBtn">jsonData</button>
	<button type="button" id="jsonListBtn">jsonList</button>
	<button type="button" id="jsonMapBtn">jsonMap</button>
	<hr/>
	<button type="button" id="xmlDataBtn">xmlData</button>
	
	<div id="output"></div>
	
	<script>
	setInterval(function(){
		$('#date').html(new Date());
	},1000);
		
	</script>
	
	
	<script>
		$('#jsonDataBtn').on('click',function(e){
			$.ajax({
				url:"<%=request.getContextPath()%>/rs/jsonData",
				method:"get",
				success:function(data){
					$('#output').append($('<h3>').html('firstName:'+data.firstName));
					$('#output').append($('<h3>').html('lastName:'+data.lastName));
				},
				error:function(error){}
			});
		});
		$('#jsonListBtn').on('click',function(e){
			var ul=$('<ul>');
			
			$.ajax({
				url:"<%=request.getContextPath()%>/rs/jsonList",
				method:"get",
				success:function(data){
					for(var sample of data){
						ul.append($('<li>').html('firstName:'+sample.firstName+"<br/>"
									   +'lastName:'+sample.lastName));						
					}
				},
				error:function(error){}				
			});
			
			$('#output').html(ul);			
		});
		$('#jsonMapBtn').on('click',function(e){
			var ul=$('<ul>');
			$.ajax({
				url:"<%=request.getContextPath()%>/rs/jsonMap",
				method:"get",
				success:function(data){
					for(var sample of data.samples){
						ul.append($('<li>').html('firstName:'+sample.firstName+"<br/>"
								   +'lastName:'+sample.lastName));		
					}
				},
				error:function(){}
			});
			
			$('#output').html(ul);		
		});
		
		
		$('#xmlDataBtn').on('click',function(e){
			var div=$('#output');
			$.ajax({
				url:"<%=request.getContextPath()%>/rs/xmlData",
				method:"get",
				success:function(data){
					var ul=$('<ul>');
					$(data).find("sample").each(function(){
						var li=$('<li>').html('<h3>firstName:'+$(this).find("firstName").text()+'</h3>'
										+'<h3>lastName:'+$(this).find("lastName").text()+'</h3>');
						ul.append(li);
					});
					div.html(ul);
				},
				error:function(error){}
			});			
		});
	</script>
</body>
</html>


