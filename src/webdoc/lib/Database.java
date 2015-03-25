package webdoc.lib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import webdoc.webdoc.WebDoc;

/**
 * Backend to DBMS class
 * @author "Aron Heinecke"
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
	
	public static void test(){
		try {
			execUpdateQuery("");
		}catch(Exception e){
			logger.error(e);
		}
	}
	
	/**
	 * Returns a list of stored procedures
	 * @return list of procedures
	 * @throws SQLException
	 */
	private static List<String> getProcedures() throws SQLException{
		Statement stm = connection.createStatement();
		stm.execute("SHOW PROCEDURE STATUS;");
		ResultSet rs = stm.getResultSet();
		List<String> procedures = new ArrayList<String>();
		while(rs.next()){
			procedures.add(rs.getString(2)); // ROUTINE_NAME
		}
		
		logger.debug("Found procedures: {}",procedures);
		return procedures;
	}
	
	/**
	 * Runns an SQL command, NOT injection safe!
	 * @param sql
	 * @return dbResult dbResult which's ResultSet WASN'T closed till now!
	 */
	public static int execUpdateQuery(String sql){
		int affectedLines = -1;
		try {
			Statement stm = null;
			dbResult dbResult = null;
			stm = connection.createStatement();
			stm.executeUpdate(sql);
			
			printResultSet(stm.getResultSet());
			logger.debug("Affected lines {}", stm.getUpdateCount());
			
			stm.close();
		} catch (SQLException e) {
			logger.error("Unable to query \n{}\n{}", sql,e);
		}
		return affectedLines;
		
		
	}
	
	/**
	 * Prints a complete ResultSet to the debug log
	 * @param rs
	 */
	@SuppressWarnings("unused")
	private static void printResultSet(ResultSet rs){
		try {
			StringBuilder sb = new StringBuilder();

			ResultSetMetaData rsmd = rs.getMetaData();
			int columns = rsmd.getColumnCount();
			for (int i = 1; i <= columns; i++) {
				int jdbcType = rsmd.getColumnType(i);
				String name = rsmd.getColumnTypeName(i);
				sb.append("Column " + i + " JDBC type " + jdbcType + ", Typename " + name + "\n");
			}

			int numberOfColumns = rsmd.getColumnCount();

			for (int i = 1; i <= numberOfColumns; i++) {
				if (i > 1)
					sb.append(",\t");
				String columnName = rsmd.getColumnName(i);
				sb.append(columnName);
			}
			sb.append("\n");

			while (rs.next()) {
				for (int i = 1; i <= numberOfColumns; i++) {
					if (i > 1)
						sb.append(",\t");
					String columnValue = rs.getString(i);
					sb.append(columnValue);
				}
				sb.append("\n");
			}

			logger.debug("ResultSet: \n{}", sb);
		}catch( SQLException e){
			logger.debug(e);
		}
	}
	
	
}
