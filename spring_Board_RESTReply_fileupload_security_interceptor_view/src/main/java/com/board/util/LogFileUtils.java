package com.board.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogFileUtils {
	
	private static final Logger logger=
			LoggerFactory.getLogger(LogFileUtils.class);
	
	public static String writeLog(String logPath,String log)
									throws Exception{
		
		String saveName="userBoardLog.txt";
		String savePath=calcPath(logPath);
		
		File target = new File(logPath+savePath,saveName);
		
		if(!target.exists()){
			target.createNewFile();
		}
		String logFilePath=logPath+savePath+File.separator+saveName;
		
		BufferedWriter out=
				new BufferedWriter(new FileWriter(logFilePath,true));
		
		out.write(log);
		out.newLine();
		out.close();
		
		return log;
	}
	
	public static String calcPath(String logPath)throws Exception{
		
		Calendar cal=Calendar.getInstance();
		
		String yearPath=File.separator+cal.get(Calendar.YEAR);
		String monthPath=yearPath+File.separator+
		new DecimalFormat("00").format(cal.get(Calendar.MONTH)+1);
		String datePath=monthPath+File.separator+
		new DecimalFormat("00").format(cal.get(Calendar.DATE));
		
		makeDir(logPath,yearPath,monthPath,datePath);
		
		logger.info(datePath);
		
		return datePath;
	}
	
	public static void makeDir(String logPath,String... paths)
		throws Exception{
		
		if(new File(paths[paths.length-1]).exists()){
			return;
		}
		for(String path:paths){
			File dirPath=new File(logPath+path);
			if(!dirPath.exists()){
				dirPath.mkdirs();
			}
		}
	}
}








