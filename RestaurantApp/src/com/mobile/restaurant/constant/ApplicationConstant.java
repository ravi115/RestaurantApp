/*
 * (c) copyright 2017. 
 */
package com.mobile.restaurant.constant;

/**
 * 
 * @author raviranjan
 *
 */
public class ApplicationConstant {

	public final static String DATABASEURL = "jdbc:mysql:";
	public final static String DRIVERNAME = "com.mysql.jdbc.Driver";
	public final static String FILENAME = "query.txt";
	public final static String FOLDER_PATH = "queries";
	public final static String REPLACE_SPECIAL_CHARACTER = "[\\t\\n\\r]";
	public final static String SPACE = "\\s";
	public final static String PLACE_HOLDER = "$type";
	//preventing to instantiation of this class.
	private ApplicationConstant() {}
}
