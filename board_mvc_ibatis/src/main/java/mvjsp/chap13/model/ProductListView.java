package mvjsp.chap13.model;

import java.util.List;

public class ProductListView {

	private int productTotalCount;
	private int currentPageNumber;
	private List<Product> productList;
	private int pageTotalCount;
	private int productCountPerPage;
	private int firstRow;
	private int endRow;

	public ProductListView(List<Product> productList, int productTotalCount,
			int currentPageNumber, int productCountPerPage, int startRow,
			int endRow) {
		this.productList = productList;
		this.productTotalCount = productTotalCount;
		this.currentPageNumber = currentPageNumber;
		this.productCountPerPage = productCountPerPage;
		this.firstRow = startRow;
		this.endRow = endRow;

		calculatePageTotalCount();
	}

	private void calculatePageTotalCount() {
		if (productTotalCount == 0) {
			pageTotalCount = 0;
		} else {
			pageTotalCount = productTotalCount / productCountPerPage;
			if (productTotalCount % productCountPerPage > 0) {
				pageTotalCount++;
			}
		}
	}

	public int getproductTotalCount() {
		return productTotalCount;
	}

	public int getCurrentPageNumber() {
		return currentPageNumber;
	}

	public List<Product> getproductList() {
		return productList;
	}

	public int getPageTotalCount() {
		return pageTotalCount;
	}

	public int getproductCountPerPage() {
		return productCountPerPage;
	}

	public int getFirstRow() {
		return firstRow;
	}

	public int getEndRow() {
		return endRow;
	}

	public boolean isEmpty() {
		return productTotalCount == 0;
	}
}










