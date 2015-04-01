package webdoc.webdoc;

import javax.swing.UIManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import webdoc.gui.GUI;
import webdoc.gui.WDBConnect;
import webdoc.gui.WProgress;
import webdoc.lib.ConfigLib;
import webdoc.lib.Database;
import webdoc.lib.dbTools;

/**
 * WebDoc Main Class
 * @author "Aron Heinecke"
 *
 */
public class WebDoc {
	
//	private static String log_file_name = "webdoc-client.log";
	private static final String CONFIG_FILE_NAME = "config.yml";
	private static final String DEFAULT_CONFIG_PATH = "/webdoc/files/config.yml";
	
	private  static final Logger logger = LogManager.getLogger();
	private static final String VERSION = "0.2 alpha";
	
	public static void main(String[] args){
		// bsp: nur bei log level info werden die strings zusammengefügt
		logger.info("Starting up {}", VERSION);
		
		loadConfig();
		
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Error setting look and feel \n{}",e);
		}
		
		//startup init
		startup();
		
		
		//### Testing area, all components loaded
		
		//test();
		
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
		
		Config.setValue("tablesql", "/webdoc/files/tables.sql");
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
			boolean runSetup = true;
			while(runSetup){
				new WDBConnect(Config.getStrValue("db"),Config.getStrValue("ip"),Config.getIntValue("port"),Config.getStrValue("user"),Config.getStrValue("password"), true);
				logger.debug("going to runt he setup..");
				
				switch(new dbTools().runDBSetup()){
				case EXTERNAL_ERROR:
					break;
				case INVALID_LOGIN:
					break;
				case NOCONNECTION:
					break;
				case NOERROR:
					runSetup = false;
					break;
				case NO_DB_OR_NO_PERM:
					break;
				case NO_DB_SELECTED:
					break;
				case UNDEFINED_ERROR:
					break;
				case NO_PERMISSIONS:
					GUI.showErrorDialog("Der angegebene Benutzer besitzt nicht genug Rechte!", "Fehler beim Setup");
					break;
				default:
					break;
				}
				runSetup = false;
			}
		}else{
			Database.connect(true);
			//TODO: show mainwindow
		}
		logger.exit();
	}
	
	@SuppressWarnings("unused")
	private static void test(){
		logger.entry();
		Database.connect(false);
		
		
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
