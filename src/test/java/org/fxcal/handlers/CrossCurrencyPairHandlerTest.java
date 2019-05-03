package org.fxcal.handlers;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.fx.money.CurrencyUnit;
import org.fx.money.ExchangeRate;
import org.fxcal.compute.ResultData;
import org.fxcal.configs.CrossViaMatrixConfig;
import org.fxcal.data.lookup.CrossCurrencyMatrixData;
import org.junit.BeforeClass;
import org.junit.Test;


public class CrossCurrencyPairHandlerTest {
	
	static CrossViaMatrixConfig crossViaMatrixConfig;
	static CrossCurrencyMatrixData crossCurrencyMatrixData;
	
	@BeforeClass
	public static void setup(){
		crossViaMatrixConfig = CrossViaMatrixConfig.getInstance();
		crossViaMatrixConfig.load(Thread.currentThread().getContextClassLoader().getResource("crossviacurrency.properties"));
		crossCurrencyMatrixData = crossViaMatrixConfig.getMatrixData();
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
		CurrencyUnit NOK =  CurrencyUnit.of("NOK");
		CurrencyUnit NZD =  CurrencyUnit.of("NZD");
		
		
		ResultData resultData = new ResultData();
	
		
		Optional DIRECT = Optional.of("D");
		Optional UNITY = Optional.of("1:1");
		Optional INVERTED = Optional.of("Inv");
		Optional O_USD = Optional.of("USD");
		Optional O_EUR = Optional.of("EUR");
		
		
		
		crossViaMatrixConfig.crossCurrencyLookup(new ExchangeRate(AUD,USD), resultData);
		assertEquals(DIRECT, resultData.getData());
		resultData.reset();

		crossViaMatrixConfig.crossCurrencyLookup(new ExchangeRate(CAD,CAD), resultData);
		assertEquals(UNITY, resultData.getData());
		resultData.reset();
		
		crossViaMatrixConfig.crossCurrencyLookup(new ExchangeRate(CNY,USD), resultData);
		assertEquals(DIRECT, resultData.getData());
		resultData.reset();
		
		crossViaMatrixConfig.crossCurrencyLookup(new ExchangeRate(DKK,EUR), resultData);
		assertEquals(INVERTED, resultData.getData());
		resultData.reset();
		
		crossViaMatrixConfig.crossCurrencyLookup(new ExchangeRate(EUR,GBP), resultData);
		assertEquals(O_USD, resultData.getData());
		resultData.reset();
		
		crossViaMatrixConfig.crossCurrencyLookup(new ExchangeRate(JPY,USD), resultData);
		assertEquals(INVERTED, resultData.getData());
		resultData.reset();
		
		crossViaMatrixConfig.crossCurrencyLookup(new ExchangeRate(NZD,NOK), resultData);
		assertEquals(O_USD, resultData.getData());
		resultData.reset();
		
		crossViaMatrixConfig.crossCurrencyLookup(new ExchangeRate(USD,EUR), resultData);
		assertEquals(INVERTED, resultData.getData());
		resultData.reset();
		
		crossViaMatrixConfig.crossCurrencyLookup(new ExchangeRate(USD,AUD), resultData);
		assertEquals(INVERTED, resultData.getData());
		resultData.reset();

		crossViaMatrixConfig.crossCurrencyLookup(new ExchangeRate(USD,AUD), resultData);
		assertEquals(INVERTED, resultData.getData());
		resultData.reset();

		
	}
	
	
	@Test
	public void negativeTests() {
		
		CurrencyUnit NO1 =  CurrencyUnit.of("NO1");
		CurrencyUnit NO2 =  CurrencyUnit.of("NO2");
		CurrencyUnit USD =  CurrencyUnit.of("USD");
		
		
		ResultData resultData = new ResultData();
		crossViaMatrixConfig.crossCurrencyLookup(new ExchangeRate(NO1,USD), resultData);
		assertEquals(Optional.empty(),resultData.getData());
		resultData.reset();
		
		crossViaMatrixConfig.crossCurrencyLookup(new ExchangeRate(NO2,USD), resultData);
		assertEquals(Optional.empty(),resultData.getData());
		resultData.reset();
		
		crossViaMatrixConfig.crossCurrencyLookup(new ExchangeRate(NO1,NO2), resultData);
		assertEquals(Optional.empty(),resultData.getData());
		resultData.reset();
	}

}
