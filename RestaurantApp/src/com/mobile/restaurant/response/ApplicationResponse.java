/*
 * (c) copyright 2017. 
 */
package com.mobile.restaurant.response;

import java.util.List;
import java.util.Map;

/**
 * The class Contains the error code, error message and result. This is Response
 * class of the application.
 * 
 * @author ravi ranjan kumar
 * @since 2017-08-28
 *
 */
public class ApplicationResponse {

	public int errorCode;
	public String errorMessage;
	public List<Map<String, String>> restaurants;

}
