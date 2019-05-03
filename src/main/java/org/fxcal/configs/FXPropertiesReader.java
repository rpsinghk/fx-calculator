package org.fxcal.configs;

import java.io.InputStream;
import java.net.URL;
import java.util.Map;
import java.util.Properties;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class FXPropertiesReader<K,T> {
	
	private Map<K,T> resourceMap;
	
	private static Object lock = new Object();
	
	public FXPropertiesReader() {
		
	}
	
	public FXPropertiesReader(Map<K, T> _resourceMap) {
		this.resourceMap  = _resourceMap;
	}

	public void load(URL url){
		synchronized (lock) {
			resourceMap.clear();
			try(InputStream is  = url.openStream()){
				Properties p = new Properties();
				p.load(is);
				p.forEach((key,value) -> {
					try {
						K parsedKey = parseKey(key);
						T parsedValue = parseValue(value);
						resourceMap.put(parsedKey,parsedValue);
						
					}catch(Exception e) {
						log.error(String.format("Removing bad data : '%s' - '%s'", key,value),e);		
					}
				});
			} catch (Exception e) {
				log.error(String.format("Error reading resources"),e);
			}			
		}
	}
	
	abstract K parseKey(Object key); 
	
	abstract T parseValue(Object value); 
}
