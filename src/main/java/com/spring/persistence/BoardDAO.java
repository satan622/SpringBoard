package com.spring.persistence;
import java.util.List;

import com.spring.domain.BoardVO;
import com.spring.domain.Criteria;
import com.spring.domain.SearchCriteria;
public interface BoardDAO {
	void create(BoardVO vo) throws Exception;//insert
	BoardVO read(Integer bno)throws Exception;//select
	void update(BoardVO vo) throws Exception;
	void delete(Integer bno) throws Exception;
	List<BoardVO> listAll() throws Exception; //select
	
	//Paging
	List<BoardVO> listPage(int page) throws Exception;
	List<BoardVO> listCriteria(Criteria cri) throws Exception;
	
	//totalCount를 반환할수 있게 처리
	int countPaging(Criteria cri) throws Exception;
	
	//Search
	List<BoardVO> listSearch(SearchCriteria cri) throws Exception;
	int listSearchCount(SearchCriteria cri) throws Exception;
	
	//Reply count
	void updateReplyCnt(Integer bno, int amount) throws Exception;
	
	//View count
	void updateViewCnt(Integer bno) throws Exception;
	
	//Attach
	void addAttach(String fullName) throws Exception;
	List<String> getAttach(Integer bno) throws Exception;
	void deleteAttach(Integer bno) throws Exception;
	void replaceAttach(String fullName, Integer bno) throws Exception;
}
