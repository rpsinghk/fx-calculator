package org.console;

import java.util.Locale;

import org.console.input.InputSource;
import org.console.input.impl.MultiLineCommand;
import org.console.input.impl.SingleLineCommand;
import org.fxcal.compute.CalculatorService;

/*
 * Create console factory, uses singleton factory
 * */

public class ConsoleFactory {

	private static ConsoleFactory consoleFactory;
	private static Object lock = new Object();
	

	public static ConsoleFactory getInstance() {
		if (consoleFactory != null) {
			return consoleFactory;
		} else {
			synchronized (lock) {
				if (consoleFactory == null) {
					consoleFactory = new ConsoleFactory();
				}
				return consoleFactory;
			}
		}
	}

	public CalculatorConsole getSingleLineConsole(CalculatorService _fxcalculator){
		InputSource<String>  input = new SingleLineCommand();
		CalculatorConsole consoleCaclulator = new CalculatorConsole(input,Locale.getDefault());
		consoleCaclulator.initializeConsole(_fxcalculator);
		return consoleCaclulator;
	}
	
	public CalculatorConsole getMultiLineConsole(CalculatorService _fxcalculator){
		InputSource<String>  input = new MultiLineCommand();
		CalculatorConsole calculatorConsole = new CalculatorConsole(input,Locale.getDefault());
		calculatorConsole.initializeConsole(_fxcalculator);
		return calculatorConsole;
	}

}
