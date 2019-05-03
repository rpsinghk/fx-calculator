package org.console.input.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.console.input.InputSource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SingleLineCommand implements InputSource<String> {
	private BufferedReader con;
	
	public SingleLineCommand() {
		this.con  = new BufferedReader(new InputStreamReader(System.in));
	}

	public String read(){
		String line ="";
		try {
			System.out.print(":");
			line = con.readLine();
		}catch(IOException io) {
			log.error("Unable to create input stream", io);
			// throw uncheck excecption & catch in unhandled exception handler.
		}
	  	return line;
	}
	
	public void message(String _message) {
		System.out.println(_message);
	}

	@Override
	public String handleArguments(String... parameter) {
		return  Arrays.asList(parameter).stream().collect(Collectors.joining(" "));
	}
}
