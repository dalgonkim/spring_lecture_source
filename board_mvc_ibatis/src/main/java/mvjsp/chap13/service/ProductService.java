package mvjsp.chap13.service;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import mvjsp.chap13.dao.ProductDAO;
import mvjsp.chap13.exception.ProductNotFoundException;
import mvjsp.chap13.exception.ServiceException;
import mvjsp.chap13.model.Product;
import mvjsp.chap13.model.ProductListView;

public class ProductService {

	private ProductDAO productDAO;

	public void setProductDAO(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}

	private static final int PRODUCT_COUNT_PER_PAGE = 5;

	public ProductListView getProductList(int pageNumber)
			throws ServiceException {
		int currentPageNumber = pageNumber;
		try {
			int productTotalCount = productDAO.selectCount();

			List<Product> productList = null;
			int firstRow = 0;
			int endRow = 0;
			if (productTotalCount > 0) {
				firstRow = (pageNumber - 1) * PRODUCT_COUNT_PER_PAGE + 1;
				endRow = firstRow + PRODUCT_COUNT_PER_PAGE - 1;
				productList = productDAO.selectList(firstRow, endRow);
			} else {
				currentPageNumber = 0;
				productList = Collections.emptyList();
			}
			return new ProductListView(productList, productTotalCount,
					currentPageNumber, PRODUCT_COUNT_PER_PAGE, firstRow, endRow);
		} catch (SQLException e) {
			throw new ServiceException("상품 목록 구하기 실패: " + e.getMessage(), e);
		}
	}

	public void write(Product product) throws ServiceException {
		try {

			productDAO.insert(product);

		} catch (SQLException e) {
			throw new ServiceException("상품 등록 실패: " + e.getMessage(), e);
		}
	}

	public Product getProduct(int proNum) throws SQLException {
		Product product = productDAO.select(proNum);
		return product;
	}

	public void updateProduct(Product product) throws SQLException {
		productDAO.update(product);
	}

	public void deleteProduct(int proNum) throws ServiceException,
			ProductNotFoundException {
		try {
			Product product = productDAO.select(proNum);
			if (product == null) {
				throw new ProductNotFoundException("상품이 없습니다.:" + proNum);
			}
			productDAO.delete(proNum);

		} catch (SQLException ex) {
			throw new ServiceException(
					"삭제 처리 중 에러가 발생했습니다.:" + ex.getMessage(), ex);
		} catch (ProductNotFoundException ex) {
			throw ex;
		}

	}
}
