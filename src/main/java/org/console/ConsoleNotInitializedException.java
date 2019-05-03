package org.console;

public class ConsoleNotInitializedException extends RuntimeException {

	private static final long serialVersionUID = 791285276221774350L;

	public ConsoleNotInitializedException() {
		super();
	}

	public ConsoleNotInitializedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public ConsoleNotInitializedException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public ConsoleNotInitializedException(String message) {
		super(message);
		
	}

	public ConsoleNotInitializedException(Throwable cause) {
		super(cause);
		
	}

}
