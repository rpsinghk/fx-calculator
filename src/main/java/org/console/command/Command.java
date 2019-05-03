package org.console.command;

import java.util.Optional;

public interface Command {

	
	String name();
	
	Optional<?> execute(String commandString);

}
