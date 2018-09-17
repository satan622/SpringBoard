package com.spring.persistence;

import java.util.List;

import com.spring.domain.Criteria;
import com.spring.domain.ReplyVO;

public interface ReplyDAO {
	List<ReplyVO> list(Integer bno) throws Exception;
	void create(ReplyVO vo) throws Exception;
	void update(ReplyVO vo) throws Exception;
	void delete(Integer rno) throws Exception;
	
	//page
	List<ReplyVO> listPage(Integer bno, Criteria cri) throws Exception;
	int count(Integer bno) throws Exception;
	
	//replycnt
	int getBno(Integer rno) throws Exception;
}
