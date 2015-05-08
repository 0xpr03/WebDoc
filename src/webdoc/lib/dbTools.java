/*******************************************************************************
 * Copyright (c) 2015 by the WebDoc group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the WebDoc license.
 * Available inside this binary and at http://webdoc.proctet.net/license.txt
 *******************************************************************************/
package webdoc.lib;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import webdoc.gui.WProgress;
import webdoc.lib.Database.DBError;
import webdoc.webdoc.Config;

/**
 * DB related functions without the direct connection
 * @author "Aron Heinecke"
 *
 */
public class dbTools {
	
	private static Logger logger = LogManager.getLogger();
	
	public DBEError sqlTblCreator(String file, WProgress window){
		try{
			InputStream is = getClass().getResourceAsStream(file);
		    InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			StringBuffer sb = new StringBuffer();
			
			Pattern pattern = Pattern.compile(".*;.*");
			Matcher matcher = pattern.matcher("");
			window.setSubMax(getMaxLines(file));
			window.setSubText("Creating missing tables..");
			
			for(String line = br.readLine(); line != null;line=br.readLine()){
				if(line.startsWith("/*")){
					//do nothing, ignore comment lines
				}else{
					matcher.reset(line);
					sb.append(line);
					if(matcher.find()){
						Database.execUpdateQuery(sb.toString());
						sb.setLength(0);
					}
				}
				window.addSubProgress();
			}
			br.close();
			isr.close();
			is.close();
			return new DBEError(DBError.NOERROR,"");
		}catch(SQLException e){
			return Database.DBEExceptionConverter(e);
		}catch(IOException | NullPointerException e){
			logger.fatal("Unable to create tables from file!", e);
			return new DBEError(DBError.EXTERNAL_ERROR,"");
		}
	}
	
	public DBEError sqlProcCreator(String file, WProgress window){
		try{
			InputStream is = getClass().getResourceAsStream(file);
		    InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			StringBuffer sb = new StringBuffer();
			
			Pattern pattern = Pattern.compile(".*\\$\\$.*");
			Matcher matcher = pattern.matcher("");
			window.setSubMax(getMaxLines(file));
			window.setSubText("Dropping & ReCreating Procedures");
			
			for(String line = br.readLine(); line != null;line=br.readLine()){
				if(line.startsWith("/*")){
					//do nothing, ignore comment lines
				}else{
					matcher.reset(line);
					sb.append(line+"\n");
					if(matcher.find()){
						Database.execMulitline(sb.toString().replace("$$", ""));
						sb.setLength(0);
					}
				}
				window.addSubProgress();
			}
			br.close();
			isr.close();
			is.close();
			return new DBEError(DBError.NOERROR,"");
		}catch(SQLException e){
			return Database.DBEExceptionConverter(e);
		}catch(IOException | NullPointerException e){
			logger.fatal("Unable to create tables from file!", e);
			return new DBEError(DBError.EXTERNAL_ERROR,"");
		}
	}
	
