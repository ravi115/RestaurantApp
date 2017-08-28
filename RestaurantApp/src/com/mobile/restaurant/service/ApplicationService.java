/*
 * (c) copyright 2017. 
 */
package com.mobile.restaurant.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * 
 * @author raviranjan
 *
 */
@Path("/menu")
public class ApplicationService {

	/**
	 * 
	 * @param type
	 * @return
	 */
	@GET
	@Path("/{type}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getResult(@PathParam("type") String type) {

		return Response.ok().status(200).entity(type).build();
	}

	/**
	 * 
	 * @param type
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getResults(@QueryParam("type") String type) {
		return Response.ok().status(200).entity(type).build();
	}
}
