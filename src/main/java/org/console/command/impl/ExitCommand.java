package org.console.command.impl;

import java.util.Optional;

import org.console.AbstractConsole;
import org.console.command.Command;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExitCommand  implements Command{

	private final AbstractConsole console;
	
	public Optional<?> execute(String commandString) {
		return Optional.empty();
	}
	public String name() {
		return console.message("exit.command.name");
	}
	
}
