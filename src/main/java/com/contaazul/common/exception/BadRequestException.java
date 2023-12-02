package com.contaazul.common.exception;

public class BadRequestException extends RuntimeException {

	public BadRequestException(String message) {
		this(message, null);
	}

	protected BadRequestException(String message, Throwable e) {
		super(message, e);
	}
}
