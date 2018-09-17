package com.spring.domain;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

//페이지 하단 들어갈 내용 설계(페이지 처리용) => listPage.jsp에서 보여짐
public class PageMaker {
	private int totalCount; //전체 글의수(행의 수)
	private int startPage; //시작 페이지 번호
	private int endPage; //끝 페이지 번호
	private boolean prev; //이전 페이지 링크
	private boolean next; //이후 페이지 링크
	
	private int displayPageNum = 10; //화면에 보여지는 페이지번호 숫자 개수
	
	private Criteria cri;

	public void setCri(Criteria cri) {
		this.cri = cri;
	}
	
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		calcData();
	}
	
	private void calcData() {
		endPage = (int) (Math.ceil(cri.getPage()/(double)displayPageNum) * displayPageNum);
		startPage = (endPage - displayPageNum) + 1;
		int tempEndPage = (int) (Math.ceil(totalCount/(double)cri.getPerPageNum())); 
		
		if(endPage > tempEndPage) {
			endPage = tempEndPage;
		}
				
		prev = startPage == 1 ?  false : true;
		next = endPage * cri.getPerPageNum() >= totalCount ? false : true;
	}
	
	//페이지 번호 전달을 단순화시킨 클래스 사용을 위한 메소드
	//http://localhost:8080/board/read?page=3&perPageNum=20
	public String makeQuery(int page) {
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
				.queryParam("page", page)
				.queryParam("perPageNum", cri.getPerPageNum())
				.build();
		
		return uriComponents.toUriString();
	}
	
	//UriComponents를 이용해서 검색 처리에 필요한 데이터를 넘기기(param)
	public String makeSearch(int page) {
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
				.queryParam("page", page)
				.queryParam("perPageNum", cri.getPerPageNum())
				.queryParam("searchType", ((SearchCriteria) cri).getSearchType())
				.queryParam("keyword", ((SearchCriteria) cri).getKeyword())
				.build();
		
		return uriComponents.toUriString();
	}
	
	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getDisplayPageNum() {
		return displayPageNum;
	}

	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public Criteria getCri() {
		return cri;
	}
	
}
