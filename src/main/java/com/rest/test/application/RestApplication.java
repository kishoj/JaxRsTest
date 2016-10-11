package com.rest.test.application;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/v1")
public class RestApplication extends ResourceConfig {
	public RestApplication() { 
		packages("com.rest.test.*");
	}
}
