package com.rest1.product.exception;

public class InSuffBalException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public InSuffBalException() {
		super();
	}
	
	public InSuffBalException(String message) {
		super(message);
	}

}
