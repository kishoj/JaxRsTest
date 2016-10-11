package com.rest.test.exceptions;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.glassfish.jersey.server.ParamException.QueryParamException;

@Provider
public class ExceptionHandler implements ExceptionMapper<Exception> {
	private static final Logger LOG = Logger.getLogger(ExceptionHandler.class.getName());
	
	@Context 
	UriInfo uriInfo;

	public Response toResponse(Exception ex) {
		LOG.log(Level.ERROR, ex.toString());
		return getResponseByException(ex);
	}

	private Response getResponseByException(Exception ex) {
		if (ex instanceof CustomException) {
			CustomException exception = (CustomException) ex;
			switch(exception.getType()) {
				case NO_RESULT_FOUND:
					return getExceptionResponse(Response.Status.NOT_FOUND, new ErrorResponse(Response.Status.NOT_FOUND.getStatusCode(), exception.getErrorMessage()));
				case REQUIRED_FIELD_MISSING:
				case INVALID_QUERY_PARAMETER:
					return getExceptionResponse(Response.Status.BAD_REQUEST, new ErrorResponse(Response.Status.BAD_REQUEST.getStatusCode(), exception.getErrorMessage()));
				case INTERNAL_SERVER_ERROR:
				default:
					return getExceptionResponse(Response.Status.INTERNAL_SERVER_ERROR, new ErrorResponse(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), exception.getErrorMessage()));
			}
		}
		if (ex instanceof NotFoundException) {
			return getExceptionResponse(Response.Status.NOT_FOUND, new ErrorResponse(Response.Status.NOT_FOUND.getStatusCode(), ex.getMessage()));
		}
		if (ex instanceof QueryParamException) {
			QueryParamException qpe = (QueryParamException) ex;
			return getExceptionResponse(Response.Status.BAD_REQUEST, new ErrorResponse(Response.Status.BAD_REQUEST.getStatusCode(), "Wrong value for query parameter '" + qpe.getParameterName() + 
					"' : "+ uriInfo.getQueryParameters().getFirst(qpe.getParameterName()) + ". " + (qpe.getCause() != null ? qpe.getCause().getMessage().replace("\"", "") : "")));
		} 
		if (ex instanceof WebApplicationException) {
			return getExceptionResponse(Response.Status.INTERNAL_SERVER_ERROR, new ErrorResponse(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), ex.getMessage()));
		}		
		return getExceptionResponse(Response.Status.BAD_REQUEST, new ErrorResponse(Response.Status.BAD_REQUEST.getStatusCode(), ex.getMessage()));
	}
	
	private Response getExceptionResponse(Status status, ErrorResponse errorDetail) {
		return Response.status(status)
				.entity(errorDetail)
				.type(MediaType.APPLICATION_JSON).build();
	}
}
