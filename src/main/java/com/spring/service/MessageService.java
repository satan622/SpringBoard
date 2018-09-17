package com.spring.service;

import com.spring.domain.MessageVO;

public interface MessageService {
	void addMessage(MessageVO vo) throws Exception;
	MessageVO readMessage(String uid, Integer mno) throws Exception;
}
