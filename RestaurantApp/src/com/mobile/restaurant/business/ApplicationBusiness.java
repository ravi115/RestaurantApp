/*
 * (c) copyright 2017. 
 */

package com.mobile.restaurant.business;

import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONException;

import com.mobile.restaurant.database.DBClientImpl;
import com.mobile.restaurant.exception.ApplicationException;
import com.mobile.restaurant.response.ApplicationResponse;

/**
 * 
 * @author raviranjan
 *
 */
public class ApplicationBusiness {

	public ApplicationResponse getResult(final String menuType)
			throws ApplicationException, JSONException, SQLException {

		//final String query = new QueryReader().readQuery();

		ApplicationResponse appResponse = new ApplicationResponse();
		String query = "select r.name, a.street, a.city,a.state, a.country, a.pincode, a.mobile\r\n" + 
				"from restaurantinformation r join address a \r\n" + 
				"on a.aid = r.id\r\n" + 
				"where r.id in (\r\n" + 
				"select tid from item where type=\"south indian\");";
		
		if (null != query && !query.isEmpty()) {
			
			appResponse.restaurants = (null != appResponse.restaurants) ? appResponse.restaurants : new ArrayList<>();
			appResponse.restaurants = new DBClientImpl().getQueryResult(query);
		}

		return appResponse;
	}
}
