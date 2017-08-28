/*
 * (c) copyright 2017.  
 */
package com.mobile.restaurant.query;

import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.mobile.restaurant.constant.ApplicationConstant;
import com.mobile.restaurant.error.ApplicationErrorInfo;
import com.mobile.restaurant.exception.ApplicationException;

/**
 * 
 * @author raviranjan
 *
 */
public abstract class GenericFileReader {

	protected String queryTemplate;
	
	/**
	 * 
	 * @return
	 */
	private String getCompleteFilePath() {
		return String.format("%s\\%s", ApplicationConstant.FOLDER_PATH, ApplicationConstant.FILENAME);
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
			final InputStream inputStream = getClass().getClassLoader().getResourceAsStream(getCompleteFilePath());
			if (null == inputStream) {
				throw new ApplicationException(ApplicationErrorInfo.FILE_NOT_FOUND.getErrorCode(),
						ApplicationErrorInfo.FILE_NOT_FOUND.getErrorMessage());
			}
			Scanner scan = new Scanner(inputStream, "UTF-8").useDelimiter("\\A");
			queryTemplate = scan.hasNext() ? scan.next() : "";

			if (queryTemplate.isEmpty()) {
				throw new ApplicationException(ApplicationErrorInfo.INTERNAL_ERROR.getErrorCode(),
						ApplicationErrorInfo.INTERNAL_ERROR.getErrorMessage());
			}
		} catch (NoSuchElementException cx) {
			throw new ApplicationException(ApplicationErrorInfo.FILE_NOT_FOUND.getErrorCode(),
					ApplicationErrorInfo.FILE_NOT_FOUND.getErrorMessage());
		}
		return queryTemplate.replaceAll(ApplicationConstant.REPLACE_SPECIAL_CHARACTER, ApplicationConstant.SPACE);
	}
	/**
	 * 
	 * @return
	 * @throws ApplicationException
	 */
	public abstract String readQuery() throws ApplicationException;
}
