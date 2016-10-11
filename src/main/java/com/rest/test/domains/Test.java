package com.rest.test.domains;

import java.io.Serializable;
import java.sql.Timestamp;

public class Test implements Serializable {
	private static final long serialVersionUID = 6261652722789382585L;

	private Long id;
	private Timestamp createdDate;
	private String createdBy;
	private String description;
	
	public Test() { }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Test [id=" + id + ", createdDate=" + createdDate + ", createdBy=" + createdBy + ", description="
				+ description + "]";
	}	
}
