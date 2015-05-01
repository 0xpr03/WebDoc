/*******************************************************************************
 * Copyright (c) 2015 by the WebDoc group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the WebDoc license.
 * Available inside this binary and at http://webdoc.proctet.net/license.txt
 *******************************************************************************/
package webdoc.lib;

import webdoc.lib.Database.DBError;

/**
 * Extended DBError with MSG inside
 * @author "Aron Heinecke"
 *
 */
public class DBEError{
	private DBError dberror;
	private String msg;
	/**
	 * DBError constructor
	 * @param dberror DBError enum
	 * @param msg String ErrorMSG
	 */
	public DBEError(DBError dberror, String msg){
		this.dberror = dberror;
		this.msg = msg;
	}
	
	/**
	 * Constructor for NOERROR, containing no MSG
	 */
	public DBEError(){
		this.dberror = DBError.NOERROR;
		this.msg = "";
	}
	
	/**
	 * Get ErrorMSG
	 * @return
	 */
	public String getMsg(){
		return msg;
	}
	/**
	 * Get DBError enum
	 * @return
	 */
	public DBError getError(){
		return dberror;
	}
}
