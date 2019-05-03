package org.fxcal.compute;

import java.util.Optional;

import lombok.Getter;
import lombok.Setter;


public class ResultData {
	
	@Getter
	@Setter
	private Object data;
	
	
	public double getDoubleValue(){
		return Double.valueOf(toString());
	}
	
	
	public String toString(){
		return data.toString();
	}
	
	public Optional<?> getOptional(){
		if( data instanceof Optional) {
			return (Optional<?>)data;
		}
		return Optional.of(data);
	}
		
	public void reset() {
		data = null;
	}

}
