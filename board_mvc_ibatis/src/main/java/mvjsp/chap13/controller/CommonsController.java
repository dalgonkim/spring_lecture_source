package mvjsp.chap13.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class CommonsController {

	@RequestMapping(value="/index")
	public void index(HttpServletResponse res){
		res.setContentType("text/html;charset=utf-8");
		try {			
			PrintWriter out=res.getWriter();
			
			out.println("<a href='member/login' ><button>로그인</button></a>");
			out.println("<a href='member/join'><button>회원가입</button></a>");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}




