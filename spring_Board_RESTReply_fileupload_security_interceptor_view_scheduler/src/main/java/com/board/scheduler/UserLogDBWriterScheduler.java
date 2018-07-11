package com.board.scheduler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.dbcp2.BasicDataSource;

public class UserLogDBWriterScheduler {
	
	private String logFileName="userBoardLog.txt";
	private String logSavePath="c:\\log";
	private BasicDataSource dataSource;
	
	public void setDataSource(BasicDataSource dataSource){
		this.dataSource=dataSource;
	}
	
	public void setLogFileName(String fileName){
		this.logFileName=fileName;
	}
	public void setLogSavePath(String path){
		this.logSavePath=path;
	}

	public void logToDBWriter() throws Exception{
		
		String logFile=calcDir(logSavePath)+File.separator+logFileName;
		File log=new File(logFile);
		if(log.exists()){
			
			System.out.println("log existed : "+logFile);
			
			deleteLog(new SimpleDateFormat("yyyyMMdd").format(new Date()));
			
			BufferedReader in=new BufferedReader(new FileReader(log));
			String line="";
			while((line=in.readLine())!=null){
				String[] args=line.split(",");
				Date date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.parse(args[4]);		
				
				String seq=new DecimalFormat("00000000").format(getLogSeq());				
				String usno=new SimpleDateFormat("yyyyMMdd").format(date)+seq;
				
				LogCommand command=new LogCommand(usno,Integer.parseInt(args[0]),args[2],args[1],args[3],date);
				insertLog(command);
			}
		}else{
			System.out.println("log not existed : "+logFile);
		}
		
		
	}
	
	
	public String calcDir(String dirPath){
		Calendar today=Calendar.getInstance();
		
		String year=File.separator+today.get(Calendar.YEAR);
		String month=year+File.separator+
				new DecimalFormat("00").format(today.get(Calendar.MONTH)+1);
		String date=month+File.separator+
				new DecimalFormat("00").format(today.get(Calendar.DATE));
		
		return dirPath+date;
	}
	
	private void insertLog(LogCommand command) throws Exception{
		
		Connection conn=dataSource.getConnection();
		String sql="insert into tbl_user_log(usno,bno,userid,uri,ip,indate)"
				+ " values(?,?,?,?,?,?)";
		
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, command.getUsno());
		pstmt.setInt(2,command.getBno());
		pstmt.setString(3,command.getUserid());
		pstmt.setString(4, command.getUri());
		pstmt.setString(5,command.getIp());
		pstmt.setTimestamp(6,new Timestamp(command.getIndate().getTime()));
		
		pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
		
	}
	
	private void deleteLog(String date)throws Exception{
		Connection conn=dataSource.getConnection();
		String sql="delete  from tbl_user_log "
				+ "where usno like '%'||?||'%'";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, date);
		pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
	}
	
	private int getLogSeq()throws Exception{
		Connection conn=dataSource.getConnection();
		Statement stmt=conn.createStatement();
		String sql="select log_seq.nextval as seq from dual";
		
		ResultSet rs=stmt.executeQuery(sql);
		
		int seqNum=-1;
		if(rs.next()) {
			seqNum=rs.getInt("seq");
		}
		
		rs.close();
		stmt.close();
		conn.close();
		
		return seqNum;
	}
	
	
}







