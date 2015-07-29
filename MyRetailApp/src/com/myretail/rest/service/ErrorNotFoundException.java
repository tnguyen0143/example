package com.myretail.rest.service;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Custom Exception to handle when queries do not return any results
 */

public class ErrorNotFoundException extends WebApplicationException {
	public ErrorNotFoundException (String message)
	{
		super(Response.status(Response.Status.BAD_REQUEST)
					  .entity(message)
					  .type(MediaType.TEXT_PLAIN).build());
	}

}
