package com.example.demo.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.naming.AuthenticationException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.DisabledException;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	
	@ExceptionHandler({AuthenticationException.class,BadCredentialsException.class,
		LockedException.class,DisabledException.class})
	public ResponseEntity<ErrorResponse> handleAuthException(Exception e){
		log.error(e.toString());
		
		return new ResponseEntity<ErrorResponse>(
				ErrorResponse.builder()
				.errorMsg("UnAuthorized request.")
				.errorCode(HttpStatus.UNAUTHORIZED.toString())
				.build()
				,HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleException(Exception e){
		log.error(e.toString());
		
		return new ResponseEntity<ErrorResponse>(
				ErrorResponse.builder()
				.errorMsg("Could not process request!")
				.errorCode(HttpStatus.INTERNAL_SERVER_ERROR.toString())
				.build()
				, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
