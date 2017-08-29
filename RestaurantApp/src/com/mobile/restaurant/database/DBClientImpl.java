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

import org.apache.log4j.Logger;

import com.mobile.restaurant.error.ApplicationErrorInfo;
import com.mobile.restaurant.exception.ApplicationException;

/**
 * This class executes the SQL Query and get the results.
 * 
 * @author ravi ranjan kumar
 * @since 2017-08-28
 *
 */
public class DBClientImpl extends DBConnectionSetup implements DBClient {

	// Logger variable
	private final Logger LOG = Logger.getLogger(getClass());

	public DBClientImpl() throws ApplicationException {
		super();
	}

	/**
	 * Method to execute query.
	 */
	@Override
	public List<Map<String, String>> getQueryResult(final String query) throws ApplicationException {

		List<Map<String, String>> resturantList = null;
		try {
			final ResultSet result = statement.executeQuery(query);
			final ResultSetMetaData resultSetMetaData = result.getMetaData();

			resturantList = new ArrayList<>();
			while (result.next()) {
				final Map<String, String> restaurnatMap = new HashMap<>();
				for (int nIndex = 1; nIndex <= resultSetMetaData.getColumnCount(); nIndex++) {
					final String columnName = resultSetMetaData.getColumnName(nIndex);
					final String value = result.getString(columnName);
					restaurnatMap.put(columnName, value);
				}
				resturantList.add(restaurnatMap);
			}
		} catch (SQLException e) {
			LOG.debug("Caught Excpetion is " + e.getLocalizedMessage());
			throw new ApplicationException(ApplicationErrorInfo.SQL_ERROR.getErrorCode(),
					ApplicationErrorInfo.SQL_ERROR.getErrorMessage());

		}
		// close connection
		closeConnection();
		LOG.info("Query result is " + resturantList);
		return resturantList;
	}
}
