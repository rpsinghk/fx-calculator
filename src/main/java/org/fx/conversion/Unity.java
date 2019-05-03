package org.fx.conversion;

import java.math.BigDecimal;
import java.util.Objects;

import org.fx.money.Conversion;
import org.fx.money.CurrencyUnit;
import org.fx.money.ExchangeRate;

public class Unity implements Conversion{
	
	public double rate(CurrencyUnit baseCurency, CurrencyUnit termCurrency) {
		Objects.requireNonNull(baseCurency, "base currency must be not null");
		Objects.requireNonNull(termCurrency, "term currency must be not null");
		double rate = 1;
		return rate;
	}

	
	public void rate(ExchangeRate exchangeRate) {
		exchangeRate.setRate(BigDecimal.valueOf(1));
	}
}
