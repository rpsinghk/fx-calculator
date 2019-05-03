package org.fxcal.utils;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class MessagesResource {
	
	
	private static final String MESSAGE_RESOURCE = "messages";
	private static ResourceBundle rb;


	private MessagesResource(Locale locale) {
			rb = ResourceBundle.getBundle(MESSAGE_RESOURCE, locale);
	}
	

	private static MessagesResource commandResource;
	private static Object lock = new Object();
	

	public static MessagesResource getInstance() {
		if (commandResource != null) {
			return commandResource;
		} else {
			synchronized (lock) {
				if (commandResource == null) {
					commandResource = new MessagesResource(Locale.getDefault());
				}
				return commandResource;
			}
		}
	}
	
	
	public void setLocale(Locale locale) {
		rb = ResourceBundle.getBundle(MESSAGE_RESOURCE, locale);
	}
	
	
	
	public String getMessage(String key, Object ... args) {
		 MessageFormat messageForm = new MessageFormat("");
	      String pattern = rb.getString(key);
	      messageForm.applyPattern(pattern);
	      return messageForm.format(args);
	}
}
