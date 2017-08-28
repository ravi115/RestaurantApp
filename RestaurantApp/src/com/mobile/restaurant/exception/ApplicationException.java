/*
 * (c) copyright 2017. 
 */
package com.mobile.restaurant.exception;

/**
 * 
 * @author raviranjan
 *
 */
public class ApplicationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8252761370784763469L;
	
	private int errorCode;
	private String errorMessage;

	public ApplicationException() {
		super();
	}

	public ApplicationException(final int errorCode, final String errorMessage) {
		super(errorMessage);
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
