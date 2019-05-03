package org.console.input.parser;

import java.util.HashMap;

import org.fx.money.CurrencyUnit;

public abstract class CommandLineParser {
	
	private static HashMap<String, CurrencyUnit> currencyCache = new HashMap<String, CurrencyUnit>();
	
	public CurrencyUnit resolveCurrency(String code) {
		
		if(currencyCache.containsKey(code)) {
			return currencyCache.get(code);
		}

		CurrencyUnit currency = CurrencyUnit.of(code);
		currencyCache.put(code, currency);
		return currency;
	}


}
