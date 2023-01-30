package com.rest1.product.handler;


import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.rest1.product.exception.ProductNotFoundException;
import com.rest1.product.model.ErrorData;
@RestControllerAdvice
public class CustomExceptionHandler {
	//--->Message comes in String format
		/*
		@ExceptionHandler(ProductNotFoundException.class)
		public ResponseEntity<String> handleProductNotFoundException(
				ProductNotFoundException pne
				)
		{
			return new ResponseEntity<String>(
					pne.getMessage(),HttpStatus.NOT_FOUND
					);
		}*/
		//--->Message comes in JSON format
		
		@ExceptionHandler(ProductNotFoundException.class)
		public ResponseEntity<ErrorData> handleProductNotFoundException(
				ProductNotFoundException pne
				)
		{
			return new ResponseEntity<ErrorData>(
					new ErrorData(
							pne.getMessage(), 
							new Date().toString(), 
							"Product"),
					HttpStatus.NOT_FOUND
					);
		}

}
