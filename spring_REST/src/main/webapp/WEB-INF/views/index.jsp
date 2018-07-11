<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<button type="button" id="listBtn">Sample List</button>
<button type="button" id="listNotBtn">List Not</button>
<button type="button" id="formBtn">Form Submit</button>
<button type="button" id="noFormBtn">No Form Submit</button>
<button type="button" id="xmlBtn">XML</button>
<button type="button" id="urlXmlBtn">URL XML</button>
<button type="button" id="jsonBtn">JSON</button>
<br/><hr/><br/>

<div id="list">
</div>

<hr/>
<form role="frm" action="sample/formData" method="post">
	 firstName:<input type="text" name="firstName" /><br/>
	 lastName:<input type="text" name="lastName" /><br/>	
	 <input type="submit" value="submit" /> 
</form>

<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script>
	$('#listBtn').on('click',function(event){
		/* alert("listbtn click"); */
		$.post("sample/jsonData","",function(data){
			var sampleList=data;
			
			var table=$('<table>');
			var tr=$('<tr>');
			var th1=$('<th>').html("firstName");
			var th2=$('<th>').html("lastName");
			tr.append(th1).append(th2);
			table.append(tr);
			
			for(var i=0;i<sampleList.length;i++){
				var firstName=sampleList[i].firstName;
				var lastName=sampleList[i].lastName;
				
				var tr=$('<tr>');
				var td1=$('<td>').html(firstName);
				var td2=$('<td>').html(lastName);
				
				tr.append(td1).append(td2);
				table.append(tr);
			}
			
			$('#list').html(table);
			
		},'json');
	});
	
	$('#listNotBtn').on('click',function(event){
		/* alert('not btn click'); */
		
		/*
			json 으로 서버와 data교환을 하기 위해서는
			pom.xml에 jackson-databind.jar를 추가해야 한다.
		*/
		$.ajax({
			url:"rs/sendErrorNot",
			type:"get",
			contentType:"application/json",
			data:"",
			success:function(data){
				alert('success');
			},
			error:function(error){
				alert('error : '+error.status);
			}
		});
	});
	
	$('#formBtn').on('click',function(event){
		
		var form=$('form[role=frm]');
		var data=form.serialize();
		
		var div=$('<div>'); 
		$.ajax({
			url:"sample/formData",
			type:"post",
			data:data,
			success:function(data){
				var firstName=$('<h1>').html("firstName : "+data.firstName);
				var lastName=$('<h1>').html("lastName : "+data.lastName);
				div.append(firstName).append(lastName);				
			}				
		});	
		
		$('#list').html(div);
	});
	
	$('#noFormBtn').on('click',function(event){
		
		/* FormData() 를 사용하기 위해서는
		   server(spring)의 servlet-context.xml 에 MultipartResolver,
		   pom.xml에 commons-fileupload.jar 를 추가해야한다.
		*/		
		var formData=new FormData();
		formData.append("firstName","dlkfjdkljfdklj");
		formData.append("lastName","fdklfjhdlkjjflkdjf");
		
		$.ajax({
			processData:false,
			contentType:false,
			dataType:"text",
			url:"sample/formData",
			type:'post',
			data:formData,
			success:function(data){
				var json=JSON.parse(data);
				alert(json.firstName);
			}
		});
						  
	});
	$('#xmlBtn').on('click',function(event){
		
		var div=$('<div>');
		$.ajax({
			url:"sample/xml",
			type:'get',
			contentType:"text/xml",
			success:function(data){
				$(data).find("sample").each(function(){
					var firstName=$(this).find("firstName").text();
					var lastName=$(this).find("lastName").text();
					
					var ul=$('<ul>');
					var li1=$('<li>').html(firstName);
					var li2=$('<li>').html(lastName);
					
					ul.append(li1).append(li2);
					div.append(ul);
				});
			}			
		});
		$('#list').html(div);
	});
	
	$('#urlXmlBtn').on('click',function(event){
		
		var xmlText="xml string";
		
		$.ajax({
			url:"sample/xml",
			contentType:'text/xml',
			type:'get',
			processData:false,
			data:"",
			success:function(data){							
				$.ajax({
					url:"sample/stringToXML",
					type:"post",
					contentType:'text/xml',
					data:data,
					processData:false,
					success:function(data){
						var samples=data.sample;
						for(var i=0;i<samples.length;i++){
							alert(samples[i].firstName);
						}
					},
					error:function(error){
						alert('error');
					}			
				});

			}
		});		
		
	});
	
	$('#jsonBtn').on('click',function(event){
		$.ajax({
			url:"sample/sendJSON",
			type:'get',
			success:function(data){
				$.ajax({
					url:"sample/jsonToXML",
					type:'post',					
					contentType:'application/json',
					data:JSON.stringify(data),
					success:function(result){
						$(result).find("sample").each(function(){
							var firstName=$(this).find("firstName").text();
							alert(firstName);
						});
					}
				});
			}			
		});
	});
</script>
</body>
</html>








