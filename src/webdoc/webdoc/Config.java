/*******************************************************************************
 * Copyright (c) 2015 by Aron Heinecke & Jonathan Peper
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the WebDoc license.
 * Available inside this binary and at http://webdoc.proctet.net/license.txt
 *******************************************************************************/
package webdoc.webdoc;

import java.util.HashMap;

/**
 * Config holder for all instances
 * Local variables for the session inclusive
 * @author "Aron Heinecke"
 *
 */
public final class Config {
	private static HashMap<String,Object> SETTINGS = new HashMap<String,Object>();
	
	/**
	 * Get boolean value
	 * @param key string
	 * @return value boolean
	 */
	public static boolean getBoolValue(String key){
		return (boolean) SETTINGS.get(key);
	}
	
	/**
	 * Get string value
	 * @param key string
	 * @return value string
	 */
	public static String getStrValue(String key){
		return (String) SETTINGS.get(key);
	}
	
	/**
	 * Get long value
	 * @param key string
	 * @return value long
	 */
	public static long getLongValue(String key){
		return (long) SETTINGS.get(key);
	}
	
	/**
	 * Get value, undefined type
	 * @param key string
	 * @return value string
	 */
	public static Object getValue(String key){
		return SETTINGS.get(key);
	}
	
	/**
	 * Get int value
	 * @param key string
	 * @return value int
	 */
	public static int getIntValue(String key){
		return (int) SETTINGS.get(key);
	}
	
	/**
	 * Sets an value
	 * @param key string
	 * @param value object
	 */
	public synchronized static void setValue(String key, Object value){
		SETTINGS.put(key, value);
	}
	
	/**
	 * Sets a boolean value based on true/false string
	 * @param key string
	 * @param value true/false string
	 */
	public synchronized static void setBoolValue(String key, String value){
		SETTINGS.put(key, value.equalsIgnoreCase("true"));
	}
	
	/**
	 * Sets a int value based on string
	 * @param key string
	 * @param value number string
	 */
	public synchronized static void setIntValue(String key, String value){
		SETTINGS.put(key, Integer.valueOf(value));
	}
	
	/**
	 * Not for normal use !
	 * @return the complete config
	 */
	@Deprecated
	public static HashMap<String,Object> getMap(){
		return SETTINGS;
	}
	
}
