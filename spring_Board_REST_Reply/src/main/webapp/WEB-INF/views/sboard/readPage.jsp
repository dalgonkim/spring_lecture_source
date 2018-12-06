<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<body>
<style>
a#modifyReplyBtn{
	color:white;
	width:40px;
	height:20px;
	padding:2px;
	font-size:10px;
}
</style>


<!-- Main content -->
<section class="content">
	<div class="row">
		<!-- left column -->
		<div class="col-md-12">
			<!-- general form elements -->
			<div class="box box-primary">
				<div class="box-header">
					<h3 class="box-title">READ BOARD</h3>
				</div>
				<!-- /.box-header -->

<form role="form" action="modifyPage" method="post">

	<input type='hidden' name='bno' value="${boardVO.bno}"> <input
		type='hidden' name='page' value="${cri.page}"> <input
		type='hidden' name='perPageNum' value="${cri.perPageNum}">
	<input type='hidden' name='searchType' value="${cri.searchType}">
	<input type='hidden' name='keyword' value="${cri.keyword}">

</form>

				<div class="box-body">
					<div class="form-group">
						<label for="exampleInputEmail1">Title</label> <input type="text"
							name='title' class="form-control" value="${boardVO.title}"
							readonly="readonly">
					</div>
					<div class="form-group">
						<label for="exampleInputPassword1">Content</label>
						<textarea class="form-control" name="content" rows="3"
							readonly="readonly">${boardVO.content}</textarea>
					</div>
					<div class="form-group">
						<label for="exampleInputEmail1">Writer</label> <input type="text"
							name="writer" class="form-control" value="${boardVO.writer}"
							readonly="readonly">
					</div>
				</div>
				<!-- /.box-body -->

				<div class="box-footer">
					<button type="submit" class="btn btn-warning" id="modifyBtn">Modify</button>
					<button type="submit" class="btn btn-danger"  id="removeBtn">REMOVE</button>
					<button type="submit" class="btn btn-primary" id="listBtn">GO LIST</button>
				</div>


<script>	

	var formObj = $("form[role='form']");
	
	console.log(formObj);
	
	$("#modifyBtn").on("click", function() {
		formObj.attr("action", "modifyPage");
		formObj.attr("method", "get");
		formObj.submit();
	});
	
	$("#removeBtn").on("click", function() {
		formObj.attr("action", "removePage");
		formObj.submit();
	});
	
	$("#listBtn").on("click", function() {
		formObj.attr("method", "get");
		formObj.attr("action", "listPage");
		formObj.submit();
	});


</script>

<div class="row">
		<div class="col-md-12">

			<div class="box box-success">
				<div class="box-header">
					<h3 class="box-title">ADD NEW REPLY</h3>
				</div>
				<div class="box-body">
					<label for="exampleInputEmail1">Writer</label>
					<input class="form-control" type="text" placeholder="USER ID" id="newReplyWriter"> 
					<label for="exampleInputEmail1">Reply Text</label>
					<input class="form-control" type="text"	placeholder="REPLY TEXT" id="newReplyText">

				</div>
				<!-- /.box-body -->
				<div class="box-footer">
					<button type="button" class="btn btn-primary" id="replyAddBtn">ADD
						REPLY</button>
				</div>
			</div>


			<!-- The time line -->
			<ul class="timeline">
				<!-- timeline time label -->
				<li class="time-label" id="repliesDiv"><span class="bg-green">
						Replies List </span></li>
			</ul>

			<div class='text-center'>
				<ul id="pagination" class="pagination pagination-sm no-margin ">

				</ul>
			</div>

		</div>
		<!-- /.col -->
	</div>
	<!-- /.row -->
	
<!-- Modal -->
<div id="modifyModal" class="modal modal-primary fade" role="dialog">
  <div class="modal-dialog">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title"></h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>        
      </div>
      <div class="modal-body" data-rno>
        <p><input type="text" id="replytext" class="form-control"></p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-info" id="replyModBtn">Modify</button>
        <button type="button" class="btn btn-danger" id="replyDelBtn">DELETE</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>      
</section>
<!-- /.content -->
<script>
	var bno=${boardVO.bno};

	$('#replyAddBtn').on('click',function(e){
		var replyer=$('#newReplyWriter').val();
		var replytext=$('#newReplyText').val();
		
		if(replyer==""){
			alert('댓글 작성자는 필수입니다.');
			$('#newReplyWriter').focus();
			return;
		}
		if(replytext==""){
			alert('댓글 내용은 필수입니다.');
			$('#newReplyText').focus();
			return;
		}
		
		$.ajax({
			type:"post",
			url:"<%=request.getContextPath()%>/replies",
			data:JSON.stringify({
				"bno":bno,
				"replyer":replyer,
				"replytext":replytext
			}),
			headers:{
				"Content-Type":"application/json",
				"X-HTTP-Method-Override":"post"
			},
			success:function(data){
				if(data="SUCCESS"){
					alert('등록되었습니다.');
				}		
				
				getPage("<%=request.getContextPath()%>/replies/"+bno+"/1");
				
				$('#newReplyWriter').val("");
				$('#newReplyText').val("");
			},
			error:function(error){
				alert("댓글등록에 실패했습니다.");
			}
		});
	});
