package org.fxcal.handler;

import java.net.URL;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

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
	
	static CrossCurrencyValue  crossCurrencyMatrixData;
	
	static AtomicBoolean cached = new AtomicBoolean(false);

	@Override
	public void load(URL url) {
		
		if(cached.get()) return;
		CrossViaMatrixConfig crossViaMatrixConfig = CrossViaMatrixConfig.getInstance();
		crossViaMatrixConfig.load(url);
		
		
		crossCurrencyMatrixData = crossViaMatrixConfig.getMatrixData();
		cached.set(true);
		
	}

	@Override
	public void crossCurrencyLookup(ExchangeRate exchangeRate, ResultData result) {
		Objects.requireNonNull(exchangeRate, "exchange rate must be not null");
		Objects.requireNonNull(crossCurrencyMatrixData, "matrix data must not be null");
		String baseCurrencyCode = exchangeRate.getBaseCurrency().getCurrencyCode();
		String termCurrencyCode = exchangeRate.getTermCurrency().getCurrencyCode();
		Optional<Object> _result = crossCurrencyMatrixData.getXValue(baseCurrencyCode,termCurrencyCode);
		result.setData(_result);
	}
	

}
