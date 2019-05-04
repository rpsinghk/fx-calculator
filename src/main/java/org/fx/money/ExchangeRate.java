package org.fx.money;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class ExchangeRate{
	
	@NonNull CurrencyUnit baseCurrency;
	
	@NonNull CurrencyUnit termCurrency;
	
	Double rate;
	
	public void setRate(Number _fraction) {
		if(_fraction != null)
			this.rate = _fraction.doubleValue();
	}
}
