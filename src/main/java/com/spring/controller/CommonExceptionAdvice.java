package com.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
//공통적인 예외처리
@ControllerAdvice
public class CommonExceptionAdvice {
	private static final Logger logger = LoggerFactory.getLogger(CommonExceptionAdvice.class);
	
	//방법1
//	@ExceptionHandler(Exception.class)
//	public String common(Exception e) {
//		logger.info(e.toString());
//		return "error_common";//error_common.jsp	
//	}
	//방법2
	@ExceptionHandler(Exception.class)
	public ModelAndView errorModelAndView(Exception ex) {
		logger.info(ex.toString());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/error_common");
		modelAndView.addObject("exception", ex);
		return modelAndView;
	}
}
