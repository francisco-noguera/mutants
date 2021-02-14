package com.app.mutants.exceptions;

import org.springframework.http.HttpStatus;

public class BaseException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String errorCode;
	private String traceId;
	private HttpStatus status;

	public BaseException() {
	}

	public BaseException(String message, Throwable cause) {
		super(message, cause);
	}

	public BaseException(String errorCode, String traceId, HttpStatus status) {
		this.errorCode = errorCode;
		this.traceId = traceId;
		this.status = status;
	}

	public String getTraceId() {
		return traceId;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public HttpStatus getStatus() {
		return status;
	}

}
