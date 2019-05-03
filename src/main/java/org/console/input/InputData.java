package org.console.input;

import java.math.BigDecimal;

import org.fx.money.CurrencyUnit;

public interface InputData{
	
	CurrencyUnit baseCurrency();
	
	CurrencyUnit termCurrency();
	
	BigDecimal baseMonetoryAmount();
	
	String error();

}
