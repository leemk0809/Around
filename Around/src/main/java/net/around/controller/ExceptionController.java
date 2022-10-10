package net.around.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import net.around.exception.IdFailException;
import net.around.exception.passFailException;

@ControllerAdvice
public class ExceptionController {
	static Logger logger = LoggerFactory.getLogger(ExceptionController.class);
	
	@ExceptionHandler(IdFailException.class)
	public String IdFailExceptionController(){
		return "index";
	}	
	
	@ExceptionHandler(passFailException.class)
	public String passFailExceptionController(){
		return "index";
	}

	
	
	
}
