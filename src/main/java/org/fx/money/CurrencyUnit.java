package org.fx.money;

import java.io.Serializable;
import java.util.Objects;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;


@Getter
public class CurrencyUnit implements Comparable<CurrencyUnit>,Serializable {
	

	private static final long serialVersionUID = -2441953766285721133L;

	@NonNull String currencyCode;
	
	@Setter Integer defaultFractionDigits;
	
	private CurrencyUnit(String _currencyCode) {
		this.currencyCode = _currencyCode;
	}
	

	public Integer getDefaultFractionDigits() {
		return defaultFractionDigits;
	}
	
	public static CurrencyUnit of(String currencyCode){
		Objects.requireNonNull(currencyCode, "currencyCode required");
		return new CurrencyUnit(currencyCode);
	}
	
	public String toString() {
		return currencyCode;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(currencyCode);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj instanceof CurrencyUnit) {
			CurrencyUnit other = (CurrencyUnit) obj;
			return Objects.equals(getCurrencyCode(), other.getCurrencyCode());
		}
		return false;
	}

	@Override
	public int compareTo(CurrencyUnit o) {
		Objects.requireNonNull(o);

		int compare = -1;
		if (currencyCode.equals(o.getCurrencyCode())) {
			compare = currencyCode.compareTo(o.getCurrencyCode());
		}

		return compare;
	}

}
