package com.contaazul.common.exception;

public class ConverterException  extends RuntimeException {

	public ConverterException(String message) {
		this(message, null);
	}

	protected ConverterException(String message, Throwable e) {
		super(message, e);
	}
}
