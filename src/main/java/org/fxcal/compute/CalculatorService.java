package org.fxcal.compute;

import java.util.function.BiFunction;

import org.fx.conversion.ConversionUtils;
import org.fx.conversion.CrossType;
import org.fx.money.Conversion;
import org.fx.money.CurrencyUnit;
import org.fx.money.ExchangeRate;
import org.fx.money.Money;
import org.fxcal.context.AppContext;
import org.fxcal.data.lookup.CrossCurrencyLookup;
import org.fxcal.data.lookup.FractionDigitLookup;
import org.fxcal.data.lookup.RateLookup;

public interface CalculatorService extends CrossCurrencyLookup,RateLookup,FractionDigitLookup {
	
	BiFunction<CrossType, AbstractFXCalculator, Conversion> conversion = ConversionUtils::getCoversionType;
	
	public void retreieveExchangeRate(ExchangeRate exchangeRate);
	
	public AppContext getAppContext();
	
	public String calculateValueOfMoneyInTargetCurrency(Money baseMonetoryAmount, CurrencyUnit targetCurrency);
}
