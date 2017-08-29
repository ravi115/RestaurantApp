/*
 * (c) copyright 2017. 
 * This package provides the implementation of rest resource. 
 */
package com.mobile.restaurant.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.mobile.restaurant.business.ApplicationBusiness;
import com.mobile.restaurant.error.ApplicationErrorInfo;
import com.mobile.restaurant.exception.ApplicationException;
import com.mobile.restaurant.response.ApplicationResponse;

/**
 * This class implements GET rest API and provide search interface.
 * 
 * @author ravi ranjan kumar
 * @since 2017-08-28
 */
@Path("/menu")
public class ApplicationService {

	// Logger variable
	private final Logger LOG = Logger.getLogger(getClass());

	/**
	 * This method's GET API uses @PathParam to read the requested input. if the
	 * input in not null then invoke business logic with this input.
	 * 
	 * @param type
	 *            menu type to search all restaurant details.
	 * @return list of restaurant details for that specified menu.
	 */
	@GET
	@Path("/{type}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getResult(@PathParam("type") String type) {

		ApplicationResponse appResponse = new ApplicationResponse();
		try {
			LOG.info("Starting searching restaurant for menu type = " + type);
			if (null == type || type.isEmpty()) {
				LOG.debug("Invalid input " + type);
				throw new ApplicationException(ApplicationErrorInfo.INVALID_INPUT.getErrorCode(),
						ApplicationErrorInfo.INVALID_INPUT.getErrorMessage());
			}
			appResponse = new ApplicationBusiness().getResult(type);
		} catch (ApplicationException e) {
			appResponse.errorCode = e.getErrorCode();
			appResponse.errorMessage = e.getErrorMessage();
			LOG.debug("caugth exception : " + e.getMessage());
		}
		LOG.info("End of search.");
		if (appResponse.errorCode == 0)
			return Response.ok().status(200).entity(appResponse).build();

		return Response.ok().status(400).entity(appResponse).build();
	}

	/**
	 * This method's GET API uses @QueryParam to read the requested input. if
	 * the input in not null then invoke business logic with this input.
	 * 
	 * @param type
	 *            menu type to search all restaurant details.
	 * @return list of restaurant details for that specified menu.
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getResults(@QueryParam("type") String type) {

		ApplicationResponse appResponse = new ApplicationResponse();
		try {
			LOG.info("Starting searching restaurant for menu type = " + type);
			if (null == type || type.isEmpty()) {
				LOG.debug("Invalid input " + type);
				throw new ApplicationException(ApplicationErrorInfo.INVALID_INPUT.getErrorCode(),
						ApplicationErrorInfo.INVALID_INPUT.getErrorMessage());
			}
			appResponse = new ApplicationBusiness().getResult(type);
		} catch (ApplicationException e) {
			appResponse.errorCode = e.getErrorCode();
			appResponse.errorMessage = e.getErrorMessage();
			LOG.debug("caugth exception : " + e.getMessage());
		}
		LOG.info("End of search.");
		if (appResponse.errorCode == 0)
			return Response.ok().status(200).entity(appResponse).build();

		return Response.ok().status(400).entity(appResponse).build();
	}
}
