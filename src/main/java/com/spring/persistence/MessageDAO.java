package com.spring.persistence;

import com.spring.domain.MessageVO;

public interface MessageDAO {
	void create(MessageVO vo) throws Exception;
	MessageVO readMessage(Integer mid) throws Exception;
	void updateState(Integer mid) throws Exception;
	
}
