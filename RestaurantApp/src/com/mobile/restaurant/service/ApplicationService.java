/*
 * (c) copyright 2017. 
 * This package provides the implementation of rest resource. 
 */
package com.mobile.restaurant.service;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONException;

import com.mobile.restaurant.business.ApplicationBusiness;
import com.mobile.restaurant.error.ApplicationErrorInfo;
import com.mobile.restaurant.exception.ApplicationException;
import com.mobile.restaurant.response.ApplicationResponse;

/**
 * This class implements GET rest API and provide search interface.
 * @author raviranjan
 * @since 2017-08-28
 */
@Path("/menu")
public class ApplicationService {

	/**
	 * This method's GET API usese @PathParam to read the requested input.
	 * if the input in not null then invoke business logic with this input.
	 * @param type menu type to search all restaurant details.
	 * @return list of restaurant details for that specified menu.
	 */
	@GET
	@Path("/{type}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getResult(@PathParam("type") String type) {
		ApplicationResponse appResponse =  new ApplicationResponse();
		if(null != type && !type.isEmpty()) {
			try {
				appResponse = new ApplicationBusiness().getResult(type);
			} catch (JSONException | ApplicationException | SQLException e) {
				e.printStackTrace();
			}
		}else {
			try {
				throw new ApplicationException(ApplicationErrorInfo.INVALID_INPUT.getErrorCode(), ApplicationErrorInfo.INVALID_INPUT.getErrorMessage());
			}catch (ApplicationException e) {
				appResponse.id = e.getErrorCode();
				appResponse.message = e.getErrorMessage();
			}
		}
		return Response.ok().status(200).entity(appResponse).build();
	}

	/**
	 * This method's GET API usese @QueryParam to read the requested input.
	 * if the input in not null then invoke business logic with this input.
	 * @param type menu type to search all restaurant details.
	 * @return list of restaurant details for that specified menu.
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getResults(@QueryParam("type") String type) {
		ApplicationResponse appResponse = new ApplicationResponse();
		try {
			appResponse = new ApplicationBusiness().getResult(type);
		} catch (JSONException | ApplicationException | SQLException e) {
			e.printStackTrace();
		}
		return Response.ok().status(200).entity(appResponse).build();
	}

	public static void main(String[] args) {
		ApplicationService obj = new ApplicationService();
		System.out.println(obj.getResult(""));

	}
}
