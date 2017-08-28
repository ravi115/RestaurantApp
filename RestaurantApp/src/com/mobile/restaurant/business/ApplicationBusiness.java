/*
 * (c) copyright 2017. 
 */

package com.mobile.restaurant.business;

import java.sql.SQLException;

import org.json.JSONException;

import com.mobile.restaurant.database.DBClientImpl;
import com.mobile.restaurant.exception.ApplicationException;
import com.mobile.restaurant.query.QueryReader;
import com.mobile.restaurant.response.ApplicationResponse;

/**
 * 
 * @author raviranjan
 *
 */
public class ApplicationBusiness {

	public ApplicationResponse getResult(final String menuType)
			throws ApplicationException, JSONException, SQLException {

		final String query = new QueryReader().readQuery();

		ApplicationResponse appResponse = new ApplicationResponse();

		if (null != query && !query.isEmpty()) {
			appResponse.result = new DBClientImpl().getQueryResult(query);
		}

		return appResponse;
	}
}
