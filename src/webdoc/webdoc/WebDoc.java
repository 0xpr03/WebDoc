package webdoc.webdoc;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import webdoc.gui.GUI;
import webdoc.lib.*;

/***
 * Main class
 * @author Aron
 *
 */
public class WebDoc {
	
	private static String sdf = "yyyy-MM-dd HH:mm:ss";
//	private static String log_file_name = "webdoc-client.log";
	private static String CONFIG_FILE_NAME = "config.yml";
	private static String DEFAULT_CONFIG_PATH = "/webdoc/files/config.yml";
	
	protected  static final Logger logger = LogManager.getLogger();
	private static String version = "0.1 alpha";
	protected  static HashMap<String,Object> settings = new HashMap<String,Object>();
	
	public static void main(String[] args){
		// bsp: nur bei log level info werden die strings zusammengefügt
		logger.info("Starting up {}", version);
		
		loadConfig();
		//Database.connect();
		
		registerExitFunction();
	}
	
	
	/***
	 * Loads the config file or defaults in case of a invalid / missing file
	 */
	private static void loadConfig(){
		Config config = new Config(CONFIG_FILE_NAME,DEFAULT_CONFIG_PATH);
		
		if(!config.loadConfig()){
			if(config.gotScannerException()){
				if( GUI.showErrorYesNoDialog("Die config Datei ist beschädigt, soll sie überschrieben werden ?", "Config Fehler") == 1){
					logger.fatal("Can't replace faulty config file.");
					System.exit(1);
					//TODO: registerExitFunction isn't called till now!
				}
			}
			config.loadDefaults();
		}
	}
	
	private static void registerExitFunction() {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				logger.entry();
				ShutDown();
			}
		});
	}
	
	private static void ShutDown(){
		logger.info("Shutting down");
		
		
	}
	
	/*private static void runTest(){
	logger.debug("Test");
	logger.warn("test");
	logger.error("test");
	
	Database.test();
	Database.add();
	Database.test();
	
	runCrap();
}*/
}
