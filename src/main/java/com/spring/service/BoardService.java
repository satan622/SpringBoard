package com.spring.service;

import java.util.List;

import com.spring.domain.BoardVO;
import com.spring.domain.Criteria;
import com.spring.domain.SearchCriteria;

public interface BoardService {
	void regist(BoardVO vo) throws Exception;// insert
	BoardVO read(Integer bno) throws Exception;// select
	void modify(BoardVO vo) throws Exception; // update
	void remove(Integer bno) throws Exception;// delete
	List<BoardVO> listAll() throws Exception; // select

	// Paging
	List<BoardVO> listCriteria(Criteria cri) throws Exception;
	int listCountCriteria(Criteria cri) throws Exception;

	// Search
	List<BoardVO> listSearchCriteria(SearchCriteria cri) throws Exception;
	int listSearchCountCriteria(SearchCriteria cri) throws Exception;
	
	// Attach
	List<String> getAttach(Integer bno) throws Exception;
}
