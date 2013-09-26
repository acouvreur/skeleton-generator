package com.skeleton.generator.exception;

public class DataSourceNotFoundException extends Exception {

private static final long serialVersionUID = 1L;
	
	public DataSourceNotFoundException (String message) {
		super(message);
	}

	public DataSourceNotFoundException (String message, Throwable t) {
		super(message, t);
	}
}
