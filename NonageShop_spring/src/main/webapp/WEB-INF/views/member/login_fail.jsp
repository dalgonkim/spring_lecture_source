<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<!-- <script type="text/javascript">
  alert("로그인 실패");
  history.go(-1);
</script> -->

<article>
	<h1>로그인에 실패했습니다.</h1>
	<form action="/member/login.do" method="post" >
		아이디:<input type="text" name="id" /><br/>
		패스워드:<input type="password" name="pwd" /><br />
		<input type="submit" value="로그인" />	
	</form>
</article>