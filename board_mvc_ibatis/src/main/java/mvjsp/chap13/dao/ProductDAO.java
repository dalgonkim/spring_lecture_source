package mvjsp.chap13.dao;

import java.sql.SQLException;
import java.util.List;

import mvjsp.chap13.model.Product;

public abstract class ProductDAO {

	public abstract int insert(Product product)
			throws SQLException;

	public abstract Product select(int proNum) throws SQLException;	
	
	public abstract int selectCount() throws SQLException;

	public abstract List<Product> selectList(int firstRow,int endRow) throws SQLException;

	public abstract int update(Product product) throws SQLException;
	
	public abstract int delete(int proNum) throws SQLException;
}
