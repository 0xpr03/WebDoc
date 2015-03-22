package webdoc.lib;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.yaml.snakeyaml.Yaml;

/***
 * Config loader, handler & parser class
 * @author Aron
 *
 */
public class Config {
	
	File FILE;
	Yaml yaml;
	Logger logger = LogManager.getLogger();
	String DEFAULT_PATH;
	
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
	public boolean loadConfig(){
		logger.entry();
		boolean passed = false;
		try{
			FileReader reader = new FileReader(FILE);
			yaml.load(reader);
			
			passed = true;
		}catch(FileNotFoundException e){
			logger.warn("Config file not found");
		}catch(Exception e){
			logger.error("Error loading the configuration", e);
		}
		return logger.exit(passed);
	}
	
	/***
	 * Load the default file
	 */
	public void loadDefaults(){
		logger.entry();
		try {
			InputStream in = getClass().getResourceAsStream(DEFAULT_PATH);
			OutputStream out = new FileOutputStream(FILE);
			byte[] buffer = new byte[1024];
			int len = in.read(buffer);
			while (len != -1) {
			    out.write(buffer, 0, len);
			    len = in.read(buffer);
			}
			out.flush();
			out.close();
			
			yaml.load(in);
		}catch(FileNotFoundException | NullPointerException e){
			logger.fatal("Interal error!", e);
		}catch(IOException e){
			logger.fatal("Interal error!", e);
		}
		logger.exit();
	}
}
