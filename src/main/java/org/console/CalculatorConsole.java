package org.console;

import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

import org.console.command.impl.ExitCommand;
import org.console.command.impl.HelpCommand;
import org.console.command.impl.InvalidCommand;
import org.console.command.impl.ParseCommand;
import org.console.input.InputData;
import org.console.input.InputSource;
import org.fx.money.Money;
import org.fxcal.compute.CalculatorService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CalculatorConsole extends AbstractConsole{
	CalculatorService calculatorService;
	static boolean consoleInitialized = false;

	public CalculatorConsole(InputSource<String> inputStreamSource, Locale defaultLocale) {
		super(inputStreamSource,defaultLocale);
	}

	public String start(String...args) {
		if(!consoleInitialized) {
			throw new ConsoleNotInitializedException("Console must be initialized before it can be started");
		}
	
		if(!inputStreamSource.supportMultiLine()) {
			if(System.getenv("REPL") != null) {
				HelpCommand.printShortMessage();
				executeWithREPL();					
			}else {
				log.trace("executing in single line mode");
				if(null != args && args.length > 0) {
					String inputQueryString = inputStreamSource.handleArguments(args);
					return executeWithoutREPL(inputQueryString);
				}else {
					HelpCommand.printREPLMessage();
				}
			}
			
		}
		return null;
	}
	

	
	public void initializeConsole(CalculatorService _fxcalculator) {
		Objects.requireNonNull(_fxcalculator, "FX Caluculator is null");
		buildCommands();
		this.calculatorService = _fxcalculator;
		consoleInitialized = true;
	}
	
	private void executeWithREPL() {
		Optional<?> inputQueryString = null;
		while ((inputQueryString = readNextInput()).isPresent()) {
			log.trace("query(repl mode) : {}",inputQueryString);
			String outputForDisplay = beginFXcalculationProcess(inputQueryString);
			System.out.println(outputForDisplay);
		}
	}
	
	private String executeWithoutREPL(String inputQueryString) {
		log.trace("query : {}",inputQueryString);
		Optional<?> parsedInput = parseInputCommandWithoutREPL(inputQueryString);
		
		return beginFXcalculationProcess(parsedInput);
	}
	
	private String beginFXcalculationProcess(Optional<?> inputStreamSource) {
		String result = null;
		if(inputStreamSource.get() instanceof InputData) {
			InputData parsedDataObject = (InputData)inputStreamSource.get();
			if(parsedDataObject.error() != null) {
				result = parsedDataObject.error();
			}else {
				Money amountToConvert = Money.of(parsedDataObject.baseMonetoryAmount(),parsedDataObject.baseCurrency());
				log.trace("Amount : {}, baseCurrency : {}, termCurrency : {}",parsedDataObject.baseMonetoryAmount(),parsedDataObject.baseCurrency(),parsedDataObject.termCurrency());
				result = calculatorService.calculateValueOfMoneyInTargetCurrency(amountToConvert,parsedDataObject.termCurrency());
			}
		}
		return result;
	}
	
	
	private void buildCommands(){
		addCommand(new ExitCommand(this));
		addCommand(new ParseCommand(this));
		addCommand(new HelpCommand(this));
		addCommand(new InvalidCommand(this));
	}
}
