package com.spring.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.domain.SampleVO;

//@RestController: Jsp와 같은 뷰를 만들지 않고, 데이터 자체를 브라우저에 반환(단순 문자열, JSON, XML 등)
//				   @ResponseBody 생략(사용)
@RestController
@RequestMapping("/sample")
public class SampleController {
	
	//1. 문자열 반환
	@RequestMapping("/hello")
	public String sayHello() {
		return "Hello World";
	}
	
	//2. VO 객체 반환
	@RequestMapping("/sendVO")
	public SampleVO sendVO() {
		SampleVO vo = new SampleVO();
		vo.setFirstName("길동");
		vo.setLastName("홍");
		vo.setMno(123);
		return vo;
	}
	
	//3. List타입 객체 반환
	@RequestMapping("/sendList")
	public List<SampleVO> sendList(){
		List<SampleVO> list = new ArrayList<>();
		for(int i=0; i<10; i++) {
			SampleVO vo = new SampleVO();
			vo.setFirstName("길동");
			vo.setLastName("홍");
			vo.setMno(i);
			
			list.add(vo);
		}
		return list;
	}
	
	//4. Map타입 객체 반환
	@RequestMapping("/sendMap")
	public Map<Integer, SampleVO> sendMap(){
		Map<Integer, SampleVO> map = new HashMap<>();
		
		for(int i=0; i<10; i++) {
			SampleVO vo = new SampleVO();
			vo.setFirstName("길동");
			vo.setLastName("홍");
			vo.setMno(i);
			map.put(i, vo);
		}
		return map;
	}
	
	//5. 예외 대신에 ResponseEntity를 이용해서 사용자에게 정보 전달(Http 상태 코드)
	@RequestMapping("/sendErrorAuth")
	public ResponseEntity<Void> sendListAuth(){
		//결과 데이터로 HTTP 상태 코드중 400(bad request)을 header 메시지로 전송
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	//6. 결과 데이터 + 상태 코드를 같이 사용하는 경우
	@RequestMapping("/sendErrorNot")
	public ResponseEntity<List<SampleVO>> sendListNot(){
		List<SampleVO> list = new ArrayList<>();
		for(int i=0; i<10; i++) {
			SampleVO vo = new SampleVO();
			vo.setFirstName("길동");
			vo.setLastName("홍");
			vo.setMno(i);
			
			list.add(vo);
		}
		//결과 데이터로 HTTP 상태 코드중 400(bad request)을 header 메시지로 전송
		return new ResponseEntity<>(list, HttpStatus.NOT_FOUND);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
