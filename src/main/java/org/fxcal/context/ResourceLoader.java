package org.fxcal.context;

import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Properties;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class ResourceLoader {
	
	protected static final String CALCULATOR_RESOURCE = "calculator.properties";
	
	protected static final String CALCULATOR_CONFIGURATION = "calculator.configuration";
	
	protected static final String FX_HANDLERS = "fxhandlers";
	
	protected static final String RESOURCE_PREFIX = "resource.";
	
	protected final URL calculatorResourceURL;
	
	public ResourceLoader() {
		calculatorResourceURL = this.getResource(CALCULATOR_RESOURCE);
	}
	
	protected void loadresources(Map<String, URL> resources) {
		loadprimary(resources);
	}

	protected boolean exists(URI uri) {
		return Paths.get(uri).toFile().exists();
	}
	
	public ClassLoader getCurrentClassLoader(){
		return Thread.currentThread().getContextClassLoader();
	}
	
	public URL getResource(String name) {
		return this.getCurrentClassLoader().getResource(name);
	}
	
	public abstract Map<String,Object> getHanlders();
	
	public abstract Map<String,Object> getAppConfig();
	
	protected void loadprimary(Map<String, URL> resources){
		Map<String,Object> resourceHandlers = getHanlders();
		Map<String,Object> applicatoinConfiguration = getAppConfig();
		try(InputStream calculatorResourceInputStream = calculatorResourceURL.openStream()){
			Properties calculatorProperties = new Properties();
			calculatorProperties.load(calculatorResourceInputStream);
			calculatorProperties.forEach((k,y)->{
				String kv = String.valueOf(k);
				if(kv.contains(RESOURCE_PREFIX)){
					String trunc = kv.substring(9);
					URL url = this.getResource((String)y);
					try {
						if(exists(url.toURI())) {
							resources.put(trunc, url);
						}
					} catch (URISyntaxException e) {
						log.error("required resources missing.",e);
					}
				}else if(kv.contains(FX_HANDLERS)){
					resourceHandlers.put((String)y, k);
				}else {
					applicatoinConfiguration.put((String)k, kv);
				}
			});
		}catch(Exception e){
			log.error("unable to load calculator.properties",e);
		}
	}

}
