package org.fxcal.handler;

import org.fx.money.CurrencyUnit;
import org.fx.money.Money;
import org.fx.money.format.MonetoryAmountFormat;
import org.fxcal.utils.MessagesResource;

public class MonetoryMessages {
	
	private static MessagesResource msg = MessagesResource.getInstance();
	
	public static String exchangeRateNotFound(Money money,CurrencyUnit targetCurrency) {
		return msg.getMessage("parse.ratenotfound.message", money.getCurrency(),targetCurrency);
	}

	public static String success(Money monetoryInOldCurrency, Money moneyInNewCurrency) {
		MonetoryAmountFormat from 	= MonetoryAmountFormat.from(monetoryInOldCurrency);
		
		MonetoryAmountFormat to 	= MonetoryAmountFormat.from(moneyInNewCurrency);
		
		return msg.getMessage("parse.sucess.message", monetoryInOldCurrency.getCurrency(), from.format(), moneyInNewCurrency.getCurrency(), to.format());
		
	}
	
}
