package org.fxcal.data.lookup;

import org.fx.money.ExchangeRate;
import org.fxcal.compute.ResultData;

public interface CrossCurrencyLookup {
	
	public void crossCurrencyLookup(ExchangeRate exchangeRate, ResultData result);

}
