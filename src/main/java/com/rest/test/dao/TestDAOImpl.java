package com.rest.test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.rest.test.domains.Test;

@Repository("testDAO")
public class TestDAOImpl implements TestDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Test> getAll() {
		String sql = "SELECT * FROM test";
		List<Test> listTests = jdbcTemplate.query(sql, new RowMapper<Test>() {
			public Test mapRow(ResultSet rs, int rowNum) throws SQLException {
				return getTestAfterMap(rs);
			}
		});
		return listTests;
	}

	public Test getById(Long testId) {
		String sql = "SELECT * FROM test WHERE id = " + testId;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Test>() {
			public Test extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return getTestAfterMap(rs);
				}
				return null;
			}
		});
	}

	public Test create(Test test) {
		/*
		 * This update cannot be used to return id
		 * jdbcTemplate.update("INSERT INTO test (created_by, created_date, description) VALUES (?, ?, ?)", 
		 * test.getCreatedBy(), 
		 * test.getCreatedDate(), 
		 * test.getDescription());
		 */
		return insertAndReturnObject(test);
	}

	private Test insertAndReturnObject(final Test test) {
		KeyHolder kh = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement ps = 
						conn.prepareStatement("INSERT INTO test (id, created_by, created_date, description) VALUES (NULL, ?, ?, ?)",
								new String[]{"id"});
				ps.setString(1, test.getCreatedBy());
				ps.setTimestamp(2, test.getCreatedDate());
				ps.setString(3, test.getDescription());
				return ps;
			}
		}, kh);
		test.setId(kh.getKey().longValue());
		return test;
	}

	public void update(Test test) {
		jdbcTemplate.update("UPDATE test SET created_by=?, created_date=?, description=? WHERE id=?",
				test.getCreatedBy(), test.getCreatedDate(), test.getDescription(), test.getId());
	}

	public void deleteById(Long testId) throws Exception {
		Test result = getById(testId);
		if (result != null) {
			jdbcTemplate.update("DELETE FROM test WHERE id=?", testId);
		} else {
			throw new Exception("No record found with id: " + testId);
		}
	}

	public void delete(Test test) throws Exception {
		deleteById(test.getId());
	}

	private Test getTestAfterMap(ResultSet rs) throws SQLException {
		Test test = new Test();
		test.setId(rs.getLong("id"));
		test.setCreatedBy(rs.getString("created_by"));
		test.setCreatedDate(rs.getTimestamp("created_date"));
		test.setDescription(rs.getString("description"));
		return test;
	}
}
