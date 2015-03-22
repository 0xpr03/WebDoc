package webdoc.lib;

import java.sql.Connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import webdoc.webdoc.WebDoc;

/***
 * Backend to DBMS class
 * @author Aron
 *
 */
public class Database extends WebDoc {
	
	private static Logger logger = LogManager.getLogger("database");
	private Connection connection;
	
	public static void connect(){
		String base = "jdbc:mariadb://";
		
		logger.info("Starting database connection");
		
		try{
			Class.forName("");
		}catch(Exception e){
			logger.error("Error initializing the jdbc driver!", e);
		}
	}
	
	
}