	/**
	 * runns the DBSetup
	 * @return
	 */
	public DBEError runDBSetup(){
		DBEError dberr = new DBEError(DBError.NOERROR,null);
		WProgress wpg = new WProgress();
		try{
		int max = 1;
		max += Config.getBoolValue("createDB") ? 1 : 0;
		max += Config.getBoolValue("createUser") ? 2 : 0;
		max += Config.getBoolValue("overwriteDB") ? 2 : 0;
		max += Config.getBoolValue("overwriteDB") || Config.getBoolValue("createDB") ? 1 : 0;
		wpg.setMax(max);
		wpg.setVisible(true);
		
		String tablefile = Config.getStrValue("tablesql");
		String procfile = Config.getStrValue("proceduresql");
		
		if(Config.getBoolValue("createDB")){
			wpg.setText("Creating DB");
			wpg.setSubMax(Config.getBoolValue("overwriteDB") ? 2 : 1);
			
			if(Config.getBoolValue("overwriteDB")){
				wpg.setSubText("Dropping DB");
				Database.execUpdateQuery(String.format("DROP DATABASE IF EXISTS `%s`", Config.getStrValue("db")));
				wpg.addSubProgress();
			}
			
			wpg.setSubText("Creating DB");
			Database.execUpdateQuery(String.format("CREATE DATABASE IF NOT EXISTS `%s` ;",Config.getStrValue("db")));
			wpg.addSubProgress();
			wpg.addProgress();
		}
		
		wpg.setText("Using DB");
		Database.execUpdateQuery(String.format("use `%s`", Config.getStrValue("db")));
		wpg.addProgress();
		
		if (Config.getBoolValue("overwriteDB")) {
			wpg.setText("Overwriting DBs..");
			wpg.setSubText("Getting DBs");
			
			List<String> dbs = getTableNames(tablefile, wpg);
			wpg.addProgress();
			wpg.setSubText("Deleting DBs..");
			wpg.setSubMax(dbs.size());
			for(String table : dbs){
				logger.debug("Dropping Tbl {}",table);
				Database.execUpdateQuery(String.format("DROP TABLE IF EXISTS `%s`;", table));
				wpg.addSubProgress();
			}
			wpg.addProgress();
		}
		
		if(Config.getBoolValue("overwriteDB") || Config.getBoolValue("createDB")){
			wpg.setText("Creating DBs");
			{
				DBEError dberror = sqlTblCreator(tablefile, wpg);
				if(dberror.getError() != DBError.NOERROR){
					return dberror;
				}
			}
			wpg.addProgress();
			wpg.setText("Creating Procedures");
			{
				DBEError dberror = sqlProcCreator(procfile, wpg);
				if(dberror.getError() != DBError.NOERROR){
					return dberror;
				}
			}
			wpg.addProgress();
		}
		
		if (Config.getBoolValue("createUser")) {
			wpg.setText("Creating User..");
			wpg.setSubMax(2);
			wpg.setSubText("Creating user webdoc");
			PasswordGenerator pwdg = new PasswordGenerator();
			Config.setValue("password", pwdg.generateGenericPassword(20));
			Config.setValue("user", "webdoc");
			try{
				String query = String.format("CREATE USER '%s'@'%s' IDENTIFIED BY '%s' ;", Config.getStrValue("user"),Config.getStrValue("userIPs"), Config.getStrValue("password"));
				Database.execUpdateQuery(query);
			}catch(SQLException e){
				// catch only the "user already exists" error, throw all other
				if(e.getMessage().contains("Operation CREATE USER failed")){
					GUIManager.showErrorDialog("Nutzer webdoc ist schon vorhanden!\nÜberschreibe das Passwort..", "Setup Fehler");
					String query = String.format("UPDATE mysql.user SET Password=PASSWORD('%s') WHERE User='%s';", Config.getStrValue("password"), Config.getStrValue("user"));
					Database.execUpdateQuery(query);
					Database.execUpdateQuery("FLUSH PRIVILEGES;");
				}else{
					throw e;
				}
			}
			wpg.addSubProgress();
			wpg.setSubText("Granting permissions on DB");
			wpg.setSubMax(1);
			String sql = "GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, REFERENCES, INDEX,"
					+"CREATE TEMPORARY TABLES, LOCK TABLES, CREATE VIEW, EVENT,"
					+"TRIGGER, SHOW VIEW, CREATE ROUTINE, ALTER ROUTINE, EXECUTE ON "
					+"`%s`.* TO '%s'@'%s';";
			sql = String.format(sql, Config.getStrValue("db"), Config.getStrValue("db"), Config.getStrValue("userIPs"));
			Database.execUpdateQuery(sql);
			wpg.addSubProgress();
			wpg.addProgress();
			
			wpg.setText("Reconnecting..");
			Database.disconnect();
			DBError error = Database.connect(true, false);
			if(error != DBError.NOERROR){
				//TODO: add better erorr handler
				GUIManager.showErrorDialog(wpg, "Failed during the setup\nreconnct: "+error+"\n\nSee the log for more infos.", "Setup error");
			}
			wpg.addProgress();
		}
			
		}catch(SQLException e){
			dberr = Database.DBEExceptionConverter(e);
		}
		wpg.dispose();
		return dberr;
	}
	
	/**
	 * Returns the max lines of a file
	 * @param f file to read
	 * @return amount of numbers or -1 on error
	 */
	private int getMaxLines(String file){
		try {
			InputStream is = getClass().getResourceAsStream(file);
		    InputStreamReader isr = new InputStreamReader(is);
			LineNumberReader lnr = new LineNumberReader(isr);
			lnr.skip(Long.MAX_VALUE);
			int i = lnr.getLineNumber();
			lnr.close();
			isr.close();
			is.close();
			return i;
		}catch(IOException e){
			logger.error("Unable to open file!",e);
			return -1;
		}
	}
	
	/**
	 * Extracts the table names out of the table.sql
	 * @param file ressource file
	 * @return List of table names, null on error
	 */
	private List<String> getTableNames(String file, WProgress window){
		try{
			InputStream is = getClass().getResourceAsStream(file);
		    InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			
			Pattern pattern = Pattern.compile("^CREATE TABLE IF NOT EXISTS \\`[a-zA-Z]+\\` \\(");
			Matcher matcher = pattern.matcher("");
			List<String> tables = new ArrayList<String>();
			
			window.setSubMax(getMaxLines(file));
			window.setSubText("Getting table names..");
			
			for(String line = br.readLine(); line != null;line=br.readLine()){
				matcher.reset(line);
				window.addSubProgress();
				if(matcher.find()){
					logger.debug("Found {}",line);
					tables.add(line.substring(line.indexOf("`")+1, line.lastIndexOf("`")));
				}
			}
			br.close();
			isr.close();
			is.close();
			return tables;
		}catch(IOException e){
			logger.fatal("Unable to open file!",e);
			return null;
		}
	}
}
