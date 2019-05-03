package org.console;

import java.util.Locale;
import java.util.Optional;

import org.console.command.Command;
import org.console.command.CommandsRepository;
import org.console.input.InputSource;
import org.fxcal.utils.MessagesResource;

public abstract class AbstractConsole {

	private static CommandsRepository commandRepository = new CommandsRepository();


	protected InputSource<String> inputStreamSource;

	public AbstractConsole(InputSource<String> _input, Locale locale) {
		this.inputStreamSource = _input;
		MessagesResource.getInstance().setLocale(locale);
	}

	public String message(String key,Object... args) {
		return MessagesResource.getInstance().getMessage(key, args);
	}
	
	public Optional<?> readNextInput() {
		Optional<?> value = Optional.empty();
		value = commandRepository.execute(read().trim());
		return value;
	}
	
	public Optional<?> parseInputCommandWithoutREPL(String inputText) {
		Optional<?> value = commandRepository.execute(inputText);
		return value;
	}


	public String read() {
		return inputStreamSource.read();
	}

	public void addCommand(Command command) {
		commandRepository.register(command.name(), command);
	}
	
}
