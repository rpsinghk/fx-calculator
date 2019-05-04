package org.fx.conversion;

import org.fx.money.Conversion;
import org.fx.money.CurrencyUnit;
import org.fx.money.ExchangeRate;
import org.fxcal.compute.CalculatorService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CrossViaCurrency implements Conversion {

	private CalculatorService fxCalls;
	
	public CrossViaCurrency(CalculatorService _cal) {
		this.fxCalls = _cal;
	}
	
	public double rate(final CurrencyUnit base, final CurrencyUnit term){
		return 0.0;
	}

	@Override
	public void rate(ExchangeRate exchangeRate, CurrencyUnit crossCurrency){
		
		log.trace("calculating exchange term :  {}  {} {} ",exchangeRate.getBaseCurrency().getCurrencyCode(),exchangeRate.getTermCurrency().getCurrencyCode(),crossCurrency.getCurrencyCode());
		Double exchangeRateTermValue = null;
		
		CurrencyUnit baseCurrency = exchangeRate.getBaseCurrency();
		CurrencyUnit termCurrency = exchangeRate.getTermCurrency();
		
		
		ExchangeRate exchangeRateWithCrossVia1 = new ExchangeRate(baseCurrency, crossCurrency);
		fxCalls.retreieveExchangeRate(exchangeRateWithCrossVia1);
		
		log.trace("1st exchange rate -  {}  {} {} ",exchangeRateWithCrossVia1.getBaseCurrency().getCurrencyCode(),exchangeRateWithCrossVia1.getTermCurrency().getCurrencyCode(),exchangeRateWithCrossVia1.getRate());
		
		ExchangeRate exchangeRateCrossVia2 = new ExchangeRate(crossCurrency, termCurrency);
		fxCalls.retreieveExchangeRate(exchangeRateCrossVia2);
		
		log.trace("2nd exchange rate :  {}  {} {} ",exchangeRate.getBaseCurrency().getCurrencyCode(),exchangeRate.getTermCurrency().getCurrencyCode(),exchangeRateCrossVia2.getRate());
		
		exchangeRateTermValue = exchangeRateWithCrossVia1.getRate() * exchangeRateCrossVia2.getRate();
		
		if(exchangeRateWithCrossVia1.getRate() == null || exchangeRateCrossVia2.getRate() == null) {
			exchangeRate.setRate(null);
		}else {
			exchangeRate.setRate(exchangeRateTermValue);
		}
		
		log.trace("ccy rate :  {}",exchangeRateTermValue.doubleValue());
		
		
	}

}
