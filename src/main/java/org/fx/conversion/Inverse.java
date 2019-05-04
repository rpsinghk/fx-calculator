package org.fx.conversion;

import java.util.Objects;

import org.fx.money.CurrencyUnit;
import org.fx.money.ExchangeRate;
import org.fx.money.VerifyNumber;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Inverse extends DirectFeed {
	
	public double rate(CurrencyUnit baseCurency, CurrencyUnit termCurrency) {
		Objects.requireNonNull(baseCurency, "base currency must be not null");
		Objects.requireNonNull(termCurrency, "term currency must be not null");
		double rate = 1/super.rate(baseCurency, termCurrency);
		return rate;
	}
	
	public void rate(ExchangeRate exchangeRate) {
		Objects.requireNonNull(exchangeRate);
		Objects.requireNonNull(exchangeRate.getRate(),"Exchange rate value can't be null");
		
		Double rate = exchangeRate.getRate();
		
		Double newRate = 1/rate;
		
		if(VerifyNumber.isInfinityAndNotNaN(newRate)) {
			newRate = new Double(0.0d);
		}
		log.trace("Inverse rate {} ",newRate);
		exchangeRate.setRate(newRate);
	}

}
