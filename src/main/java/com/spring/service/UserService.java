package com.spring.service;

import java.util.Date;

import com.spring.domain.UserVO;
import com.spring.dto.LoginDTO;

public interface UserService {
	UserVO login(LoginDTO dto) throws Exception;
	void keepLogin(String uid, String sessionId, Date next) throws Exception;
	UserVO checkLoginBefore(String value);
}
