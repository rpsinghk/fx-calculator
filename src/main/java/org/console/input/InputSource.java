package org.console.input;

public interface InputSource<T> {
	
	public T read();
	
	
	public String handleArguments(String...parameter);


	default public boolean supportMultiLine() {
		return false;
	}

}
