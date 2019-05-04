package org.fxcal.context;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import org.fxcal.handler.FXResourceHandler;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AppContext extends ResourceLoader{
	
	final ConcurrentHashMap<String, Object> conc = new ConcurrentHashMap<String, Object>(16);

	
	@Setter @Getter
	private String displayName;
	
	
	public AppContext() {
		conc.put(RESOURCE_PREFIX, new HashMap<String, URL>());
		conc.put(FX_HANDLERS, new HashMap<String, Object>());
		conc.put(CALCULATOR_CONFIGURATION, new HashMap<String, Object>());
	}
	
	@SuppressWarnings("unchecked")
	public void init(){
		loadresources((Map<String, URL>) conc.get(RESOURCE_PREFIX));
		initializeResourceHandler();
	}

	private void initializeResourceHandler(){
		Map<String, URL>  resources = getResourcesURL();
		Map<String,Object> handlers = getHanlders();
		if(Objects.nonNull(handlers) && Objects.nonNull(resources)) {
			handlers.forEach((hname,value) -> {
				try {
					if(value instanceof String) { 
						FXResourceHandler o = (FXResourceHandler)getCurrentClassLoader().loadClass(hname).getDeclaredConstructor(AppContext.class).newInstance(this);
						o.load(resources.get(o.getName()));
						handlers.put(hname, o);						
					}
				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
					log.error("unable to load handlers",e);
				}
				
			});
		}
	}
	
	
	@SuppressWarnings("unchecked")
	private Map<String, URL> getResourcesURL() {
		return (Map<String, URL>)conc.get(RESOURCE_PREFIX);
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> getHanlders() {
		return (Map<String, Object>)conc.get(FX_HANDLERS);
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> getAppConfig() {
		return (Map<String, Object>)conc.get(CALCULATOR_CONFIGURATION);
	}
	

}
