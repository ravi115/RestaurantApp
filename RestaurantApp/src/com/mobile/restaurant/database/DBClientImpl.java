/*
 * (c) copyright 2017. 
 */
package com.mobile.restaurant.database;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;

import com.mobile.restaurant.error.ApplicationErrorInfo;
import com.mobile.restaurant.exception.ApplicationException;

/**
 * 
 * @author raviranjan
 *
 */
public class DBClientImpl extends DBConnectionSetup implements DBClient {

	/**
	 * 
	 */
	@Override
	public List<Map<String,String>> getQueryResult(final String query) throws ApplicationException, JSONException, SQLException {
		ResultSet result;
		try {
			result = statement.executeQuery(query);
		} catch (SQLException e) {
			throw new ApplicationException(ApplicationErrorInfo.SQL_ERROR.getErrorCode(),
					ApplicationErrorInfo.SQL_ERROR.getErrorMessage());

		}
		final ResultSetMetaData resultSetMetaData = result.getMetaData();

		List<Map<String,String>> resturantList = new ArrayList<>();
		while (result.next()) {
			Map<String,String> restaurnatMap = new HashMap<>();
			for (int nIndex = 1; nIndex <= resultSetMetaData.getColumnCount(); nIndex++) {
				String columnName = resultSetMetaData.getColumnName(nIndex);
				String value = result.getString(columnName);
				restaurnatMap.put(columnName, value);
			}
			resturantList.add(restaurnatMap);
		}
		//close connection
		closeConnection();
		return resturantList;
	}
}
