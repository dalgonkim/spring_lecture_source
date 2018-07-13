package com.nonage.dao.iBatis;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.nonage.dao.AddressDAO;
import com.nonage.dto.AddressVO;

public class AddressDAO_iBatis implements AddressDAO {
	
	private SqlMapClient client;	
	
	public void setClient(SqlMapClient client) {
		this.client = client;
	}


	@Override
	public ArrayList<AddressVO> selectAddressByDong(String dong) throws SQLException{
		ArrayList<AddressVO> addressList=(ArrayList<AddressVO>)client.queryForList("selectAddressByDong",dong);
		return addressList;

	}

}
