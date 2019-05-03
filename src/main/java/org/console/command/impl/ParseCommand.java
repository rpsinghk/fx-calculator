package org.console.command.impl;

import java.util.Optional;

import org.console.AbstractConsole;
import org.console.command.Command;
import org.console.input.parser.LineParser;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ParseCommand implements Command {
	
	@NonNull private final AbstractConsole console;

	public Optional<?> execute(String commandString) {
		LineParser parser = new LineParser(commandString,console);
		return Optional.of(parser.matches());
	}

	public String name() {
		return console.message("parse.command.name");
	}
}

