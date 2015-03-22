package webdoc.lib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
	private static Connection connection;
	
	public static void connect(){
		String base = "jdbc:mariadb://";
		
		logger.info("Starting database connection");
		
		base = base+SETTINGS.get("ip")+":"+SETTINGS.get("port")+"/"+SETTINGS.get("db")
				+"?tcpKeepAlive=true";
		logger.debug("DB conn base: {}",base);
		try{
			//Class.forName("org.mariadb.jdbc.Driver");
			connection = DriverManager.getConnection(base, SETTINGS.get("user"), SETTINGS.get("password"));
		}catch(Exception e){
			logger.error("Error initializing the jdbc driver!", e);
		}
	}
	
	public static void disconnect(){
		try {
			connection.close();
		} catch (NullPointerException | SQLException e) {
			logger.debug(e);
		}
	}
	
	
}
