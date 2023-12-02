package com.contaazul.common.exception;

public class NotFoundException extends RuntimeException {

	public NotFoundException(String entity) {
		this(entity, null);
	}

	protected NotFoundException(String entity, Throwable e) {
		super(entity + "not found", e);
	}
}
