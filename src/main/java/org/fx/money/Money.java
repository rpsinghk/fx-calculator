package org.fx.money;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Objects;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Money implements Comparable<Money>,Serializable {
	
	private static final long serialVersionUID = -1831460796456800633L;


	private CurrencyUnit currency;
	
	
	private BigDecimal number;
	
	private MathContext mc = new MathContext(5, RoundingMode.HALF_EVEN);
	

	private Money(BigDecimal number,CurrencyUnit currency) {
		Objects.requireNonNull(number);
		Objects.requireNonNull(currency);
		this.number = number;
		this.currency= currency;
	}
	
    public static Money zero(CurrencyUnit currency) {
        return new Money(BigDecimal.ZERO, currency);
    }
    
    public static Money of(BigDecimal number, String currencyCode) {
        return new Money(number, CurrencyUnit.of(currencyCode));
    }
    
    public static Money of(BigDecimal number, CurrencyUnit currency) {
        return new Money(number, currency);
    }
    
    public static Money of(Number number, String currencyCode) {
    	Objects.requireNonNull(number);
        if (Double.isNaN(number.doubleValue())) {
            throw new ArithmeticException("Invalid input Double.NaN.");
        } else if(Double.isInfinite(number.doubleValue())) {
            throw new ArithmeticException("Invalid input Double.xxx_INFINITY.");
        }
        return new Money(new BigDecimal(number.doubleValue()), CurrencyUnit.of(currencyCode));
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Money) {
            Money other = (Money) obj;
            return Objects.equals(getCurrency(), other.getCurrency()) &&
                    Objects.equals(getNumber(), other.getNumber());
        }
        return false;
    }

	@Override
	public int compareTo(Money money) {
	      Objects.requireNonNull(money);
	        int compare = getCurrency().getCurrencyCode().compareTo(money.getCurrency().getCurrencyCode());
	        if (compare == 0) {
	            compare = this.number.compareTo(money.getNumber());
	        }
	        return compare;
	}


	public BigDecimal multiply(BigDecimal rate) {
		BigDecimal bc = number.multiply(rate,mc).setScale(currency.getDefaultFractionDigits(),RoundingMode.UNNECESSARY);
		return bc;

	}

	public BigDecimal multiply(Double rate) {
		return  number.multiply(BigDecimal.valueOf(rate),mc);
	}

}
