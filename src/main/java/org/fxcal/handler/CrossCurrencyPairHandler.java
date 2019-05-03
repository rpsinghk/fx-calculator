package org.fxcal.handler;

import java.net.URL;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

import org.fx.conversion.CrossType;
import org.fx.money.ExchangeRate;
import org.fxcal.compute.ResultData;
import org.fxcal.configs.CrossViaMatrixConfig;
import org.fxcal.context.AppContext;
import org.fxcal.data.lookup.CrossCurrencyLookup;
import org.fxcal.data.lookup.CrossCurrencyValue;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CrossCurrencyPairHandler implements FXResourceHandler,CrossCurrencyLookup{
	
	
	@NonNull
	AppContext appContext;
	
	@Getter
	String name = "ccylookup";
	
	static CrossCurrencyValue  values;
	
	static AtomicBoolean cached = new AtomicBoolean(false);

	@Override
	public void load(URL url) {
		
		if(cached.get()) return;
		CrossViaMatrixConfig crossViaMatrixConfig = CrossViaMatrixConfig.getInstance();
		crossViaMatrixConfig.load(url);
		
		
		values = crossViaMatrixConfig.getMatrixData();
		cached.set(true);
		
	}

	@Override
	public void crossCurrencyLookup(ExchangeRate exchangeRate, ResultData result) {
		Objects.requireNonNull(values, "values");
		String baseCurrencyCode = exchangeRate.getBaseCurrency().getCurrencyCode();
		String termCurrencyCode = exchangeRate.getTermCurrency().getCurrencyCode();
		Optional _result = values.getXValue(baseCurrencyCode,termCurrencyCode);
		if(_result.isPresent()) {
			Object outValue = CrossType.from(_result.get().toString());
			if(CrossType.INVERTED.toString().equalsIgnoreCase(outValue.toString())){
				exchangeRate.setInverted(Boolean.TRUE);
			}
			_result = Optional.of(outValue);
		}
		result.setData(_result);
	}

}
