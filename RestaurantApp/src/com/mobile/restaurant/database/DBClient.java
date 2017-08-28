/*
 * (c) copyright 2017. 
 */
package com.mobile.restaurant.database;

import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;

import com.mobile.restaurant.exception.ApplicationException;

/**
 * 
 * @author raviranjan
 *
 */
public interface DBClient {

	public JSONArray getQueryResult(final String query) throws ApplicationException, JSONException, SQLException;
}
