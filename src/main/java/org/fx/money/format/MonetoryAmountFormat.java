package org.fx.money.format;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.fx.money.CurrencyUnit;
import org.fx.money.Money;

public class MonetoryAmountFormat {

	static String pattern = "#,##0.00";
	
	private DecimalFormat decimalFormat;
	
	private BigDecimal amount;
	
	private CurrencyUnit currency;
	
	private MonetoryAmountFormat(BigDecimal number, CurrencyUnit _currency) {
		this.amount = number;
		this.currency = _currency;
	}


	public static MonetoryAmountFormat from(Money money) {
		return new MonetoryAmountFormat(money.getNumber(),money.getCurrency());
	}
	
	
	public static MonetoryAmountFormat from(BigDecimal amount,CurrencyUnit currency) {
		return new MonetoryAmountFormat(amount,currency);
	}
	
	public String format() {
		if(decimalFormat == null) {
			decimalFormat = new DecimalFormat(pattern);
		}
		decimalFormat.setMaximumFractionDigits(currency.getDefaultFractionDigits());
		decimalFormat.setMinimumFractionDigits(currency.getDefaultFractionDigits());
		return decimalFormat.format(amount);
		
	}
	
}
