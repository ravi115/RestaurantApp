/*
 * (c) copyright 2017. 
 */
package com.mobile.restaurant.database;

import java.util.List;
import java.util.Map;

import com.mobile.restaurant.exception.ApplicationException;

/**
 * Interface provides functionality to execute query and process result to its
 * implementing class.
 * 
 * @author ravi ranjan kumar
 * @since 2017-08-28
 *
 */
public interface DBClient {

	/**
	 * Method to execute SQL query.
	 * 
	 * @param query
	 *            SQL query for the requested input.
	 * @return collection of all query result.
	 * @throws ApplicationException
	 *             throws any checked or unchecked exception while executing the
	 *             query..
	 */
	public List<Map<String, String>> getQueryResult(final String query) throws ApplicationException;
}
