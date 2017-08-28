/*
 * (c) copyright 2017. 
 */
package com.mobile.restaurant.error;

/**
 * 
 * @author raviranjan
 *
 */
public enum ApplicationErrorInfo {

	SUCCESS(0, "Success"), FILE_NOT_FOUND(2, "File Not Found"), INVALID_INPUT(2, "Invalid Input"), INTERNAL_ERROR(3,
			"Internal Procressing Error"), SQL_ERROR(4, "Sql Query Error");

	private int errorCode;
	private String errorMessage;

	private ApplicationErrorInfo(final int errorCode, final String errorMessage) {
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
