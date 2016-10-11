package com.rest.test.resources;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.rest.test.domains.Test;
import com.rest.test.exceptions.CustomException;
import com.rest.test.exceptions.ExceptionType;
import com.rest.test.services.TestService;

@Path("/tests")
public class TestResource {
	@Inject
	private TestService testService;
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public List<Test> getAllTests() throws Exception {
		return testService.getAllTests();
	}
	
	@GET @Path("/{testId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Test getTestById(@PathParam("testId") Long testId) throws Exception {
		if (testId == null) {
			throw new CustomException("testId cannot be null or not a valid url", null, ExceptionType.NO_RESULT_FOUND);
		}
        Test result = null;
        try {
        	result = testService.getTestById(testId);
        } catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
        if (result == null) {
        	throw new CustomException("No record was found for id: " + testId, null, ExceptionType.NO_RESULT_FOUND);
        }
        return result;
    }
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Test createTest(@Valid Test test) throws Exception {
		if (test == null) {
			throw new CustomException("test should not be null", null, ExceptionType.REQUIRED_FIELD_MISSING);
		}
		return testService.createTest(test);
    }
	
	@PUT @Path("/{testId}")
	@Consumes(MediaType.APPLICATION_JSON)
    public void updateTest(@PathParam("testId") Long testId, @Valid Test test) throws Exception {
		if (testId == null) {
			throw new CustomException("testId should not be null", null, ExceptionType.REQUIRED_FIELD_MISSING);
		}
		if (test == null) {
			throw new CustomException("test should not be null", null, ExceptionType.REQUIRED_FIELD_MISSING);
		}
		testService.updateTest(test);
    }
	
	@DELETE @Path("/{testId}")
    public void deleteTest(@PathParam("testId") Long testId) throws Exception {
		if (testId == null) {
			throw new CustomException("testId should not be null", null, ExceptionType.REQUIRED_FIELD_MISSING);
		}
		testService.deleteTestById(testId);
    }
}
