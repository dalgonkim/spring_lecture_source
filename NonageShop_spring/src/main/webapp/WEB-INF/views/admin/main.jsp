<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="decorator"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript">
function worker_check()
{
  if(document.frm.workId.value==""){
      alert("아이디를 입력하세요.");
      return false;
  }else if(document.frm.workPw.value==""){
     alert("비밀번호를 입력하세요.");
      return false;
    }
    return true;  
}
</script>


    <article>
      <div id="loginform">
      <form name="frm" method="post" 
action="/admin/login.do">
      <table>
        <tr>
          <td> 아 이 디 </td>
          <td> <input type="text" name="workerId" size="10" value="admin"></td>
        </tr>
        <tr>
          <td> 비밀번호 </td>
          <td> 
            <input type="password" name="workerPwd" size="10" value="admin">
          </td>
        </tr>
        <tr align="center" >
          <td  colspan="2">          
            <input class="btn" type="submit" value="업무 로그인" 
onclick="return worker_check()"><br><br>
            <h4 style="color:red">${message}</h4>
          </td>
        </tr>
      </table>
      </form>
      </div>
    </article>
  