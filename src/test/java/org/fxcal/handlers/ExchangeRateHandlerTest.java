package org.fxcal.handlers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Map;

import org.fx.money.CurrencyUnit;
import org.fx.money.ExchangeRate;
import org.fxcal.handler.ExchangeRateHandler;
import org.junit.BeforeClass;
import org.junit.Test;


public class ExchangeRateHandlerTest {
	
	static ExchangeRateHandler exchangeRateHandler = new ExchangeRateHandler(null);
	static Map<String,Integer> exchangeRateHandlerRes;
	
	@BeforeClass
	public static void setup(){
		exchangeRateHandler.load(Thread.currentThread().getContextClassLoader().getResource("currencypairswithrate.properties"));
	}
	
	@Test
	public void rateLookup() {
		CurrencyUnit AUD =  CurrencyUnit.of("AUD");
		CurrencyUnit USD =  CurrencyUnit.of("USD");
		CurrencyUnit CAD =  CurrencyUnit.of("CAD");
		CurrencyUnit CNY =  CurrencyUnit.of("CNY");
		CurrencyUnit DKK =  CurrencyUnit.of("DKK");
		CurrencyUnit EUR =  CurrencyUnit.of("EUR");
		CurrencyUnit GBP =  CurrencyUnit.of("GBP");
		CurrencyUnit JPY =  CurrencyUnit.of("JPY");
		CurrencyUnit NOK =  CurrencyUnit.of("NOK");
		CurrencyUnit NZD =  CurrencyUnit.of("NZD");
		
		
		ExchangeRate AUDUSD = new ExchangeRate(AUD,USD);
		
		exchangeRateHandler.rateLookup(AUDUSD);
		assertEquals(0.8371, AUDUSD.getRate().doubleValue(),0.8371 - AUDUSD.getRate().doubleValue());

		ExchangeRate CADUSD = new ExchangeRate(CAD,USD);
		exchangeRateHandler.rateLookup(CADUSD);
		assertEquals(0.8711, CADUSD.getRate().doubleValue(),0.8711 - CADUSD.getRate().doubleValue());
		
		ExchangeRate EURDKK = new ExchangeRate(EUR,DKK);
		exchangeRateHandler.rateLookup(EURDKK);
		assertEquals(7.4405, EURDKK.getRate().doubleValue(),7.4405 - EURDKK.getRate().doubleValue());
		
		ExchangeRate GBPUSD = new ExchangeRate(GBP,USD);
		exchangeRateHandler.rateLookup(GBPUSD);
		assertEquals(1.5683, GBPUSD.getRate().doubleValue(),1.5683 - GBPUSD.getRate().doubleValue());

				
		
		
		
	}
	
	@Test
	public void negativeRateLookup() {
		CurrencyUnit AUD =  CurrencyUnit.of("AUD");
		CurrencyUnit USD =  CurrencyUnit.of("USD");
		CurrencyUnit CAD =  CurrencyUnit.of("CAD");
		CurrencyUnit CNY =  CurrencyUnit.of("CNY");
		CurrencyUnit DKK =  CurrencyUnit.of("DKK");
		CurrencyUnit EUR =  CurrencyUnit.of("EUR");
		CurrencyUnit GBP =  CurrencyUnit.of("GBP");
		CurrencyUnit JPY =  CurrencyUnit.of("JPY");
		CurrencyUnit NOK =  CurrencyUnit.of("NOK");
		CurrencyUnit NZD =  CurrencyUnit.of("NZD");
		
		
		
		ExchangeRate NZDNOK = new ExchangeRate(NZD,NOK);
		exchangeRateHandler.rateLookup(NZDNOK);
		assertNull(NZDNOK.getRate());
		
		ExchangeRate USDAUD = new ExchangeRate(USD,AUD);
		exchangeRateHandler.rateLookup(USDAUD);
		assertNull(USDAUD.getRate());
				
		
	}
		


}
