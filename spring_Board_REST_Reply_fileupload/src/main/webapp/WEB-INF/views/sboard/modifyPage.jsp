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
					<h3 class="box-title">MODIFY BOARD</h3>
				</div>
				<!-- /.box-header -->

<form role="form" action="modifyPage" method="post">

	<input type='hidden' name='page' value="${cri.page}"> 
	<input type='hidden' name='perPageNum' value="${cri.perPageNum}">
	<input type='hidden' name='searchType' value="${cri.searchType}">
	<input type='hidden' name='keyword' value="${cri.keyword}">

					<div class="box-body">

						<div class="form-group">
							<label for="exampleInputEmail1">BNO</label> <input type="text"
								name='bno' class="form-control" value="${boardVO.bno}"
								readonly="readonly">
						</div>

						<div class="form-group">
							<label for="exampleInputEmail1">Title</label> <input type="text"
								name='title' class="form-control" value="${boardVO.title}">
						</div>
						<div class="form-group">
							<label for="exampleInputPassword1">Content</label>
							<textarea class="form-control" name="content" rows="3">${boardVO.content}</textarea>
						</div>
						<div class="form-group">
							<label for="exampleInputEmail1">Writer</label> 
							<input readonly type="text" name="writer" class="form-control"
								value="${boardVO.writer}">
						</div>
						<div class="form-group">
							<label>File DROP Here</label>
							<div class="fileDrop"></div>
						</div>						
					</div>
					<!-- /.box-body -->
				</form>
				<div class="box-footer">
					<div>
						<hr/>
					</div>
					<ul class="mailbox-attachments clearfix uploadedList"></ul>
					<button type="submit" class="btn btn-warning">SAVE</button>
					<button type="submit" class="btn btn-primary">CANCEL</button>
				</div>

<script>
$(document).ready(
	function() {

		var formObj = $("form[role='form']");

		console.log(formObj);

		$(".btn-primary")
				.on("click",function() {
					self.location = "listPage?page=${cri.page}&perPageNum=${cri.perPageNum}"
							+ "&searchType=${cri.searchType}&keyword=${cri.keyword}";
				});

		$(".btn-warning").on("click",function() {
					formObj.submit();
				});
	});
</script>




			</div>
			<!-- /.box -->
		</div>
		<!--/.col (left) -->

	</div>
	<!-- /.row -->
</section>
<!-- /.content -->

<script id="templateAttach" type="text/x-handlebars-template">
<li style="width:10%;font-size:0.8em;position:relative;">
	<a href="{{fullName}}" class="btn btn-default btn-xs pull-right delbtn"
	   style="position:absolute;right:0;top:0;padding:0;" >
		<i class="fa fa-fw fa-remove"></i>
	</a>
	<span class="mailbox-attachment-icon has-img">
      <img src="{{imgsrc}}" alt="Attachment"></span>
    <div class="mailbox-attachment-info">
	  <a href="{{getLink}}" class="mailbox-attachment-name thumbnail">{{fileName}}</a>     
    </div>
</li>
</script>
<%@ include file="/WEB-INF/views/sboard/attach.jsp" %>
<%@ include file="/WEB-INF/views/sboard/readAttach.jsp" %>

<script>
var form=$('form[role="form"]');

$('.uploadedList').on('click','.delbtn',function(event){
	event.preventDefault();
	
	$(this).parent('li').remove();		
	var input= $('<input>').attr({
									type:"hidden",
									name:"oldAttach",
									value:$(this).attr('href')
								});	
	form.append(input);
});


form.submit(function(event){
	event.preventDefault();
	
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
	form.get(0).submit();
});

</script>


</body>












