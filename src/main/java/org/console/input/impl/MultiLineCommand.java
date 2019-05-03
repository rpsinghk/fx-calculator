package org.console.input.impl;

import org.console.input.InputSource;

public class MultiLineCommand implements InputSource<String> {


	public MultiLineCommand() {

	}

	public String read() {
		throw new RuntimeException("Not Implemented yet.");
	}

	public void message(String _message) {
		System.out.println(_message);
	}

	@Override
	public String handleArguments(String... parameter) {
		throw new RuntimeException("Not Implemented yet.");
	}
}
