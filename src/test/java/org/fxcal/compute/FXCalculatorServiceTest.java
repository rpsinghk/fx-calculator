package org.fxcal.compute;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.fx.money.CurrencyUnit;
import org.fx.money.Money;
import org.junit.Test;

public class FXCalculatorServiceTest {

	@Test
	public void calculateValueOfMoneyInTargetCurrency() {
		FXCalculatorService fxCalculator = new FXCalculatorService();
		Money amountToConvert = null;
		String actual = null;
		
		
		//AUD 100.00 in AUD
		amountToConvert = Money.of(BigDecimal.valueOf(100.00), CurrencyUnit.of("AUD"));
		actual = fxCalculator.calculateValueOfMoneyInTargetCurrency(amountToConvert, CurrencyUnit.of("AUD"));
		assertEquals("AUD 100.00 = AUD 100.00", actual);

		
		//AUD 100.00 in DKK
		amountToConvert = Money.of(BigDecimal.valueOf(100.00), CurrencyUnit.of("AUD"));
		actual = fxCalculator.calculateValueOfMoneyInTargetCurrency(amountToConvert, CurrencyUnit.of("DKK"));
		assertEquals("AUD 100.00 = DKK 505.76", actual);
		
		
		//JPY 100 in USD
		amountToConvert = Money.of(BigDecimal.valueOf(100.00), CurrencyUnit.of("JPY"));
		actual = fxCalculator.calculateValueOfMoneyInTargetCurrency(amountToConvert, CurrencyUnit.of("USD"));
		assertEquals("JPY 100 = USD 0.83", actual);

		
	}

}
