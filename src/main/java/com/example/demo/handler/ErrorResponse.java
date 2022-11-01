package com.example.demo.handler;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {
	
	private String errorMsg;
	
	private String errorCode;
	
	

}