</script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.0.12/handlebars.js"></script>

<script id="template" type="text/x-handlebars-template">
{{#each .}}
<li class="replyLi" data-rno={{rno}}>
<i class="fa fa-comments bg-blue"></i>
 <div class="timeline-item" >
  <span class="time">
    <i class="fa fa-clock-o"></i>{{prettifyDate regdate}}
	 <a class="btn btn-primary btn-xs" id="modifyReplyBtn"
	    data-toggle="modal" data-target="#modifyModal">Modify</a>
  </span>
  <h3 class="timeline-header"><strong>{{rno}}</strong> -{{replyer}}</h3>
  <div class="timeline-body">{{replytext}} </div>
</li>
{{/each}}
</script>

<script>
Handlebars.registerHelper("prettifyDate",function(timeValue){
	var dateObj=new Date(timeValue);
	var year=dateObj.getFullYear();
	var month=dateObj.getMonth()+1;
	var date=dateObj.getDate();
	return year+"/"+month+"/"+date;
});

var printData=function(replyArr,target,templateObject){
	var template=Handlebars.compile(templateObject.html());
	var html=template(replyArr);
	$('.replyLi').remove();
	target.after(html);
};

var replyPage=1;

getPage("<%=request.getContextPath()%>/replies/"+bno+"/"+replyPage);

function getPage(pageInfo){	
	$.getJSON(pageInfo,function(data){
		printData(data.list,$('#repliesDiv'),$('#template'));
		printPaging(data.pageMaker,$('.pagination'));
	});
}

var printPaging=function(pageMaker,target){
	var str="";
	if(pageMaker.prev){
		str+="<li><a href='"+(pageMaker.startPage-1)
				+"'> << </a></li>";
	}
	for(var i=pageMaker.startPage,len=pageMaker.endPage;i<=len;i++){
		var strClass = pageMaker.cri.page==i?'class=active':'';
		str+="<li "+strClass+"><a href='"+i+"'>"+i+"</a></li>";
	}
	if(pageMaker.next){
		str+="<li><a href='"+(pageMaker.endPage+1)
			+"'> >> </a></li>";
	}
	target.html(str);
}


$('.pagination').on('click','li a',function(event){		
	event.preventDefault();		
	replyPage=$(this).attr("href");
	getPage("<%=request.getContextPath()%>/replies/"+bno+"/"+replyPage);
});

$('.timeline').on('click','.replyLi',function(event){
	var reply=$(this);
	$('#replytext').val(reply.find('.timeline-body').text());
	$('.modal-title').html(reply.attr('data-rno'));
});

$('#replyModBtn').on('click',function(event){
	var rno=$('.modal-title').html();
	var replytext=$('#replytext').val();
	
	$.ajax({
		method:'put',
		url:"<%=request.getContextPath()%>/replies/"+rno,
		headers:{
			"Content-Type":"application/json",
			"X-HTTP-Method-Override":"PUT"
		},
		data:JSON.stringify({replytext:replytext}),
		dataType:'text',
		success:function(result){
			if(result=="SUCCESS"){
				alert("수정되었습니다.");			
				getPage("<%=request.getContextPath()%>/replies/"+bno+"/"+replyPage);
			}
		},
		error:function(error){
			alert("댓글 수정에 실패했습니다.");
		},
		complete:function(){
			$('#modifyModal').modal('hide');
		}
	});
});

$('#replyDelBtn').on('click',function(event){
	var rno=$('.modal-title').html();
	
	$.ajax({
		method:'delete',
		url:"<%=request.getContextPath()%>/replies/"+rno,
		headers:{
			"Content-Type":"application/json",
			"X-HTTP-Override":"delete"
		},
		dataType:'text',
		success:function(result){
			if(result="SUCCESS"){
				alert("삭제되었습니다.");				
				getPage("<%=request.getContextPath()%>/replies/"+bno+"/"+replyPage);
			}
		},
		error:function(error){
			alert('삭제 실패했습니다.');			
		},
		complete:function(){
			$('#modifyModal').modal('hide');
		}
	});

</script>
</body>









