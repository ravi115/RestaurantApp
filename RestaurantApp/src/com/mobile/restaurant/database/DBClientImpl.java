/*
 * (c) copyright 2017. 
 */
package com.mobile.restaurant.database;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mobile.restaurant.error.ApplicationErrorInfo;
import com.mobile.restaurant.exception.ApplicationException;

/**
 * 
 * @author raviranjan
 *
 */
public class DBClientImpl extends DBConnectionSetup implements DBClient {

	@Override
	public JSONArray getQueryResult(final String query) throws ApplicationException, JSONException, SQLException {
		ResultSet result;
		try {
			result = statement.executeQuery(query);
		} catch (SQLException e) {
			throw new ApplicationException(ApplicationErrorInfo.NOT_FOUND.getErrorCode(),
					ApplicationErrorInfo.NOT_FOUND.getErrorMessage());

		}
		final ResultSetMetaData resultSetMetaData = result.getMetaData();

		JSONArray jsonArr = new JSONArray();
		while (result.next()) {
			JSONObject json = new JSONObject();
			for (int nIndex = 1; nIndex <= resultSetMetaData.getColumnCount(); nIndex++) {
				json.put(resultSetMetaData.getColumnTypeName(nIndex),
						result.getString(resultSetMetaData.getColumnTypeName(nIndex)));
			}
			jsonArr.put(json);
		}
		return jsonArr;
	}

}
