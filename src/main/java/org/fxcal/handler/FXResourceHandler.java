package org.fxcal.handler;

import java.net.URL;

public interface FXResourceHandler {

	public String getName();
	
	public void load(URL url);
}
