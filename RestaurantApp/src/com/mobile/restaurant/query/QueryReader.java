/*
 * (c) copyright 2017 
 */
package com.mobile.restaurant.query;

import com.mobile.restaurant.constant.ApplicationConstant;
import com.mobile.restaurant.exception.ApplicationException;

/**
 * This class is responsible to generate required SQL query.
 * 
 * @author ravi ranjan kumar
 * @since 2017-08-28
 *
 */
public class QueryReader extends GenericFileReader {

	private String menuType;

	/**
	 * Constructor to call its abstract class and initialize the required
	 * property.
	 * 
	 * @param menuType
	 *            requested parameter.
	 * @throws ApplicationException
	 *             throws checked and unchecked exception.
	 */
	public QueryReader(final String menuType) throws ApplicationException {
		super();
		this.menuType = menuType;
	}

	/**
	 * This method provide the complete SQL query by constructing template query
	 * with requested input. with actual requested parameter and provides
	 * complete SQL query.
	 */
	@Override
	public String readQuery() throws ApplicationException {
		return queryTemplate.replace(ApplicationConstant.PLACE_HOLDER, menuType);
	}
}
