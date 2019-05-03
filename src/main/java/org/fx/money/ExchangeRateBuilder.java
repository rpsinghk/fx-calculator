package org.fx.money;

public class ExchangeRateBuilder {
	private CurrencyUnit baseCurrency;
	private CurrencyUnit termCurrency;
	
	public ExchangeRateBuilder(CurrencyUnit _baseCurrencyUnit) {
		this.baseCurrency = _baseCurrencyUnit;
	}

	public static ExchangeRateBuilder from(CurrencyUnit _baseCurrencyUnit) {
		return new ExchangeRateBuilder(_baseCurrencyUnit);
	}
	
	public ExchangeRateBuilder to(CurrencyUnit termCurrencyUnit) {
		this.termCurrency = termCurrencyUnit;
		return this;
	}
	
	public ExchangeRate build() {
		return new ExchangeRate(this.baseCurrency, this.termCurrency);
	}

}
