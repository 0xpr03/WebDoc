/*******************************************************************************
 * Copyright (c) 2015 by the WebDoc group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the WebDoc license.
 * Available inside this binary and at http://webdoc.proctet.net/license.txt
 *******************************************************************************/
package webdoc.lib;

import java.nio.file.Path;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLNonTransientConnectionException;
import java.sql.SQLSyntaxErrorException;
import java.sql.SQLTimeoutException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import webdoc.webdoc.Config;

/**
 * Backend to DBMS class
 * @author "Aron Heinecke"
 *
 */
public class Database{
	
	private static Logger logger = LogManager.getLogger("database");
	private static Connection connection;
	/**
	 * Error constant, external is for NON sql errors
	 * @author "Aron Heinecke"
	 *
	 */
	public static enum DBError{
		EXTERNAL_ERROR(-2),UNDEFINED_ERROR(-1),NOERROR(0),NOCONNECTION(1),INVALID_LOGIN(2),NO_DB_OR_NO_PERM(3),NO_DB_SELECTED(4),NO_PERMISSIONS(5),OPERATION_FAILED(6), NO_DB(7), WRONG_SYNTAX(8), SQL_TIMEOUT(9), WRONG_PARAMETER_BINDING(10);
		private DBError(int dberror){
			this.DBError = dberror;
		}
		private int DBError;
		public int getError(){
			return this.DBError;
		}
	}
	
	/**
	 * Connects to the DB server
	 * @param db connect directly to DB
	 * @param useRoot use root privilieges from r_user & r_password
	 * @return DBError DBError.NOERROR on success
	 * @author "Aron Heinecke"
	 */
	public static DBError connect(boolean db,boolean useRoot){
		DBError dberr = DBError.NOERROR;
		String base = "jdbc:mariadb://";
		
		logger.info("Starting database connection");
		
		base = base+Config.getStrValue("ip")+":"+Config.getIntValue("port");
		if(db){
			base += "/"+Config.getStrValue("db");
		}
		base +="?tcpKeepAlive=true";
		logger.debug("DB conn base: {} {} {}",base,Config.getStrValue("user"), Config.getStrValue("password"));
		try{
			//Class.forName("org.mariadb.jdbc.Driver"); not necessary here atm?
			if(useRoot)
				connection = DriverManager.getConnection(base, Config.getStrValue("r_user"), Config.getStrValue("r_password"));
			else
				connection = DriverManager.getConnection(base, Config.getStrValue("user"), Config.getStrValue("password"));
		}catch(SQLNonTransientConnectionException e){ //see https://git.proctet.net/webdoc/webdoc/snippets/11
			dberr = DBError.NOCONNECTION;
		}catch(SQLException e){
			dberr = DBExceptionConverter(e);
		}finally{
			if(dberr != DBError.NOERROR && connection != null){
				try{connection.close();}catch(SQLException e){}
			}
		}
		return dberr;
	}
	
	public static DBError initDBVars(){
		DBError dbe = DBError.NOERROR;
		try {
			Config.setValue("COMM_PHONE_ID", getCommTypeID("phone"));
			Config.setValue("COMM_MOBILE_ID", getCommTypeID("mobile"));
			Config.setValue("COMM_FAX_ID", getCommTypeID("fax"));
			Config.setValue("PARTNER_EMPLOYEE_ID", getPartnerTypeID("employee"));
			Config.setValue("PARTNER_CLIENT_ID", getPartnerTypeID("client"));
		} catch (SQLException e) {
			dbe = DBExceptionConverter(e,true);
		}
		return dbe;
	}
	
	/**
	 * Retrieve the ID of a communication type
	 * @param name communication type name
	 * @return long id
	 * @throws SQLException 
	 */
	public static long getCommTypeID(String name) throws SQLException{
		String sql = "{call getComTypeId(?,?)}";
		CallableStatement stm = connection.prepareCall(sql);
		stm.setString(1, name);
		stm.registerOutParameter(2, java.sql.Types.INTEGER);
		stm.execute();
		long id = stm.getLong(2);
		logger.debug("CommTypeID: {}",id);
		stm.closeOnCompletion();
		return id;
	}
	
