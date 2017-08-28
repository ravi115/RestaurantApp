/*
 * (c) copyright 2017. 
 */
package com.mobile.restaurant.database;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.json.JSONException;

import com.mobile.restaurant.exception.ApplicationException;

/**
 * 
 * @author raviranjan
 *
 */
public interface DBClient {

	/**
	 * 
	 * @param query
	 * @return
	 * @throws ApplicationException
	 * @throws JSONException
	 * @throws SQLException
	 */
	public List<Map<String,String>> getQueryResult(final String query) throws ApplicationException, JSONException, SQLException;
}
