package org.console.command.impl;

import java.util.Optional;

import org.console.AbstractConsole;
import org.console.command.Command;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class InvalidCommand implements Command {

	private final AbstractConsole console;
	
	public Optional<Boolean> execute(String commandString) {
		return Optional.of(Boolean.FALSE);
	}

	public String name() {
		return console.message("invalid.command.name");
	}

}