	/**
	 * Retrieve the ID of a partner type
	 * @param name
	 * @return
	 * @throws SQLException
	 */
	public static long getPartnerTypeID(String name) throws SQLException{
		String sql = "{call getRoleID(?,?)}";
		CallableStatement stm = connection.prepareCall(sql);
		stm.setString(1, name);
		stm.registerOutParameter(2, java.sql.Types.INTEGER);
		stm.execute();
		long id = stm.getLong(2);
		logger.debug("RoleTypeID: {}",id);
		stm.closeOnCompletion();
		return id;
	}
	
	/**
	 * Closes the SQL connection
	 */
	public static void disconnect(){
		try {
			connection.close();
		} catch (NullPointerException | SQLException e) {
			logger.debug(e);
		}
	}
	
	public static void test(){
		try {
			execUpdateQuery("SELECT @@version");
		}catch(Exception e){
			logger.error(e);
		}
	}
	
	/**
	 * Returns a list of stored procedures
	 * @return list of procedures
	 * @throws SQLException
	 */
	public static List<String> getProcedures() throws SQLException{
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
	
	//------------- USER SPACE
	
	/**
	 * Insert patient, based on the procedure, -> recommended
	 * @param name string(50)
	 * @param callname string(50)
	 * @param identification
	 * @param coatcolor
	 * @param weight
	 * @param birthdate string(8)
	 * @param gender boolean
	 * @param race String(20)
	 * @param comment String(100)
	 * @param picture ID
	 * @throws SQLException
	 * @author "Aron Heinecke"
	 */
	public static long insertPatient(String name, String callname, String identification, String coatcolor, double weight, Date birthdate, boolean gender, String race, String comment, Path picture) throws SQLException{
		String sql = "{call insertPatient(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
		CallableStatement stm = connection.prepareCall(sql);
		stm.setString(1, name);
		stm.setString(2, callname);
		stm.setString(3, identification);
		stm.setString(4, coatcolor);
		stm.setDouble(5, weight);
		stm.setDate(6, birthdate);
		stm.setBoolean(7, gender);
		stm.setString(8, race);
		stm.setString(9, comment);
		stm.setLong(10, 0);
		stm.registerOutParameter(11, java.sql.Types.INTEGER);
		
		if(picture != null)
			logger.error("Picture currently not implemented!");
		
		stm.execute();
		long id = stm.getLong(11);
		logger.debug("ID: {}",id);
		stm.closeOnCompletion();
		return id;
	}
	
	/**
	 * Update a patient
	 * @param name
	 * @param callname
	 * @param identification
	 * @param coatcolor
	 * @param weight
	 * @param birthdate
	 * @param gender
	 * @param race
	 * @param comment
	 * @param picture
	 * @return affected rows
	 * @throws SQLException
	 */
	public static int updatePatient(long id,String name, String callname, String identification, String coatcolor, double weight, Date birthdate, boolean gender, String race, String comment, Path picture) throws SQLException {
		String sql = "{call updatePatient(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
		CallableStatement stm = connection.prepareCall(sql);
		stm.setLong(1, id);
		stm.setString(2, name);
		stm.setString(3, callname);
		stm.setString(4, identification);
		stm.setString(5, coatcolor);
		stm.setDouble(6, weight);
		stm.setDate(7, birthdate);
		stm.setBoolean(8, gender);
		stm.setString(9, race);
		stm.setString(10, comment);
		stm.setLong(11, 0);
		
		if(picture != null)
			logger.error("Picture currently not implemented!");
		
		int affectedlines = stm.executeUpdate();
		stm.close();
		return affectedlines;
	}
	
	/**
	 * Inserts a new Partner into the DB
	 * @param firstname
	 * @param secondname
	 * @param title
	 * @param birthday
	 * @param comment
	 * @param phone
	 * @param mobile
	 * @param fax
	 * @return
	 * @throws SQLException
	 */
	public static long insertPartner(String firstname, String secondname, String title, Date birthday, String comment, String phone, String mobile, String fax, long partnertypeid, String email) throws SQLException{
		long id;
		long partnerroleid;
		{
			String sql = "INSERT INTO partner (`firstname`,`secondname`,`title`,`comment`,`birthday`) "
					+"VALUES (?,?,?,?,?)";
			PreparedStatement stm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stm.setString(1, firstname);
			stm.setString(2, secondname);
			stm.setString(3, title);
			stm.setString(4, comment);
			stm.setDate(5, birthday);
			stm.executeUpdate();
			
			ResultSet rs = stm.getGeneratedKeys();
			rs.next();
			id = rs.getLong(1);
			stm.close();
		}
		{
			String sql = "INSERT INTO `partnerroles` (`PartnerID`,`RoleID`) VALUES (?,?)";
			PreparedStatement stm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stm.setLong(1, id);
			stm.setLong(2, partnertypeid);
			stm.executeUpdate();
			
			ResultSet rs = stm.getGeneratedKeys();
			rs.next();
			partnerroleid = rs.getLong(1);
			stm.close();
		}
		{
			PreparedStatement stm = prepareTelecommInsertStm();
			stm.setString(1, phone);
			stm.setLong(2, Config.getLongValue("COMM_PHONE_ID"));
			stm.setLong(3, partnerroleid);
			stm.executeUpdate();
			stm.clearParameters();
			
			stm.setString(1, mobile);
			stm.setLong(2, Config.getLongValue("COMM_MOBILE_ID"));
			stm.setLong(3, partnerroleid);
			stm.executeUpdate();
			stm.clearParameters();
			
			stm.setString(1, fax);
			stm.setLong(2, Config.getLongValue("COMM_FAX_ID"));
			stm.setLong(3, partnerroleid);
			stm.executeUpdate();
			stm.clearParameters();
			stm.close();
		}
		{
			PreparedStatement stm = prepareEmailInsertStm();
			stm.setString(1, email);
			stm.setLong(2, partnerroleid);
			stm.executeUpdate();
			stm.close();
		}
		
		return id;
	}
	
	/**
	 * Retrives all columns of an animal for WPatient
	 * @param id long id of animal, unique
	 * @return ResultSet, needs to be closed!
	 * Name, Callname, identification, coatcolor, weight, birthdate, gender,comment, PictureID, animal.RaceID, race.race
	 * @throws SQLException
	 * @author "Aron Heinecke"
	 */
	public static ResultSet getAnimal(long id) throws SQLException{
		String sql = "SELECT `Name`, `Callname`, `identification`, `coatcolor`, `weight`, `birthdate`, `gender`, `comment`, `PictureID`, animal.`RaceID`, race.race "
			+"FROM animal "
			+"INNER JOIN race "
			+"ON animal.RaceID = race.RaceID "
			+"WHERE AnimalID = ?";	
		
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setLong(1, id);
		return stmt.executeQuery();
	}
	
	/**
	 * Execute an update query.
	 * Not injection safe!
	 * @param sql single-sql
	 * @return affected lines
	 * @throws SQLException 
	 * @author "Aron Heinecke"
	 */
	public static int execUpdateQuery(String sql) throws SQLException{
		logger.debug("Executing {}",sql);
		int affectedLines = -1;
		Statement stm = null;
		try{
			stm = connection.createStatement();
			stm.executeUpdate(sql);
			affectedLines = stm.getUpdateCount();
			logger.debug("Affected lines {}", affectedLines);
		}finally{
			if(stm != null){
				try{stm.close();}catch(Exception e){}
			}
		}
		
		return affectedLines;
	}
	
	/**
	 * Prepare email table insert
	 * @return
	 * @throws SQLException
	 */
	public static PreparedStatement prepareEmailInsertStm() throws SQLException {
		String sql = "INSERT INTO `email` (`mail`,`PartnerRoleID`) "
				+ "VALUES (?,?);";
		return prepareStm(sql);
	}
	
	/**
	 * Prepare telecommunication table insert
	 * @return
	 * @throws SQLException
	 */
	public static PreparedStatement prepareTelecommInsertStm() throws SQLException {
		String sql = "INSERT INTO `telecommunication` (`number`,`CommunicationID`,`PartnerRoleID`) "
				+ "VALUES (?,?,?);";
		return prepareStm(sql);
	}
	
	/**
	 * Basic statement preparer
	 * @param sql
	 * @return PreparedStatment, unbound
	 * @throws SQLException to be handled with DBExceptionConverter at best
	 * @author "Aron Heinecke"
	 */
	public static PreparedStatement prepareStm(String sql) throws SQLException{
		return connection.prepareStatement(sql);
	}
	
	/**
	 * Prepares an animal & partner search statement
	 * @return 'result' columns: name, optname, id, type, see ACElement, fully compatible
	 * @throws SQLException
	 * @author "Aron Heinecke"
	 */
	public static PreparedStatement prepareMultiSearchStm() throws SQLException{
		String sql =
				getAnimalSearchStm()
				+"UNION ALL "
				+getPartnerSearchStm();
		return prepareStm(sql);
	}
	
	/**
	 * Prepares a race search
	 * @return
	 * @throws SQLException
	 */
	public static PreparedStatement prepareRaceSearchStm() throws SQLException{
		String sql =
				"SELECT `RaceID`,`race` FROM race "
				+"WHERE `race` LIKE ? ";
		return prepareStm(sql);
	}
	
	/**
	 * Returns the animal search sql
	 * @return
	 * @author "Aron Heinecke"
	 */
	public static String getAnimalSearchStm(){
		return "SELECT `Name` as name, `Callname` as optname, `AnimalID` as id, 0 as type FROM animal "
				+"WHERE `Name` LIKE ? OR `Callname` LIKE ? ";
	}
	
	/**
	 * Returns the partner search sql
	 * @return
	 * @author "Aron Heinecke"
	 */
	public static String getPartnerSearchStm(){
		return "SELECT `firstname` as name, `secondname` as optname, `PartnerID` as id, 1 as type FROM partner "
				+"WHERE `firstname` LIKE ? OR `secondname` LIKE ? ";
	}
	
	/**
	 * Execute an unsafe multiline query.
	 * Not injection safe!
	 * @param sql multiline sql
	 * @return affected lines
	 * @throws SQLException
	 * @author "Aron Heinecke"
	 */
	public static int execMulitline(String sql) throws SQLException{
		logger.debug("Executing\n{}",sql);
		int affectedLines = -1;
		Statement stm = null;
		try{
			stm = connection.createStatement();
			stm.execute(sql);
			affectedLines = stm.getUpdateCount();
			logger.debug("Affected lines {}", affectedLines);
		}finally{
			if(stm != null){
				try{stm.close();}catch(Exception e){}
			}
		}
		
		return affectedLines;
	}
	
	/**
	 * Prints a complete ResultSet to the debug log
	 * @param rs
	 * @author "Aron Heinecke"
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
	
	/**
	 * Converts the (mostly) string based SQLExceptions to DBErrors
	 * This function already logs errors on debug level!
	 * Extended usage for functions where the MSG return matters
	 * @param e
	 * @return DBError enum
	 * @author "Aron Heinecke"
	 */
	public static DBError DBExceptionConverter(SQLException e, boolean printAsError){
		if(printAsError)
			logger.error("DBError: \n{}", e);
		else
			logger.debug("Converter catched:\n{}",e);
		String message = e.getMessage();
		if(message.contains("Access denied for user")){
			if(message.contains("to database")){
				return DBError.NO_DB_OR_NO_PERM;
			}else{
				return DBError.INVALID_LOGIN;
			}
		}else if(message.contains("No database selected")){
			return DBError.NO_DB_SELECTED;
		}else if(message.contains("Access denied; you need")){
			return DBError.NO_PERMISSIONS;
		}else if(message.contains("Unknown database")){
			return DBError.NO_DB;
		}else if(message.contains("Operation")){
			if(message.contains("failed")){
				return DBError.OPERATION_FAILED;
			}else{
				return DBError.UNDEFINED_ERROR;
			}
		}else if(message.contains("Could not set parameter")){
			return DBError.WRONG_PARAMETER_BINDING;
		}else if(e instanceof SQLSyntaxErrorException ){
			return DBError.WRONG_SYNTAX;
		}else if(e instanceof SQLTimeoutException){
			return DBError.SQL_TIMEOUT;
		}else{
			return DBError.UNDEFINED_ERROR;
		}
	}
	
	/**
	 * Overloaded version, see the main declaration
	 * @param e
	 * @return
	 */
	public static DBError DBExceptionConverter(SQLException e){
		return DBExceptionConverter(e, false);
	}
	
	/**
	 * Converts the (mostly) string based SQLExceptions to DBEErrors,
	 * a extended error with the error msg itself.
	 * @param e
	 * @return DBEError containing the DBError enum and the ErrorMSG
	 */
	public static DBEError DBEExceptionConverter(SQLException e){
		return new DBEError(DBExceptionConverter(e,true), e.getMessage());
	}
}
