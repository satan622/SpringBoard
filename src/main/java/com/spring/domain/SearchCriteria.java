package com.spring.domain;

public class SearchCriteria extends Criteria {
	//page
	//perPageNum
	private String searchType; //검색 유형 (6 가지)
	private String keyword; //검색 키워드, 직접 입력된 값
	
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	@Override
	public String toString() {
		return "SearchCriteria [searchType=" + searchType + ", keyword=" + keyword + "]";
	}
	
}
