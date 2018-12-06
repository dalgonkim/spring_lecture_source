<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<head>
	<style>
		.fileDrop{
			width:80%;
			height:100px;
			border:1px dotted gray;
			background-color:lightslategray;
			margin:auto;
		}
		
	</style>
</head>

<body>
<!-- Main content -->
<section class="content">
	<div class="row">
		<!-- left column -->
		<div class="col-md-12">
			<!-- general form elements -->
			<div class="box box-primary">
				<div class="box-header">
					<h3 class="box-title">REGISTER BOARD</h3>
				</div>
				<!-- /.box-header -->

<form role="form" method="post" action="register">
	<div class="box-body">
		<div class="form-group">
			<label for="exampleInputEmail1">Title</label> 
			<input type="text"
				name='title' class="form-control" placeholder="Enter Title">
		</div>
		<div class="form-group">
			<label for="exampleInputPassword1">Content</label>
			<textarea class="form-control" name="content" rows="3"
				placeholder="Enter ..."></textarea>
		</div>
		<div class="form-group">
			<label for="exampleInputEmail1">Writer</label> 
			<input type="text"
				name="writer" class="form-control" placeholder="Enter Writer">
		</div>
		
		<div class="form-group">
			<label>File DROP Here</label>
			<div class="fileDrop"></div>
		</div>
	</div>
	<!-- /.box-body -->
	
	<div class="box-footer">
		<div><hr/></div>
		<ul class="mailbox-attachments clearfix uploadedList"></ul>	
		<button type="submit" class="btn btn-primary">Submit</button>
	</div>
</form>


			</div>
			<!-- /.box -->
		</div>
		<!--/.col (left) -->

	</div>
	<!-- /.row -->
</section>
<!-- /.content -->

<%@ include file="/WEB-INF/views/sboard/attach.jsp" %>

<script>
	var form = $('form[role="form"]');
	$("button[type='submit']").on("click", function(e){
		e.preventDefault();
		/* alert("submit"); */
		$(".uploadedList .delbtn").each(function(i, obj){
			var fullName=$(this).attr('href');
			var uuid="";
			var fileName="";
			if(checkImageType(fullName)){
				uuid=fullName.substring(14).split('$$')[0];
				fileName=fullName.substring(14).split('$$')[1];
				fileType="1";
			}else{
				uuid=fullName.substring(12).split('$$')[0];
				fileName=fullName.substring(12).split('$$')[1];
				fileType="0";
			}
			
			var uploadPath=fullName.substring(0,12).replace(/\//g,"\\");
					
			var input1=$('<input>').attr('type','hidden').attr('name','attachList['+i+'].uuid').val(uuid);
			var input2=$('<input>').attr('type','hidden').attr('name','attachList['+i+'].fileName').val(fileName);
			var input3=$('<input>').attr('type','hidden').attr('name','attachList['+i+'].fileType').val(fileType);
			var input4=$('<input>').attr('type','hidden').attr('name','attachList['+i+'].uploadPath').val(uploadPath);
			
			form.append(input1).append(input2).append(input3).append(input4);
		});
		
		form.submit();
		
	});
	
	$('.uploadedList').on('click','.delbtn',function(event){
		event.preventDefault();
		var that=$(this);
		
		$.ajax({
			url:'<%=request.getContextPath()%>/deleteFile',
			type:'post',
			data:{fileName:$(this).attr('href')},
			success:function(result){
				if(result=='deleted'){
					that.parent('li').remove();
				}
			}
		});
	});

</script>

</body>






