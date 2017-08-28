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
import com.mobile.restaurant.response.ApplicationResponse;

/**
 * 
 * @author raviranjan
 *
 */
public class DBClientImpl extends DBConnectionSetup implements DBClient {

	@Override
	public List<Map<String,String>> getQueryResult(final String query) throws ApplicationException, JSONException, SQLException {
		ResultSet result;
		try {
			result = statement.executeQuery(query);
		} catch (SQLException e) {
			throw new ApplicationException(ApplicationErrorInfo.NOT_FOUND.getErrorCode(),
					ApplicationErrorInfo.NOT_FOUND.getErrorMessage());

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
	public static void main(String[] args) {
		String query = "select r.name, a.street, a.city,a.state, a.country, a.pincode, a.mobile\r\n" + 
				"from restaurantinformation r join address a \r\n" + 
				"on a.aid = r.id\r\n" + 
				"where r.id in (\r\n" + 
				"select tid from item where type=\"south indian\");";
		DBClient obj = new DBClientImpl();
		try {
			List<Map<String, String>> arr = obj.getQueryResult(query);
			ApplicationResponse appResponse = new ApplicationResponse();
			//appResponse.result = arr;
			appResponse.id = 0;
			appResponse.message = "success";
			System.out.println(arr);
		} catch (JSONException | ApplicationException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
