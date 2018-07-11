package com.board.dto;

public class SearchCriteria extends Criteria{
	
	private String searchType;
	private String keyword;
	
	public String getSearchType() {
		if(searchType==null)searchType="";
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getKeyword() {
		if(keyword==null)keyword="";
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	@Override
	public String toString() {
		return "SearchCriteria [searchType=" + searchType + ", keyword=" + keyword + "]";
	}
	
	public String getUrl(){		
		return "?page="+super.getPage()+"&perPageNum="+super.getPerPageNum()
				+"&searchType="+searchType+"&keyword="+keyword;
	}
}






