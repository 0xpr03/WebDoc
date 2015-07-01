/*******************************************************************************
 * Copyright (c) 2015 by the WebDoc group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the WebDoc license.
 * Available inside this binary and at http://webdoc.proctet.net/license.txt
 *******************************************************************************/
package webdoc.gui.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import webdoc.gui.utils.EnumObject.EnumType;

/**
 * Custom, hightly dynamic, table model for the WVerwaltung table
 * @author Aron Heinecke
 */
@SuppressWarnings("serial")
public class AdminTableModel extends AbstractTableModel {
	
    protected String[] COLUMN_NAMES;
    
    private List<TDListElement> rowData;
    
    /**
     * Creates the table model, setting the start settings according to the type specified
     * @param type A(treatment),B(partner),C(animal),D(null)
     */
    public AdminTableModel(EnumType type) {
    	rowData = new ArrayList<>(25);
    	setTableType(type);
    }
    
    public void add(TDListElement tdl) {
    	add(Arrays.asList(tdl));
    }
    
    public void add(List<TDListElement> tdl){
    	rowData.addAll(tdl);
    	fireTableDataChanged();
    }
    
    /**
     * Delete all rows
     * @author "Aron Heinecke"
     */
    public void clearElements(){
    	if(rowData.size() != 0){
    		int i = rowData.size()-1;
        	rowData.clear();
        	super.fireTableRowsDeleted(0, i-1);
    	}
    }
    
    /**
     * Set talbe type to specified enumtype
     * @param type A(treatment),B(partner),C(animal),D(null)
     */
    public void setTableType(EnumType type){
    	switch(type){
		case A:
			COLUMN_NAMES = new String[]{"Name","Price"};
			break;
		case B:
			COLUMN_NAMES = new String[]{"Name","Firstname","Birthday"};
			break;
		case C:
			COLUMN_NAMES = new String[]{"Name","Callname","Birthday"};
			break;
		default:
			COLUMN_NAMES = new String[]{"unknown"};
			break;
    	}
    	fireTableStructureChanged();
    }
    
    @Override
    public int getRowCount() {
    	return rowData.size();
    }
    
    @Override
    public int getColumnCount() {
    	return COLUMN_NAMES.length;
    }
    
    @Override
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }
    
    public TDListElement getTDLEAt(int row){
    	return rowData.get(row);
    }
    
    /**
     * Return the values according to the headers, from the TDListElement
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex){
    	TDListElement tdl = getTDLEAt(rowIndex);
    	Object value = null;
    	switch(columnIndex) {
    	case 0:
    		value = tdl.getName();
    		break;
    	case 1:
    		value = tdl.getDate();
    		break;
    	}
    	return value;
    }
    
}
