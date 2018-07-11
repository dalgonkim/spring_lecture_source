package com.spring.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

public class DownloadView extends AbstractView{

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model,
										   HttpServletRequest request, 
										   HttpServletResponse response)
												   	throws Exception {
		
		File file=(File) model.get("downloadFile");
		
		response.setContentType("application/download;charset=utf-8");
		response.setContentLength((int) file.length());
		
		String fileName=URLEncoder.encode(file.getName(),"utf-8");
		
		response.setHeader("Content-Disposition", "attachment;filename=\""
				+ fileName+"\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		
		FileInputStream fis=null;
		OutputStream out=response.getOutputStream();
		
		try{
			fis=new FileInputStream(file);
			FileCopyUtils.copy(fis,out);
		}finally{
			if(fis!=null) fis.close();
			out.flush();
			out.close();
		}
		
		
	}
	
	
}








