package org.fxcal.configs;

import java.util.Map;

public class ExchangeRatesConfig extends FXPropertiesReader<String,Double> {

	private static ExchangeRatesConfig currencyPairWithRates;
	
	private static Object lock = new Object();
	
	
	private ExchangeRatesConfig() {
		
	}
	
	private ExchangeRatesConfig( Map<String,Double> fxprice) {
		this(null,fxprice);
	}
	
	private ExchangeRatesConfig(String resources,Map<String,Double> fxprice) {
		super(fxprice);
	}
	
	@Override
	protected String parseKey(Object key) {
		return String.valueOf(key).trim(); 
	}
	
	@Override
	protected Double parseValue(Object value) {
		return Double.parseDouble(String.valueOf(value).trim()); 
	}
	
	public static ExchangeRatesConfig getInstance(Map<String,Double> fxprice) {
		if (currencyPairWithRates != null) {
			return currencyPairWithRates;
		} else {
			synchronized (lock) {
				if (currencyPairWithRates == null) {
					currencyPairWithRates = new ExchangeRatesConfig(fxprice);
				}
				return currencyPairWithRates;
			}
		}
	}

}
