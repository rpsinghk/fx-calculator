package org.fxcal.handlers;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.fx.conversion.CrossType;
import org.fx.money.CurrencyUnit;
import org.fx.money.ExchangeRate;
import org.fxcal.compute.ResultData;
import org.fxcal.context.AppContext;
import org.fxcal.handler.CrossCurrencyPairHandler;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;


public class CrossCurrencyPairHandlerTest {
	
	static CrossCurrencyPairHandler crossCurrencyPairHandler;
	static AppContext appContext = new AppContext();
	
	@BeforeClass
	public static void setup(){
		crossCurrencyPairHandler = new CrossCurrencyPairHandler(appContext);
		crossCurrencyPairHandler.load(Thread.currentThread().getContextClassLoader().getResource("crossviacurrency.properties"));
	}

	@Test
	public void validCurrency() {
		CurrencyUnit AUD =  CurrencyUnit.of("AUD");
		CurrencyUnit USD =  CurrencyUnit.of("USD");
		CurrencyUnit CAD =  CurrencyUnit.of("CAD");
		CurrencyUnit CNY =  CurrencyUnit.of("CNY");
		CurrencyUnit DKK =  CurrencyUnit.of("DKK");
		CurrencyUnit EUR =  CurrencyUnit.of("EUR");
		CurrencyUnit GBP =  CurrencyUnit.of("GBP");
		CurrencyUnit JPY =  CurrencyUnit.of("JPY");
		CurrencyUnit CZK =  CurrencyUnit.of("CZK");
		CurrencyUnit NOK =  CurrencyUnit.of("NOK");
		CurrencyUnit NZD =  CurrencyUnit.of("NZD");
		
		
		ResultData resultData = new ResultData();
	
		
		Optional<CrossType> DIRECT = Optional.of(CrossType.DIRECT);
		Optional<CrossType> UNITY = Optional.of(CrossType.UNITY);
		Optional<CrossType> INVERTED = Optional.of(CrossType.INVERTED);
		Optional<String> O_USD = Optional.of("USD");
		Optional<String> O_EUR = Optional.of("EUR");
		
		
		
		crossCurrencyPairHandler.crossCurrencyLookup(new ExchangeRate(AUD,USD), resultData);
		assertEquals(DIRECT, resultData.getData());
		resultData.reset();

		crossCurrencyPairHandler.crossCurrencyLookup(new ExchangeRate(CAD,CAD), resultData);
		assertEquals(UNITY, resultData.getData());
		resultData.reset();
		
		crossCurrencyPairHandler.crossCurrencyLookup(new ExchangeRate(CNY,USD), resultData);
		assertEquals(DIRECT, resultData.getData());
		resultData.reset();
		
		crossCurrencyPairHandler.crossCurrencyLookup(new ExchangeRate(DKK,EUR), resultData);
		assertEquals(INVERTED, resultData.getData());
		resultData.reset();
		
		crossCurrencyPairHandler.crossCurrencyLookup(new ExchangeRate(EUR,GBP), resultData);
		assertEquals(O_USD, resultData.getData());
		resultData.reset();
		
		crossCurrencyPairHandler.crossCurrencyLookup(new ExchangeRate(JPY,USD), resultData);
		assertEquals(INVERTED, resultData.getData());
		resultData.reset();
		
		crossCurrencyPairHandler.crossCurrencyLookup(new ExchangeRate(NOK,CZK), resultData);
		assertEquals(O_EUR, resultData.getData());
		resultData.reset();
		
		crossCurrencyPairHandler.crossCurrencyLookup(new ExchangeRate(USD,EUR), resultData);
		assertEquals(INVERTED, resultData.getData());
		resultData.reset();
		
		crossCurrencyPairHandler.crossCurrencyLookup(new ExchangeRate(NZD,USD), resultData);
		assertEquals(DIRECT, resultData.getData());
		resultData.reset();

		crossCurrencyPairHandler.crossCurrencyLookup(new ExchangeRate(USD,AUD), resultData);
		assertEquals(INVERTED, resultData.getData());
		resultData.reset();

		
	}
	
	
	@Test
	public void negativeTests() {
		
		CurrencyUnit NO1 =  CurrencyUnit.of("NO1");
		CurrencyUnit NO2 =  CurrencyUnit.of("NO2");
		CurrencyUnit USD =  CurrencyUnit.of("USD");
		
		
		ResultData resultData = new ResultData();
		crossCurrencyPairHandler.crossCurrencyLookup(new ExchangeRate(NO1,USD), resultData);
		assertEquals(Optional.empty(),resultData.getData());
		resultData.reset();
		
		crossCurrencyPairHandler.crossCurrencyLookup(new ExchangeRate(NO2,USD), resultData);
		assertEquals(Optional.empty(),resultData.getData());
		resultData.reset();
		
		crossCurrencyPairHandler.crossCurrencyLookup(new ExchangeRate(NO1,NO2), resultData);
		assertEquals(Optional.empty(),resultData.getData());
		resultData.reset();
	}

}
