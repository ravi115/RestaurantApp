/*
 * (c) copyright 2017.  
 */
package com.mobile.restaurant.query;

import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.mobile.restaurant.error.ApplicationErrorInfo;
import com.mobile.restaurant.exception.ApplicationException;

/**
 * 
 * @author raviranjan
 *
 */
public abstract class GenericFileReader {

	private String folderPath = "queries";
	private String fileName = "query.txt";
	protected String queryTemplate;
	
	private String getCompleteFilePath() {
		String pathName = String.format("%s\\%s", folderPath, fileName); 
		return pathName;
	}

	public GenericFileReader() throws ApplicationException {
		readQueryFile();
	}
	/**
	 * 
	 * @return
	 * @throws ApplicationException
	 */
	@SuppressWarnings("resource")
	private String readQueryFile() throws ApplicationException {
		
		try {
			final InputStream inputStream = getClass().getResourceAsStream(getCompleteFilePath());
			if (null == inputStream) {
				throw new ApplicationException(ApplicationErrorInfo.NOT_FOUND.getErrorCode(),
						ApplicationErrorInfo.NOT_FOUND.getErrorMessage());
			}
			Scanner scan = new Scanner(inputStream, "UTF-8").useDelimiter("\\A");
			queryTemplate = scan.hasNext() ? scan.next() : "";

			if (queryTemplate.isEmpty()) {
				throw new ApplicationException(ApplicationErrorInfo.NOT_FOUND.getErrorCode(),
						ApplicationErrorInfo.NOT_FOUND.getErrorMessage());
			}
		} catch (NoSuchElementException cx) {
			throw new ApplicationException(ApplicationErrorInfo.NOT_FOUND.getErrorCode(),
					ApplicationErrorInfo.NOT_FOUND.getErrorMessage());
		}
		return queryTemplate.replaceAll("[\\t\\n\\r]", "\\s");
	}
	public abstract String readQuery() throws ApplicationException;
}
