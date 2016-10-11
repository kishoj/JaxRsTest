package com.rest.test.dao;

import java.util.List;

import com.rest.test.domains.Test;

public interface TestDAO {
	List<Test> getAll();
	Test getById(Long testId);
	Test create(Test test);
	void update(Test test);
	void delete(Test test) throws Exception;
	void deleteById(Long testId) throws Exception;
}
