package org.console.command.impl;

import static org.junit.Assert.*;

import org.junit.Test;

public class HelpCommandTest {

	@Test
	public void test() {
		HelpCommand.printShortMessage();
		HelpCommand.printREPLMessage();
	}

}
