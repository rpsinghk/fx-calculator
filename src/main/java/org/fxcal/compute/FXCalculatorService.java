package org.fxcal.compute;

import java.math.BigDecimal;
import java.util.Optional;

import org.fx.conversion.CrossType;
import org.fx.money.CurrencyUnit;
import org.fx.money.ExchangeRate;
import org.fx.money.ExchangeRateBuilder;
import org.fx.money.Money;
import org.fxcal.handler.MonetoryMessages;

import lombok.extern.slf4j.Slf4j;


/*
 * Main class to calculate FX
 * 
 * */
@Slf4j
public class FXCalculatorService extends AbstractFXCalculator {

	public String calculateValueOfMoneyInTargetCurrency(Money monetoryInOldCurrency, CurrencyUnit targetCurrency) {
		String message = null;
		try {
			
			ExchangeRate exchangeRate = ExchangeRateBuilder.from(monetoryInOldCurrency.getCurrency()).to(targetCurrency).build();
			
			Money moneyInNewCurrency = calculateExchangeValueOFMoney(monetoryInOldCurrency,exchangeRate);
			
			if (moneyInNewCurrency == null) {
				
				message = MonetoryMessages.exchangeRateNotFound(monetoryInOldCurrency, targetCurrency);
			
			} else {
				
				message = MonetoryMessages.success(monetoryInOldCurrency, moneyInNewCurrency);
			}
		} catch (Exception e) {
			message = MonetoryMessages.exchangeRateNotFound(monetoryInOldCurrency, targetCurrency);
			log.error("error in calculation",e);
		}
		return message;
	}

	
	// Retrieves exchange rate fraction 
	private Money calculateExchangeValueOFMoney(Money monetoryValueOfOldCurrency, ExchangeRate exchangeRate) {
		log.trace("exchange rate in : {} {},{} {}, rate: {}",exchangeRate.getBaseCurrency().getCurrencyCode(), exchangeRate.getBaseCurrency().getDefaultFractionDigits(), exchangeRate.getTermCurrency().getCurrencyCode(), exchangeRate.getTermCurrency().getDefaultFractionDigits(), exchangeRate.getRate());
		Money monetoryValueOfNewCurrency = null;
		
		retreieveExchangeRate(exchangeRate);
		
		log.trace("exchange rate new : {} {},{} {}, rate: {}",exchangeRate.getBaseCurrency().getCurrencyCode(), exchangeRate.getBaseCurrency().getDefaultFractionDigits(), exchangeRate.getTermCurrency().getCurrencyCode(), exchangeRate.getTermCurrency().getDefaultFractionDigits(), exchangeRate.getRate());
		
		if (exchangeRate != null && exchangeRate.getRate() != null) {
			
			BigDecimal amountInNewCurrency = monetoryValueOfOldCurrency.multiply(exchangeRate.getRate());
			
			monetoryValueOfNewCurrency = Money.of(amountInNewCurrency, exchangeRate.getTermCurrency());
		}
		
		return monetoryValueOfNewCurrency;
	}

	public void retreieveExchangeRate(ExchangeRate exchangeRate) {
		final ResultData result = new ResultData();
		
		crossCurrencyLookup(exchangeRate, result);
		
		fractionDigitLookup(exchangeRate);
		
		
		log.trace("after ccy look & fraction : {} {},{} {}, rate: {}",exchangeRate.getBaseCurrency().getCurrencyCode(), exchangeRate.getBaseCurrency().getDefaultFractionDigits(), exchangeRate.getTermCurrency().getCurrencyCode(), exchangeRate.getTermCurrency().getDefaultFractionDigits(), exchangeRate.getRate());
		
		Optional<?> valueAtCross = result.getOptional();
		
		if (valueAtCross.isPresent()) {
			
			log.trace("ccy lookup : {}.",valueAtCross.get().toString());
		
			valueAtCross.filter(x -> x instanceof CrossType).ifPresent(x -> {
			
				rateLookup(exchangeRate,CrossType.INVERTED == x);
				
				conversion.apply((CrossType) x,this).rate(exchangeRate);
			
			});

			valueAtCross.filter(x -> x instanceof String).ifPresent(x -> {
				
				conversion.apply(CrossType.CROSSVIACURRENCY,this).rate(exchangeRate, CurrencyUnit.of((String) x));
			
			});
		}
	}
}
