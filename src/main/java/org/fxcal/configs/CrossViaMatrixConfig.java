package org.fxcal.configs;

import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

import org.fx.money.ExchangeRate;
import org.fxcal.compute.ResultData;
import org.fxcal.data.lookup.CrossCurrencyLookup;
import org.fxcal.data.lookup.CrossCurrencyMatrixData;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class CrossViaMatrixConfig implements CrossCurrencyLookup{

	private static final AtomicBoolean loaded = new AtomicBoolean(false);
	
	private static CrossViaMatrixConfig crossViaMatrixConfig;
	
	private transient CrossCurrencyMatrixData crossCurrencyMatrixData;
	
	public CrossCurrencyMatrixData getMatrixData() {
		return crossCurrencyMatrixData;
	}
	
	
	public void load(URL url){
		synchronized (loaded) {
			if(loaded.get()) return;
			if(Objects.nonNull(crossCurrencyMatrixData)) {
				crossCurrencyMatrixData.clear();	
			}
			try(InputStream is  = url.openStream()){
				Properties p = new Properties();
				p.load(is);
				p.forEach((key,value) -> {
					try {
						parse(String.valueOf(key),String.valueOf(value));
					}catch(Exception e) {
						log.error(String.format("Removing bad data : '%s' - '%s'", key,value),e);		
					}
				});
				loaded.set(true);
			} catch (Exception e) {
				log.error(String.format("Error reading ccy matrix data"),e);
			}			
		}
	}

	
	void parse(String key, String value) {
		if(Objects.isNull(crossCurrencyMatrixData)) {
			crossCurrencyMatrixData = new CrossCurrencyMatrixData();
		}
		if(key.equalsIgnoreCase("/") && value != null && value.length() > 0) {
			List<String> tm = crossCurrencyMatrixData.getTermValues();
			Arrays.stream(value.trim().split(",")).forEach( base ->{
				if(Objects.nonNull(base) && base.length() > 0) {
					tm.add(base);	
				}
			});
		}else {
			List<String> tm = crossCurrencyMatrixData.getBaseValues();
			tm.add(key);
			String []  values = value.trim().split(",");
			List<String[]> dataValues  = crossCurrencyMatrixData.getData();
			dataValues.add(values);
		}
	}

	
	public static CrossViaMatrixConfig getInstance() {
		if (Objects.nonNull(crossViaMatrixConfig)) {
			return crossViaMatrixConfig;
		} else {
			synchronized (loaded) {
				if (Objects.isNull(crossViaMatrixConfig)) {
					crossViaMatrixConfig = new CrossViaMatrixConfig();
				}
				return crossViaMatrixConfig;
			}
		}
	}

	@Override
	public void crossCurrencyLookup(ExchangeRate exchangeRate, ResultData result) {
		Objects.requireNonNull(exchangeRate, "exchange rate must be not null");
		Object _result = crossCurrencyMatrixData.getXValue(exchangeRate.getBaseCurrency().getCurrencyCode(),exchangeRate.getTermCurrency().getCurrencyCode());
		result.setData(_result);
	}

}
