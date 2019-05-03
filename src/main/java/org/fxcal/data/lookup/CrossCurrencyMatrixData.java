package org.fxcal.data.lookup;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.fx.conversion.CrossType;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j 
@Getter @Setter
 public class CrossCurrencyMatrixData implements CrossCurrencyValue{
	private List<String> baseValues  = new ArrayList<String>();
	private List<String> termValues  = new ArrayList<String>();
	private List<String[]> data =  new ArrayList<String[]>();
	
	
	public Optional<?> getXValue(String base,String term){
		String crossValuCode = null;

		int baseIndex = baseValues.indexOf(base);
		int termIndex = termValues.indexOf(term);
		if(baseIndex < 0 || termIndex < 0 || baseIndex > data.size() -1  || termIndex > data.get(baseIndex).length -1) {
			return Optional.empty();
		}
		
		crossValuCode = data.get(baseIndex)[termIndex];
		if(!(CrossType.from(crossValuCode) instanceof CrossType) && ( !baseValues.contains(crossValuCode) || !termValues.contains(crossValuCode) )) {
			log.error("Invalid code {} for base {} & term {} in CCY matrix", crossValuCode,base,term);
			return Optional.empty();
		}

		return Optional.of(crossValuCode);
	}
	
	public void clear() {
		baseValues.clear();
		termValues.clear();
		data.clear();
	}
		
}