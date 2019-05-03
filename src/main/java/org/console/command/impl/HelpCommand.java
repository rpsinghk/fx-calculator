package org.console.command.impl;

import java.util.Optional;

import org.console.AbstractConsole;
import org.console.command.Command;

import lombok.AllArgsConstructor;


@AllArgsConstructor
public class HelpCommand  implements Command{
	private final AbstractConsole abstractConsole;

	public Optional<?> execute(String commandString) {
		//System.out.println(abstractConsole.message("help.command.description"));
		HelpCommand.pirintHelpMessage1();
		
		return Optional.of(Boolean.FALSE);
	}
	
	
	//keep it simple
	public static void pirintHelpMessage1() {
		StringBuilder sb = new StringBuilder();
		sb.append("This is REPL mode.").append("\n");
		sb.append("Enter the conversion query to continue.").append("\n");
		sb.append("Type 'exit' to end.").append("\n").append("\n");

		sb.append("eg.").append("\n");
		sb.append("1. AUD 100.00 in USD").append("\n");
		sb.append("2. AUD 100.00 in AUD").append("\n");
		sb.append("3. AUD 100.00 in DKK").append("\n");
		sb.append("4. JPY 100 in USD").append("\n").append("\n");

		sb.append("To run in command line mode, pass the query").append("\n");
		sb.append("as an argument. Only one query at a time.").append("\n").append("\n");

		sb.append("eg.").append("\n");
		sb.append("application.jar   AUD 100.00 in USD").append("\n");
		System.out.println(sb.toString());
	}

	
	public static void pirintHelpMessage2() {
		StringBuilder sb = new StringBuilder();
		sb.append("To run this application, pass the query").append("\n");
		sb.append("as an argument. Only one query at a time.").append("\n").append("\n");

		sb.append("eg.").append("\n");
		sb.append("<<application  jar>>   AUD 100.00 in USD").append("\n").append("\n");
		sb.append("To run this in REPL mode. set 'REPL' enviroment variable to 'true'.").append("\n");
		System.out.println(sb.toString());
	}
		
	public String name() {
		return abstractConsole.message("help.command.name");
	}
}
