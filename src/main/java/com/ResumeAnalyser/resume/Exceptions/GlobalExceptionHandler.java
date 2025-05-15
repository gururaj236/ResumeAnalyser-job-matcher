package com.ResumeAnalyser.resume.Exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(UserNotfoundException.class)
	public ResponseEntity<ErrorResponse>handleUserNOtFoundExceptions(UserNotfoundException ex){
		ErrorResponse errorResponse=new ErrorResponse(
				LocalDateTime.now(),HttpStatus.NOT_FOUND.value(),"not found",
				ex.getMessage()
				);
		return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
		
	}
	
	
	@ExceptionHandler(ResumeNotFoundExceptions.class)
	public ResponseEntity<ErrorResponse>handleResumeNotFoundException(ResumeNotFoundExceptions ex){
		ErrorResponse errorResponse=new ErrorResponse(
				LocalDateTime.now(),
				HttpStatus.NOT_FOUND.value()
				,"Rseume not found",
				ex.getMessage()
				);
		return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse>GenericExceptionHandler(Exception ex){
		ErrorResponse errorResponse=new ErrorResponse(
				LocalDateTime.now(),
				HttpStatus.INTERNAL_SERVER_ERROR.value()
				,"Internal Server error",
				ex.getMessage()
				);
		return new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	

}
