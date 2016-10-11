package com.rest.test.exceptions;

public class CustomException extends Exception {
	private static final long serialVersionUID = 2320364790461013706L;

	private Exception exception;

	private String errorMessage;

	private ExceptionType type;
	
	public CustomException(String errorMessage, Exception exception, ExceptionType type) {
		this.errorMessage = errorMessage;
		this.exception = exception;
		this.type = type;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}
	
	public ExceptionType getType() {
		return type;
	}

	public void setType(ExceptionType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "CustomException: [exception=" + exception + ", errorMessage=" + errorMessage + ", type=" + type + "]";
	}	
}
