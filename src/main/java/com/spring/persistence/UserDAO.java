package com.spring.persistence;

import java.util.Date;

import com.spring.domain.UserVO;
import com.spring.dto.LoginDTO;

public interface UserDAO {
	UserVO login(LoginDTO dto) throws Exception;
	void keepLogin(String uid, String sessionId, Date next);
	UserVO checkUserWithSessionKey(String value);
}
