package com.rest.test.services;

import java.util.List;

import com.rest.test.domains.Test;

public interface TestService {
	List<Test> getAllTests() throws Exception;
	Test getTestById(Long testId) throws Exception;
	Test createTest(Test test) throws Exception;
	void updateTest(Test test) throws Exception;
	void deleteTestById(Long testId) throws Exception;
}
