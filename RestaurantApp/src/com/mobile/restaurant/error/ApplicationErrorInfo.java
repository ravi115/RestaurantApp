/*
 * (c) copyright 2017. 
 */
package com.mobile.restaurant.error;

/**
 * This Enum class is responsible to provide error code and error message to
 * exception class.
 * 
 * @author ravi ranjan kumar
 * @since 2017-08-28
 *
 */
public enum ApplicationErrorInfo {

	SUCCESS(0, "Success"), FILE_NOT_FOUND(2, "File Not Found"), INVALID_INPUT(2, "Invalid Input"), INTERNAL_ERROR(3,
			"Internal Procressing Error"), SQL_ERROR(4, "Sql Query Error"), INTERNAL_SERVER_ERROR(5,
					"Internal Server Error"), DATABASE_ERROR(6,
							"Database Error"), XML_CONFIG_ERROR(7, "Falied to load XML config file");

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
