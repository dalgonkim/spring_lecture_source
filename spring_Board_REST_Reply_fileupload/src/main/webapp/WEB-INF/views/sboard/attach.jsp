<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>




<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>

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


<script>
var template=Handlebars.compile($('#templateAttach').html());

$(document).on("dragenter dragover drop",function(event){
	event.preventDefault();
});
$('.fileDrop').on('drop',function(event){
	event.preventDefault();
	
	var files=event.originalEvent.dataTransfer.files;
	/* var file=files[0]; */
	
	for(var file of files){	
		
		if(file.size>1024*1024*5){
			alert("파일 용량이 5MB를 초과하였습니다.");
			return false;
		}
		
		var formData=new FormData();
		formData.append("file",file);
		
		$.ajax({
			url:"<%=request.getContextPath()%>/uploadAjax",
			type:"post",
			data:formData,
			processData:false,
			contentType:false,
			success:function(data){
				var fileInfo=getUploadFileInfo(data,"<%=request.getContextPath()%>");
				var html=template(fileInfo);
				$(".uploadedList").append(html);
			}
		});
	}
});
function getUploadFileInfo(fullName,contextPath){
	
	var fileName,imgsrc,getLink;
	var fileLink;
	if(checkImageType(fullName)){
		imgsrc=contextPath+"/displayFile?fileName="+fullName;
		fileLink=fullName.substr(14);
		
		var front=fullName.substr(0,12); // /2017/12/15/
		var end=fullName.substr(14); // s_ 이후
		
		getLink=front+end;		
	}else{
		imgsrc=contextPath+"/resources/dist/img/file.png";
		fileLink=fullName.substr(12);
		getLink=fullName;
	}
	
	
	fileName=fileLink.substr(fileLink.indexOf("$$")+1);
	
	return {fileName:fileName,imgsrc:imgsrc,getLink:getLink,fullName:fullName};
	
}

function checkImageType(fileName){
	var pattern=/jpg|gif|png|jpeg/i;
	
	return fileName.match(pattern);
}


$('.uploadedList').on('click','.thumbnail',function(e){
	e.preventDefault();
	
	var form =$('<form>').attr({action:"<%=request.getContextPath()%>/displayFile",method:"get"});
	var input = $('<input>').attr("name","fileName").val($(this).attr("href"));
	
	$(document.body).append(form);
	
	form.append(input);
	form.submit();
});
</script>