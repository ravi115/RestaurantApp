/*
 * (c) copyright 2017. 
 */

package com.mobile.restaurant.business;

import org.apache.log4j.Logger;

import com.mobile.restaurant.database.DBClientImpl;
import com.mobile.restaurant.exception.ApplicationException;
import com.mobile.restaurant.query.QueryReader;
import com.mobile.restaurant.response.ApplicationResponse;

/**
 * This class is the central unit of this application. It is responsible to
 * initialize Response of this application with desired result.
 * 
 * @author ravi ranjan kumar
 * @since 2017-08-28
 *
 */
public class ApplicationBusiness {
	// Logger variable
	private final Logger LOG = Logger.getLogger(getClass());

	/**
	 * Method to initialize the Application Response with desired result.
	 * 
	 * @param cuisineType
	 *            requested parameter.
	 * @return result in the form of application response.
	 * @throws ApplicationException
	 *             throws any checked or unchecked exception while processing of
	 *             application.
	 */
	public ApplicationResponse getResult(final String cuisineType) throws ApplicationException {
		LOG.info("Inside business Logic.");
		final String query = new QueryReader(cuisineType).readQuery();
		LOG.info("Generated Query is :" + query);
		final ApplicationResponse appResponse = new ApplicationResponse();
		if (null != query && !query.isEmpty()) {
			appResponse.restaurants = new DBClientImpl().getQueryResult(query);
		}

		return appResponse;
	}
}
