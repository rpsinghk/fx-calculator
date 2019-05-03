package org.fxcal.handler;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.fx.money.ExchangeRate;
import org.fxcal.configs.ExchangeRatesConfig;
import org.fxcal.context.AppContext;
import org.fxcal.data.lookup.RateLookup;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExchangeRateHandler implements FXResourceHandler,RateLookup{
	
	AppContext appContext;
	
	public ExchangeRateHandler(AppContext applicationContext){
		
	}
	
	@Getter
	String name = "currencypairswithrate";
	
	static Map<String,Double> fxprice = new HashMap<String, Double>();

	@Override
	public void load(URL url) {
		ExchangeRatesConfig exchangeRatesConfiguration =  ExchangeRatesConfig.getInstance(fxprice);
		exchangeRatesConfiguration.load(url);
		
		
	}

	@Override
	public void rateLookup(ExchangeRate exchangeRate) {
		Double rate ;
		if(exchangeRate.getBaseCurrency().compareTo(exchangeRate.getTermCurrency()) == 0) {
			rate = 1.0;
		}else {
			StringBuffer combinedKey = new StringBuffer(6);
			if(exchangeRate.getInverted()) {
				combinedKey.append(exchangeRate.getTermCurrency().getCurrencyCode()).append(exchangeRate.getBaseCurrency().getCurrencyCode());
			}else {
				combinedKey.append(exchangeRate.getBaseCurrency().getCurrencyCode()).append(exchangeRate.getTermCurrency().getCurrencyCode());	
			}
			
			rate = fxprice.get(combinedKey.toString());
		}
		exchangeRate.setRate(rate);
	}

}
