package com.app.mutants.exceptions;

import org.springframework.http.HttpStatus;

public class MutantGenericException extends BaseException {

	private static final long serialVersionUID = 1L;

	public MutantGenericException() {
	}

	public MutantGenericException(String message, Throwable cause) {
		super(message, cause);
	}

	public MutantGenericException(String errorCode, String traceId) {
		super(errorCode, traceId, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
