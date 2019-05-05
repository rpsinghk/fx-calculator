package org.fx.conversion;

import org.fx.money.Conversion;
import org.fxcal.compute.CalculatorService;

public class ConversionUtils {
	public static Conversion getCoversionType(CrossType crossType, CalculatorService fxCalculation) {
		switch(crossType){
		case DIRECT:
			return new DirectFeed();
		case INVERTED:
			return new Inverse();
		case UNITY:
			return new Unity();
		default:
			return new CrossViaCurrency(fxCalculation);
		}
	}
}
