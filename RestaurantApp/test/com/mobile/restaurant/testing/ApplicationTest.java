/*
 * (c) copyright 2017. 
 */
package com.mobile.restaurant.testing;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import com.mobile.restaurant.database.DBClient;
import com.mobile.restaurant.database.DBClientImpl;
import com.mobile.restaurant.exception.ApplicationException;
import com.mobile.restaurant.query.QueryReader;
import com.mobile.restaurant.service.ApplicationService;

/**
 * This class provides unit test cases for the application.
 * 
 * @author ravi ranjan kumar
 * @since 2017-08-28
 *
 */
public class ApplicationTest {

	/**
	 * Test Type = positive test. Input = valid input. Test one of the GET API
	 * method. This test method confirms that whole application is working
	 * properly.
	 */
	@Test
	public void testGetResults() {
		try {
			ApplicationService appService = new ApplicationService();
			Response appResponse = appService.getResults("north india");
			JSONObject json = new JSONObject(appResponse);
			int errorCode = json.getInt("errorCode");
			String errorMessage = json.getString("errorMessage");
			assertEquals(0, errorCode);
			assertEquals(null, errorMessage);
			JSONArray jsonArr = json.getJSONArray("restaurants");

			JSONObject objJSON = (JSONObject) jsonArr.get(0);
			assertEquals("india", objJSON.getString("country"));
			assertEquals(400120, objJSON.getString("pincode"));

		} catch (Exception e) {
		}
	}

	/**
	 * Test Type = Negative test. input = InValid Input Test one of the GET API
	 * method. This test method confirms that whole application is working
	 * properly.
	 */
	@Test
	public void testGetResult() {
		try {
			ApplicationService appService = new ApplicationService();
			Response appResponse = appService.getResults(null);
			JSONObject json = new JSONObject(appResponse);
			int errorCode = json.getInt("errorCode");
			String errorMessage = json.getString("errorMessage");
			assertEquals(2, errorCode);
			assertEquals("Invalid Input", errorMessage);
		} catch (Exception e) {
		}
	}

	/**
	 * Test Type = Negative test. input = InValid Input Test one of the GET API
	 * method. This test method confirms that whole application is working
	 * properly.
	 */
	@Test
	public void testGetResult2() {
		try {
			ApplicationService appService = new ApplicationService();
			Response appResponse = appService.getResults("");
			JSONObject json = new JSONObject(appResponse);
			int errorCode = json.getInt("errorCode");
			String errorMessage = json.getString("errorMessage");
			assertEquals(2, errorCode);
			assertEquals("Invalid Input", errorMessage);
		} catch (Exception e) {
		}
	}

	/**
	 * Test type = Positive Test. This method test the query reader functions.
	 * this ensures the correct SQL query which has to be run against databases.
	 */
	@Test
	public void testGetQuery() {
		try {
			final String sqlQuery = "select r.name, a.street, a.city,a.state, a.country, a.pincode, a.mobile\r\n"
					+ "from restaurantinformation r join address a \r\n" + "on a.aid = r.id\r\n" + "where r.id in (\r\n"
					+ "select tid from item where type=\"north india\");";
			final QueryReader queryReader = new QueryReader("north india");
			final String actualQuery = queryReader.readQuery();
			assertEquals(sqlQuery, actualQuery);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Test Type = Positive Test. Test method which runs SQL Query.
	 */
	@Test
	public void testGetQueryResult() {
		try {
			final String sqlQuery = "select r.name, a.street, a.city,a.state, a.country, a.pincode, a.mobile\r\n"
					+ "from restaurantinformation r join address a \r\n" + "on a.aid = r.id\r\n" + "where r.id in (\r\n"
					+ "select tid from item where type=\"south indian\");";

			final Map<String, String> map = new HashMap<>();
			map.put("pincode", "560100");
			map.put("city", "bangalore");
			map.put("street", "electronic city");
			map.put("county", "india");
			map.put("name", "barbacues");
			map.put("mobile", "+91-33668416");
			map.put("state", "karnataka");
			final List<Map<String, String>> expectedList = Arrays.asList(map);
			final DBClient objDBClient = new DBClientImpl();
			final List<Map<String, String>> actualList = objDBClient.getQueryResult(sqlQuery);
			assertEquals(expectedList, actualList);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Test Type = negative Test. If Query is wrong Then it should return null
	 */
	@Test
	public void testGetQueryResult2() {
		try {
			final String sqlQuery = "wrong query";
			DBClient objDBClient = new DBClientImpl();
			final List<Map<String, String>> actualList = objDBClient.getQueryResult(sqlQuery);
			assertEquals(null, actualList);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}
}
