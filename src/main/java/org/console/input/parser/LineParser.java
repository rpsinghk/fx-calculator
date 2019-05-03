package org.console.input.parser;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.console.AbstractConsole;
import org.console.input.InputData;
import org.fx.money.CurrencyUnit;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class LineParser extends CommandLineParser {

	@Getter
	private static String regexPatternForFXQuery = "([A-Za-z]{3})(\\s)((\\d)+.(\\d)+)((\\s)+in(\\s)+)([A-Za-z]{3})";

	@NonNull
	private String inputFromCommandLine;

	@NonNull
	AbstractConsole console;

	InputData dataHolderForParsedValue;

	public InputData matches() {
		CurrencyUnit from = null;
		CurrencyUnit to = null;
		BigDecimal amount = null;
		Pattern pattern = Pattern.compile(regexPatternForFXQuery);
		Matcher matcher = pattern.matcher(inputFromCommandLine);
		String error = null;
		Boolean errorBoolean = false;

		if (matcher.matches()) {
			matcher.reset();
			while (matcher.find()) {
				from = resolveCurrency(matcher.group(1).toUpperCase());

				try {
					amount = BigDecimal.valueOf(Double.valueOf(matcher.group(3)));
				} catch (Exception bde) {
					errorBoolean = true;
					log.error(String.format("Amount parsing error for : %s", inputFromCommandLine), bde);
				}
				to = resolveCurrency(matcher.group(9).toUpperCase());
			}
			if (errorBoolean)
				error = console.message("parse.ratenotfound.message", from, to);
			dataHolderForParsedValue = new ConversionInputData(from, to, amount, error);
		} else {
			error = String.format("Invalid input provided '%s'", inputFromCommandLine);
			dataHolderForParsedValue = new ConversionInputData(from, to, amount, error);
		}
		return dataHolderForParsedValue;
	}

	public InputData getData() {
		return dataHolderForParsedValue;
	}

	@AllArgsConstructor
	final class ConversionInputData implements InputData {
		CurrencyUnit fromCurrency;
		CurrencyUnit toCurrency;
		BigDecimal monetoryAmountFromCurrency;

		@Getter
		@Setter
		String errorMessage;

		public ConversionInputData() {
		}

		public CurrencyUnit baseCurrency() {
			return fromCurrency;
		}

		public CurrencyUnit termCurrency() {
			return toCurrency;
		}

		public BigDecimal baseMonetoryAmount() {
			return monetoryAmountFromCurrency;
		}

		public String error() {
			return errorMessage;
		}
	}

}
