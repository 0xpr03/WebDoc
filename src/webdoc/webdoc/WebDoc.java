﻿package webdoc.webdoc;

import javax.swing.UIManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import webdoc.gui.GUI;
import webdoc.gui.WDBConnect;
import webdoc.gui.WHomescreen;
import webdoc.gui.WSetupData;
import webdoc.lib.ConfigLib;
import webdoc.lib.DBEError;
import webdoc.lib.Database;
import webdoc.lib.Database.DBError;
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
	private static WHomescreen WHomescreen;
	
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
		
		loadMainwindow();
		//### Testing area, all components loaded
		
		//test();
		
		//###
		
		registerExitFunction();
		
	}
	
	private static void loadMainwindow() {
		WHomescreen = new WHomescreen();
		WHomescreen.run();
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
		Config.setValue("configFileName", CONFIG_FILE_NAME);
		Config.setValue("defaultConfigPath", DEFAULT_CONFIG_PATH);
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
			setup();
		}else{
			DBError dberr = Database.connect(true,false);
			logger.debug("Connect error {}",dberr);
			boolean showsetup = false;
			switch(dberr){
			case NOERROR:
				break;
			case NOCONNECTION:
				GUI.showErrorDialog("Es konnte keine Verbindung zum DB Server aufgebaut werden!", "Login Fehler");
				showsetup = true;
				break;
			case INVALID_LOGIN:
				GUI.showErrorDialog("Der Login auf den Datenbankserver ist fehlgeschalgen!", "Login Fehler");
				showsetup = true;
				break;
			default:
				GUI.showErrorDialog("Ein unerwarteter Fehler ist aufgetreten: "+dberr.getError(), "Login Fehler");
				break;
			}
			if(showsetup)
				setup();
			//TODO: show mainwindow
		}
		logger.exit();
	}
	
	private static void setup(){
		boolean runSetup = true;
		while(runSetup){
			new WDBConnect(Config.getStrValue("db"),Config.getStrValue("ip"),Config.getIntValue("port"),Config.getStrValue("user"),Config.getStrValue("password"), true);
			logger.debug("going to run the setup..");
			
			DBEError dbee = new dbTools().runDBSetup();
			switch(dbee.getError()){
			case NOERROR:
				Config.setValue("firstrun", false);
				ConfigLib cfg = new ConfigLib(Config.getStrValue("configFileName"), Config.getStrValue("defaultConfigPath"));
				cfg.loadConfig();
				cfg.writeConfig();
				runSetup = false;
				new WSetupData("User: "+Config.getStrValue("user")+"\nPassword: "+Config.getStrValue("password")+"\nIP: "+Config.getStrValue("ip")+"\nport: "+Config.getIntValue("port")+"\nDB: "+Config.getStrValue("db"));
				break;
			case OPERATION_FAILED:
				GUI.showErrorDialog("Einer der Befehle konnte nicht ausgeführt werden!\n"+dbee.getMsg(), "Fehler beim Setup");
				break;
			case NO_DB_OR_NO_PERM:
				GUI.showErrorDialog("Der Benutzer besitzt nicht genug Rechte auf die Datenbank!", "Fehler beim Setup");
				break;
			case NO_PERMISSIONS:
				GUI.showErrorDialog("Der angegebene Benutzer besitzt nicht genug Rechte!", "Fehler beim Setup");
				break;
			default:
				GUI.showErrorDialog("Ein unerwarteter Fehler ist aufgetreten: "+dbee.getError(), "Unerwarteter Setup Fehler");
				break;
			}
		}
	}
	
	@SuppressWarnings("unused")
	private static void test(){
		logger.entry();
		Database.connect(false,false);
		
		
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
