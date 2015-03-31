package webdoc.lib;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.sql.SQLException;
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
	
	/**
	 * 
	 * @param overwrite
	 * @param file
	 * @param window
	 * @return
	 */
	public DBError sqlTblCreator(boolean overwrite,String file, WProgress window){
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
				matcher.reset(line);
				sb.append(line);
				if(matcher.find()){
					//TODO: do sql command
					logger.debug("Found ; {}",line);
					Database.execUpdateQuery(sb.toString());
					sb.setLength(0);
				}
				window.addSubProgress(1);
			}
			window.addProgress(1);
			br.close();
			isr.close();
			is.close();
			return DBError.NOERROR;
		}catch(SQLException e){
			return Database.DBExceptionConverter(e);
		}catch(IOException | NullPointerException e){
			logger.fatal("Unable to create tables from file!", e);
			return DBError.EXTERNAL_ERROR;
		}
	}
	
	/**
	 * runns the DBSetup
	 * @return
	 */
	public DBError runDBSetup(){
		DBError dberr = DBError.NOERROR;
		WProgress wpg = new WProgress();
		try{
		int max = 1;
			max += Config.getBoolValue("createDB") ? 1 : 0;
			max += Config.getBoolValue("createUser") ? 1 : 0;
			max += Config.getBoolValue("overwriteDB") ? 1 : 0;
			// max += Config.getBoolValue("safeConfig") ? 1 : 0;
			wpg.setMax(max);
			wpg.setVisible(true);

			if (Config.getBoolValue("createUser")) {
				wpg.setText("Creating User..");
				wpg.setSubMax(2);
				wpg.setSubText("Creating user webdoc");
				// TODO: create user webdoc
				PasswordGenerator pwdg = new PasswordGenerator();
				Config.setValue("password", pwdg.generateGenericPassword(20));
				Database.execUpdateQuery("CREATE USER 'test1'@'%' IDENTIFIED BY '" + Config.getStrValue("password")
						+ "';");
				// CREATE USER 'test1'@'%' IDENTIFIED BY '***';GRANT USAGE ON
				// *.* TO 'test1'@'%' IDENTIFIED BY '***' WITH
				// MAX_QUERIES_PER_HOUR 0 MAX_CONNECTIONS_PER_HOUR 0
				// MAX_UPDATES_PER_HOUR 0 MAX_USER_CONNECTIONS 0;GRANT ALL
				// PRIVILEGES ON `webdoc`.* TO 'test1'@'%';
				// REVOKE ALL PRIVILEGES ON `webdoc`.* FROM 'test1'@'%'; GRANT
				// SELECT, INSERT, UPDATE, DELETE, CREATE, REFERENCES, INDEX,
				// CREATE TEMPORARY TABLES, LOCK TABLES, CREATE VIEW, EVENT,
				// TRIGGER, SHOW VIEW, CREATE ROUTINE, ALTER ROUTINE, EXECUTE ON
				// `webdoc`.* TO 'test1'@'%';
				wpg.addSubProgress(1);
				wpg.setSubText("Creating user webdocroot");
				// TODO: create user webdocroot
				wpg.addSubProgress(1);
				wpg.addProgress(1);
			}
			if (Config.getBoolValue("overwriteDB")) {
				wpg.setText("Deleting tables..");
				// TODO: get tables & get max
				wpg.setSubMax(2);
				// TODO: iterate through listfor() & delete DROP TABLE IF EXISTS
			}

			sqlTblCreator(false, "/webdoc/files/tables.sql", wpg);
		}catch(SQLException e){
			dberr = Database.DBExceptionConverter(e);
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
}
