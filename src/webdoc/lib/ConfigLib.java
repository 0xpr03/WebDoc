/*******************************************************************************
 * Copyright (c) 2015 by the WebDoc group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the WebDoc license.
 * Available inside this binary and at http://webdoc.proctet.net/license.txt
 *******************************************************************************/
package webdoc.lib;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.scanner.ScannerException;

import webdoc.webdoc.Config;

/**
 * Config loader, handler & parser class
 * @author "Aron Heinecke"
 *
 */
public class ConfigLib {
	
	private File FILE;
	private Yaml yaml = new Yaml();
	private Logger logger = LogManager.getLogger();
	private String DEFAULT_PATH;
	private boolean scanner_exception = false;
	private Map<String, Object> config;
	private HashMap<String,Object> defaults = new HashMap<String, Object>();
	private int missing_entrys = 0;
	
	public ConfigLib(String file, String default_path){
		logger.debug("Initializing config");
		logger.debug("OS: {}",System.getProperty("os.name"));
		File folder;
		if(System.getProperty("os.name").contains("Windows")){
			folder = new File(System.getenv("APPDATA")+"/.webdoc");
		}else{
			folder = new File(System.getProperty("user.home")+"/.webdoc");
		}
		folder.mkdirs();
		FILE = new File(folder.getAbsolutePath()+"/"+file);
		DEFAULT_PATH = default_path;
		logger.debug("Config file: {}", FILE.getAbsolutePath());
	}
	
	/***
	 * Trys to load the config file
	 * @return return true on success
	 */
	@SuppressWarnings("unchecked")
	public boolean loadConfig() {
		logger.entry();
		boolean passed = false;
		try{
			FileReader reader = new FileReader(FILE);
			config = (HashMap<String, Object>) yaml.load(reader);
			reader.close();
			
			logger.debug("Config: {}",config);
			
			passed = true;
		}catch(FileNotFoundException e){
			logger.warn("Config file not found");
		}catch(ScannerException e){
			scanner_exception = true;
			logger.error("Faulty config file!");
			logger.debug(e);
		}catch(Exception e){
			logger.error("Error loading the configuration", e);
		}
		return logger.exit(passed);
	}
	
	/**
	 * Writes the actual config back to the file
	 * @return success
	 */
	public boolean writeConfig(){
		try {
			loadDefaults();
			for (String key : defaults.keySet()){
				logger.debug("Writing: {}",key);
				config.put(key, Config.getValue(key));
			}
			logger.debug("Config: {}",config);
			FileWriter writer = new FileWriter(FILE);
			yaml.dump(config, writer);
			writer.close();
			return true;
		}catch(IOException e){
			logger.error("Unable to write the config!", e);
		}
		return false;
	}
	
	/**
	 * return an string entry
	 * @param key value key
	 * @param map result set
	 * @return string or null
	 */
	private Object getEntry(String key, Map<String,Object> map){
		if(map.containsKey(key))
			return map.get(key);
		else{
			logger.warn("Missing entry {}",key);
			missing_entrys++;
			return defaults.get(key);
		}
	}
	
	public void parseConfig(){
		loadDefaults();
		for(String key : defaults.keySet()){
			Config.setValue(key, getEntry(key,config));
		}
	}
	
	/***
	 * Loads the defaults into into the ram
	 */
	@SuppressWarnings("unchecked")
	private void loadDefaults(){
		logger.entry();
		try {
			InputStream in = getClass().getResourceAsStream(DEFAULT_PATH);
			defaults = (HashMap<String, Object>) yaml.load(in);
			in.close();
		}catch(FileNotFoundException | NullPointerException e){
			logger.fatal("Internal error!", e);
		}catch(IOException e){
			logger.fatal("Internal error!", e);
		}
		logger.exit();
	}
	
	/**
	 * Writes the default config to the external file
	 */
	public void writeDefaults(boolean to_origin){
		logger.entry();
		try {
			InputStream in = getClass().getResourceAsStream(DEFAULT_PATH);
			File f;
			if(to_origin)
				f = FILE;
			else
				f = new File(System.getProperty("user.dir")+"/origin_config.yml");
			
			OutputStream out = new FileOutputStream(f);
			byte[] buffer = new byte[1024];
			int len = in.read(buffer);
			while (len != -1) {
			    out.write(buffer, 0, len);
			    len = in.read(buffer);
			}
			out.flush();
			out.close();
			
			yaml.load(in);
			in.close();
		}catch(FileNotFoundException | NullPointerException e){
			logger.fatal("Interal error!", e);
		}catch(IOException e){
			logger.fatal("Interal error!", e);
		}
		logger.exit();
	}
	
	public boolean gotScannerException(){
		return this.scanner_exception;
	}
	public int getMissingEntrys(){
		return this.missing_entrys;
	}
}
