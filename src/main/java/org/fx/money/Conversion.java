package org.fx.money;

public interface Conversion {

	default void rate(ExchangeRate exchangeRate) {
		return;
	}
	
	default void rate(ExchangeRate exchangeRate, CurrencyUnit crossCurrency) {
		return;
	}
}
