package org.fxcal.handler;

import java.net.URL;

public interface FXResourceHandler {

	public default String getName() {return "generic";};
	
	public void load(URL url);


}
