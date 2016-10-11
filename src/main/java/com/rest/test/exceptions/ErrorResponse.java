package com.rest.test.exceptions;

import java.io.Serializable;

public class ErrorResponse implements Serializable {
	private static final long serialVersionUID = 1020154448754309210L;
	
	private int errorCode;
	private String errorMessage;
	
	public ErrorResponse(int errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}	
}
