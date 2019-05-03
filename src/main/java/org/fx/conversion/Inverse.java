package org.fx.conversion;

import java.util.Objects;

import org.fx.money.CurrencyUnit;
import org.fx.money.ExchangeRate;

public class Inverse extends DirectFeed {
	
	public double rate(CurrencyUnit baseCurency, CurrencyUnit termCurrency) {
		Objects.requireNonNull(baseCurency, "base currency must be not null");
		Objects.requireNonNull(termCurrency, "term currency must be not null");
		double rate = 1/super.rate(baseCurency, termCurrency);
		return rate;
	}
	
	public void rate(ExchangeRate exchangeRate) {
		double rate = exchangeRate.getRate().doubleValue();
		double newRate = 1/rate;
		exchangeRate.setRate(Double.valueOf(newRate));
	}

}
