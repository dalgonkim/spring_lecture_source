<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<style>
div#header {
	width: 800px;
	height: 200px;
	margin: 0 auto;
}

div#header>h1 {
	text-align: center;
}

div#section {
	width: 800px;
	height: 200px;
	margin: 0 auto;
}

form#loginForm {
	width: 400px;
	margin: 0 auto;
}

form#loginForm>input {
	height: 50px;
	width: 100%;
	line-height: 50px;
}
</style>
<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>

<script>
	function get_msg(message) {
		var move = '70px';
		jQuery('#message').text(message);
		jQuery('#message').animate({
			top : '+=' + move
		}, 'slow', function() {
			jQuery('#message').delay(1000).animate({
				top : '-=' + move
			}, 'slow');
		});
	}
	<c:if test="${error == 'true'}">
	jQuery(function() {
		get_msg("로그인 실패하였습니다.");
	});
	</c:if>
	function signin() {
		$.ajax({
			url : 'login',
			data : $('form input').serialize(),
			type : 'POST',
			dataType : 'json',
			beforeSend : function(xhr) {
				xhr.setRequestHeader("Accept", "application/json");
			}
		}).done(function(body) {
			var message = body.response.message;
			var error = body.response.error;
			if (error)
				get_msg(message);
			if (error == false) {
				var url = '${referer}';
				if (url == '')
					url = '<c:url value="/member/memberList" />';
				location.href = url;
			}
		});
	}
</script>
</head>
<body>
	<div id="header">
		<h1>로그인</h1>
	</div>	

	<div id="section">
		<form action="login" id="loginForm" method="post">
			<input type="hidden" name="returl" value="${param.returl }">
			<input type="text" name="userid" placeholder="아이디를 입력하시오" /><br />
			<br /> <input type="password" name="password"
				placeholder="패스워드를 입력하시오" /><br /> <br />
				<input style="background: #eeeeff;" 
				       type="button" value="로그인(json)"  
				       onclick="signin();"/>
				<input style="background: #eeeeff;" 
				       type="submit" value="로그인(html)"  
				       />
		</form>
	</div>
	<div> <div id="message" style="width:300px;position:absolute; top:-60px;border: 1px;border-color: #000;"></div> </div>

</body>
</html>










