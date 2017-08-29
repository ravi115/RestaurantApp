/*
 * (c) copyright 2017. 
 * This package provides the implementation of rest resource. 
 */

package com.mobile.restaurant.query;

import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.mobile.restaurant.constant.ApplicationConstant;
import com.mobile.restaurant.error.ApplicationErrorInfo;
import com.mobile.restaurant.exception.ApplicationException;

/**
 * This is Generic class to provide implementation to reading of a file.
 * 
 * @author ravi ranjan kumar
 * @since 2017-08-28
 *
 */
public abstract class GenericFileReader {
	// Logger variable
	private final Logger LOG = Logger.getLogger(getClass());

	protected String queryTemplate;

	/**
	 * This method forms complete path for file to be read.
	 * 
	 * @return complete path name to the file.
	 */
	private String getCompleteFilePath() {
		final String filePath = String.format("%s\\%s", ApplicationConstant.FOLDER_PATH, ApplicationConstant.FILENAME);
		LOG.info("File Path Location " + filePath);
		return filePath;
	}

	public GenericFileReader() throws ApplicationException {
		readQueryFile();
	}

	/**
	 * This method reads the file.
	 * 
	 * @return template query.
	 * @throws ApplicationException
	 *             throws checked and unchecked Exception while reading of file.
	 */
	@SuppressWarnings("resource")
	private String readQueryFile() throws ApplicationException {

		try {
			final InputStream inputStream = getClass().getClassLoader().getResourceAsStream(getCompleteFilePath());
			if (null == inputStream) {
				LOG.debug("Input Stream is null");
				throw new ApplicationException(ApplicationErrorInfo.FILE_NOT_FOUND.getErrorCode(),
						ApplicationErrorInfo.FILE_NOT_FOUND.getErrorMessage());
			}
			Scanner scan = new Scanner(inputStream, "UTF-8").useDelimiter("\\A");
			queryTemplate = scan.hasNext() ? scan.next() : "";
			LOG.info("Query template : " + queryTemplate);
			if (queryTemplate.isEmpty()) {
				throw new ApplicationException(ApplicationErrorInfo.INTERNAL_ERROR.getErrorCode(),
						ApplicationErrorInfo.INTERNAL_ERROR.getErrorMessage());
			}
		} catch (NoSuchElementException cx) {
			LOG.debug("Caught Exception : " + cx.getLocalizedMessage());
			throw new ApplicationException(ApplicationErrorInfo.FILE_NOT_FOUND.getErrorCode(),
					ApplicationErrorInfo.FILE_NOT_FOUND.getErrorMessage());
		}
		return queryTemplate.replaceAll(ApplicationConstant.REPLACE_SPECIAL_CHARACTER, ApplicationConstant.SPACE);
	}

	/**
	 * This method provide the complete SQL query by constructing template query
	 * with requested input.
	 * 
	 * @return complete SQL Query
	 * @throws ApplicationException
	 *             throws checked and unchecked exception.
	 */
	public abstract String readQuery() throws ApplicationException;
}
