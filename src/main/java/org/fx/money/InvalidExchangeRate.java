package org.fx.money;

public class InvalidExchangeRate extends  RuntimeException{

	private static final long serialVersionUID = 5600974371873468521L;

	public InvalidExchangeRate() {
		super();
	}

	public InvalidExchangeRate(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InvalidExchangeRate(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidExchangeRate(String message) {
		super(message);

	}

	public InvalidExchangeRate(Throwable cause) {
		super(cause);
	}
	
}
