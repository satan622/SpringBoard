package com.spring.persistence;

//사용자의 활동에 따른 포인트 적립
public interface PointDAO {
	void updatePoint(String uid, int point) throws Exception;
}
