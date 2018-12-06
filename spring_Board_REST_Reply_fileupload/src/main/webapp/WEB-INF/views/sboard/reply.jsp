<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>


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
	var month=dateObj.getMonth();
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
});
</script>