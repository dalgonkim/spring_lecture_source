<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>9장 예제</title>
<script type="text/javascript" src="js/jquery-1.11.0.min.js"></script>
<script>
	function postXml() {
		var xmlBody = '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>'
				+ '<message-list>'
				+ '<message><id>1</id><content>메시지1</content><creationTime>2014-03-16T13:22:16.767+09:00</creationTime></message><message><id>2</id><content>메시지2</content><creationTime>2014-03-16T13:22:16.767+09:00</creationTime></message>'
				+ '</message-list>';
		$.ajax({
			type : "post",
			url : "guest/post",
			contentType : "text/xml",
			data : xmlBody,
			processData : false,
			success : function(response) {
				$(response).find("message").each(function() {
					var id = $(this).find("id").text();
					var message = $(this).find("content").text();
					var creationTime = $(this).find("creationTime").text();
					creationTime=$.format.date(creationTime, "yyyy-MM-dd");
					var ul = $("<ul>");
					var li1 = $("<li>").html(id);
					var li2 = $("<li>").html(message);
					var li3 = $("<li>").html(creationTime);

					ul.append(li1).append(li2).append(li3);

					$("#result").append(ul);
					$("#result").append("<hr/>");
				});
			},
			error : function() {
				alert("ERROR", arguments);
			}
		});
	}
	function postJson() {
		var jsonBody = {
			"messages" : [ {
				"id" : 1,
				"content" : "메시지1",
				"creationTime" : "2016-07-25"
			}, {
				"id" : 2,
				"content" : "메시지2",
				"creationTime" : "2016-06-24"
			} ]
		};
		$.ajax({
			type : "post",
			url : "guest/post.json",
			contentType : "application/json",
			data : JSON.stringify(jsonBody),
			processData : false,
			success : function(response) {
				var messages = response.messages;
				for (var i = 0; i < messages.length; i++) {
					var id = messages[i].id;
					var content = messages[i].content;
					var creationTime = messages[i].creationTime;

					var ul = $("<ul>");
					var li1 = $("<li>").html(id);
					var li2 = $("<li>").html(content);
					var li3 = $("<li>").html(creationTime);

					ul.append(li1).append(li2).append(li3);

					$("#result").append(ul);
					$("#result").append("<hr/>");
				}
				;
			},
			error : function() {
				alert("ERROR", arguments);
			}
		});
	}
</script>
</head>
<body>
	<ul>
		<li>HttpMessageConverter:
			<ul>
				<li><a href="mc/simple">/mc/simple</a>: 요청몸체-&gt;String /
					String-&gt;응답몸체, SimpleConverterController</li>
				<li><a href="guest/xml">/guest/xml</a>: 자바객체-&gt;XML응답,
					GuestMessageController.listXml()</li>
				<li><a href="javascript:postXml()">/guest/post</a>:
					XML요청-&gt;자바객체, GuestMessageController.postXml()</li>
				<li><a href="javascript:postJson()">/guest/post.json</a>:
					XML요청-&gt;자바객체, GuestMessageController.postJson()</li>
				<li><a href="guest/json">/guest/json</a>: 자바객체-&gt;JSON응답,
					GuestMessageController.listJson()</li>
			</ul>
		</li>
		<li>파일 업로드:
			<ul>
				<li><a href="upload/form">/upload/form</a>: 파일 업로드 폼,
					UploadController</li>
				<li><a href="upload/form.do">/upload/form.do</a>: 파일 업로드 폼(서블릿3
					Part 이용), UploadController2</li>
			</ul>
		</li>
	</ul>

	<hr />
	<h3>XML/JSON Result</h3>
	<div id="result"></div>
</body>
</html>







