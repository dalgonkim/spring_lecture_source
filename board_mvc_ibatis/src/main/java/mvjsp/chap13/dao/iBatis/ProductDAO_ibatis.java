package mvjsp.chap13.dao.iBatis;

import java.sql.SQLException;
import java.util.List;

import mvjsp.chap13.dao.ProductDAO;
import mvjsp.chap13.model.Product;

import com.ibatis.sqlmap.client.SqlMapClient;

public class ProductDAO_ibatis extends ProductDAO{
	
	private SqlMapClient client;
	public void setClient(SqlMapClient client){
		this.client=client;
	}
	@Override
	public int insert(Product product) throws SQLException {
		int result=(Integer)client.update("insertProduct",product);
		return result;
	}
	@Override
	public Product select(int proNum) throws SQLException {
		Product product=(Product)client.queryForObject("getProduct",proNum);
		return product;
	}
	@Override
	public int selectCount() throws SQLException {
		int result=(Integer)client.queryForObject("productCount",null);
		return result;
	}
	@Override
	public List<Product> selectList(int firstRow, int endRow)
			throws SQLException {
		int startRow=firstRow-1;
		int counts=endRow-firstRow+1;
		List<Product> productList=
		(List<Product>)client.queryForList("getProductList",null,startRow,counts);
		return productList;
	}
	@Override
	public int update(Product product) throws SQLException {
		int result=(Integer)client.update("updateProduct",product);
		return result;
	}
	@Override
	public int delete(int proNum) throws SQLException {
		int result=(Integer)client.update("deleteProduct",proNum);
		return result;
	}
	
	
}
