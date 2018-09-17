package com.spring.service;

import java.util.List;

import com.spring.domain.Criteria;
import com.spring.domain.ReplyVO;

public interface ReplyService {
	void addReply(ReplyVO vo) throws Exception;
	List<ReplyVO> listReply(Integer bno) throws Exception;
	void modifyReply(ReplyVO vo) throws Exception;
	void removeReply(Integer rno) throws Exception;
	
	//page
	List<ReplyVO> listReplyPage(Integer bno, Criteria cri) throws Exception;
	int count(Integer bno) throws Exception;
}
