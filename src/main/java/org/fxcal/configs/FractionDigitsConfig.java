package org.fxcal.configs;

import java.util.Map;

public class FractionDigitsConfig extends FXPropertiesReader<String, Integer> {
	
	private static FractionDigitsConfig currencyPairWithRates;
	
	private static Object lock = new Object();

	private FractionDigitsConfig(Map<String,Integer> fxprice) {
		super(fxprice);
	}

	@Override
	protected String parseKey(Object key) {
		return String.valueOf(key).trim(); 
	}
	
	@Override
	protected Integer parseValue(Object value) {
		return Integer.parseInt(String.valueOf(value).trim()); 
	}
	
	public static FractionDigitsConfig getInstance(Map<String,Integer> fxprice) {
		if (currencyPairWithRates != null) {
			return currencyPairWithRates;
		} else {
			synchronized (lock) {
				if (currencyPairWithRates == null) {
					currencyPairWithRates = new FractionDigitsConfig(fxprice);
				}
				return currencyPairWithRates;
			}
		}
	}

}
