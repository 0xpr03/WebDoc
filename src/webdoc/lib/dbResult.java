/*******************************************************************************
 * Copyright (c) 2015 by Aron Heinecke & Jonathan Peper
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the WebDoc license.
 * Available inside this binary and at http://webdoc.proctet.net/license.txt
 *******************************************************************************/
package webdoc.lib;

import java.sql.ResultSet;

/**
 * Custom result provider for db querys
 * @author Aron
 *
 */
public class dbResult {
	
	private int affectedLines;
	private ResultSet ResultSet;
	
	public dbResult(int affectedLines, java.sql.ResultSet resultSet) {
		this.affectedLines = affectedLines;
		ResultSet = resultSet;
	}
	public dbResult(int affectedLines) {
		this.affectedLines = affectedLines;
	}
	public dbResult() {
	}
	public void setAffectedLines(int affectedLines) {
		this.affectedLines = affectedLines;
	}
	public void setResultSet(ResultSet resultSet) {
		ResultSet = resultSet;
	}
	public int getAffectedLines() {
		return affectedLines;
	}
	public ResultSet getResultSet() {
		return ResultSet;
	}
	
}
