package com.mobile.restaurant.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mobile.restaurant.config.DBConfig;
import com.mobile.restaurant.constant.ApplicationConstant;

public class DBConnectionSetup {

	private final DBConfig dbConfig = new DBConfig();
	protected Connection connection;
	protected Statement statement;

	public DBConnectionSetup() {
		initConnection();
	}

	// jdbc:mysql://localhost:3306/restaurant
	private String getDatabaseUrl(final String host, final int port, final String databaseName) {
		String databaseUrl = "";
		if (null != host && !host.isEmpty()) {
			databaseUrl = String.format("%s//%s:%s/%s", ApplicationConstant.DATABASEURL, host, port, databaseName);
		}
		return databaseUrl;
	}
	/**
	 * 
	 */
	private void initConnection() {

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
					e.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 
	 * @throws SQLException
	 */
	protected void closeConnection() throws SQLException {
		if(null != connection ) {
			connection.close();
		}
		if(null != statement ) {
			statement.close();
		}
	}
	
}
