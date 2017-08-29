/*
 * (c) copyright 2017. 
 */
package com.mobile.restaurant.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.mobile.restaurant.config.DBConfig;
import com.mobile.restaurant.constant.ApplicationConstant;
import com.mobile.restaurant.error.ApplicationErrorInfo;
import com.mobile.restaurant.exception.ApplicationException;

/**
 * This class is responsible to establish connection to database and closing the
 * connection.
 * 
 * @author ravi ranjan kumar
 * @since 2017-08-28
 *
 */
public class DBConnectionSetup {

	// Logger variable
	private final Logger LOG = Logger.getLogger(getClass());

	private final DBConfig dbConfig = new DBConfig();
	protected Connection connection;
	protected Statement statement;

	public DBConnectionSetup() throws ApplicationException {
		initConnection();
	}

	// jdbc:mysql://localhost:3306/restaurant
	private String getDatabaseUrl(final String host, final int port, final String databaseName) {
		String databaseUrl = "";
		if (null != host && !host.isEmpty()) {
			databaseUrl = String.format("%s//%s:%s/%s", ApplicationConstant.DATABASEURL, host, port, databaseName);
			LOG.info("Database URL : " + databaseUrl);
		}
		return databaseUrl;
	}

	/**
	 * Method to initialize database property and to establish the connection.
	 * 
	 * @throws ApplicationException
	 *             throws any checked or unchecked exception while establishing
	 *             the connection to database.
	 * 
	 */
	private void initConnection() throws ApplicationException {

		final String userName = dbConfig.getString("db-username");
		final String password = dbConfig.getString("db-password");
		final String databaseName = dbConfig.getString("db-name");
		final String host = dbConfig.getString("db-host");
		final int port = dbConfig.getInteger("db-port");
		final String databaseUrl = getDatabaseUrl(host, port, databaseName);

		if (!databaseUrl.isEmpty()) {
			try {
				Class.forName(ApplicationConstant.DRIVERNAME);
				try {
					connection = DriverManager.getConnection(databaseUrl, userName, password);
					statement = connection.createStatement();
				} catch (SQLException e) {
					LOG.debug("Connection falied : " + e.getMessage());
					throw new ApplicationException(ApplicationErrorInfo.SQL_ERROR.getErrorCode(),
							ApplicationErrorInfo.SQL_ERROR.getErrorMessage());
				}
			} catch (ClassNotFoundException e) {
				LOG.debug("Caught exception is : " + e.getLocalizedMessage());
				throw new ApplicationException(ApplicationErrorInfo.DATABASE_ERROR.getErrorCode(),
						ApplicationErrorInfo.DATABASE_ERROR.getErrorMessage());
			}
		}
	}

	/**
	 * Method to close the connection to database.
	 * 
	 * @throws ApplicationException
	 *             throws any checked or unchecked exception while closing the
	 *             connection.
	 */
	protected void closeConnection() throws ApplicationException {
		try {
			if (null != connection) {
				connection.close();
			}
			if (null != statement) {
				statement.close();
			}
		} catch (SQLException e) {
			LOG.debug("Caught exception while closing connection : " + e.getSQLState());
			throw new ApplicationException(ApplicationErrorInfo.SQL_ERROR.getErrorCode(),
					ApplicationErrorInfo.SQL_ERROR.getErrorMessage());

		}
	}

}
