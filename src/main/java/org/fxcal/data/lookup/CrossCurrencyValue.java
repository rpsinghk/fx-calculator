package org.fxcal.data.lookup;

import java.util.Optional;

public interface CrossCurrencyValue {
	public Optional<Object> getXValue(String baseCurrency,String termCurrency);
}
