package com.spring.domain;
//클래스 내부에 page(페이지 번호), perPageNum(페이지당 글의 갯수) 속성 보관용도
public class Criteria {
	private int page; //페이지 번호
	private int perPageNum;//페이지당 글의 갯수
	
	public Criteria() {
		this.page=1;
		this.perPageNum=10;
	}
   
	
	//해당페이지에서 시작 번호
	public int getPageStart() {
		//시작데이터번호 =(페이지번호-1)*페이지당 보여지는 갯수
		return (this.page-1)*perPageNum;
		
	}


	public int getPage() {
		return page;
	}


	public void setPage(int page) {
		if(page <= 0) {
			this.page=1;
			return;
		}
		this.page = page;
	}


	public int getPerPageNum() {
		return perPageNum;
	}


	public void setPerPageNum(int perPageNum) {
		if(perPageNum <=0 || perPageNum > 100) {
			this.perPageNum = 10;
			return;
		}
		this.perPageNum = perPageNum;
	}
	
	@Override
	public String toString() {
		String output = "Criteria [page="+page+", "+"perPageNum="+perPageNum+"]";
		return output;
	}
	
	
	
	
}
