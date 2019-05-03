package org.fxapp;

import org.console.CalculatorConsole;
import org.console.ConsoleFactory;
import org.fxcal.compute.CalculatorService;
import org.fxcal.compute.FXCalculatorService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class FXCalculator {

	
	public static void main(String[] args) {
		try {
		FXCalculator fxCalculatorApp = new FXCalculator();
		if(args == null || args.length < 1) args = null;
		String result = fxCalculatorApp.start(args);
		if(result != null)
		System.out.println(result);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public String start(String...args) {
		CalculatorService caculatorService = new FXCalculatorService();
		log.trace("initializing calculator service...");
		ConsoleFactory factory =  ConsoleFactory.getInstance();
		log.trace("initializing console service...");
		CalculatorConsole calculatorConsole  = factory.getSingleLineConsole(caculatorService);
		return calculatorConsole.start(args);
	}

}
