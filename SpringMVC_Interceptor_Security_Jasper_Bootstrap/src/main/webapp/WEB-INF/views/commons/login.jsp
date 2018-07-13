<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>LOGIN PAGE</title>
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <!-- Bootstrap 3.3.4 -->
    <link href="<%=request.getContextPath()%>/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <!-- Font Awesome Icons -->
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <!-- Theme style -->
    <link href="<%=request.getContextPath()%>/resources/dist/css/adminlte.min.css" rel="stylesheet" type="text/css" />
    <!-- iCheck -->
    <link href="<%=request.getContextPath()%>/resources/plugins/iCheck/square/blue.css" rel="stylesheet" type="text/css" />

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  	<style>
  		#body_bng{
  			background-image:url("<%=request.getContextPath()%>/resources/dist/img/main_background_img.jpg");
  			background-position:center;
  			background-repeat:no-repeat;
  			background-size:cover;
  		}
  	</style>
    
  </head>
  <body id="body_bng" class="login-page">
    <div class="login-box">
      <div class="login-logo">
        <a href="<%=request.getContextPath()%>/resources/index2.html"><b>Zerock</b>Project</a>
      </div><!-- /.login-logo -->
      <div class="login-box-body">
        <p class="login-box-msg">Sign in to start your session</p>

<form role="form" action="<%=request.getContextPath() %>/commons/login" method="post">
	<input type="hidden" name="retUrl" value="${param.retUrl }" />
  	<div class="form-group has-feedback">
    <input type="text" name="id" class="form-control" 
    			placeholder="USER ID" value="${param.id }"/>
    <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
  </div>
  <div class="form-group has-feedback">
    <input type="password" name="pwd" class="form-control" 
    		placeholder="Password" value="${param.pwd }"/>
    <span class="glyphicon glyphicon-lock form-control-feedback"></span>
  </div>
  <div class="row">
    <div class="col-xs-8">    
      <div class="checkbox icheck">
        <label>
          <input type="checkbox" name="useCookie"> Remember Me
        </label>
      </div>                        
    </div><!-- /.col -->
    <div class="col-xs-4">
      <button type="button" onclick="login_go();" 
      	class="btn btn-primary btn-block btn-flat">Sign In(json)</button>
      <button type="submit" 
        class="btn btn-primary btn-block btn-flat">Sign In(html)</button>
    </div><!-- /.col -->
  </div>
</form>


        <a href="#">I forgot my password</a><br>
        <a href="register.html" class="text-center">Register a new membership</a>

      </div><!-- /.login-box-body -->
    </div><!-- /.login-box -->
	
	<c:if test="${!empty msg}">
		<script>
			alert('${msg}');
		</script>
	</c:if>
	<script
  src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
    <script src="<%=request.getContextPath() %>/resources/bootstrap/js/bootstrap.min.js"></script>	
	<script>
		function login_go(){			
			$.ajax({
				url:"<%=request.getContextPath()%>/commons/login",
				data:$('form').serialize(),
				type:"post",
				dataType:'json',				
				beforeSend:function(xhr){
					xhr.setRequestHeader("Accept","application/json");			
				}
			
			}).done(function(body){
				var message=body.message;
				var retUrl=body.retUrl;
				var error=body.error;
				
				alert(message);
				
				if(error){
					$('input[name="pwd"]').val("");
				}else{
					location.href=retUrl;
					//location.href=$('input[name="retUrl"]').val();					
					/* var id=$('input[name="id"]').val();
					var divBox=$('div[class="login-box-body"]');
					divBox.html("");
					var label=$('<label>').text(id+"님 환영합니다.");
					var button=$('<button>').attr('type','button')
								.text("메인페이지 이동");
					
					divBox.append(label).append(button); */
				}
				
			});
		};
	</script>
   
    
 </body>
</html>











