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

/***
 * Config loader, handler & parser class
 * @author Aron
 *
 */
public class Config {
	
	private File FILE;
	private Yaml yaml;
	private Logger logger = LogManager.getLogger();
	private String DEFAULT_PATH;
	private boolean scanner_exception = false;
	private Map<String, Object> config;
	private HashMap<String,Object> defaults = new HashMap<String, Object>();
	private int missing_entrys = 0;
	
	
	public Config(String file, String default_path){
		logger.debug("Initializing config");
		this.FILE = new File(System.getProperty("user.dir")+"/"+file);
		logger.debug("Config file: {}", FILE.getAbsolutePath());
		yaml = new Yaml();
		DEFAULT_PATH = default_path;
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
	@Deprecated
	public boolean writeConfig(){
		try {
			FileWriter writer = new FileWriter(FILE);
			yaml.dump(config, writer);
			writer.close();
			return true;
		}catch(IOException e){
			logger.error("Unable to write the config!", e);
		}
		return false;
	}
	
	
//	public boolean writeConfig(Map<String,String>){
//		
//		return true;
//	}
	
	/**
	 * return an string entry
	 * @param key value key
	 * @param map result set
	 * @return string or null
	 */
	private String getEntryStr(String key, Map<String,Object> map){
		if(map.containsKey(key))
			return String.valueOf(map.get(key));
		else{
			logger.warn("Missing entry {}",key);
			missing_entrys++;
			return String.valueOf(defaults.get(key));
		}
	}
	
	public HashMap<String, String> parseConfig(){
		HashMap<String, String> config_db = new HashMap<String, String>();

		loadDefaults();
		
		config_db.put("password", getEntryStr("password",config));
		config_db.put("user", getEntryStr("user",config));
		config_db.put("port", getEntryStr("port",config));
		config_db.put("ip", getEntryStr("ip",config));
		config_db.put("db", getEntryStr("db",config));
		
		config_db.put("firstrun", getEntryStr("firstrun",config));
		config_db.put("guistyle", getEntryStr("guistyle",config));
		
		return config_db;
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
			logger.fatal("Interal error!", e);
		}catch(IOException e){
			logger.fatal("Interal error!", e);
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
