package com.rest.test.services;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.rest.test.dao.TestDAO;
import com.rest.test.domains.Test;

@Service("testService")
public class TestServiceImpl implements TestService{
	@Inject 
	private TestDAO testDAO;

	public List<Test> getAllTests() {
		return testDAO.getAll();
	}

	public Test getTestById(Long testId) {
		return testDAO.getById(testId);
	}

	public Test createTest(Test test) {
		return testDAO.create(test);
	}

	public void updateTest(Test test) {
		testDAO.update(test);
	}

	public void deleteTestById(Long testId) throws Exception {
		testDAO.deleteById(testId);
	}
	
	
}
