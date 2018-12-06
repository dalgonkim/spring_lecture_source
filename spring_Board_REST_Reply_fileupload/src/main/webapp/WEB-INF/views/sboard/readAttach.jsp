<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>


<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.0.12/handlebars.js"></script> -->
<script id="templateAttach" type="text/x-handlebars-template">
<li style="width:10%;font-size:0.8em;" data-src="{{fullName}}">	
    <div class="mailbox-attachment-info">
	  <a href="{{getLink}}" class="mailbox-attachment-name thumbnail">
	   <span class="mailbox-attachment-icon has-img">
       <img src="{{imgsrc}}" alt="Attachment"></span>
	   <span>{{fileName}}</span>
      </a>      
    </div>
</li>

</script>

<script>

var bno=${boardVO.bno};
var template=Handlebars.compile($('#templateAttach').html());


$.getJSON("getAttach/"+bno,function(list){
	$(list).each(function(){
		var fileInfo=getFileInfo(this,"<%=request.getContextPath()%>");
		var html=template(fileInfo);
		$('.uploadedList').append(html);
		
	});
});

function getFileInfo(attachJson,contextPath){
	var fileName,imgsrc,getLink;
	var fullName;
	if(attachJson.fileType=="1"){
		fullName=attachJson.uploadPath.replace(/\\/g,"/")
				 +"s_"+attachJson.uuid+"$$"+attachJson.fileName;
		imgsrc=contextPath+"/displayFile?fileName="+fullName;				
	}else{
		fullName=attachJson.uploadPath.replace(/\\/g,"/")
				 +attachJson.uuid+"$$"+attachJson.fileName;
		imgsrc=contextPath+"/resources/dist/img/file.png";		
	}
	getLink=attachJson.uploadPath.replace(/\\/g,"/")
		    +attachJson.uuid+"$$"+attachJson.fileName;
	fileName=attachJson.fileName;
	
	return {fileName:fileName,imgsrc:imgsrc,getLink:getLink,fullName:fullName};
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