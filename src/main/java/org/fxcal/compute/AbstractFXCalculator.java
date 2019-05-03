package org.fxcal.compute;

import org.fx.money.ExchangeRate;
import org.fxcal.context.AppContext;
import org.fxcal.data.lookup.CrossCurrencyLookup;
import org.fxcal.data.lookup.FractionDigitLookup;
import org.fxcal.data.lookup.RateLookup;

public abstract class AbstractFXCalculator implements CalculatorService{
	
	AppContext calculatorContext  = null;
	
	{
		initAppConext();
	}
	
	
	protected void initAppConext() {
		calculatorContext = createContext();
		calculatorContext.init();
	}
	
	protected AppContext createContext() {
		return new AppContext();
	}
	
	
	public AppContext getAppContext() {
		return calculatorContext;
	}
	

	@Override
	public void crossCurrencyLookup(ExchangeRate exchangeRate, ResultData result) {
		calculatorContext.getHanlders().forEach((x,y) ->{
			if(y instanceof CrossCurrencyLookup) {
				((CrossCurrencyLookup)y).crossCurrencyLookup(exchangeRate,result);
			}
		});
	}
	
	@Override
	public void rateLookup(ExchangeRate exchangeRate) {
		calculatorContext.getHanlders().forEach((x,y) ->{
			if(y instanceof RateLookup) {
				((RateLookup)y).rateLookup(exchangeRate);
			}
		});
	}

	
	@Override
	public void fractionDigitLookup(ExchangeRate exchangeRate) {
		calculatorContext.getHanlders().forEach((x,y) ->{
			if(y instanceof FractionDigitLookup) {
				((FractionDigitLookup)y).fractionDigitLookup(exchangeRate);
			}
		});
	}
	
	
}