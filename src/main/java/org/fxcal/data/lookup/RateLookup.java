package org.fxcal.data.lookup;

import org.fx.money.ExchangeRate;

public interface RateLookup {
	
	public void rateLookup(ExchangeRate exchangeRate, Boolean invertLookup);

}
