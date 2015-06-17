/*******************************************************************************
 * Copyright (c) 2015 by the WebDoc group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the WebDoc license.
 * Available inside this binary and at http://webdoc.proctet.net/license.txt
 *******************************************************************************/
package webdoc.webdoc;

import java.sql.SQLException;

import javax.swing.UIManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import webdoc.gui.GUIFunctions;
import webdoc.gui.WDBConnect;
import webdoc.gui.WLicense;
import webdoc.gui.WLicenseInput;
import webdoc.gui.WSetupData;
import webdoc.lib.ConfigLib;
import webdoc.lib.DBEError;
import webdoc.lib.Database;
import webdoc.lib.Database.DBError;
import webdoc.lib.GUIManager;
import webdoc.lib.Verifier;
import webdoc.lib.Verifier.LicenseError;
import webdoc.lib.dbTools;

/**
 * WebDoc Main Class
 * @author "Aron Heinecke"
 */
public class WebDoc {
	private static final String CONFIG_FILE_NAME = "config.yml";
	private static final String DEFAULT_CONFIG_PATH = "/webdoc/files/config.yml";
	private static final String LICENSE_FILE_PATH = "/license.txt";
	
	private  static final Logger logger = LogManager.getLogger();
	private static final String VERSION = "0.4 alpha";
	
	public static void main(String[] args){
		logger.info("Starting up {}", VERSION);
		
		loadConfig();
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			logger.error("Error setting look and feel \n{}",e);
		}
		
		//startup init
		startup();
		
		loadMainwindow();
		//### Testing area, all components loaded
		
		//test();
		try {
			logger.debug(Database.getProcedures());
		} catch (SQLException e) {
			logger.debug(e);
		}
		
		//###
		
		registerExitFunction();
		
