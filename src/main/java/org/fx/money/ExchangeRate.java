package org.fx.money;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
public class ExchangeRate{
	
	@NonNull CurrencyUnit baseCurrency;
	
	@NonNull CurrencyUnit termCurrency;
	
	Double rate;
	
	@Setter
	Boolean inverted = Boolean.FALSE;
	
	public void setRate(Number _fraction) {
		if(_fraction != null)
			this.rate = _fraction.doubleValue();
	}
}
