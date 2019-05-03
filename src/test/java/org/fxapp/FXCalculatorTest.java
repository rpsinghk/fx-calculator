package org.fxapp;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

public class FXCalculatorTest {

	
	
	static FXCalculator calculator; 
	
	@BeforeClass
	public static void setup(){
		
		calculator = new FXCalculator();
	}
	
	@Test
	public void exchangeMoneyWithCurrencyTest() {
		String input;
		String actual;
		
		
		input 		= "AUD 100.00 in USD";
		actual 		= calculator.start(input);
		assertEquals("AUD 100.00 = USD 83.71",actual);
		
		
		input 		= "AUD 100.00 in AUD";
		actual 		= calculator.start(input);
		assertEquals("AUD 100.00 = AUD 100.00",actual);

		
		input 		= "AUD 100.00 in DKK";
		actual 		= calculator.start(input);
		assertEquals("AUD 100.00 = DKK 505.76",actual);
		
		
		
		input 		= "JPY 100 in USD";
		actual 		= calculator.start(input);
		assertEquals("JPY 100 = USD 0.83",actual);
		
		
		input 		= "KRW 1000.00 in FJD";
		actual 		= calculator.start(input);
		assertEquals("Unable to find rate for KRW/FJD",actual);
	}


}

 

 

 


