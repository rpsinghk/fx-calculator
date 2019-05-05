package org.fxcal.configs;

import java.util.Map;

import org.fxcal.handler.FXResourceHandler;

public class HandlerFactory {
		
	public static CrossViaMatrixConfig getCrossViaMatrixConfig() {
		return CrossViaMatrixConfig.getInstance();
	}
	
	public static FXResourceHandler getExchangeRatesConfig(Map<String,Double> fxprice) {
		return ExchangeRatesConfig.getInstance(fxprice);
	}
	
	public static FXResourceHandler getFractionDigitsConfig(Map<String,Integer> fxprice) {
		return FractionDigitsConfig.getInstance(fxprice);
	}

}
