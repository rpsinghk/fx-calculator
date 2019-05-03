package org.fxcal.handlers;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.fx.money.CurrencyUnit;
import org.fx.money.ExchangeRate;
import org.fxcal.handler.FractionDigitsHandler;
import org.junit.BeforeClass;
import org.junit.Test;


public class FractionDigitsHandlerTest {
	
	static FractionDigitsHandler fractionDigitsHandler = new FractionDigitsHandler(null);
	static Map<String,Integer> crossViaMatrixConfigRes;
	
	@BeforeClass
	public static void setup(){
		fractionDigitsHandler.load(Thread.currentThread().getContextClassLoader().getResource("fractiondigits.properties"));
	}
	
	@Test
	public void defaultFractionDigits() {
		CurrencyUnit AUD =  CurrencyUnit.of("AUD");
		CurrencyUnit USD =  CurrencyUnit.of("USD");
		CurrencyUnit CAD =  CurrencyUnit.of("CAD");
		CurrencyUnit CNY =  CurrencyUnit.of("CNY");
		CurrencyUnit DKK =  CurrencyUnit.of("DKK");
		CurrencyUnit EUR =  CurrencyUnit.of("EUR");
		CurrencyUnit GBP =  CurrencyUnit.of("GBP");
		CurrencyUnit JPY =  CurrencyUnit.of("JPY");

		
		
		ExchangeRate   exchangeRate1  = new ExchangeRate(CNY,USD);
		fractionDigitsHandler.fractionDigitLookup(exchangeRate1);
		assertEquals(2, CNY.getDefaultFractionDigits(),2 - CNY.getDefaultFractionDigits());
		assertEquals(2, USD.getDefaultFractionDigits(),2 - USD.getDefaultFractionDigits());
		
		ExchangeRate   exchangeRate2  = new ExchangeRate(CAD,EUR);
		fractionDigitsHandler.fractionDigitLookup(exchangeRate2);
		assertEquals(2, CAD.getDefaultFractionDigits(),2 - CAD.getDefaultFractionDigits());
		assertEquals(2, EUR.getDefaultFractionDigits(),2 - EUR.getDefaultFractionDigits());
		
		ExchangeRate   exchangeRate3  = new ExchangeRate(AUD,GBP);
		fractionDigitsHandler.fractionDigitLookup(exchangeRate3);
		assertEquals(2, AUD.getDefaultFractionDigits(),2 - AUD.getDefaultFractionDigits());
		assertEquals(2, GBP.getDefaultFractionDigits(),2 - GBP.getDefaultFractionDigits());
		
		ExchangeRate   exchangeRate4  = new ExchangeRate(JPY,DKK);
		fractionDigitsHandler.fractionDigitLookup(exchangeRate4);
		assertEquals(2, JPY.getDefaultFractionDigits(),2 - JPY.getDefaultFractionDigits());
		assertEquals(2, DKK.getDefaultFractionDigits(),2 - DKK.getDefaultFractionDigits());
	

	}
	
	
	@Test
	public void defaultFractionDigitsforInvalidCurrency() {

		CurrencyUnit NOK =  CurrencyUnit.of("NOK");
		CurrencyUnit NZD =  CurrencyUnit.of("NZD");
		
		
		ExchangeRate   exchangeRateforInvalidCurrency  = new ExchangeRate(NOK,NZD);
		fractionDigitsHandler.fractionDigitLookup(exchangeRateforInvalidCurrency);
		assertEquals(2, NOK.getDefaultFractionDigits(),2 - NOK.getDefaultFractionDigits());
		assertEquals(2, NZD.getDefaultFractionDigits(),2 - NZD.getDefaultFractionDigits());
	

	}

}
