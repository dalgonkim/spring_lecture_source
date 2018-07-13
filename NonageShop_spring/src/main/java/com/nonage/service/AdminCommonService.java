package com.nonage.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nonage.dao.WorkerDAO;

@Service
public class AdminCommonService {
	
	@Autowired
	private WorkerDAO workerDAO;
	
	public int getAdminLogin(String workerId,String workerPwd){
		int result=-1;
		try {
			result = workerDAO.workerCheck(workerId, workerPwd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
