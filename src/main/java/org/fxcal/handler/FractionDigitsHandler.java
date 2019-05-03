package org.fxcal.handler;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.fx.money.CurrencyUnit;
import org.fx.money.ExchangeRate;
import org.fxcal.configs.FractionDigitsConfig;
import org.fxcal.context.AppContext;
import org.fxcal.data.lookup.FractionDigitLookup;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FractionDigitsHandler implements FXResourceHandler,FractionDigitLookup{
	
	AppContext appContext;
	
	public FractionDigitsHandler(AppContext appContext){
		
	}
	
	@Getter
	String name = "fractiondigits";
	
	private static Map<String,Integer> currenciesFractionData = new HashMap<String, Integer>();

	@Override
	public void load(URL url) {
		FractionDigitsConfig precisionConfig =  FractionDigitsConfig.getInstance(currenciesFractionData);
		precisionConfig.load(url);
	}

	@Override
	public void fractionDigitLookup(ExchangeRate exchangeRate) {
		CurrencyUnit baseCurrency =  exchangeRate.getBaseCurrency();
		Integer baseFractionDigits = currenciesFractionData.get(baseCurrency.getCurrencyCode());
		baseCurrency.setDefaultFractionDigits(baseFractionDigits);

		
		CurrencyUnit termCurrency =  exchangeRate.getTermCurrency();
		Integer termFractionDigits = currenciesFractionData.get(termCurrency.getCurrencyCode());
		termCurrency.setDefaultFractionDigits(termFractionDigits);

	}
}
