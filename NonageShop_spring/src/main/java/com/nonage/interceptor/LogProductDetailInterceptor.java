package com.nonage.interceptor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.nonage.dto.ProductVO;

public class LogProductDetailInterceptor extends HandlerInterceptorAdapter {

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		HttpSession session = request.getSession();
		String workerId = (String) session.getAttribute("workerId");

		ProductVO product = (ProductVO) modelAndView.getModel()
				.get("productVO");

		if (workerId != null && product != null) {
			String savePath="/WEB-INF/log";
			ServletContext context=session.getServletContext();
			String logFilePath=context.getRealPath(savePath)+"/productDetailLog.txt";
			
			BufferedWriter out=new BufferedWriter(new FileWriter(logFilePath,true));
			
			String log=workerId+","+product.getPseq()+","+product.getName()+","+
					   product.getContent()+","+new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			
			out.write(log);
			out.newLine();
			out.close();
			
		}

	}

}
