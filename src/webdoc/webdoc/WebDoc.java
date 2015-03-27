package webdoc.webdoc;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import webdoc.gui.GUI;
import webdoc.gui.WDBConnect;
import webdoc.gui.WProgress;
import webdoc.lib.*;

/**
 * WebDoc Main Class
 * @author "Aron Heinecke"
 *
 */
public class WebDoc {
	
//	private static String log_file_name = "webdoc-client.log";
	private static String CONFIG_FILE_NAME = "config.yml";
	private static String DEFAULT_CONFIG_PATH = "/webdoc/files/config.yml";
	
	private  static final Logger logger = LogManager.getLogger();
	private static String VERSION = "0.2 alpha";
	
	public static void main(String[] args){
		// bsp: nur bei log level info werden die strings zusammengefügt
		logger.info("Starting up {}", VERSION);
		
		loadConfig();
		
		//startup init
		startup();
		
		
		//### Testing area, all components loaded
		
		Database.test();
		
		//###
		
		registerExitFunction();
		
	}
	
	/***
	 * Loads the config file or defaults in case of a invalid / missing file
	 */
	private static void loadConfig(){
		ConfigLib configLib = new ConfigLib(CONFIG_FILE_NAME,DEFAULT_CONFIG_PATH);
		
		if(!configLib.loadConfig()){
			if(configLib.gotScannerException()){
				if( GUI.showErrorYesNoDialog("Die config Datei ist beschädigt, soll sie überschrieben werden ?", "Config Fehler") == 1){
					logger.fatal("Can't replace faulty config file.");
					System.exit(1);
					//TODO: registerExitFunction isn't called till now!
				}
			}
			configLib.writeDefaults(true);
			configLib.loadConfig();
		}
		configLib.parseConfig();
		
		if(configLib.getMissingEntrys()>0){
			if( GUI.showErrorYesNoDialog("Es fehlen "+configLib.getMissingEntrys()+" Einträge in der Config!"+
										"\nSoll eine Vergleichsdatei \"origin.yml\" erzeugt werden ?", "Config Fehler") == 0){
				configLib.writeDefaults(false);
			}
		}
	}
	
	private static void registerExitFunction() {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				ShutDown();
			}
		});
	}

	private static void startup(){
		logger.entry();
		if(Config.getBoolValue("firstrun")){
			
			WDBConnect dbc = new WDBConnect(Config.getStrValue("db"),Config.getStrValue("ip"),Config.getIntValue("port"),Config.getStrValue("user"),Config.getStrValue("password"), true);
			Database.connect();
			WProgress wpg = new WProgress();
			wpg.setMax(2);
			wpg.setVisible(true);
			wpg.setText("asd");
			dbTools dbt = new dbTools();
			dbt.sqlTblCreator("/webdoc/files/tables.sql", wpg);
			
			
			wpg.dispose();
		}else{
			Database.connect();
			//TODO: show mainwindow
		}
		logger.exit();
	}
	
	@SuppressWarnings("unused")
	private static void test(){
		logger.entry();
		ConfigLib configLib = new ConfigLib(CONFIG_FILE_NAME,DEFAULT_CONFIG_PATH);
		
		
		logger.exit();
	}
	
	private static void ShutDown(){
		logger.info("Shutting down");
		Database.disconnect();
		
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
