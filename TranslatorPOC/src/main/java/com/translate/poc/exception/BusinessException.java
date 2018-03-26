package com.translate.poc.exception;

public class BusinessException extends Exception  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BusinessException(String exception){
		super(exception);
	}

}