		while (true) {
			try {
				// Make sure that the Java VM don't quit this program.
				Thread.sleep(100);
			} catch (Exception e) {/* ignore */
			}
		}
	}
	
	private static void loadMainwindow() {
		GUIFunctions.initFunctions();
		GUIManager.crateHomescreen();
	}

	/***
	 * Loads the config file or defaults in case of a invalid / missing file
	 */
	private static void loadConfig(){
		ConfigLib configLib = new ConfigLib(CONFIG_FILE_NAME,DEFAULT_CONFIG_PATH);
		
		if(!configLib.loadConfig()){
			if(configLib.gotScannerException()){
				if( GUIManager.showErrorYesNoDialog("Die config Datei ist beschädigt, soll sie überschrieben werden ?", "Config Fehler") == 1){
					logger.fatal("Can't replace faulty config file.");
					System.exit(1);
				}
			}
			configLib.writeDefaults(true);
			configLib.loadConfig();
		}
		configLib.parseConfig();
		
		if(configLib.getMissingEntrys()>0){
			if( GUIManager.showErrorYesNoDialog("Es fehlen "+configLib.getMissingEntrys()+" Einträge in der Config!"+
										"\nSoll eine Vergleichsdatei \"origin.yml\" erzeugt werden ?", "Config Fehler") == 0){
				configLib.writeDefaults(false);
			}
		}
		
		Config.setValue("tablesql", "/webdoc/files/tables.sql");
		Config.setValue("proceduresql", "/webdoc/files/procedures.sql");
		Config.setValue("configFileName", CONFIG_FILE_NAME);
		Config.setValue("defaultConfigPath", DEFAULT_CONFIG_PATH);
		Config.setValue("version", VERSION);
		Config.setValue("licenseFilePath", LICENSE_FILE_PATH);
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
		Verifier verifier = new Verifier();
		final String lz = Config.getStrValue("licenseKey");
		if(Config.getBoolValue("firstrun")){
			new WLicense(true);
			setup();
		}else{
			DBError dberr = Database.connect(true,false);
			logger.debug("Connect error {}",dberr);
			boolean showsetup = false;
			switch(dberr){
			case NOERROR:
				break;
			case NOCONNECTION:
				GUIManager.showErrorDialog("Es konnte keine Verbindung zum DB Server aufgebaut werden!", "Login Fehler");
				showsetup = true;
				break;
			case INVALID_LOGIN:
				GUIManager.showErrorDialog("Der Login auf den Datenbankserver ist fehlgeschalgen!", "Login Fehler");
				showsetup = true;
				break;
			case NO_DB:
				GUIManager.showErrorDialog("Die Datenbank konnte nicht gefunden werden!", "Datenbank Fehler");
				showsetup = true;
				break;
			default:
				GUIManager.showErrorDialog("Ein unerwarteter Fehler ist aufgetreten: "+dberr.getError(), "Login Fehler");
				break;
			}
			if(dberr == DBError.NOERROR ) {
				DBError dbe = Database.initDBVars();
				if(dbe != DBError.NOERROR){
					GUIManager.showErrorDialog("Fehler beim auslesen der DB Variablen!\n"+dbe.getError(), "DB init Fehler");
					showsetup = true;
				}
			}
			if(showsetup)
				setup();
		}
		
		LicenseError le = LicenseError.NO_KEY;
		
		if(!lz.equals("")){
			try {
				le = verifier.checkOfflineLicense(lz);
				if(le != LicenseError.VALID){
					logger.info("offline license validation: {}",le);
					le = verifier.checkLicense(lz);
					
					if(le == LicenseError.VALID){
						logger.debug("valid online license");
						verifier.insertOfflLZ(lz);
					}else{
						logger.info("online license validation: {}",le);
					}
				}else{
					Thread t = new Thread(new Runnable() { // run refresh of offline LZ
						public void run() {
							logger.debug("Valid offline license, lazy refresh..");
							Verifier vef = new Verifier();
							LicenseError le = vef.checkLicense(lz);
							if(le == LicenseError.VALID || le == LicenseError.EXPIRED){
								vef.insertOfflLZ(lz);
							}
						}
					});
					t.start();
				}
			} catch (Exception e) {
				logger.error("validation error {}",e);
				le = LicenseError.VALIDATION_ERROR;
			}
		}
		
		if(le != LicenseError.VALID){
			le = verifier.checkOfflineLicense(lz);
			logger.debug("LZE: {}",le);
			if(le != LicenseError.VALID){
				new WLicenseInput(true,lz, le);
				if(verifier.checkOfflineLicense(Config.getStrValue("licenseKey")) == LicenseError.VALID){ // catch dialog fails..
					saveConfig();
				}else{
					System.exit(1);
				}
			}
		}
		logger.exit();
	}
	
	private static void setup(){
		boolean runSetup = true;
		while(runSetup){
			new WDBConnect(Config.getStrValue("db"),Config.getStrValue("ip"),Config.getIntValue("port"),Config.getStrValue("user"),Config.getStrValue("password"), true);
			logger.debug("going to run the setup..");
			
			DBEError dbee = new dbTools().runDBSetup();
			if(dbee.getError() == DBError.NOERROR)
				dbee = new DBEError(Database.initDBVars(),"");
			switch(dbee.getError()){
			case NOERROR:
				Config.setValue("firstrun", false);
				saveConfig();
				runSetup = false;
				new WSetupData("User: "+Config.getStrValue("user")+"\nPassword: "+Config.getStrValue("password")+"\nIP: "+Config.getStrValue("ip")+"\nport: "+Config.getIntValue("port")+"\nDB: "+Config.getStrValue("db"));
				break;
			case OPERATION_FAILED:
				GUIManager.showErrorDialog("Einer der Befehle konnte nicht ausgeführt werden!\n"+dbee.getMsg(), "Fehler beim Setup");
				break;
			case NO_DB_OR_NO_PERM:
				GUIManager.showErrorDialog("Der Benutzer besitzt nicht genug Rechte auf die Datenbank!", "Fehler beim Setup");
				break;
			case NO_PERMISSIONS:
				GUIManager.showErrorDialog("Der angegebene Benutzer besitzt nicht genug Rechte!", "Fehler beim Setup");
				break;
			case NO_DB:
				GUIManager.showErrorDialog("Die angegebene Datenbank existiert nicht!", "Fehler beim Setup");
				break;
			default:
				GUIManager.showErrorDialog("Ein unerwarteter Fehler ist aufgetreten: "+dbee.getError(), "Unerwarteter Setup Fehler");
				break;
			}
		}
	}
	
	private static void saveConfig(){
		ConfigLib cfg = new ConfigLib(Config.getStrValue("configFileName"), Config.getStrValue("defaultConfigPath"));
		cfg.loadConfig();
		cfg.writeConfig();
	}
	
	private static void ShutDown(){
		logger.info("Shutting down");
		Database.disconnect();
		
	}
}
